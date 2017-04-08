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

print_r($dpt -> getAllInstructors());

// Dummy courses & jobs
$teacher1 = new Instructor("Teacher Teaching", "260543215", "teacher.teaching@mcgill.ca");
$teacher2 = new Instructor("Instructor Teaching", "260102344", "instructor.teaching@mcgill.ca");
$teacher3 = new Instructor("Test Instructor", "000000000", "test.instructor@mcgill.ca");
$dpt -> addAllInstructor($teacher1);
$dpt -> addAllInstructor($teacher2);
$dpt -> addAllInstructor($teacher3);

$allTeachers = $dpt -> getAllInstructors();

print_r($allTeachers);

$course1 = new Course("ECSE321", "ISE", "W2017", "3", "0", "2", "9", "60", "2", "5", "11.75", "11.75", "60", array($allTeachers[0]));
$course2 = new Course("ECSE323", "DSD", "W2017", "5", "7", "2", "15", "300", "6", "5", "11.75", "11.75", "100", array($allTeachers[1]));
$dpt -> addAllCourse($course1);
$dpt -> addAllCourse($course2);

$allCourses = $dpt -> getAllCourses();

$myCourse = $allCourses;

$job1 = new Job("TA", "2017-07-21", $myCourse[0]);
$job2 = new Job("Grader", "2017-07-21", $myCourse[0]);
$job3 = new Job("TA", "2017-07-21", $myCourse[1]);
$job4 = new Job("Grader", "2017-07-21", $myCourse[1]);

$dpt -> addAllJob($job1);
$dpt -> addAllJob($job2);
$dpt -> addAllJob($job3);
$dpt -> addAllJob($job4);

// Write data to persistence
$persis->writeDataToStore($dpt);

// Go to main page
header("Location: LoginPage.php");
exit;
?>