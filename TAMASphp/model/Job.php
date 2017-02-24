<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Job
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $nextJobID = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Job Attributes
  private $jobDescription;
  private $posType;
  private $skillsRequired;
  private $experienceRequired;
  private $postingDeadlineDate;
  private $offerDeadlineDate;
  private $state;

  //Autounique Attributes
  private $jobID;

  //Job Associations
  private $allocatedStudent;
  private $previousWorker;
  private $applicant;
  private $offerReceiver;
  private $employee;
  private $correspondingCourse;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aPosType, $aCorrespondingCourse)
  {
    $this->jobDescription = NULL;
    $this->posType = $aPosType;
    $this->skillsRequired = NULL;
    $this->experienceRequired = NULL;
    $this->postingDeadlineDate = NULL;
    $this->offerDeadlineDate = NULL;
    $this->resetState();
    $this->jobID = self::$nextJobID++;
    $this->allocatedStudent = array();
    $this->previousWorker = array();
    $this->applicant = array();
    $this->offerReceiver = array();
    $this->employee = array();
    $didAddCorrespondingCourse = $this->setCorrespondingCourse($aCorrespondingCourse);
    if (!$didAddCorrespondingCourse)
    {
      throw new Exception("Unable to create job due to correspondingCourse");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setJobDescription($aJobDescription)
  {
    $wasSet = false;
    $this->jobDescription = $aJobDescription;
    $wasSet = true;
    return $wasSet;
  }

  public function setPosType($aPosType)
  {
    $wasSet = false;
    $this->posType = $aPosType;
    $wasSet = true;
    return $wasSet;
  }

  public function setSkillsRequired($aSkillsRequired)
  {
    $wasSet = false;
    $this->skillsRequired = $aSkillsRequired;
    $wasSet = true;
    return $wasSet;
  }

  public function setExperienceRequired($aExperienceRequired)
  {
    $wasSet = false;
    $this->experienceRequired = $aExperienceRequired;
    $wasSet = true;
    return $wasSet;
  }

  public function setPostingDeadlineDate($aPostingDeadlineDate)
  {
    $wasSet = false;
    $this->postingDeadlineDate = $aPostingDeadlineDate;
    $wasSet = true;
    return $wasSet;
  }

  public function setOfferDeadlineDate($aOfferDeadlineDate)
  {
    $wasSet = false;
    $this->offerDeadlineDate = $aOfferDeadlineDate;
    $wasSet = true;
    return $wasSet;
  }

  public function setState($aState)
  {
    $wasSet = false;
    $this->state = $aState;
    $wasSet = true;
    return $wasSet;
  }

  public function resetState()
  {
    $wasReset = false;
    $this->state = $this->getDefaultState();
    $wasReset = true;
    return $wasReset;
  }

  public function getJobDescription()
  {
    return $this->jobDescription;
  }

  public function getPosType()
  {
    return $this->posType;
  }

  public function getSkillsRequired()
  {
    return $this->skillsRequired;
  }

  public function getExperienceRequired()
  {
    return $this->experienceRequired;
  }

  public function getPostingDeadlineDate()
  {
    return $this->postingDeadlineDate;
  }

  public function getOfferDeadlineDate()
  {
    return $this->offerDeadlineDate;
  }

  public function getState()
  {
    return $this->state;
  }

  public function getDefaultState()
  {
    return JobStatus.Ready;
  }

  public function getJobID()
  {
    return $this->jobID;
  }

  public function getAllocatedStudent_index($index)
  {
    $aAllocatedStudent = $this->allocatedStudent[$index];
    return $aAllocatedStudent;
  }

  public function getAllocatedStudent()
  {
    $newAllocatedStudent = $this->allocatedStudent;
    return $newAllocatedStudent;
  }

  public function numberOfAllocatedStudent()
  {
    $number = count($this->allocatedStudent);
    return $number;
  }

  public function hasAllocatedStudent()
  {
    $has = $this->numberOfAllocatedStudent() > 0;
    return $has;
  }

  public function indexOfAllocatedStudent($aAllocatedStudent)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->allocatedStudent as $allocatedStudent)
    {
      if ($allocatedStudent->equals($aAllocatedStudent))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getPreviousWorker_index($index)
  {
    $aPreviousWorker = $this->previousWorker[$index];
    return $aPreviousWorker;
  }

  public function getPreviousWorker()
  {
    $newPreviousWorker = $this->previousWorker;
    return $newPreviousWorker;
  }

  public function numberOfPreviousWorker()
  {
    $number = count($this->previousWorker);
    return $number;
  }

  public function hasPreviousWorker()
  {
    $has = $this->numberOfPreviousWorker() > 0;
    return $has;
  }

  public function indexOfPreviousWorker($aPreviousWorker)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->previousWorker as $previousWorker)
    {
      if ($previousWorker->equals($aPreviousWorker))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getApplicant_index($index)
  {
    $aApplicant = $this->applicant[$index];
    return $aApplicant;
  }

  public function getApplicant()
  {
    $newApplicant = $this->applicant;
    return $newApplicant;
  }

  public function numberOfApplicant()
  {
    $number = count($this->applicant);
    return $number;
  }

  public function hasApplicant()
  {
    $has = $this->numberOfApplicant() > 0;
    return $has;
  }

  public function indexOfApplicant($aApplicant)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->applicant as $applicant)
    {
      if ($applicant->equals($aApplicant))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getOfferReceiver_index($index)
  {
    $aOfferReceiver = $this->offerReceiver[$index];
    return $aOfferReceiver;
  }

  public function getOfferReceiver()
  {
    $newOfferReceiver = $this->offerReceiver;
    return $newOfferReceiver;
  }

  public function numberOfOfferReceiver()
  {
    $number = count($this->offerReceiver);
    return $number;
  }

  public function hasOfferReceiver()
  {
    $has = $this->numberOfOfferReceiver() > 0;
    return $has;
  }

  public function indexOfOfferReceiver($aOfferReceiver)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->offerReceiver as $offerReceiver)
    {
      if ($offerReceiver->equals($aOfferReceiver))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getEmployee_index($index)
  {
    $aEmployee = $this->employee[$index];
    return $aEmployee;
  }

  public function getEmployee()
  {
    $newEmployee = $this->employee;
    return $newEmployee;
  }

  public function numberOfEmployee()
  {
    $number = count($this->employee);
    return $number;
  }

  public function hasEmployee()
  {
    $has = $this->numberOfEmployee() > 0;
    return $has;
  }

  public function indexOfEmployee($aEmployee)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->employee as $employee)
    {
      if ($employee->equals($aEmployee))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getCorrespondingCourse()
  {
    return $this->correspondingCourse;
  }

  public static function minimumNumberOfAllocatedStudent()
  {
    return 0;
  }

  public function addAllocatedStudent($aAllocatedStudent)
  {
    $wasAdded = false;
    if ($this->indexOfAllocatedStudent($aAllocatedStudent) !== -1) { return false; }
    $this->allocatedStudent[] = $aAllocatedStudent;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAllocatedStudent($aAllocatedStudent)
  {
    $wasRemoved = false;
    if ($this->indexOfAllocatedStudent($aAllocatedStudent) != -1)
    {
      unset($this->allocatedStudent[$this->indexOfAllocatedStudent($aAllocatedStudent)]);
      $this->allocatedStudent = array_values($this->allocatedStudent);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAllocatedStudentAt($aAllocatedStudent, $index)
  {  
    $wasAdded = false;
    if($this->addAllocatedStudent($aAllocatedStudent))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllocatedStudent()) { $index = $this->numberOfAllocatedStudent() - 1; }
      array_splice($this->allocatedStudent, $this->indexOfAllocatedStudent($aAllocatedStudent), 1);
      array_splice($this->allocatedStudent, $index, 0, array($aAllocatedStudent));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAllocatedStudentAt($aAllocatedStudent, $index)
  {
    $wasAdded = false;
    if($this->indexOfAllocatedStudent($aAllocatedStudent) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllocatedStudent()) { $index = $this->numberOfAllocatedStudent() - 1; }
      array_splice($this->allocatedStudent, $this->indexOfAllocatedStudent($aAllocatedStudent), 1);
      array_splice($this->allocatedStudent, $index, 0, array($aAllocatedStudent));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAllocatedStudentAt($aAllocatedStudent, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfPreviousWorker()
  {
    return 0;
  }

  public function addPreviousWorker($aPreviousWorker)
  {
    $wasAdded = false;
    if ($this->indexOfPreviousWorker($aPreviousWorker) !== -1) { return false; }
    $this->previousWorker[] = $aPreviousWorker;
    if ($aPreviousWorker->indexOfPreviousJobExperience($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aPreviousWorker->addPreviousJobExperience($this);
      if (!$wasAdded)
      {
        array_pop($this->previousWorker);
      }
    }
    return $wasAdded;
  }

  public function removePreviousWorker($aPreviousWorker)
  {
    $wasRemoved = false;
    if ($this->indexOfPreviousWorker($aPreviousWorker) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfPreviousWorker($aPreviousWorker);
    unset($this->previousWorker[$oldIndex]);
    if ($aPreviousWorker->indexOfPreviousJobExperience($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aPreviousWorker->removePreviousJobExperience($this);
      if (!$wasRemoved)
      {
        $this->previousWorker[$oldIndex] = $aPreviousWorker;
        ksort($this->previousWorker);
      }
    }
    $this->previousWorker = array_values($this->previousWorker);
    return $wasRemoved;
  }

  public function addPreviousWorkerAt($aPreviousWorker, $index)
  {  
    $wasAdded = false;
    if($this->addPreviousWorker($aPreviousWorker))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPreviousWorker()) { $index = $this->numberOfPreviousWorker() - 1; }
      array_splice($this->previousWorker, $this->indexOfPreviousWorker($aPreviousWorker), 1);
      array_splice($this->previousWorker, $index, 0, array($aPreviousWorker));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMovePreviousWorkerAt($aPreviousWorker, $index)
  {
    $wasAdded = false;
    if($this->indexOfPreviousWorker($aPreviousWorker) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPreviousWorker()) { $index = $this->numberOfPreviousWorker() - 1; }
      array_splice($this->previousWorker, $this->indexOfPreviousWorker($aPreviousWorker), 1);
      array_splice($this->previousWorker, $index, 0, array($aPreviousWorker));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addPreviousWorkerAt($aPreviousWorker, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfApplicant()
  {
    return 0;
  }

  public function addApplicant($aApplicant)
  {
    $wasAdded = false;
    if ($this->indexOfApplicant($aApplicant) !== -1) { return false; }
    $this->applicant[] = $aApplicant;
    if ($aApplicant->indexOfJobsAppliedTo($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aApplicant->addJobsAppliedTo($this);
      if (!$wasAdded)
      {
        array_pop($this->applicant);
      }
    }
    return $wasAdded;
  }

  public function removeApplicant($aApplicant)
  {
    $wasRemoved = false;
    if ($this->indexOfApplicant($aApplicant) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfApplicant($aApplicant);
    unset($this->applicant[$oldIndex]);
    if ($aApplicant->indexOfJobsAppliedTo($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aApplicant->removeJobsAppliedTo($this);
      if (!$wasRemoved)
      {
        $this->applicant[$oldIndex] = $aApplicant;
        ksort($this->applicant);
      }
    }
    $this->applicant = array_values($this->applicant);
    return $wasRemoved;
  }

  public function addApplicantAt($aApplicant, $index)
  {  
    $wasAdded = false;
    if($this->addApplicant($aApplicant))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplicant()) { $index = $this->numberOfApplicant() - 1; }
      array_splice($this->applicant, $this->indexOfApplicant($aApplicant), 1);
      array_splice($this->applicant, $index, 0, array($aApplicant));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveApplicantAt($aApplicant, $index)
  {
    $wasAdded = false;
    if($this->indexOfApplicant($aApplicant) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplicant()) { $index = $this->numberOfApplicant() - 1; }
      array_splice($this->applicant, $this->indexOfApplicant($aApplicant), 1);
      array_splice($this->applicant, $index, 0, array($aApplicant));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addApplicantAt($aApplicant, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfOfferReceiver()
  {
    return 0;
  }

  public function addOfferReceiver($aOfferReceiver)
  {
    $wasAdded = false;
    if ($this->indexOfOfferReceiver($aOfferReceiver) !== -1) { return false; }
    $this->offerReceiver[] = $aOfferReceiver;
    if ($aOfferReceiver->indexOfOfferedJob($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aOfferReceiver->addOfferedJob($this);
      if (!$wasAdded)
      {
        array_pop($this->offerReceiver);
      }
    }
    return $wasAdded;
  }

  public function removeOfferReceiver($aOfferReceiver)
  {
    $wasRemoved = false;
    if ($this->indexOfOfferReceiver($aOfferReceiver) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfOfferReceiver($aOfferReceiver);
    unset($this->offerReceiver[$oldIndex]);
    if ($aOfferReceiver->indexOfOfferedJob($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aOfferReceiver->removeOfferedJob($this);
      if (!$wasRemoved)
      {
        $this->offerReceiver[$oldIndex] = $aOfferReceiver;
        ksort($this->offerReceiver);
      }
    }
    $this->offerReceiver = array_values($this->offerReceiver);
    return $wasRemoved;
  }

  public function addOfferReceiverAt($aOfferReceiver, $index)
  {  
    $wasAdded = false;
    if($this->addOfferReceiver($aOfferReceiver))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOfferReceiver()) { $index = $this->numberOfOfferReceiver() - 1; }
      array_splice($this->offerReceiver, $this->indexOfOfferReceiver($aOfferReceiver), 1);
      array_splice($this->offerReceiver, $index, 0, array($aOfferReceiver));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveOfferReceiverAt($aOfferReceiver, $index)
  {
    $wasAdded = false;
    if($this->indexOfOfferReceiver($aOfferReceiver) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOfferReceiver()) { $index = $this->numberOfOfferReceiver() - 1; }
      array_splice($this->offerReceiver, $this->indexOfOfferReceiver($aOfferReceiver), 1);
      array_splice($this->offerReceiver, $index, 0, array($aOfferReceiver));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addOfferReceiverAt($aOfferReceiver, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfEmployee()
  {
    return 0;
  }

  public function addEmployee($aEmployee)
  {
    $wasAdded = false;
    if ($this->indexOfEmployee($aEmployee) !== -1) { return false; }
    $this->employee[] = $aEmployee;
    if ($aEmployee->indexOfCurrentJob($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aEmployee->addCurrentJob($this);
      if (!$wasAdded)
      {
        array_pop($this->employee);
      }
    }
    return $wasAdded;
  }

  public function removeEmployee($aEmployee)
  {
    $wasRemoved = false;
    if ($this->indexOfEmployee($aEmployee) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfEmployee($aEmployee);
    unset($this->employee[$oldIndex]);
    if ($aEmployee->indexOfCurrentJob($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aEmployee->removeCurrentJob($this);
      if (!$wasRemoved)
      {
        $this->employee[$oldIndex] = $aEmployee;
        ksort($this->employee);
      }
    }
    $this->employee = array_values($this->employee);
    return $wasRemoved;
  }

  public function addEmployeeAt($aEmployee, $index)
  {  
    $wasAdded = false;
    if($this->addEmployee($aEmployee))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployee()) { $index = $this->numberOfEmployee() - 1; }
      array_splice($this->employee, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employee, $index, 0, array($aEmployee));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEmployeeAt($aEmployee, $index)
  {
    $wasAdded = false;
    if($this->indexOfEmployee($aEmployee) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployee()) { $index = $this->numberOfEmployee() - 1; }
      array_splice($this->employee, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employee, $index, 0, array($aEmployee));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEmployeeAt($aEmployee, $index);
    }
    return $wasAdded;
  }

  public function setCorrespondingCourse($aCorrespondingCourse)
  {
    $wasSet = false;
    if ($aCorrespondingCourse == null)
    {
      return $wasSet;
    }
    
    $existingCorrespondingCourse = $this->correspondingCourse;
    $this->correspondingCourse = $aCorrespondingCourse;
    if ($existingCorrespondingCourse != null && $existingCorrespondingCourse != $aCorrespondingCourse)
    {
      $existingCorrespondingCourse->removeJob($this);
    }
    $this->correspondingCourse->addJob($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->allocatedStudent = array();
    $copyOfPreviousWorker = $this->previousWorker;
    $this->previousWorker = array();
    foreach ($copyOfPreviousWorker as $aPreviousWorker)
    {
      $aPreviousWorker->removePreviousJobExperience($this);
    }
    $copyOfApplicant = $this->applicant;
    $this->applicant = array();
    foreach ($copyOfApplicant as $aApplicant)
    {
      $aApplicant->removeJobsAppliedTo($this);
    }
    $copyOfOfferReceiver = $this->offerReceiver;
    $this->offerReceiver = array();
    foreach ($copyOfOfferReceiver as $aOfferReceiver)
    {
      $aOfferReceiver->removeOfferedJob($this);
    }
    $copyOfEmployee = $this->employee;
    $this->employee = array();
    foreach ($copyOfEmployee as $aEmployee)
    {
      $aEmployee->removeCurrentJob($this);
    }
    $placeholderCorrespondingCourse = $this->correspondingCourse;
    $this->correspondingCourse = null;
    $placeholderCorrespondingCourse->removeJob($this);
  }

}
?>
