/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.project.model;
import java.util.*;
import java.sql.Date;

// line 40 "../../../../../ECSE321UmpleCode.ump"
public class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private String code;
  private String name;
  private String semester;
  private int numberOfCredits;
  private int numberOfLabs;
  private int numberOfTutorials;
  private int numberOfHours;
  private int studentsEnrolled;
  private int tasNeeded;
  private int gradersNeeded;
  private int taHourlyRate;
  private int graderHourlyRate;
  private int budget;

  //Course Associations
  private List<Job> jobs;
  private List<Instructor> instructors;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course(String aCode, String aName, String aSemester, int aNumberOfCredits, int aNumberOfLabs, int aNumberOfTutorials, int aNumberOfHours, int aStudentsEnrolled, int aTasNeeded, int aGradersNeeded, int aTaHourlyRate, int aGraderHourlyRate, int aBudget, Instructor... allInstructors)
  {
    code = aCode;
    name = aName;
    semester = aSemester;
    numberOfCredits = aNumberOfCredits;
    numberOfLabs = aNumberOfLabs;
    numberOfTutorials = aNumberOfTutorials;
    numberOfHours = aNumberOfHours;
    studentsEnrolled = aStudentsEnrolled;
    tasNeeded = aTasNeeded;
    gradersNeeded = aGradersNeeded;
    taHourlyRate = aTaHourlyRate;
    graderHourlyRate = aGraderHourlyRate;
    budget = aBudget;
    jobs = new ArrayList<Job>();
    instructors = new ArrayList<Instructor>();
    boolean didAddInstructors = setInstructors(allInstructors);
    if (!didAddInstructors)
    {
      throw new RuntimeException("Unable to create Course, must have at least 1 instructors");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCode(String aCode)
  {
    boolean wasSet = false;
    code = aCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setSemester(String aSemester)
  {
    boolean wasSet = false;
    semester = aSemester;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfCredits(int aNumberOfCredits)
  {
    boolean wasSet = false;
    numberOfCredits = aNumberOfCredits;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfLabs(int aNumberOfLabs)
  {
    boolean wasSet = false;
    numberOfLabs = aNumberOfLabs;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfTutorials(int aNumberOfTutorials)
  {
    boolean wasSet = false;
    numberOfTutorials = aNumberOfTutorials;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfHours(int aNumberOfHours)
  {
    boolean wasSet = false;
    numberOfHours = aNumberOfHours;
    wasSet = true;
    return wasSet;
  }

  public boolean setStudentsEnrolled(int aStudentsEnrolled)
  {
    boolean wasSet = false;
    studentsEnrolled = aStudentsEnrolled;
    wasSet = true;
    return wasSet;
  }

  public boolean setTasNeeded(int aTasNeeded)
  {
    boolean wasSet = false;
    tasNeeded = aTasNeeded;
    wasSet = true;
    return wasSet;
  }

  public boolean setGradersNeeded(int aGradersNeeded)
  {
    boolean wasSet = false;
    gradersNeeded = aGradersNeeded;
    wasSet = true;
    return wasSet;
  }

  public boolean setTaHourlyRate(int aTaHourlyRate)
  {
    boolean wasSet = false;
    taHourlyRate = aTaHourlyRate;
    wasSet = true;
    return wasSet;
  }

  public boolean setGraderHourlyRate(int aGraderHourlyRate)
  {
    boolean wasSet = false;
    graderHourlyRate = aGraderHourlyRate;
    wasSet = true;
    return wasSet;
  }

  public boolean setBudget(int aBudget)
  {
    boolean wasSet = false;
    budget = aBudget;
    wasSet = true;
    return wasSet;
  }

  public String getCode()
  {
    return code;
  }

  public String getName()
  {
    return name;
  }

  public String getSemester()
  {
    return semester;
  }

  public int getNumberOfCredits()
  {
    return numberOfCredits;
  }

  public int getNumberOfLabs()
  {
    return numberOfLabs;
  }

  public int getNumberOfTutorials()
  {
    return numberOfTutorials;
  }

  public int getNumberOfHours()
  {
    return numberOfHours;
  }

  public int getStudentsEnrolled()
  {
    return studentsEnrolled;
  }

  public int getTasNeeded()
  {
    return tasNeeded;
  }

  public int getGradersNeeded()
  {
    return gradersNeeded;
  }

  public int getTaHourlyRate()
  {
    return taHourlyRate;
  }

  public int getGraderHourlyRate()
  {
    return graderHourlyRate;
  }

  public int getBudget()
  {
    return budget;
  }

  public Job getJob(int index)
  {
    Job aJob = jobs.get(index);
    return aJob;
  }

  public List<Job> getJobs()
  {
    List<Job> newJobs = Collections.unmodifiableList(jobs);
    return newJobs;
  }

  public int numberOfJobs()
  {
    int number = jobs.size();
    return number;
  }

  public boolean hasJobs()
  {
    boolean has = jobs.size() > 0;
    return has;
  }

  public int indexOfJob(Job aJob)
  {
    int index = jobs.indexOf(aJob);
    return index;
  }

  public Instructor getInstructor(int index)
  {
    Instructor aInstructor = instructors.get(index);
    return aInstructor;
  }

  public List<Instructor> getInstructors()
  {
    List<Instructor> newInstructors = Collections.unmodifiableList(instructors);
    return newInstructors;
  }

  public int numberOfInstructors()
  {
    int number = instructors.size();
    return number;
  }

  public boolean hasInstructors()
  {
    boolean has = instructors.size() > 0;
    return has;
  }

  public int indexOfInstructor(Instructor aInstructor)
  {
    int index = instructors.indexOf(aInstructor);
    return index;
  }

  public static int minimumNumberOfJobs()
  {
    return 0;
  }

  public Job addJob(PositionType aPosType)
  {
    return new Job(aPosType, this);
  }

  public boolean addJob(Job aJob)
  {
    boolean wasAdded = false;
    if (jobs.contains(aJob)) { return false; }
    Course existingCorrespondingCourse = aJob.getCorrespondingCourse();
    boolean isNewCorrespondingCourse = existingCorrespondingCourse != null && !this.equals(existingCorrespondingCourse);
    if (isNewCorrespondingCourse)
    {
      aJob.setCorrespondingCourse(this);
    }
    else
    {
      jobs.add(aJob);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeJob(Job aJob)
  {
    boolean wasRemoved = false;
    //Unable to remove aJob, as it must always have a correspondingCourse
    if (!this.equals(aJob.getCorrespondingCourse()))
    {
      jobs.remove(aJob);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addJobAt(Job aJob, int index)
  {  
    boolean wasAdded = false;
    if(addJob(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobAt(Job aJob, int index)
  {
    boolean wasAdded = false;
    if(jobs.contains(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobAt(aJob, index);
    }
    return wasAdded;
  }

  public boolean isNumberOfInstructorsValid()
  {
    boolean isValid = numberOfInstructors() >= minimumNumberOfInstructors();
    return isValid;
  }

  public static int minimumNumberOfInstructors()
  {
    return 1;
  }

  public boolean addInstructor(Instructor aInstructor)
  {
    boolean wasAdded = false;
    if (instructors.contains(aInstructor)) { return false; }
    instructors.add(aInstructor);
    if (aInstructor.indexOfCourse(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aInstructor.addCourse(this);
      if (!wasAdded)
      {
        instructors.remove(aInstructor);
      }
    }
    return wasAdded;
  }

  public boolean removeInstructor(Instructor aInstructor)
  {
    boolean wasRemoved = false;
    if (!instructors.contains(aInstructor))
    {
      return wasRemoved;
    }

    if (numberOfInstructors() <= minimumNumberOfInstructors())
    {
      return wasRemoved;
    }

    int oldIndex = instructors.indexOf(aInstructor);
    instructors.remove(oldIndex);
    if (aInstructor.indexOfCourse(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aInstructor.removeCourse(this);
      if (!wasRemoved)
      {
        instructors.add(oldIndex,aInstructor);
      }
    }
    return wasRemoved;
  }

  public boolean setInstructors(Instructor... newInstructors)
  {
    boolean wasSet = false;
    ArrayList<Instructor> verifiedInstructors = new ArrayList<Instructor>();
    for (Instructor aInstructor : newInstructors)
    {
      if (verifiedInstructors.contains(aInstructor))
      {
        continue;
      }
      verifiedInstructors.add(aInstructor);
    }

    if (verifiedInstructors.size() != newInstructors.length || verifiedInstructors.size() < minimumNumberOfInstructors())
    {
      return wasSet;
    }

    ArrayList<Instructor> oldInstructors = new ArrayList<Instructor>(instructors);
    instructors.clear();
    for (Instructor aNewInstructor : verifiedInstructors)
    {
      instructors.add(aNewInstructor);
      if (oldInstructors.contains(aNewInstructor))
      {
        oldInstructors.remove(aNewInstructor);
      }
      else
      {
        aNewInstructor.addCourse(this);
      }
    }

    for (Instructor anOldInstructor : oldInstructors)
    {
      anOldInstructor.removeCourse(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addInstructorAt(Instructor aInstructor, int index)
  {  
    boolean wasAdded = false;
    if(addInstructor(aInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInstructors()) { index = numberOfInstructors() - 1; }
      instructors.remove(aInstructor);
      instructors.add(index, aInstructor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInstructorAt(Instructor aInstructor, int index)
  {
    boolean wasAdded = false;
    if(instructors.contains(aInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInstructors()) { index = numberOfInstructors() - 1; }
      instructors.remove(aInstructor);
      instructors.add(index, aInstructor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInstructorAt(aInstructor, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=jobs.size(); i > 0; i--)
    {
      Job aJob = jobs.get(i - 1);
      aJob.delete();
    }
    ArrayList<Instructor> copyOfInstructors = new ArrayList<Instructor>(instructors);
    instructors.clear();
    for(Instructor aInstructor : copyOfInstructors)
    {
      aInstructor.removeCourse(this);
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "code" + ":" + getCode()+ "," +
            "name" + ":" + getName()+ "," +
            "semester" + ":" + getSemester()+ "," +
            "numberOfCredits" + ":" + getNumberOfCredits()+ "," +
            "numberOfLabs" + ":" + getNumberOfLabs()+ "," +
            "numberOfTutorials" + ":" + getNumberOfTutorials()+ "," +
            "numberOfHours" + ":" + getNumberOfHours()+ "," +
            "studentsEnrolled" + ":" + getStudentsEnrolled()+ "," +
            "tasNeeded" + ":" + getTasNeeded()+ "," +
            "gradersNeeded" + ":" + getGradersNeeded()+ "," +
            "taHourlyRate" + ":" + getTaHourlyRate()+ "," +
            "graderHourlyRate" + ":" + getGraderHourlyRate()+ "," +
            "budget" + ":" + getBudget()+ "]"
     + outputString;
  }
}