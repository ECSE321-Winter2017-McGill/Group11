<?php
// Imports
require_once 'controller\InvalidInputException.php';
require_once 'persistence\PersistenceTAMAS.php';
require_once 'model\Course.php';
require_once 'model\Department.php';
require_once 'model\Instructor.php';
require_once 'model\Job.php';
require_once 'model\Review.php';
require_once 'model\Student.php';

session_start();

// Load data from persistence
$persis = new PersistenceTamas();
$dpt = $persis -> loadDataFromStore();

// Dummy teachers
$teacher1 = new Instructor("Teacher Teaching", "260543215", "teacher.teaching@mcgill.ca");
$teacher2 = new Instructor("Instructor Teaching", "260102344", "instructor.teaching@mcgill.ca");
$dpt -> addAllInstructor($teacher1);
$dpt -> addAllInstructor($teacher2);

// Dummy courses
$allTeachers = $dpt -> getAllInstructors();
$course1 = new Course("ECSE321", "ISE", "W2017", "3", "0", "2", "9", "60", "2", "5", "11.75", "11.75", "60", array($allTeachers[0]));
$course2 = new Course("ECSE323", "DSD", "W2017", "5", "7", "2", "15", "300", "6", "5", "11.75", "11.75", "100", array($allTeachers[1]));
$dpt -> addAllCourse($course1);
$dpt -> addAllCourse($course2);

// Dummy jobs
$myCourse = $dpt -> getAllCourses();
$job1 = new Job("TA", "2017-07-21", $myCourse[0]);
$job2 = new Job("Grader", "2017-07-21", $myCourse[0]);
$job3 = new Job("TA", "2017-07-21", $myCourse[1]);
$job4 = new Job("Grader", "2017-07-21", $myCourse[1]);
$dpt -> addAllJob($job1);
$dpt -> addAllJob($job2);
$dpt -> addAllJob($job3);
$dpt -> addAllJob($job4);

// Dummy students
$student1 = new Student("260453125", "Student TA", "student.ta@mcgill.ca", false, "2", "TA", "15");
$student2 = new Student("260023784", "Grad TA", "grad.ta@mcgill.ca", true, "1", "TA", "30");
$student3 = new Student("260146723", "Student Grader", "student.grader@mcgill.ca", false, "3", "Grader", "20");
$student4 = new Student("260943112", "Grad Grader", "grad.grader@mcgill.ca", true, "3", "Grader", "35");
$dpt -> addAllStudent($student1);
$dpt -> addAllStudent($student2);
$dpt -> addAllStudent($student3);
$dpt -> addAllStudent($student4);

// Allocate students to jobs
$myJobs = $dpt -> getAllJobs();
$TAs = $dpt -> getAllStudents();
$myJobs[0] -> addEmployee($TAs[0]);
$myJobs[2] -> addEmployee($TAs[1]);
$myJobs[1] -> addEmployee($TAs[2]);
$myJobs[3] -> addEmployee($TAs[3]);

// Set the right state for the jobs
$myJobs[0] -> setState("Accepted");
$myJobs[1] -> setState("Accepted");
$myJobs[2] -> setState("Accepted");
$myJobs[3] -> setState("Accepted");

// Write data to persistence
$persis->writeDataToStore($dpt);

// Go to main page
header("Location: LoginPage.php");
exit;
?>