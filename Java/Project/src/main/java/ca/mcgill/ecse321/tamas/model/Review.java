/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.tamas.model;

// line 76 "../../../../../ECSE321UmpleCode.ump"
public class Review
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Review Attributes
  private String content;
  private int associatedJobID;

  //Review Associations
  private Student reviewee;
  private Instructor reviewer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Review(String aContent, int aAssociatedJobID, Student aReviewee, Instructor aReviewer)
  {
    content = aContent;
    associatedJobID = aAssociatedJobID;
    boolean didAddReviewee = setReviewee(aReviewee);
    if (!didAddReviewee)
    {
      throw new RuntimeException("Unable to create reviewText due to reviewee");
    }
    boolean didAddReviewer = setReviewer(aReviewer);
    if (!didAddReviewer)
    {
      throw new RuntimeException("Unable to create reviewText due to reviewer");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setContent(String aContent)
  {
    boolean wasSet = false;
    content = aContent;
    wasSet = true;
    return wasSet;
  }

  public boolean setAssociatedJobID(int aAssociatedJobID)
  {
    boolean wasSet = false;
    associatedJobID = aAssociatedJobID;
    wasSet = true;
    return wasSet;
  }

  public String getContent()
  {
    return content;
  }

  public int getAssociatedJobID()
  {
    return associatedJobID;
  }

  public Student getReviewee()
  {
    return reviewee;
  }

  public Instructor getReviewer()
  {
    return reviewer;
  }

  public boolean setReviewee(Student aReviewee)
  {
    boolean wasSet = false;
    if (aReviewee == null)
    {
      return wasSet;
    }

    Student existingReviewee = reviewee;
    reviewee = aReviewee;
    if (existingReviewee != null && !existingReviewee.equals(aReviewee))
    {
      existingReviewee.removeReviewText(this);
    }
    reviewee.addReviewText(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setReviewer(Instructor aReviewer)
  {
    boolean wasSet = false;
    if (aReviewer == null)
    {
      return wasSet;
    }

    Instructor existingReviewer = reviewer;
    reviewer = aReviewer;
    if (existingReviewer != null && !existingReviewer.equals(aReviewer))
    {
      existingReviewer.removeReviewText(this);
    }
    reviewer.addReviewText(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Student placeholderReviewee = reviewee;
    this.reviewee = null;
    placeholderReviewee.removeReviewText(this);
    Instructor placeholderReviewer = reviewer;
    this.reviewer = null;
    placeholderReviewer.removeReviewText(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "content" + ":" + getContent()+ "," +
            "associatedJobID" + ":" + getAssociatedJobID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reviewee = "+(getReviewee()!=null?Integer.toHexString(System.identityHashCode(getReviewee())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "reviewer = "+(getReviewer()!=null?Integer.toHexString(System.identityHashCode(getReviewer())):"null")
     + outputString;
  }
}