/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.project.model;
import java.util.*;

// line 29 "../../../../../ECSE321UmpleCode.ump"
public class Instructor
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Instructor> instructorsByInstructorID = new HashMap<Integer, Instructor>();
  private static Map<String, Instructor> instructorsByEmail = new HashMap<String, Instructor>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Attributes
  private String name;
  private int instructorID;
  private String email;

  //Instructor Associations
  private List<Course> courses;
  private List<Review> reviewText;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Instructor(String aName, int aInstructorID, String aEmail)
  {
    name = aName;
    if (!setInstructorID(aInstructorID))
    {
      throw new RuntimeException("Cannot create due to duplicate instructorID");
    }
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email");
    }
    courses = new ArrayList<Course>();
    reviewText = new ArrayList<Review>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setInstructorID(int aInstructorID)
  {
    boolean wasSet = false;
    Integer anOldInstructorID = getInstructorID();
    if (hasWithInstructorID(aInstructorID)) {
      return wasSet;
    }
    instructorID = aInstructorID;
    wasSet = true;
    if (anOldInstructorID != null) {
      instructorsByInstructorID.remove(anOldInstructorID);
    }
    instructorsByInstructorID.put(aInstructorID, this);
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      instructorsByEmail.remove(anOldEmail);
    }
    instructorsByEmail.put(aEmail, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getInstructorID()
  {
    return instructorID;
  }

  public static Instructor getWithInstructorID(int aInstructorID)
  {
    return instructorsByInstructorID.get(aInstructorID);
  }

  public static boolean hasWithInstructorID(int aInstructorID)
  {
    return getWithInstructorID(aInstructorID) != null;
  }

  public String getEmail()
  {
    return email;
  }

  public static Instructor getWithEmail(String aEmail)
  {
    return instructorsByEmail.get(aEmail);
  }

  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public Course getCourse(int index)
  {
    Course aCourse = courses.get(index);
    return aCourse;
  }

  public List<Course> getCourses()
  {
    List<Course> newCourses = Collections.unmodifiableList(courses);
    return newCourses;
  }

  public int numberOfCourses()
  {
    int number = courses.size();
    return number;
  }

  public boolean hasCourses()
  {
    boolean has = courses.size() > 0;
    return has;
  }

  public int indexOfCourse(Course aCourse)
  {
    int index = courses.indexOf(aCourse);
    return index;
  }

  public Review getReviewText(int index)
  {
    Review aReviewText = reviewText.get(index);
    return aReviewText;
  }

  public List<Review> getReviewText()
  {
    List<Review> newReviewText = Collections.unmodifiableList(reviewText);
    return newReviewText;
  }

  public int numberOfReviewText()
  {
    int number = reviewText.size();
    return number;
  }

  public boolean hasReviewText()
  {
    boolean has = reviewText.size() > 0;
    return has;
  }

  public int indexOfReviewText(Review aReviewText)
  {
    int index = reviewText.indexOf(aReviewText);
    return index;
  }

  public static int minimumNumberOfCourses()
  {
    return 0;
  }

  public boolean addCourse(Course aCourse)
  {
    boolean wasAdded = false;
    if (courses.contains(aCourse)) { return false; }
    courses.add(aCourse);
    if (aCourse.indexOfInstructor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCourse.addInstructor(this);
      if (!wasAdded)
      {
        courses.remove(aCourse);
      }
    }
    return wasAdded;
  }

  public boolean removeCourse(Course aCourse)
  {
    boolean wasRemoved = false;
    if (!courses.contains(aCourse))
    {
      return wasRemoved;
    }

    int oldIndex = courses.indexOf(aCourse);
    courses.remove(oldIndex);
    if (aCourse.indexOfInstructor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCourse.removeInstructor(this);
      if (!wasRemoved)
      {
        courses.add(oldIndex,aCourse);
      }
    }
    return wasRemoved;
  }

  public boolean addCourseAt(Course aCourse, int index)
  {  
    boolean wasAdded = false;
    if(addCourse(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseAt(Course aCourse, int index)
  {
    boolean wasAdded = false;
    if(courses.contains(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseAt(aCourse, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfReviewText()
  {
    return 0;
  }

  public Review addReviewText(String aContent, int aAssociatedJobID, Student aReviewee)
  {
    return new Review(aContent, aAssociatedJobID, aReviewee, this);
  }

  public boolean addReviewText(Review aReviewText)
  {
    boolean wasAdded = false;
    if (reviewText.contains(aReviewText)) { return false; }
    Instructor existingReviewer = aReviewText.getReviewer();
    boolean isNewReviewer = existingReviewer != null && !this.equals(existingReviewer);
    if (isNewReviewer)
    {
      aReviewText.setReviewer(this);
    }
    else
    {
      reviewText.add(aReviewText);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReviewText(Review aReviewText)
  {
    boolean wasRemoved = false;
    //Unable to remove aReviewText, as it must always have a reviewer
    if (!this.equals(aReviewText.getReviewer()))
    {
      reviewText.remove(aReviewText);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addReviewTextAt(Review aReviewText, int index)
  {  
    boolean wasAdded = false;
    if(addReviewText(aReviewText))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReviewText()) { index = numberOfReviewText() - 1; }
      reviewText.remove(aReviewText);
      reviewText.add(index, aReviewText);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReviewTextAt(Review aReviewText, int index)
  {
    boolean wasAdded = false;
    if(reviewText.contains(aReviewText))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReviewText()) { index = numberOfReviewText() - 1; }
      reviewText.remove(aReviewText);
      reviewText.add(index, aReviewText);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReviewTextAt(aReviewText, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    instructorsByInstructorID.remove(getInstructorID());
    instructorsByEmail.remove(getEmail());
    ArrayList<Course> copyOfCourses = new ArrayList<Course>(courses);
    courses.clear();
    for(Course aCourse : copyOfCourses)
    {
      if (aCourse.numberOfInstructors() <= Course.minimumNumberOfInstructors())
      {
        aCourse.delete();
      }
      else
      {
        aCourse.removeInstructor(this);
      }
    }
    for(int i=reviewText.size(); i > 0; i--)
    {
      Review aReviewText = reviewText.get(i - 1);
      aReviewText.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "instructorID" + ":" + getInstructorID()+ "," +
            "email" + ":" + getEmail()+ "]"
     + outputString;
  }
}