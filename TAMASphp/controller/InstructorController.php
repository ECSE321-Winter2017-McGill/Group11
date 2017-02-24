<?php
//require_once __DIR__.'\..\controller\InputValidator.php';
//require_once __DIR__.'\..\persistence\PersistenceEventRegistration.php';
require_once __DIR__.'\..\model\Course.php';
require_once __DIR__.'\..\model\Department.php';
require_once __DIR__.'\..\model\Instructor.php';
require_once __DIR__.'\..\model\Job.php';
require_once __DIR__.'\..\model\Review.php';
require_once __DIR__.'\..\model\Student.php';

class InstructorController
{
	public function __construct(){

	}
	
	public function createInstructor($instructorName, $instructorID, $instructorEmail) {
		$error = "";
		//1. validating inputs
		$instructorName = InvalidInputException::validate_input($instructorName);
		$instructorID = InvalidInputException::validate_input($instructorID);
		$instructorEmail = InvalidInputException::validate_input($instructorEmail);
			
			// throw exceptions, if need be
		if ($instructorName == null || strlen ( $instructorName ) == 0 
				|| $instructorID == null || strlen ( $instructorID ) == 0 
				|| $instructorEmail == null || strlen ( $instructorEmail ) == 0) {
			if ($instructorName == null || strlen ( $instructorName ) == 0) {
				$error .= "@1Instructor name cannot be empty!";
			}
			if ($instructorID == null || strlen ( $instructorID ) == 0) {
				$error .= "@2Instructor ID cannot be empty!";
			}
			if ($instructorEmail == null || strlen ( $instructorEmail ) == 0) {
				$error .= "@3Instructor name cannot be empty!";
			}
			throw new Exception ( $error );
		} else {
			//if inputs are valid
			
			//2. load data from persistence
			$persis= new PersistenceTamas();
			$dpt = $persis -> loadDataFromStore();
			
			//3. add instructor
			$instructor = new Instructor($instructorName, $instructorID, $instructorEmail);
			$dpt->addAllInstructor($instructor);
		}
		
		
		
	}
	
	public function createJobPosting($job, $jobDescription, $skillsRequired, $experienceRequired) {
		
	}
	
	public function modifyAllocaion($job, $student){
		
	}
	
	public function createReview($instructor, $reviewContent, $job, $student){
		
	}
	
}
?>