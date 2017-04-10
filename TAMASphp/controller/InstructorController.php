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

	public function createInstructor($myinstructorName, $myinstructorID, $myinstructorEmail) {
		$error = "";
		//1. validating inputs
		$instructorName = InvalidInputException::validate_input($myinstructorName);
		$instructorID = InvalidInputException::validate_input($myinstructorID);
		$instructorEmail = InvalidInputException::validate_input($myinstructorEmail);
		
		//ID validity checks
		$validIDlength = strlen($instructorID) == 9;
		$validIDFormat = is_numeric($instructorID);
		 
		//Email validity check
		$validEmailFormat = filter_var($instructorEmail, FILTER_VALIDATE_EMAIL);
		
		// throw exceptions, if need be
		if ($instructorName == null || strlen ( $instructorName ) == 0) {
			$error .= "@1Instructor name cannot be empty! ";
		}
		if ($instructorID == null || strlen ( $instructorID ) == 0) {
			$error .= "@2Instructor ID cannot be empty! ";
		}
		else if(! $validIDlength || ! $validIDFormat) {
			$error .= "@3Instructor ID must be a 9-digit integer! ";
		}
		if ($instructorEmail == null || strlen ( $instructorEmail ) == 0) {
			$error .= "@4Instructor E-mail address cannot be empty! ";
		}
		else if ( ! $validEmailFormat){
			$error .= "@5Instructor E-mail address has to be of the form stringA@stringB.com! ";
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

		$myJob = NULL;
		foreach ($dpt->getAllJobs() as $job){
			if(strcmp($job->getJobID(), $aJobID) == 0){
				$myJob = $job;
				break;
			}
		}


		$error = "";
		//3. validate inputs

		$jobDesc = InvalidInputException::validate_input($jobDescription);
		$skillsReq = InvalidInputException::validate_input($skillsRequired);
		$expReq = InvalidInputException::validate_input($experienceRequired);
		$offerDate = InvalidInputException::validate_input($offerDeadlineDate);

		$bool_myJob = $myJob == null;
		$bool_aJobID = $aJobID == null || $aJobID == "-1";
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
			$error .= "@5Offer deadline date must be specified correctly (YYYY-MM-DD)! ";
			}
			if(!$bool_myJob && !$bool_myOfferDate){
				$postDate = $myJob->getPostingDeadlineDate();
				$bool_myOfferDateBeforePostingDate = strtotime($offerDate) < strtotime($postDate);
					
				if ($bool_myOfferDateBeforePostingDate) {
					$error .= "@6Offer deadline date must be after Posting deadline: " . $postDate . "!";
				}
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

			$myJob->setState("Posted");
			
			
			
			$dpt->addAllJob($myJob);
			$persis->writeDataToStore($dpt);
		}
	}

	public function modifyAllocaion($aJobID, $allocatedStudentID, $appliedStudentID){

		//1. load data
		$persis= new PersistenceTamas();
		$dpt = $persis -> loadDataFromStore();

		//2. find job
		$myJob = NULL;
		foreach ($dpt->getAllJobs() as $job){
			if(strcmp($job->getJobID(), $aJobID) == 0){
				$myJob = $job;
				break;
			}
		}



		$myAllocStud = NULL;
		$myApplStud = NULL;

		if($myJob != null){
			//3. find allocated student
			foreach ($myJob->getAllocatedStudent() as $student){
				if(strcmp($student->getStudentID(), $allocatedStudentID) == 0){
					$myAllocStud = $student;
					break;
				}
			}
				
			//4. find applied student
			foreach ($myJob->getApplicant() as $student){
				if(strcmp($student->getStudentID(), $appliedStudentID) == 0){
					$myApplStud = $student;
					break;
				}
			}
		}



		//5. validating inputs
		$error = "";

		if ($myJob == null) {
			$error .= "@1Job ";
			if($aJobID != null){
				$error .= $aJobID;
			}
			$error .= " not found! ";
		} else {
			if ($myJob->getState() != "Allocated"){
				$error .= "@2Job ";
				$error .= $aJobID;
				$error .= " must be allocated! ";
			}
		}
		if ($myAllocStud == null) {
			$error .= "@3Allocated student ";
			if($allocatedStudentID != null){
				$error .= $allocatedStudentID;
			}
			$error .= " not found in job! ";
		}
		if ($myApplStud == null) {
			$error .= "@4Applied student ";
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

		$content = InvalidInputException::validate_input ( $reviewContent );

		$myJob = NULL;
		foreach ($dpt->getAllJobs() as $job){
			if(strcmp($job->getJobID(), $ajobID) == 0){
				$myJob = $job;
				break;
			}
		}
		$myReviewer = NULL;
		$myReviewee = NULL;
		if ($myJob != null) {
			foreach ( $myJob->getCorrespondingCourse ()->getInstructors () as $instructor ) {
				if (strcmp ( $instructor->getInstructorID (), $aninstructorID ) == 0) {
					$myReviewer = $instructor;
					break;
				}
			}
			foreach ( $myJob->getEmployee () as $student ) {
				if (strcmp ( $student->getStudentID (), $astudentID ) == 0) {
					$myReviewee = $student;
					break;
				}
			}
		}
		
		$error = "";

		//if($content == null || strlen($content)){
			if($content == null){
			$error .= "@1Review content cannot be empty! ";
		}
		if ($myJob == null) {
			$error .= "@2Job ";
			if($ajobID != null && $ajobID != "-1"){
				$error .= $ajobID;
			}
			$error .= " not found! ";
		} else {
			if ($myJob->getState() != "Accepted"){
				$error .= "@3Job ";
				$error .= $ajobID;
				$error .= " must be in the Accepted state! ";
			}
		}
		if ($myReviewer == null) {
			$error .= "@4Reviewer ";
			if($aninstructorID != null && $aninstructorID != "-1"){
				$error .= $aninstructorID;
			}
			$error .= " not found in job! ";
		}
		if ($myReviewee == null) {
			$error .= "@5Reviewed student ";
			if($astudentID != null && $astudentID != "-1"){
				$error .= $astudentID;
			}
			$error .= " not found in job! ";
		}
		if (strlen($error) > 0){
			throw new Exception (trim($error));
			// throw exceptions, if need be
		}
		else{
				
			$review = new Review ($content, $myReviewee, $myJob, $myReviewer);
			$dpt->addAllReview($review);
				
			//other end of connections (for student and instructor) are set automatically
			
			/*$dpt->removeAllStudent($myReviewee);
			$dpt->removeAllInstructor($myReviewer);
				
			$myReviewee->addReviewText($review);
			$myReviewer->addReviewText($review);

			$dpt->addAllStudent($myReviewee);
			$dpt->addAllInstructor($myReviewer);*/

			$persis->writeDataToStore($dpt);
		}
	}

}
?>