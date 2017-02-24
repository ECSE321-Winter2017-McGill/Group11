<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Instructor
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $instructorsByInstructorID = array();
  private static $instructorsByEmail = array();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Attributes
  private $name;
  private $instructorID;
  private $email;

  //Instructor Associations
  private $courses;
  private $reviewText;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aInstructorID, $aEmail)
  {
    $this->name = $aName;
    if (!$this->setInstructorID($aInstructorID))
    {
      throw new RuntimeException("Cannot create due to duplicate instructorID");
    }
    if (!$this->setEmail($aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email");
    }
    $this->courses = array();
    $this->reviewText = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setInstructorID($aInstructorID)
  {
    $wasSet = false;
    if (isset($this->instructorID)) {
      $anOldInstructorID = $this->getInstructorID();
    }
    if (Instructor::hasWithInstructorID($aInstructorID)) {
      return $wasSet;
    }
    $this->instructorID = $aInstructorID;
    $wasSet = true;
    if (isset($anOldInstructorID)) {
      unset(Instructor::$instructorsByInstructorID[(string)$anOldInstructorID]);
    }
    Instructor::$instructorsByInstructorID[(string)$aInstructorID] = $this;
    return $wasSet;
  }

  public function setEmail($aEmail)
  {
    $wasSet = false;
    if (isset($this->email)) {
      $anOldEmail = $this->getEmail();
    }
    if (Instructor::hasWithEmail($aEmail)) {
      return $wasSet;
    }
    $this->email = $aEmail;
    $wasSet = true;
    if (isset($anOldEmail)) {
      unset(Instructor::$instructorsByEmail[(string)$anOldEmail]);
    }
    Instructor::$instructorsByEmail[(string)$aEmail] = $this;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getInstructorID()
  {
    return $this->instructorID;
  }

  public static function getWithInstructorID($aInstructorID)
  {
    return Instructor::$instructorsByInstructorID[(string)$aInstructorID];
  }

  public static function hasWithInstructorID($aInstructorID)
  {
    return array_key_exists((string)$aInstructorID, Instructor::$instructorsByInstructorID);
  }

  public function getEmail()
  {
    return $this->email;
  }

  public static function getWithEmail($aEmail)
  {
    return Instructor::$instructorsByEmail[(string)$aEmail];
  }

  public static function hasWithEmail($aEmail)
  {
    return array_key_exists((string)$aEmail, Instructor::$instructorsByEmail);
  }

  public function getCourse_index($index)
  {
    $aCourse = $this->courses[$index];
    return $aCourse;
  }

  public function getCourses()
  {
    $newCourses = $this->courses;
    return $newCourses;
  }

  public function numberOfCourses()
  {
    $number = count($this->courses);
    return $number;
  }

  public function hasCourses()
  {
    $has = $this->numberOfCourses() > 0;
    return $has;
  }

  public function indexOfCourse($aCourse)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->courses as $course)
    {
      if ($course->equals($aCourse))
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

  public static function minimumNumberOfCourses()
  {
    return 0;
  }

  public function addCourse($aCourse)
  {
    $wasAdded = false;
    if ($this->indexOfCourse($aCourse) !== -1) { return false; }
    $this->courses[] = $aCourse;
    if ($aCourse->indexOfInstructor($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aCourse->addInstructor($this);
      if (!$wasAdded)
      {
        array_pop($this->courses);
      }
    }
    return $wasAdded;
  }

  public function removeCourse($aCourse)
  {
    $wasRemoved = false;
    if ($this->indexOfCourse($aCourse) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfCourse($aCourse);
    unset($this->courses[$oldIndex]);
    if ($aCourse->indexOfInstructor($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aCourse->removeInstructor($this);
      if (!$wasRemoved)
      {
        $this->courses[$oldIndex] = $aCourse;
        ksort($this->courses);
      }
    }
    $this->courses = array_values($this->courses);
    return $wasRemoved;
  }

  public function addCourseAt($aCourse, $index)
  {  
    $wasAdded = false;
    if($this->addCourse($aCourse))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveCourseAt($aCourse, $index)
  {
    $wasAdded = false;
    if($this->indexOfCourse($aCourse) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addCourseAt($aCourse, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfReviewText()
  {
    return 0;
  }

  public function addReviewTextVia($aContent, $aReviewee, $aReviewedJob)
  {
    return new Review($aContent, $aReviewee, $aReviewedJob, $this);
  }

  public function addReviewText($aReviewText)
  {
    $wasAdded = false;
    if ($this->indexOfReviewText($aReviewText) !== -1) { return false; }
    $existingReviewer = $aReviewText->getReviewer();
    $isNewReviewer = $existingReviewer != null && $this !== $existingReviewer;
    if ($isNewReviewer)
    {
      $aReviewText->setReviewer($this);
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
    //Unable to remove aReviewText, as it must always have a reviewer
    if ($this !== $aReviewText->getReviewer())
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
    unset(Instructor::$instructorsByInstructorID[(string)$this->getInstructorID()]);
    unset(Instructor::$instructorsByEmail[(string)$this->getEmail()]);
    $copyOfCourses = $this->courses;
    $this->courses = array();
    foreach ($copyOfCourses as $aCourse)
    {
      if ($aCourse->numberOfInstructors() <= Course::minimumNumberOfInstructors())
      {
        $aCourse->delete();
      }
      else
      {
        $aCourse->removeInstructor($this);
      }
    }
    foreach ($this->reviewText as $aReviewText)
    {
      $aReviewText->delete();
    }
  }

}
?>