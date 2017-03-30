<?php
require_once __DIR__.'\..\controller\InvalidInputException.php';
require_once __DIR__.'\..\persistence\PersistenceTAMAS.php';
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
		if ($instructorName == null || strlen ( $instructorName ) == 0) {
			$error .= "@1Instructor name cannot be empty!";
		}
		if ($instructorID == null || strlen ( $instructorID ) == 0) {
			$error .= "@2Instructor ID cannot be empty!";
		}
		if ($instructorEmail == null || strlen ( $instructorEmail ) == 0) {
			$error .= "@3Instructor name cannot be empty!";
		}
		if (strlen($error) > 0){
			throw new Exception ( trim($error) );
		}
		else{
			//if inputs are valid
				
			//2. load data from persistence
			$persis= new PersistenceTamas();
			$dpt = $persis -> loadDataFromStore();
				
			//3. add instructor
			$instructor = new Instructor($instructorName, $instructorID, $instructorEmail);
			$dpt->addAllInstructor($instructor);
				
			//4. store data
			$persis->writeDataToStore($dpt);
		}
	}

	public function createJobPosting($aJobID, $jobDescription, $skillsRequired, $experienceRequired, $offerDeadlineDate) {
		//1. load data
		$persis= new PersistenceTamas();
		$dpt = $persis -> loadDataFromStore();

		//2. find job
		//REVIEW****************
		$myJob = NULL;
		foreach ($dpt->getAllJobs() as $job){
			if(strcmp($job->getJobID(), $aJobID) == 0){
				$myJob = $job;
				break;
			}
		}
		//END REVIEW****************

		//3. remove current version of the job in question
		$dpt->removeAllJob($myJob);
		//deletes the job saved in dpt equal to myJob

		$error = "";
		//3. validate inputs

		$jobDesc = InvalidInputException::validate_input($jobDescription);
		$skillsReq = InvalidInputException::validate_input($skillsRequired);
		$expReq = InvalidInputException::validate_input($experienceRequired);
		$offerDate = InvalidInputException::validate_input($offerDeadlineDate);
		
		$bool_myJob = $myJob == null;
		$bool_aJobID = $aJobID == null;
		$bool_myJobDesc = $jobDesc == null || strlen ( $jobDesc ) == 0;
		$bool_mySkillsReq = $skillsReq == null || strlen ( $skillsReq ) == 0;
		$bool_myExpReq = $expReq == null || strlen ( $expReq ) == 0;
		$bool_myOfferDate = $offerDate == null || strlen ( $offerDate ) == 0 || !strtotime($offerDate);
		
		//if there are issues...
		if ($bool_myJob) {
			$error .= "@1Job ";
			if(!$bool_aJobID){
				$error .= $aJobID;
			}
			$error .= " not found! ";
		}
		if ($bool_myJobDesc) {
			$error .= "@2Job description field cannot be empty! ";
		}
		if ($bool_mySkillsReq) {
			$error .= "@3Skills required field cannot be empty! ";
		}
		if ($bool_myExpReq) {
			$error .= "@4Experience required field cannot be empty! ";
		}
		if ($bool_myOfferDate) {
			$error .= "@5Posting deadline date must be specified correctly (YYYY-MM-DD)! ";
		}
		if (strlen($error) > 0){
			throw new Exception (trim($error));
		}
		else{
			//4. Convert date
			$dateConv = date('Y-m-d', strtotime($offerDate));
	
			//5. addding parameters to Job
	
			//$myJob = new Job($job, $dateConv);
			$myJob->setJobDescription($jobDesc);
			$myJob->setSkillsRequired($skillsReq);
			$myJob->setExperienceRequired($expReq);
			$myJob->setOfferDeadlineDate($dateConv);
	
			$myJob->setState(JobStatusEnum::Posted);
			//REVIEW****************
			$dpt->addAllJob($myJob);
			//END REVIEW****************
			$persis->writeDataToStore($dpt);
		}
	}

	public function modifyAllocaion($ajobID, $allocatedStudentID, $appliedStudentID){
		
		//1. load data
		$persis= new PersistenceTamas();
		$dpt = $persis -> loadDataFromStore();
		
		//2. find job
		//REVIEW****************
		$myJob = NULL;
		foreach ($dpt->getAllJobs() as $job){
			if(strcmp($job->getJobID(), $ajobID) == 0){
				$myJob = $job;
				break;
			}
		}
		//END REVIEW****************
		$dpt->removeAllJob($myJob);
		
		
		$myAllocStud = NULL;
		$myApplStud = NULL;
		
		if($myJob != null){
				//3. find allocated student
			//REVIEW****************
			foreach ($myJob->getAllocatedStudent() as $student){
				if(strcmp($student->getStudentID(), $allocatedStudentID) == 0){
					$myAllocStud = $student;
					break;
				}
			}
			//END REVIEW****************
			
			//4. find applied student
			//REVIEW****************
			foreach ($myJob->getApplicantt() as $student){
				if(strcmp($student->getStudentID(), $appliedStudentID) == 0){
					$myApplStud = $student;
					break;
				}
			}
		}
		
		//END REVIEW****************
		
		
		//5. validating inputs
		$error = "";
		
		if ($myJob == null) {
			$error .= "@1Job ";
			if($aJobID != null){
				$error .= $aJobID;
			}
			$error .= " not found! ";
		}
		if ($myAllocStud == null) {
			$error .= "@1Allocated student ";
			if($allocatedStudentID != null){
				$error .= $allocatedStudentID;
			}
			$error .= " not found in job! ";
		}
		if ($myApplStud == null) {
			$error .= "@1Applied student ";
			if($appliedStudentID != null){
				$error .= $appliedStudentID;
			}
			$error .= " not found in job! ";
		}
		if (strlen($error) > 0){
			throw new Exception (trim($error));
			// throw exceptions, if need be
		}
		else{
			$myJob->addAllocatedStudent($myApplStud);
			$myJob->removeAllocatedStudent($myAllocStud);
			$myJob->removeApplicant($myApplStud);
			
			$dpt->addAllJob($myJob);
			
			$persis->writeDataToStore($dpt);
		}
		
			//2. load data from persistence
		
	}

	public function createReview($instructor, $reviewContent, $job, $student){

	}

}
?>