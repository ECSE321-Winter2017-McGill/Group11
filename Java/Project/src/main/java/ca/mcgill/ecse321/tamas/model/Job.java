/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.tamas.model;
import java.sql.Date;
import java.util.*;

// line 60 "../../../../../ECSE321UmpleCode.ump"
public class Job
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextJobID = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Job Attributes
  private String jobDescription;
  private PositionType posType;
  private String skillsRequired;
  private String experienceRequired;
  private Date postingDeadlineDate;
  private Date offerDeadlineDate;
  private JobStatus state;

  //Autounique Attributes
  private int jobID;

  //Job Associations
  private List<Student> allocatedStudent;
  private List<Student> previousWorker;
  private List<Student> applicant;
  private List<Student> offerReceiver;
  private List<Student> employee;
  private Course correspondingCourse;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Job(PositionType aPosType, Course aCorrespondingCourse)
  {
    jobDescription = null;
    posType = aPosType;
    skillsRequired = null;
    experienceRequired = null;
    postingDeadlineDate = null;
    offerDeadlineDate = null;
    resetState();
    jobID = nextJobID++;
    allocatedStudent = new ArrayList<Student>();
    previousWorker = new ArrayList<Student>();
    applicant = new ArrayList<Student>();
    offerReceiver = new ArrayList<Student>();
    employee = new ArrayList<Student>();
    boolean didAddCorrespondingCourse = setCorrespondingCourse(aCorrespondingCourse);
    if (!didAddCorrespondingCourse)
    {
      throw new RuntimeException("Unable to create job due to correspondingCourse");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setJobDescription(String aJobDescription)
  {
    boolean wasSet = false;
    jobDescription = aJobDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setPosType(PositionType aPosType)
  {
    boolean wasSet = false;
    posType = aPosType;
    wasSet = true;
    return wasSet;
  }

  public boolean setSkillsRequired(String aSkillsRequired)
  {
    boolean wasSet = false;
    skillsRequired = aSkillsRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setExperienceRequired(String aExperienceRequired)
  {
    boolean wasSet = false;
    experienceRequired = aExperienceRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostingDeadlineDate(Date aPostingDeadlineDate)
  {
    boolean wasSet = false;
    postingDeadlineDate = aPostingDeadlineDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setOfferDeadlineDate(Date aOfferDeadlineDate)
  {
    boolean wasSet = false;
    offerDeadlineDate = aOfferDeadlineDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setState(JobStatus aState)
  {
    boolean wasSet = false;
    state = aState;
    wasSet = true;
    return wasSet;
  }

  public boolean resetState()
  {
    boolean wasReset = false;
    state = getDefaultState();
    wasReset = true;
    return wasReset;
  }

  public String getJobDescription()
  {
    return jobDescription;
  }

  public PositionType getPosType()
  {
    return posType;
  }

  public String getSkillsRequired()
  {
    return skillsRequired;
  }

  public String getExperienceRequired()
  {
    return experienceRequired;
  }

  public Date getPostingDeadlineDate()
  {
    return postingDeadlineDate;
  }

  public Date getOfferDeadlineDate()
  {
    return offerDeadlineDate;
  }

  public JobStatus getState()
  {
    return state;
  }

  public JobStatus getDefaultState()
  {
    return JobStatus.Ready;
  }

  public int getJobID()
  {
    return jobID;
  }

  public Student getAllocatedStudent(int index)
  {
    Student aAllocatedStudent = allocatedStudent.get(index);
    return aAllocatedStudent;
  }

  public List<Student> getAllocatedStudent()
  {
    List<Student> newAllocatedStudent = Collections.unmodifiableList(allocatedStudent);
    return newAllocatedStudent;
  }

  public int numberOfAllocatedStudent()
  {
    int number = allocatedStudent.size();
    return number;
  }

  public boolean hasAllocatedStudent()
  {
    boolean has = allocatedStudent.size() > 0;
    return has;
  }

  public int indexOfAllocatedStudent(Student aAllocatedStudent)
  {
    int index = allocatedStudent.indexOf(aAllocatedStudent);
    return index;
  }

  public Student getPreviousWorker(int index)
  {
    Student aPreviousWorker = previousWorker.get(index);
    return aPreviousWorker;
  }

  public List<Student> getPreviousWorker()
  {
    List<Student> newPreviousWorker = Collections.unmodifiableList(previousWorker);
    return newPreviousWorker;
  }

  public int numberOfPreviousWorker()
  {
    int number = previousWorker.size();
    return number;
  }

  public boolean hasPreviousWorker()
  {
    boolean has = previousWorker.size() > 0;
    return has;
  }

  public int indexOfPreviousWorker(Student aPreviousWorker)
  {
    int index = previousWorker.indexOf(aPreviousWorker);
    return index;
  }

  public Student getApplicant(int index)
  {
    Student aApplicant = applicant.get(index);
    return aApplicant;
  }

  public List<Student> getApplicant()
  {
    List<Student> newApplicant = Collections.unmodifiableList(applicant);
    return newApplicant;
  }

  public int numberOfApplicant()
  {
    int number = applicant.size();
    return number;
  }

  public boolean hasApplicant()
  {
    boolean has = applicant.size() > 0;
    return has;
  }

  public int indexOfApplicant(Student aApplicant)
  {
    int index = applicant.indexOf(aApplicant);
    return index;
  }

  public Student getOfferReceiver(int index)
  {
    Student aOfferReceiver = offerReceiver.get(index);
    return aOfferReceiver;
  }

  public List<Student> getOfferReceiver()
  {
    List<Student> newOfferReceiver = Collections.unmodifiableList(offerReceiver);
    return newOfferReceiver;
  }

  public int numberOfOfferReceiver()
  {
    int number = offerReceiver.size();
    return number;
  }

  public boolean hasOfferReceiver()
  {
    boolean has = offerReceiver.size() > 0;
    return has;
  }

  public int indexOfOfferReceiver(Student aOfferReceiver)
  {
    int index = offerReceiver.indexOf(aOfferReceiver);
    return index;
  }

  public Student getEmployee(int index)
  {
    Student aEmployee = employee.get(index);
    return aEmployee;
  }

  public List<Student> getEmployee()
  {
    List<Student> newEmployee = Collections.unmodifiableList(employee);
    return newEmployee;
  }

  public int numberOfEmployee()
  {
    int number = employee.size();
    return number;
  }

  public boolean hasEmployee()
  {
    boolean has = employee.size() > 0;
    return has;
  }

  public int indexOfEmployee(Student aEmployee)
  {
    int index = employee.indexOf(aEmployee);
    return index;
  }

  public Course getCorrespondingCourse()
  {
    return correspondingCourse;
  }

  public static int minimumNumberOfAllocatedStudent()
  {
    return 0;
  }

  public boolean addAllocatedStudent(Student aAllocatedStudent)
  {
    boolean wasAdded = false;
    if (allocatedStudent.contains(aAllocatedStudent)) { return false; }
    allocatedStudent.add(aAllocatedStudent);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllocatedStudent(Student aAllocatedStudent)
  {
    boolean wasRemoved = false;
    if (allocatedStudent.contains(aAllocatedStudent))
    {
      allocatedStudent.remove(aAllocatedStudent);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAllocatedStudentAt(Student aAllocatedStudent, int index)
  {  
    boolean wasAdded = false;
    if(addAllocatedStudent(aAllocatedStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllocatedStudent()) { index = numberOfAllocatedStudent() - 1; }
      allocatedStudent.remove(aAllocatedStudent);
      allocatedStudent.add(index, aAllocatedStudent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllocatedStudentAt(Student aAllocatedStudent, int index)
  {
    boolean wasAdded = false;
    if(allocatedStudent.contains(aAllocatedStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllocatedStudent()) { index = numberOfAllocatedStudent() - 1; }
      allocatedStudent.remove(aAllocatedStudent);
      allocatedStudent.add(index, aAllocatedStudent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllocatedStudentAt(aAllocatedStudent, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPreviousWorker()
  {
    return 0;
  }

  public boolean addPreviousWorker(Student aPreviousWorker)
  {
    boolean wasAdded = false;
    if (previousWorker.contains(aPreviousWorker)) { return false; }
    previousWorker.add(aPreviousWorker);
    if (aPreviousWorker.indexOfPreviousJobExperience(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPreviousWorker.addPreviousJobExperience(this);
      if (!wasAdded)
      {
        previousWorker.remove(aPreviousWorker);
      }
    }
    return wasAdded;
  }

  public boolean removePreviousWorker(Student aPreviousWorker)
  {
    boolean wasRemoved = false;
    if (!previousWorker.contains(aPreviousWorker))
    {
      return wasRemoved;
    }

    int oldIndex = previousWorker.indexOf(aPreviousWorker);
    previousWorker.remove(oldIndex);
    if (aPreviousWorker.indexOfPreviousJobExperience(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPreviousWorker.removePreviousJobExperience(this);
      if (!wasRemoved)
      {
        previousWorker.add(oldIndex,aPreviousWorker);
      }
    }
    return wasRemoved;
  }

  public boolean addPreviousWorkerAt(Student aPreviousWorker, int index)
  {  
    boolean wasAdded = false;
    if(addPreviousWorker(aPreviousWorker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPreviousWorker()) { index = numberOfPreviousWorker() - 1; }
      previousWorker.remove(aPreviousWorker);
      previousWorker.add(index, aPreviousWorker);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePreviousWorkerAt(Student aPreviousWorker, int index)
  {
    boolean wasAdded = false;
    if(previousWorker.contains(aPreviousWorker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPreviousWorker()) { index = numberOfPreviousWorker() - 1; }
      previousWorker.remove(aPreviousWorker);
      previousWorker.add(index, aPreviousWorker);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPreviousWorkerAt(aPreviousWorker, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfApplicant()
  {
    return 0;
  }

  public boolean addApplicant(Student aApplicant)
  {
    boolean wasAdded = false;
    if (applicant.contains(aApplicant)) { return false; }
    applicant.add(aApplicant);
    if (aApplicant.indexOfJobsAppliedTo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aApplicant.addJobsAppliedTo(this);
      if (!wasAdded)
      {
        applicant.remove(aApplicant);
      }
    }
    return wasAdded;
  }

  public boolean removeApplicant(Student aApplicant)
  {
    boolean wasRemoved = false;
    if (!applicant.contains(aApplicant))
    {
      return wasRemoved;
    }

    int oldIndex = applicant.indexOf(aApplicant);
    applicant.remove(oldIndex);
    if (aApplicant.indexOfJobsAppliedTo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aApplicant.removeJobsAppliedTo(this);
      if (!wasRemoved)
      {
        applicant.add(oldIndex,aApplicant);
      }
    }
    return wasRemoved;
  }

  public boolean addApplicantAt(Student aApplicant, int index)
  {  
    boolean wasAdded = false;
    if(addApplicant(aApplicant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplicant()) { index = numberOfApplicant() - 1; }
      applicant.remove(aApplicant);
      applicant.add(index, aApplicant);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveApplicantAt(Student aApplicant, int index)
  {
    boolean wasAdded = false;
    if(applicant.contains(aApplicant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplicant()) { index = numberOfApplicant() - 1; }
      applicant.remove(aApplicant);
      applicant.add(index, aApplicant);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addApplicantAt(aApplicant, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOfferReceiver()
  {
    return 0;
  }

  public boolean addOfferReceiver(Student aOfferReceiver)
  {
    boolean wasAdded = false;
    if (offerReceiver.contains(aOfferReceiver)) { return false; }
    offerReceiver.add(aOfferReceiver);
    if (aOfferReceiver.indexOfOfferedJob(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOfferReceiver.addOfferedJob(this);
      if (!wasAdded)
      {
        offerReceiver.remove(aOfferReceiver);
      }
    }
    return wasAdded;
  }

  public boolean removeOfferReceiver(Student aOfferReceiver)
  {
    boolean wasRemoved = false;
    if (!offerReceiver.contains(aOfferReceiver))
    {
      return wasRemoved;
    }

    int oldIndex = offerReceiver.indexOf(aOfferReceiver);
    offerReceiver.remove(oldIndex);
    if (aOfferReceiver.indexOfOfferedJob(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOfferReceiver.removeOfferedJob(this);
      if (!wasRemoved)
      {
        offerReceiver.add(oldIndex,aOfferReceiver);
      }
    }
    return wasRemoved;
  }

  public boolean addOfferReceiverAt(Student aOfferReceiver, int index)
  {  
    boolean wasAdded = false;
    if(addOfferReceiver(aOfferReceiver))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOfferReceiver()) { index = numberOfOfferReceiver() - 1; }
      offerReceiver.remove(aOfferReceiver);
      offerReceiver.add(index, aOfferReceiver);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOfferReceiverAt(Student aOfferReceiver, int index)
  {
    boolean wasAdded = false;
    if(offerReceiver.contains(aOfferReceiver))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOfferReceiver()) { index = numberOfOfferReceiver() - 1; }
      offerReceiver.remove(aOfferReceiver);
      offerReceiver.add(index, aOfferReceiver);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOfferReceiverAt(aOfferReceiver, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEmployee()
  {
    return 0;
  }

  public boolean addEmployee(Student aEmployee)
  {
    boolean wasAdded = false;
    if (employee.contains(aEmployee)) { return false; }
    employee.add(aEmployee);
    if (aEmployee.indexOfCurrentJob(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEmployee.addCurrentJob(this);
      if (!wasAdded)
      {
        employee.remove(aEmployee);
      }
    }
    return wasAdded;
  }

  public boolean removeEmployee(Student aEmployee)
  {
    boolean wasRemoved = false;
    if (!employee.contains(aEmployee))
    {
      return wasRemoved;
    }

    int oldIndex = employee.indexOf(aEmployee);
    employee.remove(oldIndex);
    if (aEmployee.indexOfCurrentJob(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEmployee.removeCurrentJob(this);
      if (!wasRemoved)
      {
        employee.add(oldIndex,aEmployee);
      }
    }
    return wasRemoved;
  }

  public boolean addEmployeeAt(Student aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployee()) { index = numberOfEmployee() - 1; }
      employee.remove(aEmployee);
      employee.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Student aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employee.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployee()) { index = numberOfEmployee() - 1; }
      employee.remove(aEmployee);
      employee.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }

  public boolean setCorrespondingCourse(Course aCorrespondingCourse)
  {
    boolean wasSet = false;
    if (aCorrespondingCourse == null)
    {
      return wasSet;
    }

    Course existingCorrespondingCourse = correspondingCourse;
    correspondingCourse = aCorrespondingCourse;
    if (existingCorrespondingCourse != null && !existingCorrespondingCourse.equals(aCorrespondingCourse))
    {
      existingCorrespondingCourse.removeJob(this);
    }
    correspondingCourse.addJob(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    allocatedStudent.clear();
    ArrayList<Student> copyOfPreviousWorker = new ArrayList<Student>(previousWorker);
    previousWorker.clear();
    for(Student aPreviousWorker : copyOfPreviousWorker)
    {
      aPreviousWorker.removePreviousJobExperience(this);
    }
    ArrayList<Student> copyOfApplicant = new ArrayList<Student>(applicant);
    applicant.clear();
    for(Student aApplicant : copyOfApplicant)
    {
      aApplicant.removeJobsAppliedTo(this);
    }
    ArrayList<Student> copyOfOfferReceiver = new ArrayList<Student>(offerReceiver);
    offerReceiver.clear();
    for(Student aOfferReceiver : copyOfOfferReceiver)
    {
      aOfferReceiver.removeOfferedJob(this);
    }
    ArrayList<Student> copyOfEmployee = new ArrayList<Student>(employee);
    employee.clear();
    for(Student aEmployee : copyOfEmployee)
    {
      aEmployee.removeCurrentJob(this);
    }
    Course placeholderCorrespondingCourse = correspondingCourse;
    this.correspondingCourse = null;
    placeholderCorrespondingCourse.removeJob(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "jobID" + ":" + getJobID()+ "," +
            "jobDescription" + ":" + getJobDescription()+ "," +
            "skillsRequired" + ":" + getSkillsRequired()+ "," +
            "experienceRequired" + ":" + getExperienceRequired()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "posType" + "=" + (getPosType() != null ? !getPosType().equals(this)  ? getPosType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "postingDeadlineDate" + "=" + (getPostingDeadlineDate() != null ? !getPostingDeadlineDate().equals(this)  ? getPostingDeadlineDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "offerDeadlineDate" + "=" + (getOfferDeadlineDate() != null ? !getOfferDeadlineDate().equals(this)  ? getOfferDeadlineDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "state" + "=" + (getState() != null ? !getState().equals(this)  ? getState().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "correspondingCourse = "+(getCorrespondingCourse()!=null?Integer.toHexString(System.identityHashCode(getCorrespondingCourse())):"null")
     + outputString;
  }
}