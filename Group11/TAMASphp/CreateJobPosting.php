<?php
require_once 'controller/InstructorController.php';

session_start();

$c = new InstructorController();
try{
	$_SESSION['errorJobID'] = "";
	$_SESSION['errorJobDesc'] = "";
	$_SESSION['errorSkillsReq'] = "";
	$_SESSION['errorExpReq'] = "";
	$_SESSION['errorOfferDate'] = "";
	$c->createJobPosting($_POST['aJobID'], $_POST['jobDescription'], $_POST['skillsRequired'], $_POST['experienceRequired'], $_POST['offerDeadlineDate']);
} catch (Exception $e){
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