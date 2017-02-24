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
	public function __construct()
	{

	}
	
	public function createInstructor($instructorName, $instructorID, $instructorEmail) {
		
	}
	
	public function createJobPosting($job, $jobDescription, $skillsRequired, $experienceRequired) {
		
	}
	
	public function modifyAllocaion($job, $student){
		
	}
	
	public function createReview($instructor, $reviewContent, $job, $student){
		
	}
	
	
	

	public function createParticipant($participant_name) {
		// 1. Validate input
		$name = InputValidator::validate_input($participant_name);
		if ($name == null || strlen($name) == 0) {
			throw new Exception("Participant name cannot be empty!");
		} else {
			// 2. Load all of the data
			$pm = new PersistenceEventRegistration();
			$rm = $pm->loadDataFromStore();
				
			// 3. Add the new participant
			$participant = new Participant($name);
			$rm->addParticipant($participant);
				
			// 4. Write all of the data
			$pm->writeDataToStore($rm);
		}
	}

	public function createEvent($event_name, $event_date, $starttime, $endtime) {

		$error = "";
		// 1. Validate inputs
		$name = InputValidator::validate_input ( $event_name );
		$date = InputValidator::validate_input ( $event_date );
		$strttm = InputValidator::validate_input ( $starttime );
		$endtm = InputValidator::validate_input ( $endtime );






		//Throw the correct error depending on the situation
		if ($name == null || strlen ( $name ) == 0
				|| $date == null || strlen ( $date ) == 0 || !strtotime($date)
				|| $strttm == null || strlen ( $strttm ) == 0 || !strtotime($strttm)
				|| $endtm == null || strlen ( $endtm ) == 0 || !strtotime($endtm)
				|| strtotime($strttm) > strtotime($endtm)) {
					if ($name == null || strlen ( $name ) == 0){
						$error .= "@1Event name cannot be empty! ";
					}
					if ($date == null || strlen ( $date ) == 0 || !strtotime($date)){
						$error .= "@2Event date must be specified correctly (YYYY-MM-DD)! ";
					}
					if ($strttm == null || strlen ( $strttm ) == 0 || !strtotime($strttm)){
						$error .= "@3Event start time must be specified correctly (HH:MM)! ";
					}
					if ($endtm == null || strlen ( $endtm ) == 0 || !strtotime($endtm)){
						$error .= "@4Event end time must be specified correctly (HH:MM)!";
					} else{
						if (strtotime($strttm) > strtotime($endtm)){
							$error .= "@4Event end time cannot be before event start time!";
						}
					}
						
					throw new Exception ( $error );
				} else {
					//if there are no errors...
						
					// 2. Load all of the data
					$pm = new PersistenceEventRegistration ();
					$rm = $pm->loadDataFromStore ();
						
					// 3. Convert the dates and times
					$dateconverted = date('Y-m-d', strtotime($date));
					$strttmconverted = date('H:i', strtotime($strttm));
					$endtmconverted = date('H:i', strtotime($endtm));
						
					// 4. Add the new event
					$event = new Event ( $name, $dateconverted, $strttmconverted, $endtmconverted );
					$rm->addEvent ( $event );
						
					// 5. Write all of the data
					$pm->writeDataToStore ( $rm );
				}
	}

	public function register($aParticipant, $aEvent){
		// 1. Load all of the data
		$pm = new PersistenceEventRegistration();
		$rm = $pm->loadDataFromStore();

		// 2. Find the participant
		$myparticipant = NULL;
		foreach ($rm->getParticipants() as $participant) {
			if (strcmp($participant->getName(), $aParticipant) == 0) {
				$myparticipant = $participant;
				break;
			}
		}

		// 3. Find the event
		$myevent = NULL;
		foreach ($rm->getEvents() as $event) {
			if (strcmp($event->getName(), $aEvent) == 0) {
				$myevent = $event;
				break;
			}
		}

		// 4. Register for the event
		$error = "";
		if ($myparticipant != NULL && $myevent != NULL) {
			$myregistration = new Registration($myparticipant, $myevent);
			$rm->addRegistration($myregistration);
			$pm->writeDataToStore($rm);
		} else {
			if ($myparticipant == NULL) {
				$error .= "@1Participant ";
				if ($aParticipant != NULL) {
					$error .= $aParticipant;
				}
				$error .= " not found! ";
			}
			if ($myevent == NULL) {
				$error .= "@2Event ";
				if ($aEvent != NULL) {
					$error .= $aEvent;
				}
				$error .= " not found!";
			}
			throw new Exception(trim($error));
		}

	}
}
?>