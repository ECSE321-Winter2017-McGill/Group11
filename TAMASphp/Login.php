<?php
require_once 'controller\InstructorController.php';
require_once 'persistence\PersistenceTAMAS.php';
require_once 'model\Job.php';
require_once 'model\Course.php';
require_once 'model\Department.php';
require_once 'model\Instructor.php';

session_start();
$persis = new PersistenceTamas(); // Load data from persistence
$dpt = $persis -> loadDataFromStore();

$_SESSION['errorEmail'] = ""; // Reset the error messages
$_SESSION['errorID'] = "";

$TeacherFound = false;
$MatchingID = false;

$Teachers = $dpt -> getAllInstructors();

// Search for if the teacher email is within the repository
foreach($Teachers as $Teacher){
	$email = $Teacher -> getEmail();
	if(strcmp($email, $_POST['instructorEmail']) == 0){
		$TeacherFound = true;
		$id = $Teacher -> getInstructorID();
		
		// If it is, check the ID provided match the one associated to the email
		if(strcmp($id, $_POST['instructorID']) == 0){
			$MatchingID = true;
			$_SESSION['user'] = $Teacher;
		}
	}
}

// Error messages
if(!$TeacherFound){
	$_SESSION['errorEmail'] = "No instructor found associated with this email.";
}
else if(!$MatchingID){
	$_SESSION['errorID'] = "ID does not match the provided instructor email.";
}
else if($TeacherFound && $MatchingID){
	header("Location: InstructorHomePage.php");
	exit;
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TAMASphp/LoginPage.php" />
	</head>
</html>