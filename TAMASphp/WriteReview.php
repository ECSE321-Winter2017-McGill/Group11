<?php
require_once 'controller\InstructorController.php';
require_once 'persistence\PersistenceTAMAS.php';
require_once 'model\Job.php';
require_once 'model\Course.php';
require_once 'model\Department.php';
require_once 'model\Instructor.php';

session_start();
$c = new InstructorController();

// Try writing a review
try{
	
	// Reset the error messages
	$_SESSION['errorAuthor'] = "";
	$_SESSION['errorRevJobID'] = "";
	$_SESSION['errorEmployee'] = "";
	$_SESSION['errorRevDesc'] = "";
	$_SESSION['errorJobState'] = "";
	$_SESSION['revPostingSuccess'] = "";
	$_SESSION['wrongEmployeeAssoc'] = "";
	
	
	$c->createReview($_POST['author'], $_POST['revDescription'], $_POST['aRevJobID'], $_POST['anEmployeeID']);
	
	// If it is succesful, this line will return a success notification
	$_SESSION['revPostingSuccess'] = "Review published successfully.";
} catch (Exception $e){
	
	// Parse and cut the error message from the controller
	$e = $e->getMessage();
	$e_array = explode('@', $e);
	foreach($e_array as $entry){
		if(!empty($entry) && $entry[0] == "1"){
			$_SESSION['errorRevDesc'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "2"){
			$_SESSION['errorRevJobID'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "3"){
			$_SESSION['errorJobState'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "4"){
			$_SESSION['errorAuthor'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "5"){
			$_SESSION['errorEmployee'] = substr($entry, 1);
		}
	}
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TAMASphp/WriteReviewPage.php" />
	</head>
</html>