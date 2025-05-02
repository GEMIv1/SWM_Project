### Learning manegment system

## Functional Requirements
- The system conatins three types of users (admin, students, instructors) each can do different functionality also it conains courses each course has students enrolled in it and has quzies, lessons
- The system allow the users to login, register 

## 1. User Management

### 1.1 Use Cases
- Register new users (students, instructors, admins)
- Log in / authenticate
- Manage and view profiles & roles

### 1.2 Components
- **Controllers**: `UserController`, `AdminUserController`
- **Services**: `UserService`, `AdminUserService`
- **DTOs**: `UserRegistrationDTO`, `LoginReqDTO`, `AuthTokenDTO`
- **Entities**: `User`, `Role`

---

## 2. Course Management

### 2.1 Use Cases
- Admin: full CRUD on all courses
- Instructor: CRUD on courses they teach; manage enrollments
- Student: browse, enroll in, and view courses

### 2.2 Components
- **Controllers**: `AdminCourseController`, `InstructorCourseController`, `StudentCourseController`, `CourseController`
- **Services**: `AdminCourseService`, `InstructorCourseService`, `StudentCourseService`, `CourseService`
- **DTOs**: `CreateCourseRequestDTO`, `UpdateCourseRequestDTO`, `DeleteCourseRequestDTO`, `CourseDTO`
- **Entities**: `Course`, `Attendance`

---

## 3. Assignment Workflow

### 3.1 Use Cases
- Instructors: create assignments & tasks
- Students: submit assignments
- Instructors: grade submissions

### 3.2 Components
- **Controllers**: `AssignmentGradesController`, `StudentQuizAssignmentController`
- **Services**: `AssignmentService`, `AssignmentTaskService`, `AssignmentGradesService`, `StudentQuizAssignmentService`
- **DTOs**: `AssignmentDetailsDTO`, `AssignmentSubmissionDTO`, `AssignmentGradesDTO`
- **Entities**: `Assignment`, `AssignmentTask`, `AssignmentGrades`

---

## 4. Quiz Workflow

### 4.1 Use Cases
- Instructors: build quizzes & add questions
- Students: take quizzes
- Instructors: grade quizzes (auto/manual)

### 4.2 Components
- **Controllers**: `QuizController`, `QuestionController`, `QuizGradesController`
- **Services**: `QuizService`, `QuestionService`, `QuizGradesService`
- **DTOs**: `QuizDetailsDTO`, `QuizGradesDTO`
- **Entities**: `Quiz`, `Question`, `QuizGrades`

---

## 5. Notifications & Messaging

### 5.1 Use Cases
- System announcements (new courses, deadlines)
- Email alerts (assignment due, grades posted)

### 5.2 Components
- **Controller**: `NotificationController`
- **Services**: `NotificationService`, `EmailNotificationService`
- **DTO**: `NotificationDTO`
- **Entity**: `Notification`

---

## 6. Tracking & Reporting

### 6.1 Use Cases
- Monitor student progress (grades, quiz performance, attendance)
- Generate performance reports / dashboards

### 6.2 Components
- **Service**: `TrackingPerformanceService`
- **DTO**: `PerformanceReportDTO`
- **Entities**: `AssignmentGrades`, `QuizGrades`, `Attendance`
