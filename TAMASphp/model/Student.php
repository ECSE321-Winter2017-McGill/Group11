<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Student
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $studentsByStudentID = array();
  private static $studentsByEmail = array();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Attributes
  private $studentID;
  private $name;
  private $email;
  private $isGrad;
  private $year;
  private $jobPreference;
  private $numberOfHours;

  //Student Associations
  private $previousJobExperiences;
  private $jobsAppliedTo;
  private $offeredJobs;
  private $currentJobs;
  private $reviewText;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aStudentID, $aName, $aEmail, $aIsGrad, $aYear, $aJobPreference, $aNumberOfHours)
  {
    $this->name = $aName;
    $this->isGrad = $aIsGrad;
    $this->year = $aYear;
    $this->jobPreference = $aJobPreference;
    $this->numberOfHours = $aNumberOfHours;
    if (!$this->setStudentID($aStudentID))
    {
      throw new RuntimeException("Cannot create due to duplicate studentID");
    }
    if (!$this->setEmail($aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email");
    }
    $this->previousJobExperiences = array();
    $this->jobsAppliedTo = array();
    $this->offeredJobs = array();
    $this->currentJobs = array();
    $this->reviewText = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setStudentID($aStudentID)
  {
    $wasSet = false;
    if (isset($this->studentID)) {
      $anOldStudentID = $this->getStudentID();
    }
    if (Student::hasWithStudentID($aStudentID)) {
      return $wasSet;
    }
    $this->studentID = $aStudentID;
    $wasSet = true;
    if (isset($anOldStudentID)) {
      unset(Student::$studentsByStudentID[(string)$anOldStudentID]);
    }
    Student::$studentsByStudentID[(string)$aStudentID] = $this;
    return $wasSet;
  }

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setEmail($aEmail)
  {
    $wasSet = false;
    if (isset($this->email)) {
      $anOldEmail = $this->getEmail();
    }
    if (Student::hasWithEmail($aEmail)) {
      return $wasSet;
    }
    $this->email = $aEmail;
    $wasSet = true;
    if (isset($anOldEmail)) {
      unset(Student::$studentsByEmail[(string)$anOldEmail]);
    }
    Student::$studentsByEmail[(string)$aEmail] = $this;
    return $wasSet;
  }

  public function setIsGrad($aIsGrad)
  {
    $wasSet = false;
    $this->isGrad = $aIsGrad;
    $wasSet = true;
    return $wasSet;
  }

  public function setYear($aYear)
  {
    $wasSet = false;
    $this->year = $aYear;
    $wasSet = true;
    return $wasSet;
  }

  public function setJobPreference($aJobPreference)
  {
    $wasSet = false;
    $this->jobPreference = $aJobPreference;
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

  public function getStudentID()
  {
    return $this->studentID;
  }

  public static function getWithStudentID($aStudentID)
  {
    return Student::$studentsByStudentID[(string)$aStudentID];
  }

  public static function hasWithStudentID($aStudentID)
  {
    return array_key_exists((string)$aStudentID, Student::$studentsByStudentID);
  }

  public function getName()
  {
    return $this->name;
  }

  public function getEmail()
  {
    return $this->email;
  }

  public static function getWithEmail($aEmail)
  {
    return Student::$studentsByEmail[(string)$aEmail];
  }

  public static function hasWithEmail($aEmail)
  {
    return array_key_exists((string)$aEmail, Student::$studentsByEmail);
  }

  public function getIsGrad()
  {
    return $this->isGrad;
  }

  public function getYear()
  {
    return $this->year;
  }

  public function getJobPreference()
  {
    return $this->jobPreference;
  }

  public function getNumberOfHours()
  {
    return $this->numberOfHours;
  }

  public function isIsGrad()
  {
    return $this->isGrad;
  }

  public function getPreviousJobExperience_index($index)
  {
    $aPreviousJobExperience = $this->previousJobExperiences[$index];
    return $aPreviousJobExperience;
  }

  public function getPreviousJobExperiences()
  {
    $newPreviousJobExperiences = $this->previousJobExperiences;
    return $newPreviousJobExperiences;
  }

  public function numberOfPreviousJobExperiences()
  {
    $number = count($this->previousJobExperiences);
    return $number;
  }

  public function hasPreviousJobExperiences()
  {
    $has = $this->numberOfPreviousJobExperiences() > 0;
    return $has;
  }

  public function indexOfPreviousJobExperience($aPreviousJobExperience)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->previousJobExperiences as $previousJobExperience)
    {
      if ($previousJobExperience->equals($aPreviousJobExperience))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getJobsAppliedTo_index($index)
  {
    $aJobsAppliedTo = $this->jobsAppliedTo[$index];
    return $aJobsAppliedTo;
  }

  public function getJobsAppliedTo()
  {
    $newJobsAppliedTo = $this->jobsAppliedTo;
    return $newJobsAppliedTo;
  }

  public function numberOfJobsAppliedTo()
  {
    $number = count($this->jobsAppliedTo);
    return $number;
  }

  public function hasJobsAppliedTo()
  {
    $has = $this->numberOfJobsAppliedTo() > 0;
    return $has;
  }

  public function indexOfJobsAppliedTo($aJobsAppliedTo)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->jobsAppliedTo as $jobsAppliedTo)
    {
      if ($jobsAppliedTo->equals($aJobsAppliedTo))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getOfferedJob_index($index)
  {
    $aOfferedJob = $this->offeredJobs[$index];
    return $aOfferedJob;
  }

  public function getOfferedJobs()
  {
    $newOfferedJobs = $this->offeredJobs;
    return $newOfferedJobs;
  }

  public function numberOfOfferedJobs()
  {
    $number = count($this->offeredJobs);
    return $number;
  }

  public function hasOfferedJobs()
  {
    $has = $this->numberOfOfferedJobs() > 0;
    return $has;
  }

  public function indexOfOfferedJob($aOfferedJob)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->offeredJobs as $offeredJob)
    {
      if ($offeredJob->equals($aOfferedJob))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getCurrentJob_index($index)
  {
    $aCurrentJob = $this->currentJobs[$index];
    return $aCurrentJob;
  }

  public function getCurrentJobs()
  {
    $newCurrentJobs = $this->currentJobs;
    return $newCurrentJobs;
  }

  public function numberOfCurrentJobs()
  {
    $number = count($this->currentJobs);
    return $number;
  }

  public function hasCurrentJobs()
  {
    $has = $this->numberOfCurrentJobs() > 0;
    return $has;
  }

  public function indexOfCurrentJob($aCurrentJob)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->currentJobs as $currentJob)
    {
      if ($currentJob->equals($aCurrentJob))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getReviewText_index($index)
  {
    $aReviewText = $this->reviewText[$index];
    return $aReviewText;
  }

  public function getReviewText()
  {
    $newReviewText = $this->reviewText;
    return $newReviewText;
  }

  public function numberOfReviewText()
  {
    $number = count($this->reviewText);
    return $number;
  }

  public function hasReviewText()
  {
    $has = $this->numberOfReviewText() > 0;
    return $has;
  }

  public function indexOfReviewText($aReviewText)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->reviewText as $reviewText)
    {
      if ($reviewText->equals($aReviewText))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfPreviousJobExperiences()
  {
    return 0;
  }

  public function addPreviousJobExperience($aPreviousJobExperience)
  {
    $wasAdded = false;
    if ($this->indexOfPreviousJobExperience($aPreviousJobExperience) !== -1) { return false; }
    $this->previousJobExperiences[] = $aPreviousJobExperience;
    if ($aPreviousJobExperience->indexOfPreviousWorker($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aPreviousJobExperience->addPreviousWorker($this);
      if (!$wasAdded)
      {
        array_pop($this->previousJobExperiences);
      }
    }
    return $wasAdded;
  }

  public function removePreviousJobExperience($aPreviousJobExperience)
  {
    $wasRemoved = false;
    if ($this->indexOfPreviousJobExperience($aPreviousJobExperience) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfPreviousJobExperience($aPreviousJobExperience);
    unset($this->previousJobExperiences[$oldIndex]);
    if ($aPreviousJobExperience->indexOfPreviousWorker($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aPreviousJobExperience->removePreviousWorker($this);
      if (!$wasRemoved)
      {
        $this->previousJobExperiences[$oldIndex] = $aPreviousJobExperience;
        ksort($this->previousJobExperiences);
      }
    }
    $this->previousJobExperiences = array_values($this->previousJobExperiences);
    return $wasRemoved;
  }

  public function addPreviousJobExperienceAt($aPreviousJobExperience, $index)
  {  
    $wasAdded = false;
    if($this->addPreviousJobExperience($aPreviousJobExperience))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPreviousJobExperiences()) { $index = $this->numberOfPreviousJobExperiences() - 1; }
      array_splice($this->previousJobExperiences, $this->indexOfPreviousJobExperience($aPreviousJobExperience), 1);
      array_splice($this->previousJobExperiences, $index, 0, array($aPreviousJobExperience));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMovePreviousJobExperienceAt($aPreviousJobExperience, $index)
  {
    $wasAdded = false;
    if($this->indexOfPreviousJobExperience($aPreviousJobExperience) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfPreviousJobExperiences()) { $index = $this->numberOfPreviousJobExperiences() - 1; }
      array_splice($this->previousJobExperiences, $this->indexOfPreviousJobExperience($aPreviousJobExperience), 1);
      array_splice($this->previousJobExperiences, $index, 0, array($aPreviousJobExperience));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addPreviousJobExperienceAt($aPreviousJobExperience, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfJobsAppliedTo()
  {
    return 0;
  }

  public static function maximumNumberOfJobsAppliedTo()
  {
    return 3;
  }

  public function addJobsAppliedTo($aJobsAppliedTo)
  {
    $wasAdded = false;
    if ($this->indexOfJobsAppliedTo($aJobsAppliedTo) !== -1) { return false; }
    if ($this->numberOfJobsAppliedTo() >= self::maximumNumberOfJobsAppliedTo())
    {
      return $wasAdded;
    }

    $this->jobsAppliedTo[] = $aJobsAppliedTo;
    if ($aJobsAppliedTo->indexOfApplicant($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aJobsAppliedTo->addApplicant($this);
      if (!$wasAdded)
      {
        array_pop($this->jobsAppliedTo);
      }
    }
    return $wasAdded;
  }

  public function removeJobsAppliedTo($aJobsAppliedTo)
  {
    $wasRemoved = false;
    if ($this->indexOfJobsAppliedTo($aJobsAppliedTo) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfJobsAppliedTo($aJobsAppliedTo);
    unset($this->jobsAppliedTo[$oldIndex]);
    if ($aJobsAppliedTo->indexOfApplicant($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aJobsAppliedTo->removeApplicant($this);
      if (!$wasRemoved)
      {
        $this->jobsAppliedTo[$oldIndex] = $aJobsAppliedTo;
        ksort($this->jobsAppliedTo);
      }
    }
    $this->jobsAppliedTo = array_values($this->jobsAppliedTo);
    return $wasRemoved;
  }

  public function setJobsAppliedTo($newJobsAppliedTo)
  {
    $wasSet = false;
    $verifiedJobsAppliedTo = array();
    foreach ($newJobsAppliedTo as $aJobsAppliedTo)
    {
      if (array_search($aJobsAppliedTo,$verifiedJobsAppliedTo) !== false)
      {
        continue;
      }
      $verifiedJobsAppliedTo[] = $aJobsAppliedTo;
    }

    if (count($verifiedJobsAppliedTo) != count($newJobsAppliedTo) || count($verifiedJobsAppliedTo) > self::maximumNumberOfJobsAppliedTo())
    {
      return $wasSet;
    }

    $oldJobsAppliedTo = $this->jobsAppliedTo;
    $this->jobsAppliedTo = array();
    foreach ($verifiedJobsAppliedTo as $aNewJobsAppliedTo)
    {
      $this->jobsAppliedTo[] = $aNewJobsAppliedTo;
      $removeIndex = array_search($aNewJobsAppliedTo,$oldJobsAppliedTo);
      if ($removeIndex !== false)
      {
        unset($oldJobsAppliedTo[$removeIndex]);
        $oldJobsAppliedTo = array_values($oldJobsAppliedTo);
      }
      else
      {
        $aNewJobsAppliedTo->addApplicant($this);
      }
    }

    foreach ($oldJobsAppliedTo as $anOldJobsAppliedTo)
    {
      $anOldJobsAppliedTo->removeApplicant($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function addJobsAppliedToAt($aJobsAppliedTo, $index)
  {  
    $wasAdded = false;
    if($this->addJobsAppliedTo($aJobsAppliedTo))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJobsAppliedTo()) { $index = $this->numberOfJobsAppliedTo() - 1; }
      array_splice($this->jobsAppliedTo, $this->indexOfJobsAppliedTo($aJobsAppliedTo), 1);
      array_splice($this->jobsAppliedTo, $index, 0, array($aJobsAppliedTo));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveJobsAppliedToAt($aJobsAppliedTo, $index)
  {
    $wasAdded = false;
    if($this->indexOfJobsAppliedTo($aJobsAppliedTo) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJobsAppliedTo()) { $index = $this->numberOfJobsAppliedTo() - 1; }
      array_splice($this->jobsAppliedTo, $this->indexOfJobsAppliedTo($aJobsAppliedTo), 1);
      array_splice($this->jobsAppliedTo, $index, 0, array($aJobsAppliedTo));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addJobsAppliedToAt($aJobsAppliedTo, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfOfferedJobs()
  {
    return 0;
  }

  public static function maximumNumberOfOfferedJobs()
  {
    return 3;
  }

  public function addOfferedJob($aOfferedJob)
  {
    $wasAdded = false;
    if ($this->indexOfOfferedJob($aOfferedJob) !== -1) { return false; }
    if ($this->numberOfOfferedJobs() >= self::maximumNumberOfOfferedJobs())
    {
      return $wasAdded;
    }

    $this->offeredJobs[] = $aOfferedJob;
    if ($aOfferedJob->indexOfOfferReceiver($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aOfferedJob->addOfferReceiver($this);
      if (!$wasAdded)
      {
        array_pop($this->offeredJobs);
      }
    }
    return $wasAdded;
  }

  public function removeOfferedJob($aOfferedJob)
  {
    $wasRemoved = false;
    if ($this->indexOfOfferedJob($aOfferedJob) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfOfferedJob($aOfferedJob);
    unset($this->offeredJobs[$oldIndex]);
    if ($aOfferedJob->indexOfOfferReceiver($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aOfferedJob->removeOfferReceiver($this);
      if (!$wasRemoved)
      {
        $this->offeredJobs[$oldIndex] = $aOfferedJob;
        ksort($this->offeredJobs);
      }
    }
    $this->offeredJobs = array_values($this->offeredJobs);
    return $wasRemoved;
  }

  public function setOfferedJobs($newOfferedJobs)
  {
    $wasSet = false;
    $verifiedOfferedJobs = array();
    foreach ($newOfferedJobs as $aOfferedJob)
    {
      if (array_search($aOfferedJob,$verifiedOfferedJobs) !== false)
      {
        continue;
      }
      $verifiedOfferedJobs[] = $aOfferedJob;
    }

    if (count($verifiedOfferedJobs) != count($newOfferedJobs) || count($verifiedOfferedJobs) > self::maximumNumberOfOfferedJobs())
    {
      return $wasSet;
    }

    $oldOfferedJobs = $this->offeredJobs;
    $this->offeredJobs = array();
    foreach ($verifiedOfferedJobs as $aNewOfferedJob)
    {
      $this->offeredJobs[] = $aNewOfferedJob;
      $removeIndex = array_search($aNewOfferedJob,$oldOfferedJobs);
      if ($removeIndex !== false)
      {
        unset($oldOfferedJobs[$removeIndex]);
        $oldOfferedJobs = array_values($oldOfferedJobs);
      }
      else
      {
        $aNewOfferedJob->addOfferReceiver($this);
      }
    }

    foreach ($oldOfferedJobs as $anOldOfferedJob)
    {
      $anOldOfferedJob->removeOfferReceiver($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function addOfferedJobAt($aOfferedJob, $index)
  {  
    $wasAdded = false;
    if($this->addOfferedJob($aOfferedJob))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOfferedJobs()) { $index = $this->numberOfOfferedJobs() - 1; }
      array_splice($this->offeredJobs, $this->indexOfOfferedJob($aOfferedJob), 1);
      array_splice($this->offeredJobs, $index, 0, array($aOfferedJob));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveOfferedJobAt($aOfferedJob, $index)
  {
    $wasAdded = false;
    if($this->indexOfOfferedJob($aOfferedJob) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOfferedJobs()) { $index = $this->numberOfOfferedJobs() - 1; }
      array_splice($this->offeredJobs, $this->indexOfOfferedJob($aOfferedJob), 1);
      array_splice($this->offeredJobs, $index, 0, array($aOfferedJob));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addOfferedJobAt($aOfferedJob, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfCurrentJobs()
  {
    return 0;
  }

  public static function maximumNumberOfCurrentJobs()
  {
    return 3;
  }

  public function addCurrentJob($aCurrentJob)
  {
    $wasAdded = false;
    if ($this->indexOfCurrentJob($aCurrentJob) !== -1) { return false; }
    if ($this->numberOfCurrentJobs() >= self::maximumNumberOfCurrentJobs())
    {
      return $wasAdded;
    }

    $this->currentJobs[] = $aCurrentJob;
    if ($aCurrentJob->indexOfEmployee($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aCurrentJob->addEmployee($this);
      if (!$wasAdded)
      {
        array_pop($this->currentJobs);
      }
    }
    return $wasAdded;
  }

  public function removeCurrentJob($aCurrentJob)
  {
    $wasRemoved = false;
    if ($this->indexOfCurrentJob($aCurrentJob) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfCurrentJob($aCurrentJob);
    unset($this->currentJobs[$oldIndex]);
    if ($aCurrentJob->indexOfEmployee($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aCurrentJob->removeEmployee($this);
      if (!$wasRemoved)
      {
        $this->currentJobs[$oldIndex] = $aCurrentJob;
        ksort($this->currentJobs);
      }
    }
    $this->currentJobs = array_values($this->currentJobs);
    return $wasRemoved;
  }

  public function setCurrentJobs($newCurrentJobs)
  {
    $wasSet = false;
    $verifiedCurrentJobs = array();
    foreach ($newCurrentJobs as $aCurrentJob)
    {
      if (array_search($aCurrentJob,$verifiedCurrentJobs) !== false)
      {
        continue;
      }
      $verifiedCurrentJobs[] = $aCurrentJob;
    }

    if (count($verifiedCurrentJobs) != count($newCurrentJobs) || count($verifiedCurrentJobs) > self::maximumNumberOfCurrentJobs())
    {
      return $wasSet;
    }

    $oldCurrentJobs = $this->currentJobs;
    $this->currentJobs = array();
    foreach ($verifiedCurrentJobs as $aNewCurrentJob)
    {
      $this->currentJobs[] = $aNewCurrentJob;
      $removeIndex = array_search($aNewCurrentJob,$oldCurrentJobs);
      if ($removeIndex !== false)
      {
        unset($oldCurrentJobs[$removeIndex]);
        $oldCurrentJobs = array_values($oldCurrentJobs);
      }
      else
      {
        $aNewCurrentJob->addEmployee($this);
      }
    }

    foreach ($oldCurrentJobs as $anOldCurrentJob)
    {
      $anOldCurrentJob->removeEmployee($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function addCurrentJobAt($aCurrentJob, $index)
  {  
    $wasAdded = false;
    if($this->addCurrentJob($aCurrentJob))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCurrentJobs()) { $index = $this->numberOfCurrentJobs() - 1; }
      array_splice($this->currentJobs, $this->indexOfCurrentJob($aCurrentJob), 1);
      array_splice($this->currentJobs, $index, 0, array($aCurrentJob));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveCurrentJobAt($aCurrentJob, $index)
  {
    $wasAdded = false;
    if($this->indexOfCurrentJob($aCurrentJob) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCurrentJobs()) { $index = $this->numberOfCurrentJobs() - 1; }
      array_splice($this->currentJobs, $this->indexOfCurrentJob($aCurrentJob), 1);
      array_splice($this->currentJobs, $index, 0, array($aCurrentJob));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addCurrentJobAt($aCurrentJob, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfReviewText()
  {
    return 0;
  }

  public function addReviewTextVia($aContent, $aReviewedJob, $aReviewer)
  {
    return new Review($aContent, $this, $aReviewedJob, $aReviewer);
  }

  public function addReviewText($aReviewText)
  {
    $wasAdded = false;
    if ($this->indexOfReviewText($aReviewText) !== -1) { return false; }
    $existingReviewee = $aReviewText->getReviewee();
    $isNewReviewee = $existingReviewee != null && $this !== $existingReviewee;
    if ($isNewReviewee)
    {
      $aReviewText->setReviewee($this);
    }
    else
    {
      $this->reviewText[] = $aReviewText;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeReviewText($aReviewText)
  {
    $wasRemoved = false;
    //Unable to remove aReviewText, as it must always have a reviewee
    if ($this !== $aReviewText->getReviewee())
    {
      unset($this->reviewText[$this->indexOfReviewText($aReviewText)]);
      $this->reviewText = array_values($this->reviewText);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addReviewTextAt($aReviewText, $index)
  {  
    $wasAdded = false;
    if($this->addReviewText($aReviewText))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfReviewText()) { $index = $this->numberOfReviewText() - 1; }
      array_splice($this->reviewText, $this->indexOfReviewText($aReviewText), 1);
      array_splice($this->reviewText, $index, 0, array($aReviewText));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveReviewTextAt($aReviewText, $index)
  {
    $wasAdded = false;
    if($this->indexOfReviewText($aReviewText) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfReviewText()) { $index = $this->numberOfReviewText() - 1; }
      array_splice($this->reviewText, $this->indexOfReviewText($aReviewText), 1);
      array_splice($this->reviewText, $index, 0, array($aReviewText));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addReviewTextAt($aReviewText, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    unset(Student::$studentsByStudentID[(string)$this->getStudentID()]);
    unset(Student::$studentsByEmail[(string)$this->getEmail()]);
    $copyOfPreviousJobExperiences = $this->previousJobExperiences;
    $this->previousJobExperiences = array();
    foreach ($copyOfPreviousJobExperiences as $aPreviousJobExperience)
    {
      $aPreviousJobExperience->removePreviousWorker($this);
    }
    $copyOfJobsAppliedTo = $this->jobsAppliedTo;
    $this->jobsAppliedTo = array();
    foreach ($copyOfJobsAppliedTo as $aJobsAppliedTo)
    {
      $aJobsAppliedTo->removeApplicant($this);
    }
    $copyOfOfferedJobs = $this->offeredJobs;
    $this->offeredJobs = array();
    foreach ($copyOfOfferedJobs as $aOfferedJob)
    {
      $aOfferedJob->removeOfferReceiver($this);
    }
    $copyOfCurrentJobs = $this->currentJobs;
    $this->currentJobs = array();
    foreach ($copyOfCurrentJobs as $aCurrentJob)
    {
      $aCurrentJob->removeEmployee($this);
    }
    foreach ($this->reviewText as $aReviewText)
    {
      $aReviewText->delete();
    }
  }

}
?>
