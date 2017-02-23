/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse321.project.model;
import java.util.*;

// line 12 "../../../../../ECSE321UmpleCode.ump"
public class Student
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Student> studentsByStudentID = new HashMap<Integer, Student>();
  private static Map<String, Student> studentsByEmail = new HashMap<String, Student>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Attributes
  private int studentID;
  private String name;
  private String email;
  private boolean isGrad;
  private int year;
  private String jobPreference;
  private int numberOfHours;

  //Student Associations
  private List<Job> previousJobExperiences;
  private List<Job> jobsAppliedTo;
  private List<Job> offeredJobs;
  private List<Job> currentJobs;
  private List<Review> reviewText;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Student(int aStudentID, String aName, String aEmail, boolean aIsGrad, int aYear, String aJobPreference, int aNumberOfHours)
  {
    name = aName;
    isGrad = aIsGrad;
    year = aYear;
    jobPreference = aJobPreference;
    numberOfHours = aNumberOfHours;
    if (!setStudentID(aStudentID))
    {
      throw new RuntimeException("Cannot create due to duplicate studentID");
    }
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email");
    }
    previousJobExperiences = new ArrayList<Job>();
    jobsAppliedTo = new ArrayList<Job>();
    offeredJobs = new ArrayList<Job>();
    currentJobs = new ArrayList<Job>();
    reviewText = new ArrayList<Review>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStudentID(int aStudentID)
  {
    boolean wasSet = false;
    Integer anOldStudentID = getStudentID();
    if (hasWithStudentID(aStudentID)) {
      return wasSet;
    }
    studentID = aStudentID;
    wasSet = true;
    if (anOldStudentID != null) {
      studentsByStudentID.remove(anOldStudentID);
    }
    studentsByStudentID.put(aStudentID, this);
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
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
      studentsByEmail.remove(anOldEmail);
    }
    studentsByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setIsGrad(boolean aIsGrad)
  {
    boolean wasSet = false;
    isGrad = aIsGrad;
    wasSet = true;
    return wasSet;
  }

  public boolean setYear(int aYear)
  {
    boolean wasSet = false;
    year = aYear;
    wasSet = true;
    return wasSet;
  }

  public boolean setJobPreference(String aJobPreference)
  {
    boolean wasSet = false;
    jobPreference = aJobPreference;
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

  public int getStudentID()
  {
    return studentID;
  }

  public static Student getWithStudentID(int aStudentID)
  {
    return studentsByStudentID.get(aStudentID);
  }

  public static boolean hasWithStudentID(int aStudentID)
  {
    return getWithStudentID(aStudentID) != null;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public static Student getWithEmail(String aEmail)
  {
    return studentsByEmail.get(aEmail);
  }

  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public boolean getIsGrad()
  {
    return isGrad;
  }

  public int getYear()
  {
    return year;
  }

  public String getJobPreference()
  {
    return jobPreference;
  }

  public int getNumberOfHours()
  {
    return numberOfHours;
  }

  public boolean isIsGrad()
  {
    return isGrad;
  }

  public Job getPreviousJobExperience(int index)
  {
    Job aPreviousJobExperience = previousJobExperiences.get(index);
    return aPreviousJobExperience;
  }

  public List<Job> getPreviousJobExperiences()
  {
    List<Job> newPreviousJobExperiences = Collections.unmodifiableList(previousJobExperiences);
    return newPreviousJobExperiences;
  }

  public int numberOfPreviousJobExperiences()
  {
    int number = previousJobExperiences.size();
    return number;
  }

  public boolean hasPreviousJobExperiences()
  {
    boolean has = previousJobExperiences.size() > 0;
    return has;
  }

  public int indexOfPreviousJobExperience(Job aPreviousJobExperience)
  {
    int index = previousJobExperiences.indexOf(aPreviousJobExperience);
    return index;
  }

  public Job getJobsAppliedTo(int index)
  {
    Job aJobsAppliedTo = jobsAppliedTo.get(index);
    return aJobsAppliedTo;
  }

  public List<Job> getJobsAppliedTo()
  {
    List<Job> newJobsAppliedTo = Collections.unmodifiableList(jobsAppliedTo);
    return newJobsAppliedTo;
  }

  public int numberOfJobsAppliedTo()
  {
    int number = jobsAppliedTo.size();
    return number;
  }

  public boolean hasJobsAppliedTo()
  {
    boolean has = jobsAppliedTo.size() > 0;
    return has;
  }

  public int indexOfJobsAppliedTo(Job aJobsAppliedTo)
  {
    int index = jobsAppliedTo.indexOf(aJobsAppliedTo);
    return index;
  }

  public Job getOfferedJob(int index)
  {
    Job aOfferedJob = offeredJobs.get(index);
    return aOfferedJob;
  }

  public List<Job> getOfferedJobs()
  {
    List<Job> newOfferedJobs = Collections.unmodifiableList(offeredJobs);
    return newOfferedJobs;
  }

  public int numberOfOfferedJobs()
  {
    int number = offeredJobs.size();
    return number;
  }

  public boolean hasOfferedJobs()
  {
    boolean has = offeredJobs.size() > 0;
    return has;
  }

  public int indexOfOfferedJob(Job aOfferedJob)
  {
    int index = offeredJobs.indexOf(aOfferedJob);
    return index;
  }

  public Job getCurrentJob(int index)
  {
    Job aCurrentJob = currentJobs.get(index);
    return aCurrentJob;
  }

  public List<Job> getCurrentJobs()
  {
    List<Job> newCurrentJobs = Collections.unmodifiableList(currentJobs);
    return newCurrentJobs;
  }

  public int numberOfCurrentJobs()
  {
    int number = currentJobs.size();
    return number;
  }

  public boolean hasCurrentJobs()
  {
    boolean has = currentJobs.size() > 0;
    return has;
  }

  public int indexOfCurrentJob(Job aCurrentJob)
  {
    int index = currentJobs.indexOf(aCurrentJob);
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

  public static int minimumNumberOfPreviousJobExperiences()
  {
    return 0;
  }

  public boolean addPreviousJobExperience(Job aPreviousJobExperience)
  {
    boolean wasAdded = false;
    if (previousJobExperiences.contains(aPreviousJobExperience)) { return false; }
    previousJobExperiences.add(aPreviousJobExperience);
    if (aPreviousJobExperience.indexOfPreviousWorker(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPreviousJobExperience.addPreviousWorker(this);
      if (!wasAdded)
      {
        previousJobExperiences.remove(aPreviousJobExperience);
      }
    }
    return wasAdded;
  }

  public boolean removePreviousJobExperience(Job aPreviousJobExperience)
  {
    boolean wasRemoved = false;
    if (!previousJobExperiences.contains(aPreviousJobExperience))
    {
      return wasRemoved;
    }

    int oldIndex = previousJobExperiences.indexOf(aPreviousJobExperience);
    previousJobExperiences.remove(oldIndex);
    if (aPreviousJobExperience.indexOfPreviousWorker(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPreviousJobExperience.removePreviousWorker(this);
      if (!wasRemoved)
      {
        previousJobExperiences.add(oldIndex,aPreviousJobExperience);
      }
    }
    return wasRemoved;
  }

  public boolean addPreviousJobExperienceAt(Job aPreviousJobExperience, int index)
  {  
    boolean wasAdded = false;
    if(addPreviousJobExperience(aPreviousJobExperience))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPreviousJobExperiences()) { index = numberOfPreviousJobExperiences() - 1; }
      previousJobExperiences.remove(aPreviousJobExperience);
      previousJobExperiences.add(index, aPreviousJobExperience);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePreviousJobExperienceAt(Job aPreviousJobExperience, int index)
  {
    boolean wasAdded = false;
    if(previousJobExperiences.contains(aPreviousJobExperience))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPreviousJobExperiences()) { index = numberOfPreviousJobExperiences() - 1; }
      previousJobExperiences.remove(aPreviousJobExperience);
      previousJobExperiences.add(index, aPreviousJobExperience);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPreviousJobExperienceAt(aPreviousJobExperience, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfJobsAppliedTo()
  {
    return 0;
  }

  public static int maximumNumberOfJobsAppliedTo()
  {
    return 3;
  }

  public boolean addJobsAppliedTo(Job aJobsAppliedTo)
  {
    boolean wasAdded = false;
    if (jobsAppliedTo.contains(aJobsAppliedTo)) { return false; }
    if (numberOfJobsAppliedTo() >= maximumNumberOfJobsAppliedTo())
    {
      return wasAdded;
    }

    jobsAppliedTo.add(aJobsAppliedTo);
    if (aJobsAppliedTo.indexOfApplicant(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aJobsAppliedTo.addApplicant(this);
      if (!wasAdded)
      {
        jobsAppliedTo.remove(aJobsAppliedTo);
      }
    }
    return wasAdded;
  }

  public boolean removeJobsAppliedTo(Job aJobsAppliedTo)
  {
    boolean wasRemoved = false;
    if (!jobsAppliedTo.contains(aJobsAppliedTo))
    {
      return wasRemoved;
    }

    int oldIndex = jobsAppliedTo.indexOf(aJobsAppliedTo);
    jobsAppliedTo.remove(oldIndex);
    if (aJobsAppliedTo.indexOfApplicant(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aJobsAppliedTo.removeApplicant(this);
      if (!wasRemoved)
      {
        jobsAppliedTo.add(oldIndex,aJobsAppliedTo);
      }
    }
    return wasRemoved;
  }

  public boolean setJobsAppliedTo(Job... newJobsAppliedTo)
  {
    boolean wasSet = false;
    ArrayList<Job> verifiedJobsAppliedTo = new ArrayList<Job>();
    for (Job aJobsAppliedTo : newJobsAppliedTo)
    {
      if (verifiedJobsAppliedTo.contains(aJobsAppliedTo))
      {
        continue;
      }
      verifiedJobsAppliedTo.add(aJobsAppliedTo);
    }

    if (verifiedJobsAppliedTo.size() != newJobsAppliedTo.length || verifiedJobsAppliedTo.size() > maximumNumberOfJobsAppliedTo())
    {
      return wasSet;
    }

    ArrayList<Job> oldJobsAppliedTo = new ArrayList<Job>(jobsAppliedTo);
    jobsAppliedTo.clear();
    for (Job aNewJobsAppliedTo : verifiedJobsAppliedTo)
    {
      jobsAppliedTo.add(aNewJobsAppliedTo);
      if (oldJobsAppliedTo.contains(aNewJobsAppliedTo))
      {
        oldJobsAppliedTo.remove(aNewJobsAppliedTo);
      }
      else
      {
        aNewJobsAppliedTo.addApplicant(this);
      }
    }

    for (Job anOldJobsAppliedTo : oldJobsAppliedTo)
    {
      anOldJobsAppliedTo.removeApplicant(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addJobsAppliedToAt(Job aJobsAppliedTo, int index)
  {  
    boolean wasAdded = false;
    if(addJobsAppliedTo(aJobsAppliedTo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobsAppliedTo()) { index = numberOfJobsAppliedTo() - 1; }
      jobsAppliedTo.remove(aJobsAppliedTo);
      jobsAppliedTo.add(index, aJobsAppliedTo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobsAppliedToAt(Job aJobsAppliedTo, int index)
  {
    boolean wasAdded = false;
    if(jobsAppliedTo.contains(aJobsAppliedTo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobsAppliedTo()) { index = numberOfJobsAppliedTo() - 1; }
      jobsAppliedTo.remove(aJobsAppliedTo);
      jobsAppliedTo.add(index, aJobsAppliedTo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobsAppliedToAt(aJobsAppliedTo, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOfferedJobs()
  {
    return 0;
  }

  public static int maximumNumberOfOfferedJobs()
  {
    return 3;
  }

  public boolean addOfferedJob(Job aOfferedJob)
  {
    boolean wasAdded = false;
    if (offeredJobs.contains(aOfferedJob)) { return false; }
    if (numberOfOfferedJobs() >= maximumNumberOfOfferedJobs())
    {
      return wasAdded;
    }

    offeredJobs.add(aOfferedJob);
    if (aOfferedJob.indexOfOfferReceiver(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOfferedJob.addOfferReceiver(this);
      if (!wasAdded)
      {
        offeredJobs.remove(aOfferedJob);
      }
    }
    return wasAdded;
  }

  public boolean removeOfferedJob(Job aOfferedJob)
  {
    boolean wasRemoved = false;
    if (!offeredJobs.contains(aOfferedJob))
    {
      return wasRemoved;
    }

    int oldIndex = offeredJobs.indexOf(aOfferedJob);
    offeredJobs.remove(oldIndex);
    if (aOfferedJob.indexOfOfferReceiver(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOfferedJob.removeOfferReceiver(this);
      if (!wasRemoved)
      {
        offeredJobs.add(oldIndex,aOfferedJob);
      }
    }
    return wasRemoved;
  }

  public boolean setOfferedJobs(Job... newOfferedJobs)
  {
    boolean wasSet = false;
    ArrayList<Job> verifiedOfferedJobs = new ArrayList<Job>();
    for (Job aOfferedJob : newOfferedJobs)
    {
      if (verifiedOfferedJobs.contains(aOfferedJob))
      {
        continue;
      }
      verifiedOfferedJobs.add(aOfferedJob);
    }

    if (verifiedOfferedJobs.size() != newOfferedJobs.length || verifiedOfferedJobs.size() > maximumNumberOfOfferedJobs())
    {
      return wasSet;
    }

    ArrayList<Job> oldOfferedJobs = new ArrayList<Job>(offeredJobs);
    offeredJobs.clear();
    for (Job aNewOfferedJob : verifiedOfferedJobs)
    {
      offeredJobs.add(aNewOfferedJob);
      if (oldOfferedJobs.contains(aNewOfferedJob))
      {
        oldOfferedJobs.remove(aNewOfferedJob);
      }
      else
      {
        aNewOfferedJob.addOfferReceiver(this);
      }
    }

    for (Job anOldOfferedJob : oldOfferedJobs)
    {
      anOldOfferedJob.removeOfferReceiver(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addOfferedJobAt(Job aOfferedJob, int index)
  {  
    boolean wasAdded = false;
    if(addOfferedJob(aOfferedJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOfferedJobs()) { index = numberOfOfferedJobs() - 1; }
      offeredJobs.remove(aOfferedJob);
      offeredJobs.add(index, aOfferedJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOfferedJobAt(Job aOfferedJob, int index)
  {
    boolean wasAdded = false;
    if(offeredJobs.contains(aOfferedJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOfferedJobs()) { index = numberOfOfferedJobs() - 1; }
      offeredJobs.remove(aOfferedJob);
      offeredJobs.add(index, aOfferedJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOfferedJobAt(aOfferedJob, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfCurrentJobs()
  {
    return 0;
  }

  public static int maximumNumberOfCurrentJobs()
  {
    return 3;
  }

  public boolean addCurrentJob(Job aCurrentJob)
  {
    boolean wasAdded = false;
    if (currentJobs.contains(aCurrentJob)) { return false; }
    if (numberOfCurrentJobs() >= maximumNumberOfCurrentJobs())
    {
      return wasAdded;
    }

    currentJobs.add(aCurrentJob);
    if (aCurrentJob.indexOfEmployee(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCurrentJob.addEmployee(this);
      if (!wasAdded)
      {
        currentJobs.remove(aCurrentJob);
      }
    }
    return wasAdded;
  }

  public boolean removeCurrentJob(Job aCurrentJob)
  {
    boolean wasRemoved = false;
    if (!currentJobs.contains(aCurrentJob))
    {
      return wasRemoved;
    }

    int oldIndex = currentJobs.indexOf(aCurrentJob);
    currentJobs.remove(oldIndex);
    if (aCurrentJob.indexOfEmployee(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCurrentJob.removeEmployee(this);
      if (!wasRemoved)
      {
        currentJobs.add(oldIndex,aCurrentJob);
      }
    }
    return wasRemoved;
  }

  public boolean setCurrentJobs(Job... newCurrentJobs)
  {
    boolean wasSet = false;
    ArrayList<Job> verifiedCurrentJobs = new ArrayList<Job>();
    for (Job aCurrentJob : newCurrentJobs)
    {
      if (verifiedCurrentJobs.contains(aCurrentJob))
      {
        continue;
      }
      verifiedCurrentJobs.add(aCurrentJob);
    }

    if (verifiedCurrentJobs.size() != newCurrentJobs.length || verifiedCurrentJobs.size() > maximumNumberOfCurrentJobs())
    {
      return wasSet;
    }

    ArrayList<Job> oldCurrentJobs = new ArrayList<Job>(currentJobs);
    currentJobs.clear();
    for (Job aNewCurrentJob : verifiedCurrentJobs)
    {
      currentJobs.add(aNewCurrentJob);
      if (oldCurrentJobs.contains(aNewCurrentJob))
      {
        oldCurrentJobs.remove(aNewCurrentJob);
      }
      else
      {
        aNewCurrentJob.addEmployee(this);
      }
    }

    for (Job anOldCurrentJob : oldCurrentJobs)
    {
      anOldCurrentJob.removeEmployee(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addCurrentJobAt(Job aCurrentJob, int index)
  {  
    boolean wasAdded = false;
    if(addCurrentJob(aCurrentJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCurrentJobs()) { index = numberOfCurrentJobs() - 1; }
      currentJobs.remove(aCurrentJob);
      currentJobs.add(index, aCurrentJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCurrentJobAt(Job aCurrentJob, int index)
  {
    boolean wasAdded = false;
    if(currentJobs.contains(aCurrentJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCurrentJobs()) { index = numberOfCurrentJobs() - 1; }
      currentJobs.remove(aCurrentJob);
      currentJobs.add(index, aCurrentJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCurrentJobAt(aCurrentJob, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfReviewText()
  {
    return 0;
  }

  public Review addReviewText(String aContent, int aAssociatedJobID, Instructor aReviewer)
  {
    return new Review(aContent, aAssociatedJobID, this, aReviewer);
  }

  public boolean addReviewText(Review aReviewText)
  {
    boolean wasAdded = false;
    if (reviewText.contains(aReviewText)) { return false; }
    Student existingReviewee = aReviewText.getReviewee();
    boolean isNewReviewee = existingReviewee != null && !this.equals(existingReviewee);
    if (isNewReviewee)
    {
      aReviewText.setReviewee(this);
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
    //Unable to remove aReviewText, as it must always have a reviewee
    if (!this.equals(aReviewText.getReviewee()))
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
    studentsByStudentID.remove(getStudentID());
    studentsByEmail.remove(getEmail());
    ArrayList<Job> copyOfPreviousJobExperiences = new ArrayList<Job>(previousJobExperiences);
    previousJobExperiences.clear();
    for(Job aPreviousJobExperience : copyOfPreviousJobExperiences)
    {
      aPreviousJobExperience.removePreviousWorker(this);
    }
    ArrayList<Job> copyOfJobsAppliedTo = new ArrayList<Job>(jobsAppliedTo);
    jobsAppliedTo.clear();
    for(Job aJobsAppliedTo : copyOfJobsAppliedTo)
    {
      aJobsAppliedTo.removeApplicant(this);
    }
    ArrayList<Job> copyOfOfferedJobs = new ArrayList<Job>(offeredJobs);
    offeredJobs.clear();
    for(Job aOfferedJob : copyOfOfferedJobs)
    {
      aOfferedJob.removeOfferReceiver(this);
    }
    ArrayList<Job> copyOfCurrentJobs = new ArrayList<Job>(currentJobs);
    currentJobs.clear();
    for(Job aCurrentJob : copyOfCurrentJobs)
    {
      aCurrentJob.removeEmployee(this);
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
            "studentID" + ":" + getStudentID()+ "," +
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "isGrad" + ":" + getIsGrad()+ "," +
            "year" + ":" + getYear()+ "," +
            "jobPreference" + ":" + getJobPreference()+ "," +
            "numberOfHours" + ":" + getNumberOfHours()+ "]"
     + outputString;
  }
}