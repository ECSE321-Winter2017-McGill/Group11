<?php
require_once __DIR__.'/../controller/InvalidInputException.php';
require_once __DIR__.'/../persistence/PersistenceTAMAS.php';
require_once __DIR__.'/../model/Course.php';
require_once __DIR__.'/../model/Department.php';
require_once __DIR__.'/../model/Instructor.php';
require_once __DIR__.'/../model/Job.php';
require_once __DIR__.'/../model/Review.php';
require_once __DIR__.'/../model/Student.php';

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
		
		/*
		$validIDlength = strlen($instructorID) == 9;
		$validIDFormat = true;
		for($i = 0; $i < strlen ( $instructorID ); $i ++) {
			// ****************
			if (true) {
				$validIDFormat = FALSE;
				break;
			}
		}	
		
		$validEmailFormat = true;
		*/
		
		
		// throw exceptions, if need be
		if ($instructorName == null || strlen ( $instructorName ) == 0) {
			$error .= "@1Instructor name cannot be empty!";
		}
		if ($instructorID == null || strlen ( $instructorID ) == 0) {
			$error .= "@2Instructor ID cannot be empty!";
		}
		//if($validIDFormat || $validIDlength) {
		if(false){
			$error .= "@3Instructor ID must be a 9-digit integer!";
		}
		if ($instructorEmail == null || strlen ( $instructorEmail ) == 0) {
			$error .= "@4Instructor E-mail address cannot be empty!";
		}
		if ($validEmailFormat){
			$error .= "@5Instructor E-mail address has to be of the form example@example.com!";
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
			
			//3. remove current version of the job in question
			$dpt->removeAllJob($myJob);
			//deletes the job saved in dpt equal to myJob
			
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
			$error .= "@2Allocated student ";
			if($allocatedStudentID != null){
				$error .= $allocatedStudentID;
			}
			$error .= " not found in job! ";
		}
		if ($myApplStud == null) {
			$error .= "@3Applied student ";
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
			$dpt->removeAllJob($myJob);
			
			$myJob->addAllocatedStudent($myApplStud);
			$myJob->removeAllocatedStudent($myAllocStud);
			$myJob->removeApplicant($myApplStud);
			
			$dpt->addAllJob($myJob);
			
			$persis->writeDataToStore($dpt);
		}	
	}

	public function createReview($aninstructorID, $reviewContent, $ajobID, $astudentID){

		//1. load data
		$persis= new PersistenceTamas();
		$dpt = $persis -> loadDataFromStore();
		
		$content = InputValidator::validate_input ( $reviewContent );
		
		$myReviewer = NULL;
		foreach ($dpt->getAllInstructors() as $instructor){
			if(strcmp($instructor->getInstructorID(), $aninstructorID) == 0){
				$myReviewer = $instructor;
				break;
			}
		}
		
		$myJob = NULL;
		foreach ($dpt->getAllJobs() as $job){
			if(strcmp($job->getJobID(), $ajobID) == 0){
				$myJob = $job;
				break;
			}
		}
		
		$myReviewee = NULL;
		if($myJob != null){
		foreach ($myJob->getEmployee() as $student){
				if(strcmp($student->getStudentID(), $aStudentID) == 0){
					$myReviewee = $student;
					break;
				}
			}
		}
		
		$error = "";
		
		if($content == null || srtlen ($content) == 0){
			$error .= "@1Review content cannot be empty! ";
		}
		
		if ($myReviewer == null) {
			$error .= "@2Reviewer ";
			if($aninstructorID != null){
				$error .= $aninstructorID;
			}
			$error .= " not found! ";
		}
		
		if ($myJob == null) {
			$error .= "@3Job ";
			if($aJobID != null){
				$error .= $aJobID;
			}
			$error .= " not found! ";
		}
		
		if ($myReviewee == null) {
			$error .= "@4Reviewed student ";
			if($astudentID != null){
				$error .= $astudentID;
			}
			$error .= " not found! ";
		}
		if (strlen($error) > 0){
			throw new Exception (trim($error));
			// throw exceptions, if need be
		}
		else{
			
			$review = new Review ($content, $myReviewee, $myJob, $myReviewer);
			$dpt->addAllReview($review);
			
			$dpt->removeAllStudent($myReviewee);
			$dpt->removeAllInstructor($myReviewer);
			
			$myReviewee->addReviewText($review);
			$myReviewer->addReviewText($review);
				
			$dpt->addAllStudent($myReviewee);
			$dpt->addAllInstructor($myReviewer);
				
			$persis->writeDataToStore($dpt);
		}
	}

}
?>