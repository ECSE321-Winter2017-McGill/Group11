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
        $this->dpt->delete();
        $this->persis->writeDataToStore($this->dpt);
    }
	
    protected function tearDown()
    {
    }
    
    public function testCreateInstructor(){
    	
    }
    
    public function testCreateInstructorNull(){
    	 
    }
    
    public function testCreateInstructorEmpty(){
    	 
    }
    
    public function testCreateInstructorSpace(){
    	 
    }
    
    public function testCreateInstructorInvalidFormats(){
    	 
    }
    
    public function testCreateJobPosting(){
    	
    }
    
    public function testCreateJobPostingNull(){
    	 
    }
    
    public function testCreateJobPostingEmpty(){
    	 
    }
    
    public function testCreateJobPostingSpace(){
    	 
    }
    
    public function testCreateJobPostingOfferDeadlineDateBeforePostingDeadlineDate(){
    	 
    }
    
    public function testCreateJobPostingJobInexistant(){
    	 
    }
    
    public function testmodyifyAllocation(){
    	
    }
    
    public function testmodyifyAllocationNull(){
    	 
    }
    
    public function testmodyifyAllocationJobInexistant(){
    	 
    }
    
    public function testmodyifyAllocationStudentsInexistant(){
    
    }
    
    public function testCreateReview(){
    	 
    }
    
    public function testCreateReviewNull(){
    
    }
    
    public function testCreateReviewEmptyContent(){
    
    }
    
    public function testCreateReviewSpaceContent(){
    
    }
    
    public function testCreateReviewInstructorJobAndStudentInexistant(){
    
    }
}