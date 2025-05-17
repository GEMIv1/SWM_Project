package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.RepositoryLayer.AssignmentRepo;
import com.LMS.LMS.RepositoryLayer.AssignmentGradesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;

    @Autowired
    private AssignmentGradesRepo gradesRepo;

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }

    public Optional<Assignment> getAssignment(Long id) {
        return assignmentRepo.findById(id);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    public AssignmentGrades submitAssignment(AssignmentGrades submission) {
        return gradesRepo.save(submission);
    }

    public AssignmentGrades gradeAssignment(Long submissionId, String grade, String feedback) {
        Optional<AssignmentGrades> submission = gradesRepo.findById(submissionId);
        if (submission.isPresent()) {
            AssignmentGrades graded = submission.get();
            graded.setGrade(grade);
            graded.setFeedback(feedback);
            return gradesRepo.save(graded);
        }
        return null;
    }
   @Transactional
    public void DeleteAssignment(long id){
        Assignment assignment = assignmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));assignmentRepo.delete(assignment);
    }
}
