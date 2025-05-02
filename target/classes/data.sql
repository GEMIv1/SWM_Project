-- -- Insert statements for User
INSERT INTO Users (User_Name, Password, role, Email)
VALUES ( 'admin', 'admin123', 'ADMIN', 'admin@example.com'),
       ( 'instructor1', 'instr123', 'INSTRUCTOR', 'instr1@example.com'),
       ( 'student1', 'stud123', 'STUDENT', 'student1@example.com'),
       ( 'student2', 'stud2123', 'STUDENT', 'wdm46857@gmail.com'),
       ( 'student3', 'stud3123', 'STUDENT', 'ziadtawfik2003@gmail.com');
--
-- Insert statements for Course
INSERT INTO Course (title, description, duration, instructor_id)
VALUES ( 'Java Basics', 'Introduction to Java programming', 30, 2),
       ( 'Spring Boot Advanced', 'Deep dive into Spring Boot framework', 45, 2),
       ( 'Software Engineering', 'Architectural styles', 45, 2);
--
-- Insert statements for Lesson
INSERT INTO lesson ( topic, course_id)
VALUES ( 'Introduction to Java', 1),
       ( 'OOP Basics', 1),
       ( 'Spring Boot Setup', 2),
       ( 'Spring Boot Setup', 2),
       ( 'Design patterns', 3),
       ( 'Design patterns2', 3);
--
-- Insert statements for Quiz
INSERT INTO QUIZ (
    COURSE_ID,
    END_TIME,
    ID,
    MAX_ATTEMPTS,
    MAX_SCORE,
    START_TIME,
    TIME_LIMIT,
    DESCRIPTION,
    TITLE
) VALUES
(1, '2023-09-30 23:59:59', 1, 3, 50, '2023-09-30 20:00:00', 60, 'Java Basics quiz', 'Java Quiz'),
(2, '2023-12-01 23:59:59', 2, 3, 50, '2023-12-01 20:00:00', 60, 'Spring Boot quiz', 'Spring Boot Quiz');
--
--
-- Insert statements for Assignment
INSERT INTO assignment ( title, course_id,instructor_id)
VALUES ('Java Project 1', 1,2),
       ('design patterns', 3,2),
       ( 'Spring Boot Project', 2,2);

INSERT INTO ASSIGNMENT (
    COURSE_ID, DUE_DATE, INSTRUCTOR_ID, MAX_SCORE, DESCRIPTION, TITLE
) VALUES
      (1, '2023-12-15 23:59:59', 2, 100, 'Java Basics final project', 'Java Project'),
      (2, '2023-11-20 23:59:59', 2, 100, 'Spring Boot application', 'Spring Boot Project'),
      (1, '2023-10-10 23:59:59', 2, 100, 'Software architecture analysis', 'Architecture Analysis'),
      (1, '2023-09-30 23:59:59', 2, 50, 'Java Basics quiz', 'Java Quiz'),
      (2, '2023-12-01 23:59:59', 2, 50, 'Spring Boot quiz', 'Spring Boot Quiz'),
      (3, '2023-12-04 23:59:59', 2, 50, 'design patterns', 'design patterns');


-- Insert statements for Assignment
INSERT INTO ASSIGNMENT_GRADES (
    IS_LATE,
    ASSIGNMENT_ID,
    ID,
    STUDENT_ID,
    SUBMISSION_DATE,
    FEEDBACK,
    FILE_NAME,
    FILE_TYPE,
    GRADE,
    SUBMISSION_CONTENT,
    FILE_DATA
) VALUES
(FALSE, 1, 1, 3, '2023-12-14 20:00:00', 'Great job!', 'java_project.zip', 'application/zip', 95, 'Java project content', NULL),
(TRUE, 2, 2, 3, '2023-11-21 10:00:00', 'Submitted late', 'spring_boot_project.zip', 'application/zip', 85, 'Spring Boot project content', NULL),
(FALSE, 3, 3, 3, '2023-10-09 15:00:00', 'Excellent analysis', 'architecture_analysis.pdf', 'application/pdf', 90, 'Architecture analysis content', NULL),
(FALSE, 4, 4, 4, '2023-09-29 18:00:00', 'Good effort', 'java_quiz.docx', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 45, 'Java quiz answers', NULL),
(TRUE, 5, 5, 5, '2023-12-02 12:00:00', 'Late submission', 'spring_boot_quiz.docx', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 40, 'Spring Boot quiz answers', NULL);
--



-- -- Insert statements for Attendance
INSERT INTO attendance ( student_id, lesson_id, attend)
VALUES ( 3, 1, true),
       ( 3, 2, false),
       ( 3, 5, false),
       ( 3, 6, false),
       ( 3, 3, true),
       ( 3, 4, true),
       ( 4, 1, true),
       ( 4, 2, true),
       ( 4, 3, false),
       ( 4, 4, true),
       ( 4, 5, true),
       ( 4, 6, true),
       ( 5, 1, true),
       ( 5, 2, true),
       ( 5, 3, false),
       ( 5, 4, true),
       ( 5, 5, true),
       ( 5, 6, false);


-- Insert statements for QuizScore
INSERT INTO QUIZ_GRADES (
    ATTEMPT_NUMBER,
    END_TIME,
    ID,
    QUIZ_ID,
    START_TIME,
    STUDENT_ID,
    ANSWERS,
    FEEDBACK,
    GRADE
) VALUES
(1, '2023-09-30 21:00:00', 1, 1, '2023-09-30 20:00:00', 3, 'A,B,C,D', 'Good understanding of Java basics', '45'),
(1, '2023-12-01 21:00:00', 2, 2, '2023-12-01 20:00:00', 3, 'C,D,A,B', 'Excellent work on Spring Boot', '50'),
(1, '2023-09-30 21:00:00', 3, 1, '2023-09-30 20:00:00', 4, 'A,C,B,D', 'Needs improvement in Java basics', '30'),
(1, '2023-12-01 21:00:00', 4, 2, '2023-12-01 20:00:00', 4, 'D,C,B,A', 'Good effort on Spring Boot', '40'),
(1, '2023-09-30 21:00:00', 5, 1, '2023-09-30 20:00:00', 5, 'B,A,D,C', 'Satisfactory performance in Java basics', '35');
---- Insert statements for Assignment_Task
INSERT INTO ASSIGNMENT_TASK (
    POINTS,
    ASSIGNMENT_ID,
    ID,
    ADDITIONAL_RESOURCES,
    EXPECTED_OUTPUT,
    TASK_DESCRIPTION,
    TASK_TYPE
) VALUES
(20, 1, 1, 'Java documentation', 'Working Java application', 'Implement a basic Java program', 'Programming'),
(25, 2, 2, 'Spring Boot guide', 'Functional Spring Boot app', 'Create a Spring Boot application', 'Programming'),
(30, 3, 3, 'Architecture patterns book', 'Detailed analysis report', 'Analyze architectural styles', 'Research'),
(10, 4, 4, 'Java quiz guide', 'Correct answers', 'Complete the Java Basics quiz', 'Quiz'),
(15, 5, 5, 'Spring Boot quiz guide', 'Correct answers', 'Complete the Spring Boot quiz', 'Quiz');
-- Insert statements for Question
INSERT INTO QUESTION ( QUIZ_ID, QUESTION_TEXT, OPTIONA, OPTIONB, OPTIONC, OPTIOND, CORRECT_ANSWER, POINTS)
VALUES
( 1, 'What is the correct way to declare a variable in Java?',
    'var x;',
    'int x;',
    'variable x;',
    'declare x;',
    'B',
    5),
( 1, 'Which method is the entry point of a Java program?',
    'start()',
    'run()',
    'main()',
    'execute()',
    'C',
    5),
( 1, 'What is the size of int data type in Java?',
    NULL,
    NULL,
    NULL,
    NULL,
    '16 bit',
    5);
INSERT INTO QUESTION ( QUIZ_ID, QUESTION_TEXT, OPTIONA, OPTIONB, OPTIONC, OPTIOND, CORRECT_ANSWER, POINTS)
VALUES
( 2, 'Which annotation is used to create a REST controller in Spring?',
    '@Controller',
    '@RestController',
    '@Service',
    '@Repository',
    'B',
    10),
( 2, 'What is the default scope of a bean in Spring?',
    'Prototype',
    'Singleton',
    'Request',
    'Session',
    'B',
    10),
( 2, 'Which dependency is required for Spring Boot Web applications?',
    'spring-boot-starter-data-jpa',
    'spring-boot-starter-test',
    'spring-boot-starter-web',
    'spring-boot-starter-security',
    'C',
    10),
( 2, 'Java is a platform-dependent programming language',
    'True',
    'False',
    NULL,
    NULL,
    'B',
    10),
( 2, 'The == operator in Java compares the content of two String objects.',
    'True',
    'False',
    NULL,
    NULL,
    'B',
    10);


