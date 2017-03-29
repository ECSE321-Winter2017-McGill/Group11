/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.tamas.model;
import java.util.*;
import java.sql.Date;

// line 3 "../../../../../ECSE321UmpleCode.ump"
public class Department
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Department Associations
  private List<Job> allJobs;
  private List<Course> allCourses;
  private List<Instructor> allInstructors;
  private List<Student> allStudents;
  private List<Review> allReviews;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Department()
  {
    allJobs = new ArrayList<Job>();
    allCourses = new ArrayList<Course>();
    allInstructors = new ArrayList<Instructor>();
    allStudents = new ArrayList<Student>();
    allReviews = new ArrayList<Review>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Job getAllJob(int index)
  {
    Job aAllJob = allJobs.get(index);
    return aAllJob;
  }

  public List<Job> getAllJobs()
  {
    List<Job> newAllJobs = Collections.unmodifiableList(allJobs);
    return newAllJobs;
  }

  public int numberOfAllJobs()
  {
    int number = allJobs.size();
    return number;
  }

  public boolean hasAllJobs()
  {
    boolean has = allJobs.size() > 0;
    return has;
  }

  public int indexOfAllJob(Job aAllJob)
  {
    int index = allJobs.indexOf(aAllJob);
    return index;
  }

  public Course getAllCourse(int index)
  {
    Course aAllCourse = allCourses.get(index);
    return aAllCourse;
  }

  public List<Course> getAllCourses()
  {
    List<Course> newAllCourses = Collections.unmodifiableList(allCourses);
    return newAllCourses;
  }

  public int numberOfAllCourses()
  {
    int number = allCourses.size();
    return number;
  }

  public boolean hasAllCourses()
  {
    boolean has = allCourses.size() > 0;
    return has;
  }

  public int indexOfAllCourse(Course aAllCourse)
  {
    int index = allCourses.indexOf(aAllCourse);
    return index;
  }

  public Instructor getAllInstructor(int index)
  {
    Instructor aAllInstructor = allInstructors.get(index);
    return aAllInstructor;
  }

  public List<Instructor> getAllInstructors()
  {
    List<Instructor> newAllInstructors = Collections.unmodifiableList(allInstructors);
    return newAllInstructors;
  }

  public int numberOfAllInstructors()
  {
    int number = allInstructors.size();
    return number;
  }

  public boolean hasAllInstructors()
  {
    boolean has = allInstructors.size() > 0;
    return has;
  }

  public int indexOfAllInstructor(Instructor aAllInstructor)
  {
    int index = allInstructors.indexOf(aAllInstructor);
    return index;
  }

  public Student getAllStudent(int index)
  {
    Student aAllStudent = allStudents.get(index);
    return aAllStudent;
  }

  public List<Student> getAllStudents()
  {
    List<Student> newAllStudents = Collections.unmodifiableList(allStudents);
    return newAllStudents;
  }

  public int numberOfAllStudents()
  {
    int number = allStudents.size();
    return number;
  }

  public boolean hasAllStudents()
  {
    boolean has = allStudents.size() > 0;
    return has;
  }

  public int indexOfAllStudent(Student aAllStudent)
  {
    int index = allStudents.indexOf(aAllStudent);
    return index;
  }

  public Review getAllReview(int index)
  {
    Review aAllReview = allReviews.get(index);
    return aAllReview;
  }

  public List<Review> getAllReviews()
  {
    List<Review> newAllReviews = Collections.unmodifiableList(allReviews);
    return newAllReviews;
  }

  public int numberOfAllReviews()
  {
    int number = allReviews.size();
    return number;
  }

  public boolean hasAllReviews()
  {
    boolean has = allReviews.size() > 0;
    return has;
  }

  public int indexOfAllReview(Review aAllReview)
  {
    int index = allReviews.indexOf(aAllReview);
    return index;
  }

  public static int minimumNumberOfAllJobs()
  {
    return 0;
  }

  public boolean addAllJob(Job aAllJob)
  {
    boolean wasAdded = false;
    if (allJobs.contains(aAllJob)) { return false; }
    allJobs.add(aAllJob);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllJob(Job aAllJob)
  {
    boolean wasRemoved = false;
    if (allJobs.contains(aAllJob))
    {
      allJobs.remove(aAllJob);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAllJobAt(Job aAllJob, int index)
  {  
    boolean wasAdded = false;
    if(addAllJob(aAllJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllJobs()) { index = numberOfAllJobs() - 1; }
      allJobs.remove(aAllJob);
      allJobs.add(index, aAllJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllJobAt(Job aAllJob, int index)
  {
    boolean wasAdded = false;
    if(allJobs.contains(aAllJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllJobs()) { index = numberOfAllJobs() - 1; }
      allJobs.remove(aAllJob);
      allJobs.add(index, aAllJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllJobAt(aAllJob, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfAllCourses()
  {
    return 0;
  }

  public boolean addAllCourse(Course aAllCourse)
  {
    boolean wasAdded = false;
    if (allCourses.contains(aAllCourse)) { return false; }
    allCourses.add(aAllCourse);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllCourse(Course aAllCourse)
  {
    boolean wasRemoved = false;
    if (allCourses.contains(aAllCourse))
    {
      allCourses.remove(aAllCourse);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAllCourseAt(Course aAllCourse, int index)
  {  
    boolean wasAdded = false;
    if(addAllCourse(aAllCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllCourses()) { index = numberOfAllCourses() - 1; }
      allCourses.remove(aAllCourse);
      allCourses.add(index, aAllCourse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllCourseAt(Course aAllCourse, int index)
  {
    boolean wasAdded = false;
    if(allCourses.contains(aAllCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllCourses()) { index = numberOfAllCourses() - 1; }
      allCourses.remove(aAllCourse);
      allCourses.add(index, aAllCourse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllCourseAt(aAllCourse, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfAllInstructors()
  {
    return 0;
  }

  public boolean addAllInstructor(Instructor aAllInstructor)
  {
    boolean wasAdded = false;
    if (allInstructors.contains(aAllInstructor)) { return false; }
    allInstructors.add(aAllInstructor);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllInstructor(Instructor aAllInstructor)
  {
    boolean wasRemoved = false;
    if (allInstructors.contains(aAllInstructor))
    {
      allInstructors.remove(aAllInstructor);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAllInstructorAt(Instructor aAllInstructor, int index)
  {  
    boolean wasAdded = false;
    if(addAllInstructor(aAllInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllInstructors()) { index = numberOfAllInstructors() - 1; }
      allInstructors.remove(aAllInstructor);
      allInstructors.add(index, aAllInstructor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllInstructorAt(Instructor aAllInstructor, int index)
  {
    boolean wasAdded = false;
    if(allInstructors.contains(aAllInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllInstructors()) { index = numberOfAllInstructors() - 1; }
      allInstructors.remove(aAllInstructor);
      allInstructors.add(index, aAllInstructor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllInstructorAt(aAllInstructor, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfAllStudents()
  {
    return 0;
  }

  public boolean addAllStudent(Student aAllStudent)
  {
    boolean wasAdded = false;
    if (allStudents.contains(aAllStudent)) { return false; }
    allStudents.add(aAllStudent);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllStudent(Student aAllStudent)
  {
    boolean wasRemoved = false;
    if (allStudents.contains(aAllStudent))
    {
      allStudents.remove(aAllStudent);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAllStudentAt(Student aAllStudent, int index)
  {  
    boolean wasAdded = false;
    if(addAllStudent(aAllStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllStudents()) { index = numberOfAllStudents() - 1; }
      allStudents.remove(aAllStudent);
      allStudents.add(index, aAllStudent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllStudentAt(Student aAllStudent, int index)
  {
    boolean wasAdded = false;
    if(allStudents.contains(aAllStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllStudents()) { index = numberOfAllStudents() - 1; }
      allStudents.remove(aAllStudent);
      allStudents.add(index, aAllStudent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllStudentAt(aAllStudent, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfAllReviews()
  {
    return 0;
  }

  public boolean addAllReview(Review aAllReview)
  {
    boolean wasAdded = false;
    if (allReviews.contains(aAllReview)) { return false; }
    allReviews.add(aAllReview);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllReview(Review aAllReview)
  {
    boolean wasRemoved = false;
    if (allReviews.contains(aAllReview))
    {
      allReviews.remove(aAllReview);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAllReviewAt(Review aAllReview, int index)
  {  
    boolean wasAdded = false;
    if(addAllReview(aAllReview))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllReviews()) { index = numberOfAllReviews() - 1; }
      allReviews.remove(aAllReview);
      allReviews.add(index, aAllReview);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllReviewAt(Review aAllReview, int index)
  {
    boolean wasAdded = false;
    if(allReviews.contains(aAllReview))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllReviews()) { index = numberOfAllReviews() - 1; }
      allReviews.remove(aAllReview);
      allReviews.add(index, aAllReview);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllReviewAt(aAllReview, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    allJobs.clear();
    allCourses.clear();
    allInstructors.clear();
    allStudents.clear();
    allReviews.clear();
  }

}