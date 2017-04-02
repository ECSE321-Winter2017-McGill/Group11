<?php
require_once __DIR__.'\..\controller\InstructorController.php';
require_once __DIR__.'\..\persistence\PersistenceTAMAS.php';
require_once __DIR__.'\..\model\Course.php';
require_once __DIR__.'\..\model\Department.php';
require_once __DIR__.'\..\model\Instructor.php';
require_once __DIR__.'\..\model\Job.php';
require_once __DIR__.'\..\model\Review.php';
require_once __DIR__.'\..\model\Student.php';

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
        //$this->dpt->delete();
        $this->persis->writeDataToStore($this->dpt);
    }
	
    protected function tearDown()
    {
    }
    
    public function testCreateInstructor(){
    	$this->assertEquals(0, count($this->dpt->getAllInstructors()));
    	
    	$name = "Daniel Varro";
    	$id = "123456789";
    	$email = "daniel.varro@mail.mcgill.ca";
    	
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
    	$this->assertEquals($email, $this->dpt->getEvent_index(0)->getEmail());    	
    }
    
    public function testCreateInstructorNull(){
    	 $this->pass();
    }
    
    public function testCreateInstructorEmpty(){
    	$this->pass();
    }
    
    public function testCreateInstructorSpace(){
    	$this->pass();
    }
    
    public function testCreateInstructorInvalidFormats(){
    	$this->pass();
    }
    
    public function testCreateJobPosting(){
    	$this->pass();
    }
    
    public function testCreateJobPostingNull(){
    	$this->pass();
    }
    
    public function testCreateJobPostingEmpty(){
    	$this->pass();
    }
    
    public function testCreateJobPostingSpace(){
    	$this->pass();
    }
    
    public function testCreateJobPostingOfferDeadlineDateBeforePostingDeadlineDate(){
    	$this->pass();
    }
    
    public function testCreateJobPostingJobInexistant(){
    	$this->pass();
    }
    
    public function testmodyifyAllocation(){
    	$this->pass();
    }
    
    public function testmodyifyAllocationNull(){
    	$this->pass();
    }
    
    public function testmodyifyAllocationJobInexistant(){
    	$this->pass();
    }
    
    public function testmodyifyAllocationStudentsInexistant(){
    	$this->pass();
    }
    
    public function testCreateReview(){
    	$this->pass();
    }
    
    public function testCreateReviewNull(){
    	$this->pass();
    }
    
    public function testCreateReviewEmptyContent(){
    	$this->pass();
    }
    
    public function testCreateReviewSpaceContent(){
    	$this->pass();
    }
    
    public function testCreateReviewInstructorJobAndStudentInexistant(){
    	$this->pass();
    }
}