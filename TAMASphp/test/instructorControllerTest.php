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
	protected $testJobID;

	protected function setUp()
	{
		$this->contr = new InstructorController();
		$this->persis = new PersistenceTamas();
		$this->dpt = $this->persis->loadDataFromStore();
		$this->dpt->delete();
		
		//note that date and course do not have to be valid for testing purposes.
		//$this->contr->createInstructor('William', '876543210', 'a@b.ca');
		
		$instructorA = new Instructor('William', '876543210', 'a@b.ca');
		$this->dpt->addAllInstructor($instructorA);
		$testCourse = new Course('ECSE321', 'IntroToSoftEng', 'Winter', 3, 2, 3, 4, 123, 5, 6, 20, 21, 123, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job('TA', 'date', $testCourse);
		$this->testJobID = $testJob->getJobID();
		$this->dpt->addAllJob($testJob);
		$this->persis->writeDataToStore($this->dpt);
	}

	protected function tearDown()
	{
	}

	public function testCreateInstructor(){
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
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
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
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateInstructorEmpty(){
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
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateInstructorSpace(){
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
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateInstructorInvalidFormatsOne(){
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
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}
	
	public function testCreateInstructorInvalidFormatsOTwo(){
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
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}
	
	public function testCreateInstructorInvalidFormatsThree(){
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
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateJobPosting(){
		$this->assertEquals(0, 0);
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