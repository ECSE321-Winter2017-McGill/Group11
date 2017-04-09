<?php
require_once __DIR__.'/../controller/InstructorController.php';
require_once __DIR__.'/../persistence/PersistenceTAMAS.php';
require_once __DIR__.'/../model/Course.php';
require_once __DIR__.'/../model/Department.php';
require_once __DIR__.'/../model/Instructor.php';
require_once __DIR__.'/../model/Job.php';
require_once __DIR__.'/../model/Review.php';
require_once __DIR__.'/../model/Student.php';

class instructorControllerTest extends PHPUnit_Framework_TestCase
{
	protected $contr;
	protected $persis;
	protected $dpt;

	protected function setUp()
	{
		$this->contr = new InstructorController();
		$this->persis = new PersistenceTamas();
		$this->dpt = $this->persis->loadDataFromStore();
		$this->dpt->delete();		
		
		//note that date and course do not have to be valid for testing purposes.
		//$this->contr->createInstructor('William', '876543210', 'a@b.ca');
		
		$this->persis->writeDataToStore($this->dpt);
	}

	protected function tearDown()
	{
	}

	public function testCreateInstructor(){
		$this->dpt = $this->persis->loadDataFromStore();
		$this->dpt->delete();
		$this->persis->writeDataToStore($this->dpt);
		
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
		 
		$name = "Daniel";
		$id = "123456789";
		$email = "daniel@mcgill.com";
		 
		try{
			$this->contr->createInstructor($name, $id, $email);
		} catch (Exception $e) {
			$this->fail();
		}
		 
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));
		 
		$this->assertEquals(0, count($this->dpt->getAllInstructor_index(0)->getCourses()));
		$this->assertEquals(0, count($this->dpt->getAllInstructor_index(0)->getReviewText()));
		$this->assertEquals($name, $this->dpt->getAllInstructor_index(0)->getName());
		$this->assertEquals($id, $this->dpt->getAllInstructor_index(0)->getInstructorID());
		$this->assertEquals($email, $this->dpt->getAllInstructor_index(0)->getEmail());
	}

	public function testCreateInstructorNull(){
		$this->dpt = $this->persis->loadDataFromStore();
		$this->dpt->delete();
		$this->persis->writeDataToStore($this->dpt);
		
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
		 
		$name = null;
		$id = null;
		$email = null;
		 
		$error = "";
		try{
			$this->contr->createInstructor($name, $id, $email);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}
		 
		$this->assertEquals($error, "@1Instructor name cannot be empty! @2Instructor ID cannot be empty! @4Instructor E-mail address cannot be empty!");
		 
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateInstructorEmpty(){
		$this->dpt = $this->persis->loadDataFromStore();
		$this->dpt->delete();
		$this->persis->writeDataToStore($this->dpt);
		
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));

		$name = "";
		$id = "";
		$email = "";

		$error = "";
		try{
			$this->contr->createInstructor($name, $id, $email);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}

		$this->assertEquals($error, "@1Instructor name cannot be empty! @2Instructor ID cannot be empty! @4Instructor E-mail address cannot be empty!");

		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateInstructorSpace(){
		$this->dpt = $this->persis->loadDataFromStore();
		$this->dpt->delete();
		$this->persis->writeDataToStore($this->dpt);
		
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));

		$name = " ";
		$id = " ";
		$email = " ";

		$error = "";
		try{
			$this->contr->createInstructor($name, $id, $email);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}

		$this->assertEquals($error, "@1Instructor name cannot be empty! @2Instructor ID cannot be empty! @4Instructor E-mail address cannot be empty!");

		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateInstructorInvalidFormatsOne(){
		$this->dpt = $this->persis->loadDataFromStore();
		$this->dpt->delete();
		$this->persis->writeDataToStore($this->dpt);
		
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
		
		$name = "Sahil";
		$id = "87654321";
		$email = "Sahilmcgill.ca";
		
		$error = "";
		try{
			$this->contr->createInstructor($name, $id, $email);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}
		
		$this->assertEquals($error, "@3Instructor ID must be a 9-digit integer! @5Instructor E-mail address has to be of the form stringA@stringB.com!");
		
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}
	
	public function testCreateInstructorInvalidFormatsOTwo(){
		$this->dpt = $this->persis->loadDataFromStore();
		$this->dpt->delete();
		$this->persis->writeDataToStore($this->dpt);
		
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	
		$name = "Yash";
		$id = "0246813579";
		$email = "Yash@mcgillca";
	
		$error = "";
		try{
			$this->contr->createInstructor($name, $id, $email);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}
	
		$this->assertEquals($error, "@3Instructor ID must be a 9-digit integer! @5Instructor E-mail address has to be of the form stringA@stringB.com!");
	
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}
	
	public function testCreateInstructorInvalidFormatsThree(){
		$this->dpt = $this->persis->loadDataFromStore();
		$this->dpt->delete();
		$this->persis->writeDataToStore($this->dpt);
		
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	
		$name = "Aren";
		$id = "123a45678";
		$email = "Arenmcgillca";
	
		$error = "";
		try{
			$this->contr->createInstructor($name, $id, $email);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}
	
		$this->assertEquals($error, "@3Instructor ID must be a 9-digit integer! @5Instructor E-mail address has to be of the form stringA@stringB.com!");
	
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}
	
	

	public function testCreateJobPosting(){
		$this->dpt = $this->persis->loadDataFromStore();
		
		$testInstr = new Instructor("William", "99777881", "a1@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("ECSE321", "IntroToSoftEng", "Winter", 3, 2, 3, 4, 123, 5, 6, 20, 21, 123, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("TA", "2016-10-16", $testCourse);
		$jobID = $testJob->getJobID();
		$this->dpt->addAllJob($testJob);
		
		$this->persis->writeDataToStore($this->dpt);
		

		$jobDesc = "Help Students solve problems";
		$skillsReq = "Good at writing software";
		$experienceReq = "Must have at least 1 year of TA-ing experience";
		$deadlineDate = "2017-12-31";
		
		try{
			$this->contr->createJobPosting($jobID, $jobDesc, $skillsReq, $experienceReq, $deadlineDate);
		} catch (Exception $e) {
			$this->fail;
		}
		
		$testCourse->removeJob($this->dpt->getAllJob_index(0));
		
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));
			
		//kept attributes
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getAllocatedStudent()));
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getApplicant()));
		$this->assertEquals($testCourse, $this->dpt->getAllJob_index(0)->getCorrespondingCourse());
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getEmployee()));
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getOfferReceiver()));
		$this->assertEquals("2016-10-16", $this->dpt->getAllJob_index(0)->getPostingDeadlineDate());
		$this->assertEquals("TA", $this->dpt->getAllJob_index(0)->getPosType());
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getPreviousWorker()));
		
		//changed attributes
		$this->assertEquals($experienceReq, $this->dpt->getAllJob_index(0)->getExperienceRequired());
		$this->assertEquals($jobDesc, $this->dpt->getAllJob_index(0)->getJobDescription());
		$this->assertEquals($jobID, $this->dpt->getAllJob_index(0)->getJobID());
		$this->assertEquals($deadlineDate, $this->dpt->getAllJob_index(0)->getOfferDeadlineDate());
		$this->assertEquals($skillsReq, $this->dpt->getAllJob_index(0)->getSkillsRequired());
		$this->assertEquals("Posted", $this->dpt->getAllJob_index(0)->getState());
		
		

	}

	public function testCreateJobPostingNull(){
		$this->assertEquals(0, 0);
	}

	public function testCreateJobPostingEmpty(){
		$this->assertEquals(0, 0);
	}

	public function testCreateJobPostingSpace(){
		$this->assertEquals(0, 0);
	}

	public function testCreateJobPostingOfferDeadlineDateBeforePostingDeadlineDate(){
		$this->assertEquals(0, 0);
	}

	public function testCreateJobPostingJobInexistant(){
		$this->assertEquals(0, 0);
	}

	public function testmodyifyAllocation(){
		$this->assertEquals(0, 0);
	}

	public function testmodyifyAllocationNull(){
		$this->assertEquals(0, 0);
	}

	public function testmodyifyAllocationJobInexistant(){
		$this->assertEquals(0, 0);
	}

	public function testmodyifyAllocationStudentsInexistant(){
		$this->assertEquals(0, 0);
	}

	public function testCreateReview(){
		$this->assertEquals(0, 0);
	}

	public function testCreateReviewNull(){
		$this->assertEquals(0, 0);
	}

	public function testCreateReviewEmptyContent(){
		$this->assertEquals(0, 0);
	}

	public function testCreateReviewSpaceContent(){
		$this->assertEquals(0, 0);
	}

	public function testCreateReviewInstructorJobAndStudentInexistant(){
		$this->assertEquals(0, 0);
	}
}