package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.*;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import com.LMS.LMS.RepositoryLayer.AttendanceRepo;
import com.LMS.LMS.RepositoryLayer.QuizGradesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrackingPerformanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private QuizGradesRepo quizGradesRepo;

    @Autowired
    private AssignmentGradesRepo assignmentGradesRepo;


    public Map<String, Object> getSimplifiedPerformance(User student, Course course) {
        Map<String, Object> performanceData = new HashMap<>();

        // Attendance summary
        List<Attendance> attendanceRecords = attendanceRepo.findAll().stream()
                .filter(a -> a.getStudent().equals(student) && a.getLesson().getCourse().equals(course))
                .toList();
        long attendedSessions = attendanceRecords.stream().filter(Attendance::isAttend).count();
        long totalSessions = attendanceRecords.size();
        performanceData.put("attendance", String.format("%d/%d sessions", attendedSessions, totalSessions));

        // Quiz scores summary
        List<Map<String, String>> quizScores = quizGradesRepo.findAll().stream()
                .filter(qs -> qs.getStudent().equals(student) && qs.getQuiz().getCourse().equals(course))
                .map(qs -> Map.of ("quizTitle", qs.getQuiz().getTitle(), "Grades", qs.getGrades()))
                .collect(Collectors.toList());
        performanceData.put("quizScores", quizScores);

        // Assignments summary
        List<Map<String, String>> assignments = assignmentGradesRepo.findAll().stream()
                .filter(a -> a.getStudent().equals(student) && a.getAssignment().getCourse().equals(course))
                .map(a -> Map.of(
                        "assignmentTitle", a.getAssignment().getTitle(),
                        "grade", a.getGrade(),
                        "feedback", a.getFeedback()))
                .collect(Collectors.toList());
        performanceData.put("assignments", assignments);

        return performanceData;
    }

    public void generateExcelReport(User student, Course course) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Performance Report");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Metric");
        headerRow.createCell(1).setCellValue("Value");

        // Sample data for attendance and grades
        Map<String, Object> performanceData = getSimplifiedPerformance(student, course);

        // Fill attendance
        Row attendanceRow = sheet.createRow(1);
        attendanceRow.createCell(0).setCellValue("Attendance");
        attendanceRow.createCell(1).setCellValue(performanceData.get("attendance").toString());

        // Fill quiz scores
        List<Map<String, String>> quizScores = (List<Map<String, String>>) performanceData.get("quizScores");
        int quizRowIndex = 2;
        for (Map<String, String> quiz : quizScores) {
            Row quizRow = sheet.createRow(quizRowIndex++);
            quizRow.createCell(0).setCellValue(quiz.get("quizTitle"));
            quizRow.createCell(1).setCellValue(quiz.get("Grades"));
        }

        // Fill assignments
        List<Map<String, String>> assignments = (List<Map<String, String>>) performanceData.get("assignments");
        int assignmentRowIndex = quizRowIndex;
        for (Map<String, String> assignment : assignments) {
            Row assignmentRow = sheet.createRow(assignmentRowIndex++);
            assignmentRow.createCell(0).setCellValue(assignment.get("assignmentTitle"));
            assignmentRow.createCell(1).setCellValue(assignment.get("grade"));
        }

        // Write to output stream
        try (FileOutputStream fileOut = new FileOutputStream( "Student " + student.getID() +" performance_report.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

