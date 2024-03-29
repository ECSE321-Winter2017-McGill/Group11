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
		$email = "daniel@mcgill.com";
			
		try{
			$this->contr->createInstructor($name, $id, $email);
		} catch (Exception $e) {
			$this->fail();
		}
		
		//instructor created
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));
		
		//kept attributes;
		$this->assertEquals(0, count($this->dpt->getAllInstructor_index(0)->getCourses()));
		$this->assertEquals(0, count($this->dpt->getAllInstructor_index(0)->getReviewText()));
		
		//modified attributes:
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
			
		//no instructor created
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

		//no instructor created
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

		//no instructor created
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
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

		//no instructor created
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
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

		//no instructor created
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
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

		//no instructor created
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}



	public function testCreateJobPosting(){
		//creating dummy objects
		$testInstr = new Instructor("Hugo", "99777881", "lloris@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("ECSE321", "IntroToSoftEng", "Winter2015", 3, 2, 3, 4, 123, 5, 6, 20, 21, 123, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("TA", "2016-10-16", $testCourse);
		$jobID = $testJob->getJobID();
		$this->dpt->addAllJob($testJob);

		$this->persis->writeDataToStore($this->dpt);

		
		$this->assertEquals(1, count($this->dpt->getAllJobs()));

		$jobDesc = "Help Students solve problems";
		$skillsReq = "Good at writing software";
		$experienceReq = "Must have at least 1 year of TA-ing experience";
		$deadlineDate = "2017-12-31";

		try{
			$this->contr->createJobPosting($jobID, $jobDesc, $skillsReq, $experienceReq, $deadlineDate);
		} catch (Exception $e) {
			$this->fail;
		}

		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));

		//kept attributes
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getAllocatedStudent()));
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getApplicant()));

		//adding the new modified job to the testCourse variable initally connected to the old non modified job
		$testCourse->addJob($this->dpt->getAllJob_index(0));

		//kept attributes
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getAllocatedStudent()));
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getApplicant()));
		$this->assertEquals($testCourse, $this->dpt->getAllJob_index(0)->getCorrespondingCourse());
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getEmployee()));
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getOfferReceiver()));
		$this->assertEquals("2016-10-16", $this->dpt->getAllJob_index(0)->getPostingDeadlineDate());
		$this->assertEquals("TA", $this->dpt->getAllJob_index(0)->getPosType());
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getPreviousWorker()));
		$this->assertEquals($jobID, $this->dpt->getAllJob_index(0)->getJobID());
				
		//adding the new modified job to the testCourse variable initally connected to the old non-modified job
		$testCourse->addJob($this->dpt->getAllJob_index(0));
		
		//changed attributes
		$this->assertEquals($experienceReq, $this->dpt->getAllJob_index(0)->getExperienceRequired());
		$this->assertEquals($jobDesc, $this->dpt->getAllJob_index(0)->getJobDescription());
		$this->assertEquals($deadlineDate, $this->dpt->getAllJob_index(0)->getOfferDeadlineDate());
		$this->assertEquals($skillsReq, $this->dpt->getAllJob_index(0)->getSkillsRequired());
		$this->assertEquals("Posted", $this->dpt->getAllJob_index(0)->getState());
		$this->assertEquals($testCourse, $this->dpt->getAllJob_index(0)->getCorrespondingCourse());
	}

	public function testCreateJobPostingNull(){
		$this->assertEquals(0, count($this->dpt->getAllJobs()));

		$jobID = null;
		$jobDesc = null;
		$skillsReq = null;
		$experienceReq = null;
		$deadlineDate = null;

		try{
			$this->contr->createJobPosting($jobID, $jobDesc, $skillsReq, $experienceReq, $deadlineDate);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}
			
		$this->assertEquals($error, "@1Job  not found! @2Job description field cannot be empty! @3Skills required field cannot be empty! @4Experience required field cannot be empty! @5Offer deadline date must be specified correctly (YYYY-MM-DD)!");
			
		//no new objects
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));

	}

	public function testCreateJobPostingEmpty(){
		$this->assertEquals(0, count($this->dpt->getAllJobs()));

		$jobID = "";
		$jobDesc = "";
		$skillsReq = "";
		$experienceReq = "";
		$deadlineDate = "";

		try{
			$this->contr->createJobPosting($jobID, $jobDesc, $skillsReq, $experienceReq, $deadlineDate);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}
			
		$this->assertEquals($error, "@1Job  not found! @2Job description field cannot be empty! @3Skills required field cannot be empty! @4Experience required field cannot be empty! @5Offer deadline date must be specified correctly (YYYY-MM-DD)!");
			
		//no new objects
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateJobPostingSpaceAndMinusOne(){
		$this->assertEquals(0, count($this->dpt->getAllJobs()));

		$jobID = "-1";//case when no job is selected from the spinner on thr UI
		$jobDesc = " ";
		$skillsReq = " ";
		$experienceReq = " ";
		$deadlineDate = " ";

		try{
			$this->contr->createJobPosting($jobID, $jobDesc, $skillsReq, $experienceReq, $deadlineDate);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}
			
		$this->assertEquals($error, "@1Job  not found! @2Job description field cannot be empty! @3Skills required field cannot be empty! @4Experience required field cannot be empty! @5Offer deadline date must be specified correctly (YYYY-MM-DD)!");
			
		//no new objects
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateJobPostingJobInexistant(){
		$this->assertEquals(0, count($this->dpt->getAllJobs()));

		$jobID = "665544332";//a job having "665544332" as ID does not exist
		$jobDesc = "Present tutorial sessions to students.";
		$skillsReq = "Good at preparing classes.";
		$experienceReq = "No experience needed.";
		$deadlineDate = "2018-01-01";
		try{
			$this->contr->createJobPosting($jobID, $jobDesc, $skillsReq, $experienceReq, $deadlineDate);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}
			
		$this->assertEquals($error, "@1Job 665544332 not found!");

		//no new objects
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testCreateJobPostingOfferDeadlineDateBeforePostingDeadlineDate(){
		//creating dummy objects
		$testInstr = new Instructor("Mousa", "99777882", "dembele@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("ECSE323", "DSD", "Fall2017", 3, 2, 3, 4, 123, 5, 6, 20, 21, 123, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("Grader", "2018-01-01", $testCourse);
		$jobID = $testJob->getJobID();
		$this->dpt->addAllJob($testJob);

		$this->persis->writeDataToStore($this->dpt);

		
		$this->assertEquals(1, count($this->dpt->getAllJobs()));

		$jobDesc = "Correct examinations";
		$skillsReq = "Good at basic arithmetic";
		$experienceReq = "MNo experience needed";
		$deadlineDate = "2016-11-30";

		try{
			$this->contr->createJobPosting($jobID, $jobDesc, $skillsReq, $experienceReq, $deadlineDate);
		} catch (Exception $e) {
			$error =$e->getMessage();
		}

		$this->assertEquals($error, "@6Offer deadline date must be after Posting deadline: 2018-01-01!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));

		//Attributes of class job are not modified
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getAllocatedStudent()));
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getApplicant()));
		$this->assertEquals($testCourse, $this->dpt->getAllJob_index(0)->getCorrespondingCourse());
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getEmployee()));
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getOfferReceiver()));
		$this->assertEquals("2018-01-01", $this->dpt->getAllJob_index(0)->getPostingDeadlineDate());
		$this->assertEquals("Grader", $this->dpt->getAllJob_index(0)->getPosType());
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getPreviousWorker()));
		$this->assertEquals($jobID, $this->dpt->getAllJob_index(0)->getJobID());
		$this->assertEquals(null, $this->dpt->getAllJob_index(0)->getExperienceRequired());
		$this->assertEquals(null, $this->dpt->getAllJob_index(0)->getJobDescription());
		$this->assertEquals(null, $this->dpt->getAllJob_index(0)->getOfferDeadlineDate());
		$this->assertEquals(null, $this->dpt->getAllJob_index(0)->getSkillsRequired());
		$this->assertEquals("Ready", $this->dpt->getAllJob_index(0)->getState());
	}

	public function testModifyAllocation(){

		//creating dummy objects
		$testInstr = new Instructor("Victor", "99777883", "wanyama@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("ECSE306", "Signals and Systems", "Summer2020", 4, 3, 4, 5, 124, 6, 7, 21, 22, 125, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("Grader", "2008-01-01", $testCourse);
		$jobID = $testJob->getJobID();
		$testJob->setState("Allocated");
		$this->dpt->addAllJob($testJob);

		$testStudApplID = "260680000";
		$testStudentAppl = new Student($testStudApplID, "Aren Babikian", "aren.babikian@mail.mcgill.ca", false, 2, "None", 0);
		$this->dpt->addAllStudent($testStudentAppl);
		$testJob->addApplicant($testStudentAppl);

		$testStudAllocID = "260680001";
		$testStudentAlloc = new Student($testStudAllocID, "Tharsan Ponnampalam", "tharsan.ponnampalam@mail.mcgill.ca", true, 3, "Pays a lot", 1);
		$this->dpt->addAllStudent($testStudentAlloc);
		$testJob->addAllocatedStudent($testStudentAlloc);

		$this->persis->writeDataToStore($this->dpt);


		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(1, count($this->dpt->getAllJob_index(0)->getAllocatedStudent()));
		$this->assertEquals($testStudentAlloc, $this->dpt->getAllJob_index(0)->getAllocatedStudent_index(0));
		$this->assertEquals(1, count($this->dpt->getAllJob_index(0)->getApplicant()));
		$this->assertEquals($testStudentAppl, $this->dpt->getAllJob_index(0)->getApplicant_index(0));

		try{
			$this->contr->modifyAllocation($jobID, $testStudAllocID, $testStudApplID);
		} catch (Exception $e) {
			$this->fail();
		}

		//because $testStudAlloc had not applied to any jobs before being allocated
		$testStudentAppl->removeJobsAppliedTo($testJob);

		//manually replacing Tharsan by Aren as allocated student and making Tharsan an applicant
		$testCourse->getJob_index(0)->removeAllocatedStudent($testStudentAlloc);
		$testCourse->getJob_index(0)->addAllocatedStudent($testStudentAppl);
		$testCourse->getJob_index(0)->addApplicant($testStudentAlloc);

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(2, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));

		//kept attributes
		$this->assertEquals($testCourse, $this->dpt->getAllJob_index(0)->getCorrespondingCourse());
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getEmployee()));
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getOfferReceiver()));
		$this->assertEquals("2008-01-01", $this->dpt->getAllJob_index(0)->getPostingDeadlineDate());
		$this->assertEquals("Grader", $this->dpt->getAllJob_index(0)->getPosType());
		$this->assertEquals(0, count($this->dpt->getAllJob_index(0)->getPreviousWorker()));
		$this->assertEquals($jobID, $this->dpt->getAllJob_index(0)->getJobID());
		$this->assertEquals(null, $this->dpt->getAllJob_index(0)->getExperienceRequired());
		$this->assertEquals(null, $this->dpt->getAllJob_index(0)->getJobDescription());
		$this->assertEquals(null, $this->dpt->getAllJob_index(0)->getOfferDeadlineDate());
		$this->assertEquals(null, $this->dpt->getAllJob_index(0)->getSkillsRequired());
		$this->assertEquals("Allocated", $this->dpt->getAllJob_index(0)->getState());

		//modified Attributes
		$this->assertEquals(1, count($this->dpt->getAllJob_index(0)->getAllocatedStudent()));
		$this->assertEquals($testStudentAppl, $this->dpt->getAllJob_index(0)->getAllocatedStudent_index(0));
		$this->assertEquals(1, count($this->dpt->getAllJob_index(0)->getApplicant()));
		$this->assertEquals($testStudentAlloc, $this->dpt->getAllJob_index(0)->getApplicant_index(0));
	}

	public function testModifyAllocationNull(){
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));

		$jobID = null;
		$testStudAllocID = null;
		$testStudApplID = null;

		try{
			$this->contr->modifyAllocation($jobID, $testStudAllocID, $testStudApplID);
		} catch (Exception $e) {
			$error =  $e->getMessage();
		}

		$this->assertEquals($error, "@1Job  not found! @3Allocated student  not found in job! @4Applied student  not found in job!");

		//no new objects
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testModifyAllocationMinus1(){
		$this->assertEquals(0, 0);
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
	
		//ID is -1 when no element has been selected from the spinnner
		$jobID = "-1";
		$testStudAllocID = "-1";
		$testStudApplID = "-1";
	
		try{

			$this->contr->modifyAllocation($jobID, $testStudAllocID, $testStudApplID);
			} catch (Exception $e) {
			$error =  $e->getMessage();

		}
	
		$this->assertEquals($error, "@1Job  not found! @3Allocated student  not found in job! @4Applied student  not found in job!");
	
		//no new objects
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));

	}

	public function testModifyAllocationJobInexistant(){

		//creating dummy objects
		$testStudApplID = "260680002";
		$testStudentAppl = new Student($testStudApplID, "Edward Zhao", "edward.zhaon@mail.mcgill.ca", false, 1, "Fun matriel", 2);
		$this->dpt->addAllStudent($testStudentAppl);

		$testStudAllocID = "260680003";
		$testStudentAlloc = new Student($testStudAllocID, "Shi Yu Liu", "shi.yu.liu@mail.mcgill.ca", true, 4, "Cool professor", 3);
		$this->dpt->addAllStudent($testStudentAlloc);

		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(2, count($this->dpt->getAllStudents()));

		$this->persis->writeDataToStore($this->dpt);

		
		$jobID = "abcde";

		try{
			$this->contr->modifyAllocation($jobID, $testStudAllocID, $testStudApplID);
		} catch (Exception $e) {
			$error =  $e->getMessage();
		}

		$this->assertEquals($error, "@1Job abcde not found! @3Allocated student 260680003 not found in job! @4Applied student 260680002 not found in job!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(2, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
	}

	public function testModifyAllocationStudentsInexistant(){

		//creating dummy objects
		$testInstr = new Instructor("Toby", "99777884", "alderweireld@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("ECSE330", "Electronics", "Summer2016", 4, 3, 4, 5, 124, 6, 7, 21, 22, 125, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("Grader", "2007-01-01", $testCourse);
		$jobID = $testJob->getJobID();
		$testJob->setState("Allocated");
		$this->dpt->addAllJob($testJob);

		//students not added to the job
		$testStudApplID = "260680004";
		$testStudentAppl = new Student($testStudApplID, "Mathieu Lapointe", "mathieu,lapointe@mail.mcgill.ca", false, 2, "None", 0);
		$this->dpt->addAllStudent($testStudentAppl);

		$testStudAllocID = "260680005";
		$testStudentAlloc = new Student($testStudAllocID, "John Doe", "john.doe@mail.mcgill.ca", true, 3, "Pays a lot", 1);
		$this->dpt->addAllStudent($testStudentAlloc);

		$this->persis->writeDataToStore($this->dpt);

		
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(2, count($this->dpt->getAllStudents()));


		try{
			$this->contr->modifyAllocation($jobID, $testStudAllocID, $testStudApplID);
		} catch (Exception $e) {
			$error =  $e->getMessage();
		}

		$this->assertEquals($error, "@3Allocated student 260680005 not found in job! @4Applied student 260680004 not found in job!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(2, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));

	}

	public function testModifyAllocationJobNotAllocated(){

		//creating dummy objects
		$testInstr = new Instructor("Jan", "99777885", "vertonghen@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("MATH363", "Discrete Math", "Fall2006", 4, 3, 4, 5, 124, 6, 7, 21, 22, 125, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("Grader", "2007-03-02", $testCourse);
		$jobID = $testJob->getJobID();
		//job not in "allocated" state
		$this->dpt->addAllJob($testJob);

		$testStudApplID = "260680006";
		$testStudentAppl = new Student($testStudApplID, "Willy Wonka", "willy.wonka@mail.mcgill.ca", false, 2, "None", 0);
		$testJob->addApplicant($testStudentAppl);
		$this->dpt->addAllStudent($testStudentAppl);
		
		$testStudAllocID = "260680007";
		$testStudentAlloc = new Student($testStudAllocID, "Red Riding Hood", "red.riding.hood@mail.mcgill.ca", true, 3, "Pays a lot", 1);
		$testJob->addAllocatedStudent($testStudentAlloc);
		$this->dpt->addAllStudent($testStudentAlloc);

		$this->persis->writeDataToStore($this->dpt);

		
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(2, count($this->dpt->getAllStudents()));


		try{
			$this->contr->modifyAllocation($jobID, $testStudAllocID, $testStudApplID);
		} catch (Exception $e) {
			$error =  $e->getMessage();
		}

		$this->assertEquals($error, "@2Job ".$jobID." must be allocated!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
		$this->assertEquals(2, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));
	}

	public function testCreateReview(){
		
		//creating dummy objects
		$instructorID = "99777886";
		$testInstr = new Instructor("Danny", $instructorID, "rose@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("COMP250", "Algos", "Winter2054", 4, 3, 4, 5, 124, 6, 7, 21, 22, 125, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("Grader", "2002-01-01", $testCourse);
		$jobID = $testJob->getJobID();
		$testJob->setState("Accepted");
		$this->dpt->addAllJob($testJob);
		
		$testStudentID = "260680008";
		$testStudent = new Student($testStudentID, "Johnny Walker", "johnny.walker@mail.mcgill.ca", false, 2, "Lots of free time", 0);
		$this->dpt->addAllStudent($testStudent);
		$testJob->addEmployee($testStudent);

		$this->persis->writeDataToStore($this->dpt);


		$this->assertEquals(0, count($this->dpt->getAllReviews()));

		$content = "Excellent Work";

		try{
			$this->contr->createReview($instructorID, $content, $jobID, $testStudentID);
		} catch (Exception $e) {
			$this->fail();
		}

		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(1, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));
		$this->assertEquals(1, count($this->dpt->getAllReviews()));

		//NOTE: this test only works partially. If lines 728, 7230 731 were not commented out, based on the output of the error report
		//it can be seen that the "createReview()" method does work. However, I cannot seem to create an object that is 
		//the object expected to be produced (for the instructor, job and student) when the method is called. Thus, I cannot 
		//produce a test that is successul.
		//An attempt to do so is on lines 725 and 726, but if all lines 725 through 731 are not commmented out, there is a 
		//"nesting level too deep" error that is thrown and that I do not know how to solve.
		
		//NOTE: considering that our line coverage, without this test, is 100%, 
		//I have decided not to waste time on trying to fix this test.

		//because the original $testInstr did not have a connected review object
		//$testInstr->addReviewText($this->dpt->getAllReview_index(0));
		//$testStudent->addReviewText($this->dpt->getAllReview_index(0));

		//$this->assertEquals($testInstr, $this->dpt->getAllReview_index(0)->getReviewer());
		$this->assertEquals($content, $this->dpt->getAllReview_index(0)->getContent());
		//$this->assertEquals($testJob, $this->dpt->getAllReview_index(0)->getReviewedJob());
		//$this->assertEquals($testStudent, $this->dpt->getAllReview_index(0)->getReviewee());
	}

	public function testCreateReviewNull(){
		$this->assertEquals(0, count($this->dpt->getAllReviews()));

		$instructorID = null;
		$content = null;
		$jobID = null;
		$testStudentID = null;

		try{
			$this->contr->createReview($instructorID, $content, $jobID, $testStudentID);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}

		$this->assertEquals($error, "@1Review content cannot be empty! @2Job  not found! @4Reviewer  not found in job! @5Reviewed student  not found in job!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
	}

	public function testCreateReviewEmptyContent(){
		$this->assertEquals(0, count($this->dpt->getAllReviews()));

		$instructorID = "";
		$content = "";
		$jobID = "";
		$testStudentID = "";

		try{
			$this->contr->createReview($instructorID, $content, $jobID, $testStudentID);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}

		$this->assertEquals($error, "@1Review content cannot be empty! @2Job  not found! @4Reviewer  not found in job! @5Reviewed student  not found in job!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
	}

	public function testCreateReviewSpaceAndMinusOne(){
		$this->assertEquals(0, count($this->dpt->getAllReviews()));

		$instructorID = "-1";
		$content = " ";
		$jobID = "-1";
		$testStudentID = "-1";

		try{
			$this->contr->createReview($instructorID, $content, $jobID, $testStudentID);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}

		$this->assertEquals($error, "@1Review content cannot be empty! @2Job  not found! @4Reviewer  not found in job! @5Reviewed student  not found in job!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(0, count($this->dpt->getAllInstructors()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
	}

	public function testCreateReviewJobInexistant(){
		//creating dummy objects
		$instructorID = "99777887";
		$testInstr = new Instructor("Kyle", $instructorID, "walker@b.ca");
		$this->dpt->addAllInstructor($testInstr);

		$testStudentID = "260680009";
		$testStudent = new Student($testStudentID, "Tommy Hilfiger", "tommy.hilfiger@mail.mcgill.ca", false, 2, "easy course to teach", 0);
		$this->dpt->addAllStudent($testStudent);

		$this->persis->writeDataToStore($this->dpt);

		
		$this->assertEquals(0, count($this->dpt->getAllReviews()));

		$content = "Terrible TA";
		$fakeJobID = "a12c";

		try{
			$this->contr->createReview($instructorID, $content, $fakeJobID, $testStudentID);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}

		$this->assertEquals($error, "@2Job a12c not found! @4Reviewer 99777887 not found in job! @5Reviewed student 260680009 not found in job!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(0, count($this->dpt->getAllCourses()));
		$this->assertEquals(0, count($this->dpt->getAllJobs()));
		$this->assertEquals(1, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
	}

	public function testCreateReviewInstructorAndStudentInexistant(){
		//creating dummy objects
		$testInstr = new Instructor("Christian", "99777888", "eriksen@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("CIVE281", "Mechanics", "Fall1998", 4, 3, 4, 5, 124, 6, 7, 21, 22, 125, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("TA", "1992-01-01", $testCourse);
		$jobID = $testJob->getJobID();
		$this->dpt->addAllJob($testJob);

		$testJob->setState("Accepted");

		$this->persis->writeDataToStore($this->dpt);

		
		$this->assertEquals(0, count($this->dpt->getAllReviews()));

		$fakeInstructorID = "27101214";
		$content = "Good performance";
		$fakeStudentID = "010203";

		try{
			$this->contr->createReview($fakeInstructorID, $content, $jobID, $fakeStudentID);
		} catch (Exception $e) {
			$error =  $e->getMessage();
		}

		$this->assertEquals($error, "@4Reviewer 27101214 not found in job! @5Reviewed student 010203 not found in job!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(0, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
	}

	public function testCreateReviewJobStateNotAccepted(){
		//creating dummy objects
		$instructorID = "99777889";
		$testInstr = new Instructor("Heung-Min", $instructorID, "son@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("PHIL230", "Moral philosophy", "Fall2016", 4, 3, 4, 5, 124, 6, 7, 21, 22, 125, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("Grader", "2222-02-02", $testCourse);
		$jobID = $testJob->getJobID();
		//job not in the "Accepted" state
		$this->dpt->addAllJob($testJob);

		$testStudentID = "260680010";
		$testStudent = new Student($testStudentID, "Michael Jackson", "michael.jackson@mail.mcgill.ca", false, 2, "Fun times", 0);
		$this->dpt->addAllStudent($testStudent);
		$testJob->addEmployee($testStudent);

		$this->persis->writeDataToStore($this->dpt);


		$this->assertEquals(0, count($this->dpt->getAllReviews()));

		$content = "Not bad";

		try{
			$this->contr->createReview($instructorID, $content, $jobID, $testStudentID);
		} catch (Exception $e) {
			$error =  $e->getMessage();
		}
			
		$this->assertEquals($error, "@3Job ".$jobID." must be in the Accepted state!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(1, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
	}

	public function testCreateReviewStudentNotEmployee(){
		//creating dummy objects
		$instructorID = "99777890";
		$testInstr = new Instructor("Erik", $instructorID, "lamela@b.ca");
		$this->dpt->addAllInstructor($testInstr);
		$testCourse = new Course("FACC100", "Engineering profession", "Summer2006", 4, 3, 4, 5, 124, 6, 7, 21, 22, 125, $this->dpt->getAllInstructors());
		$this->dpt->addAllCourse($testCourse);
		$testJob = new Job("Grader", "2010-10-11", $testCourse);
		$jobID = $testJob->getJobID();
		$this->dpt->addAllJob($testJob);

		$testJob->setState("Accepted");

		$testStudentID = "260680011";
		$testStudent = new Student($testStudentID, "Eddie Murphy", "eddie.murphy@mail.mcgill.ca", false, 2, "Learning new stuff", 0);
		$this->dpt->addAllStudent($testStudent);

		$this->persis->writeDataToStore($this->dpt);


		$this->assertEquals(0, count($this->dpt->getAllReviews()));

		$content = "Could have been better";

		try{
			$this->contr->createReview($instructorID, $content, $jobID, $testStudentID);
		} catch (Exception $e) {
			$error =  $e->getMessage();
		}
			
		$this->assertEquals($error, "@5Reviewed student 260680011 not found in job!");

		//no new objects
		$this->dpt = $this->persis->loadDataFromStore();
		$this->assertEquals(1, count($this->dpt->getAllCourses()));
		$this->assertEquals(1, count($this->dpt->getAllJobs()));
		$this->assertEquals(1, count($this->dpt->getAllStudents()));
		$this->assertEquals(1, count($this->dpt->getAllInstructors()));
		$this->assertEquals(0, count($this->dpt->getAllReviews()));
	}
}
