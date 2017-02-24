<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private $code;
  private $name;
  private $semester;
  private $numberOfCredits;
  private $numberOfLabs;
  private $numberOfTutorials;
  private $numberOfHours;
  private $studentsEnrolled;
  private $tasNeeded;
  private $gradersNeeded;
  private $taHourlyRate;
  private $graderHourlyRate;
  private $budget;

  //Course Associations
  private $jobs;
  private $instructors;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aCode, $aName, $aSemester, $aNumberOfCredits, $aNumberOfLabs, $aNumberOfTutorials, $aNumberOfHours, $aStudentsEnrolled, $aTasNeeded, $aGradersNeeded, $aTaHourlyRate, $aGraderHourlyRate, $aBudget, $allInstructors)
  {
    $this->code = $aCode;
    $this->name = $aName;
    $this->semester = $aSemester;
    $this->numberOfCredits = $aNumberOfCredits;
    $this->numberOfLabs = $aNumberOfLabs;
    $this->numberOfTutorials = $aNumberOfTutorials;
    $this->numberOfHours = $aNumberOfHours;
    $this->studentsEnrolled = $aStudentsEnrolled;
    $this->tasNeeded = $aTasNeeded;
    $this->gradersNeeded = $aGradersNeeded;
    $this->taHourlyRate = $aTaHourlyRate;
    $this->graderHourlyRate = $aGraderHourlyRate;
    $this->budget = $aBudget;
    $this->jobs = array();
    $this->instructors = array();
    $didAddInstructors = $this->setInstructors($allInstructors);
    if (!$didAddInstructors)
    {
      throw new Exception("Unable to create Course, must have at least 1 instructors");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setCode($aCode)
  {
    $wasSet = false;
    $this->code = $aCode;
    $wasSet = true;
    return $wasSet;
  }

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setSemester($aSemester)
  {
    $wasSet = false;
    $this->semester = $aSemester;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumberOfCredits($aNumberOfCredits)
  {
    $wasSet = false;
    $this->numberOfCredits = $aNumberOfCredits;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumberOfLabs($aNumberOfLabs)
  {
    $wasSet = false;
    $this->numberOfLabs = $aNumberOfLabs;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumberOfTutorials($aNumberOfTutorials)
  {
    $wasSet = false;
    $this->numberOfTutorials = $aNumberOfTutorials;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumberOfHours($aNumberOfHours)
  {
    $wasSet = false;
    $this->numberOfHours = $aNumberOfHours;
    $wasSet = true;
    return $wasSet;
  }

  public function setStudentsEnrolled($aStudentsEnrolled)
  {
    $wasSet = false;
    $this->studentsEnrolled = $aStudentsEnrolled;
    $wasSet = true;
    return $wasSet;
  }

  public function setTasNeeded($aTasNeeded)
  {
    $wasSet = false;
    $this->tasNeeded = $aTasNeeded;
    $wasSet = true;
    return $wasSet;
  }

  public function setGradersNeeded($aGradersNeeded)
  {
    $wasSet = false;
    $this->gradersNeeded = $aGradersNeeded;
    $wasSet = true;
    return $wasSet;
  }

  public function setTaHourlyRate($aTaHourlyRate)
  {
    $wasSet = false;
    $this->taHourlyRate = $aTaHourlyRate;
    $wasSet = true;
    return $wasSet;
  }

  public function setGraderHourlyRate($aGraderHourlyRate)
  {
    $wasSet = false;
    $this->graderHourlyRate = $aGraderHourlyRate;
    $wasSet = true;
    return $wasSet;
  }

  public function setBudget($aBudget)
  {
    $wasSet = false;
    $this->budget = $aBudget;
    $wasSet = true;
    return $wasSet;
  }

  public function getCode()
  {
    return $this->code;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getSemester()
  {
    return $this->semester;
  }

  public function getNumberOfCredits()
  {
    return $this->numberOfCredits;
  }

  public function getNumberOfLabs()
  {
    return $this->numberOfLabs;
  }

  public function getNumberOfTutorials()
  {
    return $this->numberOfTutorials;
  }

  public function getNumberOfHours()
  {
    return $this->numberOfHours;
  }

  public function getStudentsEnrolled()
  {
    return $this->studentsEnrolled;
  }

  public function getTasNeeded()
  {
    return $this->tasNeeded;
  }

  public function getGradersNeeded()
  {
    return $this->gradersNeeded;
  }

  public function getTaHourlyRate()
  {
    return $this->taHourlyRate;
  }

  public function getGraderHourlyRate()
  {
    return $this->graderHourlyRate;
  }

  public function getBudget()
  {
    return $this->budget;
  }

  public function getJob_index($index)
  {
    $aJob = $this->jobs[$index];
    return $aJob;
  }

  public function getJobs()
  {
    $newJobs = $this->jobs;
    return $newJobs;
  }

  public function numberOfJobs()
  {
    $number = count($this->jobs);
    return $number;
  }

  public function hasJobs()
  {
    $has = $this->numberOfJobs() > 0;
    return $has;
  }

  public function indexOfJob($aJob)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->jobs as $job)
    {
      if ($job->equals($aJob))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getInstructor_index($index)
  {
    $aInstructor = $this->instructors[$index];
    return $aInstructor;
  }

  public function getInstructors()
  {
    $newInstructors = $this->instructors;
    return $newInstructors;
  }

  public function numberOfInstructors()
  {
    $number = count($this->instructors);
    return $number;
  }

  public function hasInstructors()
  {
    $has = $this->numberOfInstructors() > 0;
    return $has;
  }

  public function indexOfInstructor($aInstructor)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->instructors as $instructor)
    {
      if ($instructor->equals($aInstructor))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfJobs()
  {
    return 0;
  }

  public function addJobVia($aPosType)
  {
    return new Job($aPosType, $this);
  }

  public function addJob($aJob)
  {
    $wasAdded = false;
    if ($this->indexOfJob($aJob) !== -1) { return false; }
    $existingCorrespondingCourse = $aJob->getCorrespondingCourse();
    $isNewCorrespondingCourse = $existingCorrespondingCourse != null && $this !== $existingCorrespondingCourse;
    if ($isNewCorrespondingCourse)
    {
      $aJob->setCorrespondingCourse($this);
    }
    else
    {
      $this->jobs[] = $aJob;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeJob($aJob)
  {
    $wasRemoved = false;
    //Unable to remove aJob, as it must always have a correspondingCourse
    if ($this !== $aJob->getCorrespondingCourse())
    {
      unset($this->jobs[$this->indexOfJob($aJob)]);
      $this->jobs = array_values($this->jobs);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addJobAt($aJob, $index)
  {  
    $wasAdded = false;
    if($this->addJob($aJob))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJobs()) { $index = $this->numberOfJobs() - 1; }
      array_splice($this->jobs, $this->indexOfJob($aJob), 1);
      array_splice($this->jobs, $index, 0, array($aJob));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveJobAt($aJob, $index)
  {
    $wasAdded = false;
    if($this->indexOfJob($aJob) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJobs()) { $index = $this->numberOfJobs() - 1; }
      array_splice($this->jobs, $this->indexOfJob($aJob), 1);
      array_splice($this->jobs, $index, 0, array($aJob));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addJobAt($aJob, $index);
    }
    return $wasAdded;
  }

  public function isNumberOfInstructorsValid()
  {
    $isValid = $this->numberOfInstructors() >= self::minimumNumberOfInstructors();
    return $isValid;
  }

  public static function minimumNumberOfInstructors()
  {
    return 1;
  }

  public function addInstructor($aInstructor)
  {
    $wasAdded = false;
    if ($this->indexOfInstructor($aInstructor) !== -1) { return false; }
    $this->instructors[] = $aInstructor;
    if ($aInstructor->indexOfCourse($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aInstructor->addCourse($this);
      if (!$wasAdded)
      {
        array_pop($this->instructors);
      }
    }
    return $wasAdded;
  }

  public function removeInstructor($aInstructor)
  {
    $wasRemoved = false;
    if ($this->indexOfInstructor($aInstructor) == -1)
    {
      return $wasRemoved;
    }

    if ($this->numberOfInstructors() <= self::minimumNumberOfInstructors())
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfInstructor($aInstructor);
    unset($this->instructors[$oldIndex]);
    if ($aInstructor->indexOfCourse($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aInstructor->removeCourse($this);
      if (!$wasRemoved)
      {
        $this->instructors[$oldIndex] = $aInstructor;
        ksort($this->instructors);
      }
    }
    $this->instructors = array_values($this->instructors);
    return $wasRemoved;
  }

  public function setInstructors($newInstructors)
  {
    $wasSet = false;
    $verifiedInstructors = array();
    foreach ($newInstructors as $aInstructor)
    {
      if (array_search($aInstructor,$verifiedInstructors) !== false)
      {
        continue;
      }
      $verifiedInstructors[] = $aInstructor;
    }

    if (count($verifiedInstructors) != count($newInstructors) || count($verifiedInstructors) < self::minimumNumberOfInstructors())
    {
      return $wasSet;
    }

    $oldInstructors = $this->instructors;
    $this->instructors = array();
    foreach ($verifiedInstructors as $aNewInstructor)
    {
      $this->instructors[] = $aNewInstructor;
      $removeIndex = array_search($aNewInstructor,$oldInstructors);
      if ($removeIndex !== false)
      {
        unset($oldInstructors[$removeIndex]);
        $oldInstructors = array_values($oldInstructors);
      }
      else
      {
        $aNewInstructor->addCourse($this);
      }
    }

    foreach ($oldInstructors as $anOldInstructor)
    {
      $anOldInstructor->removeCourse($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function addInstructorAt($aInstructor, $index)
  {  
    $wasAdded = false;
    if($this->addInstructor($aInstructor))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfInstructors()) { $index = $this->numberOfInstructors() - 1; }
      array_splice($this->instructors, $this->indexOfInstructor($aInstructor), 1);
      array_splice($this->instructors, $index, 0, array($aInstructor));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveInstructorAt($aInstructor, $index)
  {
    $wasAdded = false;
    if($this->indexOfInstructor($aInstructor) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfInstructors()) { $index = $this->numberOfInstructors() - 1; }
      array_splice($this->instructors, $this->indexOfInstructor($aInstructor), 1);
      array_splice($this->instructors, $index, 0, array($aInstructor));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addInstructorAt($aInstructor, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->jobs as $aJob)
    {
      $aJob->delete();
    }
    $copyOfInstructors = $this->instructors;
    $this->instructors = array();
    foreach ($copyOfInstructors as $aInstructor)
    {
      $aInstructor->removeCourse($this);
    }
  }

}
?>
