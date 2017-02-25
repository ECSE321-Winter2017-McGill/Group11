<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Review
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Review Attributes
  private $content;

  //Review Associations
  private $reviewee;
  private $reviewedJob;
  private $reviewer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aContent, $aReviewee, $aReviewedJob, $aReviewer)
  {
    $this->content = $aContent;
    $didAddReviewee = $this->setReviewee($aReviewee);
    if (!$didAddReviewee)
    {
      throw new Exception("Unable to create reviewText due to reviewee");
    }
    if (!$this->setReviewedJob($aReviewedJob))
    {
      throw new Exception("Unable to create Review due to aReviewedJob");
    }
    $didAddReviewer = $this->setReviewer($aReviewer);
    if (!$didAddReviewer)
    {
      throw new Exception("Unable to create reviewText due to reviewer");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setContent($aContent)
  {
    $wasSet = false;
    $this->content = $aContent;
    $wasSet = true;
    return $wasSet;
  }

  public function getContent()
  {
    return $this->content;
  }

  public function getReviewee()
  {
    return $this->reviewee;
  }

  public function getReviewedJob()
  {
    return $this->reviewedJob;
  }

  public function getReviewer()
  {
    return $this->reviewer;
  }

  public function setReviewee($aReviewee)
  {
    $wasSet = false;
    if ($aReviewee == null)
    {
      return $wasSet;
    }
    
    $existingReviewee = $this->reviewee;
    $this->reviewee = $aReviewee;
    if ($existingReviewee != null && $existingReviewee != $aReviewee)
    {
      $existingReviewee->removeReviewText($this);
    }
    $this->reviewee->addReviewText($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setReviewedJob($aNewReviewedJob)
  {
    $wasSet = false;
    if ($aNewReviewedJob != null)
    {
      $this->reviewedJob = $aNewReviewedJob;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function setReviewer($aReviewer)
  {
    $wasSet = false;
    if ($aReviewer == null)
    {
      return $wasSet;
    }
    
    $existingReviewer = $this->reviewer;
    $this->reviewer = $aReviewer;
    if ($existingReviewer != null && $existingReviewer != $aReviewer)
    {
      $existingReviewer->removeReviewText($this);
    }
    $this->reviewer->addReviewText($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderReviewee = $this->reviewee;
    $this->reviewee = null;
    $placeholderReviewee->removeReviewText($this);
    $this->reviewedJob = null;
    $placeholderReviewer = $this->reviewer;
    $this->reviewer = null;
    $placeholderReviewer->removeReviewText($this);
  }

}
?>