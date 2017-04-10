<?php
require_once 'controller\InstructorController.php';
require_once 'persistence\PersistenceTAMAS.php';
require_once 'model\Job.php';
require_once 'model\Course.php';
require_once 'model\Department.php';
require_once 'model\Instructor.php';

session_start();
$c = new InstructorController();
try{
	$_SESSION['errorAuthor'] = "";
	$_SESSION['errorRevJobID'] = "";
	$_SESSION['errorEmployee'] = "";
	$_SESSION['errorRevDesc'] = "";
	$_SESSION['errorJobState'] = "";
	$_SESSION['revPostingSuccess'] = "";
	$_SESSION['wrongEmployeeAssoc'] = "";
	$c->createReview($_POST['author'], $_POST['revDescription'], $_POST['aRevJobID'], $_POST['anEmployeeID']);
	$_SESSION['revPostingSuccess'] = "Review published successfully.";
} catch (Exception $e){
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
		else if(!empty($entry) && $entry[0] == "4"){
			$_SESSION['errorEmployee'] = substr($entry, 1);
		}
	}
	if(empty($_SESSION['errorRevDesc']) && empty($_SESSION['errorRevJobID']) && empty($_SESSION['errorJobState'])
			&& empty($_SESSION['errorAuthor']) && empty($_SESSION['errorEmployee'])){
		$_SESSION['wrongEmployeeAssoc'] = "Wrong employee associated to chosen job.";
	}
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TAMASphp/WriteReviewPage.php" />
	</head>
</html>