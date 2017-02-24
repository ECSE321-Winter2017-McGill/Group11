<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Department
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Department Associations
  private $allJobs;
  private $allCourses;
  private $allInstructors;
  private $allStudents;
  private $allReviews;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->allJobs = array();
    $this->allCourses = array();
    $this->allInstructors = array();
    $this->allStudents = array();
    $this->allReviews = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getAllJob_index($index)
  {
    $aAllJob = $this->allJobs[$index];
    return $aAllJob;
  }

  public function getAllJobs()
  {
    $newAllJobs = $this->allJobs;
    return $newAllJobs;
  }

  public function numberOfAllJobs()
  {
    $number = count($this->allJobs);
    return $number;
  }

  public function hasAllJobs()
  {
    $has = $this->numberOfAllJobs() > 0;
    return $has;
  }

  public function indexOfAllJob($aAllJob)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->allJobs as $allJob)
    {
      if ($allJob->equals($aAllJob))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getAllCourse_index($index)
  {
    $aAllCourse = $this->allCourses[$index];
    return $aAllCourse;
  }

  public function getAllCourses()
  {
    $newAllCourses = $this->allCourses;
    return $newAllCourses;
  }

  public function numberOfAllCourses()
  {
    $number = count($this->allCourses);
    return $number;
  }

  public function hasAllCourses()
  {
    $has = $this->numberOfAllCourses() > 0;
    return $has;
  }

  public function indexOfAllCourse($aAllCourse)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->allCourses as $allCourse)
    {
      if ($allCourse->equals($aAllCourse))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getAllInstructor_index($index)
  {
    $aAllInstructor = $this->allInstructors[$index];
    return $aAllInstructor;
  }

  public function getAllInstructors()
  {
    $newAllInstructors = $this->allInstructors;
    return $newAllInstructors;
  }

  public function numberOfAllInstructors()
  {
    $number = count($this->allInstructors);
    return $number;
  }

  public function hasAllInstructors()
  {
    $has = $this->numberOfAllInstructors() > 0;
    return $has;
  }

  public function indexOfAllInstructor($aAllInstructor)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->allInstructors as $allInstructor)
    {
      if ($allInstructor->equals($aAllInstructor))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getAllStudent_index($index)
  {
    $aAllStudent = $this->allStudents[$index];
    return $aAllStudent;
  }

  public function getAllStudents()
  {
    $newAllStudents = $this->allStudents;
    return $newAllStudents;
  }

  public function numberOfAllStudents()
  {
    $number = count($this->allStudents);
    return $number;
  }

  public function hasAllStudents()
  {
    $has = $this->numberOfAllStudents() > 0;
    return $has;
  }

  public function indexOfAllStudent($aAllStudent)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->allStudents as $allStudent)
    {
      if ($allStudent->equals($aAllStudent))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getAllReview_index($index)
  {
    $aAllReview = $this->allReviews[$index];
    return $aAllReview;
  }

  public function getAllReviews()
  {
    $newAllReviews = $this->allReviews;
    return $newAllReviews;
  }

  public function numberOfAllReviews()
  {
    $number = count($this->allReviews);
    return $number;
  }

  public function hasAllReviews()
  {
    $has = $this->numberOfAllReviews() > 0;
    return $has;
  }

  public function indexOfAllReview($aAllReview)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->allReviews as $allReview)
    {
      if ($allReview->equals($aAllReview))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfAllJobs()
  {
    return 0;
  }

  public function addAllJob($aAllJob)
  {
    $wasAdded = false;
    if ($this->indexOfAllJob($aAllJob) !== -1) { return false; }
    $this->allJobs[] = $aAllJob;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAllJob($aAllJob)
  {
    $wasRemoved = false;
    if ($this->indexOfAllJob($aAllJob) != -1)
    {
      unset($this->allJobs[$this->indexOfAllJob($aAllJob)]);
      $this->allJobs = array_values($this->allJobs);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAllJobAt($aAllJob, $index)
  {  
    $wasAdded = false;
    if($this->addAllJob($aAllJob))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllJobs()) { $index = $this->numberOfAllJobs() - 1; }
      array_splice($this->allJobs, $this->indexOfAllJob($aAllJob), 1);
      array_splice($this->allJobs, $index, 0, array($aAllJob));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAllJobAt($aAllJob, $index)
  {
    $wasAdded = false;
    if($this->indexOfAllJob($aAllJob) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllJobs()) { $index = $this->numberOfAllJobs() - 1; }
      array_splice($this->allJobs, $this->indexOfAllJob($aAllJob), 1);
      array_splice($this->allJobs, $index, 0, array($aAllJob));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAllJobAt($aAllJob, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfAllCourses()
  {
    return 0;
  }

  public function addAllCourse($aAllCourse)
  {
    $wasAdded = false;
    if ($this->indexOfAllCourse($aAllCourse) !== -1) { return false; }
    $this->allCourses[] = $aAllCourse;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAllCourse($aAllCourse)
  {
    $wasRemoved = false;
    if ($this->indexOfAllCourse($aAllCourse) != -1)
    {
      unset($this->allCourses[$this->indexOfAllCourse($aAllCourse)]);
      $this->allCourses = array_values($this->allCourses);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAllCourseAt($aAllCourse, $index)
  {  
    $wasAdded = false;
    if($this->addAllCourse($aAllCourse))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllCourses()) { $index = $this->numberOfAllCourses() - 1; }
      array_splice($this->allCourses, $this->indexOfAllCourse($aAllCourse), 1);
      array_splice($this->allCourses, $index, 0, array($aAllCourse));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAllCourseAt($aAllCourse, $index)
  {
    $wasAdded = false;
    if($this->indexOfAllCourse($aAllCourse) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllCourses()) { $index = $this->numberOfAllCourses() - 1; }
      array_splice($this->allCourses, $this->indexOfAllCourse($aAllCourse), 1);
      array_splice($this->allCourses, $index, 0, array($aAllCourse));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAllCourseAt($aAllCourse, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfAllInstructors()
  {
    return 0;
  }

  public function addAllInstructor($aAllInstructor)
  {
    $wasAdded = false;
    if ($this->indexOfAllInstructor($aAllInstructor) !== -1) { return false; }
    $this->allInstructors[] = $aAllInstructor;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAllInstructor($aAllInstructor)
  {
    $wasRemoved = false;
    if ($this->indexOfAllInstructor($aAllInstructor) != -1)
    {
      unset($this->allInstructors[$this->indexOfAllInstructor($aAllInstructor)]);
      $this->allInstructors = array_values($this->allInstructors);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAllInstructorAt($aAllInstructor, $index)
  {  
    $wasAdded = false;
    if($this->addAllInstructor($aAllInstructor))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllInstructors()) { $index = $this->numberOfAllInstructors() - 1; }
      array_splice($this->allInstructors, $this->indexOfAllInstructor($aAllInstructor), 1);
      array_splice($this->allInstructors, $index, 0, array($aAllInstructor));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAllInstructorAt($aAllInstructor, $index)
  {
    $wasAdded = false;
    if($this->indexOfAllInstructor($aAllInstructor) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllInstructors()) { $index = $this->numberOfAllInstructors() - 1; }
      array_splice($this->allInstructors, $this->indexOfAllInstructor($aAllInstructor), 1);
      array_splice($this->allInstructors, $index, 0, array($aAllInstructor));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAllInstructorAt($aAllInstructor, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfAllStudents()
  {
    return 0;
  }

  public function addAllStudent($aAllStudent)
  {
    $wasAdded = false;
    if ($this->indexOfAllStudent($aAllStudent) !== -1) { return false; }
    $this->allStudents[] = $aAllStudent;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAllStudent($aAllStudent)
  {
    $wasRemoved = false;
    if ($this->indexOfAllStudent($aAllStudent) != -1)
    {
      unset($this->allStudents[$this->indexOfAllStudent($aAllStudent)]);
      $this->allStudents = array_values($this->allStudents);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAllStudentAt($aAllStudent, $index)
  {  
    $wasAdded = false;
    if($this->addAllStudent($aAllStudent))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllStudents()) { $index = $this->numberOfAllStudents() - 1; }
      array_splice($this->allStudents, $this->indexOfAllStudent($aAllStudent), 1);
      array_splice($this->allStudents, $index, 0, array($aAllStudent));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAllStudentAt($aAllStudent, $index)
  {
    $wasAdded = false;
    if($this->indexOfAllStudent($aAllStudent) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllStudents()) { $index = $this->numberOfAllStudents() - 1; }
      array_splice($this->allStudents, $this->indexOfAllStudent($aAllStudent), 1);
      array_splice($this->allStudents, $index, 0, array($aAllStudent));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAllStudentAt($aAllStudent, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfAllReviews()
  {
    return 0;
  }

  public function addAllReview($aAllReview)
  {
    $wasAdded = false;
    if ($this->indexOfAllReview($aAllReview) !== -1) { return false; }
    $this->allReviews[] = $aAllReview;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAllReview($aAllReview)
  {
    $wasRemoved = false;
    if ($this->indexOfAllReview($aAllReview) != -1)
    {
      unset($this->allReviews[$this->indexOfAllReview($aAllReview)]);
      $this->allReviews = array_values($this->allReviews);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAllReviewAt($aAllReview, $index)
  {  
    $wasAdded = false;
    if($this->addAllReview($aAllReview))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllReviews()) { $index = $this->numberOfAllReviews() - 1; }
      array_splice($this->allReviews, $this->indexOfAllReview($aAllReview), 1);
      array_splice($this->allReviews, $index, 0, array($aAllReview));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAllReviewAt($aAllReview, $index)
  {
    $wasAdded = false;
    if($this->indexOfAllReview($aAllReview) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAllReviews()) { $index = $this->numberOfAllReviews() - 1; }
      array_splice($this->allReviews, $this->indexOfAllReview($aAllReview), 1);
      array_splice($this->allReviews, $index, 0, array($aAllReview));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAllReviewAt($aAllReview, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->allJobs = array();
    $this->allCourses = array();
    $this->allInstructors = array();
    $this->allStudents = array();
    $this->allReviews = array();
  }

}
?>
