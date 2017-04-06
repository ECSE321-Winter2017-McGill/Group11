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
        $this->persis->writeDataToStore($this->dpt);
    }
	
    protected function tearDown()
    {
    }
    
    public function testCreateInstructor(){
    	$this->assertEquals(0, count($this->dpt->getAllInstructors()));
    	
    	$name = "Daniel";
    	$id = "123456789";
    	$email = "daniel@mcgill.ca";
    	
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
    
    public function testCreateInstructorInvalidFormats(){
    	$this->assertEquals(0, 0);
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