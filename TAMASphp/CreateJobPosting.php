<?php
require_once 'controller\InstructorController.php';
require_once 'persistence\PersistenceTAMAS.php';
require_once 'model\Job.php';
require_once 'model\Course.php';
require_once 'model\Department.php';
require_once 'model\Instructor.php';

session_start();
$c = new InstructorController();

// Try to create a job posting
try{
	
	// Reset the error messages
	$_SESSION['errorJobID'] = "";
	$_SESSION['errorJobDesc'] = "";
	$_SESSION['errorSkillsReq'] = "";
	$_SESSION['errorExpReq'] = "";
	$_SESSION['errorOfferDate'] = "";
	$_SESSION['jobPostingSuccess'] = "";
	
	$c->createJobPosting($_POST['aJobID'], $_POST['jobDescription'], $_POST['skillsRequired'], $_POST['experienceRequired'], $_POST['offerDeadlineDate']);
	
	// If it is succesful, this line will return a success notification
	$_SESSION['jobPostingSuccess'] = "Job published successfully.";
} catch (Exception $e){
	
	// Parse and cut the error message from the controller
	$e = $e->getMessage();
	$e_array = explode('@', $e);
	foreach($e_array as $entry){
		if(!empty($entry) && $entry[0] == "1"){
			$_SESSION['errorJobID'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "2"){
			$_SESSION['errorJobDesc'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "3"){
			$_SESSION['errorSkillsReq'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "4"){
			$_SESSION['errorExpReq'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "5"){
			$_SESSION['errorOfferDateFormat'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "6"){
			$_SESSION['errorOfferDate'] = substr($entry, 1);
		}
	}
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TAMASphp/CreateJobPostingPage.php" />
	</head>
</html>