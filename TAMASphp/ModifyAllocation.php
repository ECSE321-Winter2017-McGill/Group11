<?php
require_once 'controller\InstructorController.php';
require_once 'persistence\PersistenceTAMAS.php';
require_once 'model\Job.php';
require_once 'model\Course.php';
require_once 'model\Department.php';
require_once 'model\Instructor.php';

session_start();
$c = new InstructorController();

// Try modifying the allocation
try{
	
	// Reset the error messages
	$_SESSION['errorAllocID'] = "";
	$_SESSION['errorAllocJobState'] = "";
	$_SESSION['errorAllocStudent'] = "";
	$_SESSION['errorAllocAppliedStudent'] = "";
	$_SESSION['modAllocationSuccess'] = "";
	
	$c->modifyAllocation($_POST['aJobID'], $_POST['allocatedStudentID'], $_POST['appliedStudentID']);
	
	// If it is succesful, this line will return a success notification
	$_SESSION['modAllocationSuccess'] = "Allocation modified succesfully.";
} catch (Exception $e){
	
	// Parse and cut the error message from the controller
	$e = $e->getMessage();
	$e_array = explode('@', $e);
	foreach($e_array as $entry){
		if(!empty($entry) && $entry[0] == "1"){
			$_SESSION['errorAllocID'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "2"){
			$_SESSION['errorAllocJobState'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "3"){
			$_SESSION['errorAllocStudent'] = substr($entry, 1);
		}
		else if(!empty($entry) && $entry[0] == "4"){
			$_SESSION['errorAllocAppliedStudent'] = substr($entry, 1);
		}
	}
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TAMASphp/ModifyAllocationPage.php" />
	</head>
</html>