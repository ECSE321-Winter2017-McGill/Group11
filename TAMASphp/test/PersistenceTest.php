<?php 

require_once __DIR__.'\..\persistence\PersistenceTAMAS.php';
require_once __DIR__.'\..\model\Department.php';
require_once __DIR__.'\..\model\Instructor.php';

class PersistenceTest extends PHPUnit_Framework_TestCase
{
	protected $peris;
	
	protected function setUp()
	{
		$this->persis = new PersistenceTamas();
	}
	
	protected function tearDown()
	{
	}
	
	public function testPersistence()
	{
		// 1. Create test data
		$dpt = new Department();
		$instructor = new Instructor("John", "111222333", "john@mcgill.ca");
		$dpt->addAllInstructor($instructor);
	
		// 2. Write all of the data
		$this->persis->writeDataToStore($dpt);
	
		// 3. Clear the data from memory
		$dpt->delete();
	
		$this->assertEquals(0, count($dpt->getParticipants()));
	
		// 4. Load it back
		$dpt = $this->persis->loadDataFromStore();
	
		// 5. Check that we got it back
		$this->assertEquals(1, count($rdpt->getParticipants()));
		$myInstructor = $dpt->getAllInstructor_index(0);
		$this->assertEquals("John", $myInstructor->getName());
		$this->assertEquals("111222333", $myInstructor->getInstructorID());
		$this->assertEquals("john@mcgill.ca", $myInstructor->getEmail());
	}
	
	
}



?>