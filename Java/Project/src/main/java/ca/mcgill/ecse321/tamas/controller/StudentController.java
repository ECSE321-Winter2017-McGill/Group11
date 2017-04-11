package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.*;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tharsan Ponnampalam
 *
 */
public class StudentController {

	private final String createStudentNotIntegerStudentIDError = " Input a valid 9 digits student ID!<br>";
	private final String createStudentAlreadyExistError = " Student ID already registered!<br>";
	private final String createStudentNotIntegerYearError = " Input a valid numeric year!<br>";
	private final String createStudentNotIntegerNumberOfHoursError = " Input a valid numeric number of hours!<br>";
	private final String createStudentNullNameError = " Student name cannot be empty!<br>";
	private final String createStudentNullEmailError = " Student email cannot be empty!<br>";
	private final String createStudentInvalidEmailError = " Please input a valid email address!<br>";
	private final String createStudentNullJobPreferenceError = " Job Preference cannot be empty!<br>";


	private Department department;
	
	public StudentController(Department department){
		
		this.department = department;
		
	}

	/**
	 *
	 * @param studentID
	 * @param name
	 * @param email
	 * @param isGrad
	 * @param year
	 * @param jobPreference
	 * @param numberOfHours
	 */
    public void createStudent(int studentID, String name, String email, boolean isGrad, int year, String jobPreference, int numberOfHours){
		String error = "";

        Student student = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        department.addAllStudent(student);
        PersistenceXStream.saveToXMLwithXStream(department);

    }

	/**
	 * This method is used to create a student, inputs are either string or boolean that will be used to identify a
	 * student by. The ID should be unique to the student, the email should contain the correct pattern, the year of
	 * the student should be in the range [0,1,2,3,4], number of hours will be 0, non empty name and job preference.
	 *
	 * @param studentIDString
	 * @param name
	 * @param email
	 * @param isGrad
	 * @param yearString
	 * @param jobPreference
	 * @param numberOfHoursString
	 * @throws InvalidInputException
	 */
	public void createStudent(String studentIDString, String name, String email, boolean isGrad, String yearString, String jobPreference, String numberOfHoursString) throws InvalidInputException {
		String error = "";
		int studentID = -999;
		int year =-999;
		int numberOfHours = -999;

		boolean isIntegerStudentID = true;
		boolean isIntegerYear = true;
		boolean isIntegerNumberOfHours = true;

		try{
			studentID = Integer.parseInt(studentIDString);
		}catch (NumberFormatException e){
			isIntegerStudentID = false;
		}

		try{
			year = Integer.parseInt(yearString);
		}catch (NumberFormatException e){
			isIntegerYear = false;
		}

		try{
			numberOfHours = Integer.parseInt(numberOfHoursString);
		}catch (NumberFormatException e){
			isIntegerNumberOfHours = false;
		}


		if(!isIntegerStudentID || studentID/100000000 == 0 ){
			error += createStudentNotIntegerStudentIDError;
		}else{
			for(Student s: department.getAllStudents()){
				if(studentID == s.getStudentID()){
					error += createStudentAlreadyExistError;
					break;
				}
			}
		}



		if(name == null ||  name.trim().length() == 0){
			error += createStudentNullNameError;
		}
		if(email == null || email.trim().length() == 0){
			error += createStudentNullEmailError;
		}else{
			String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
			Pattern p = Pattern.compile(emailPattern);
			Matcher m = p.matcher(email);
			if(!m.matches()){
				error += createStudentInvalidEmailError;
			}
		}
		if(!isIntegerYear){
			error += createStudentNotIntegerYearError;
		}
		if(jobPreference == null || jobPreference.trim().length() == 0){
			error += createStudentNullJobPreferenceError;
		}
		if(!isIntegerNumberOfHours){
			error += createStudentNotIntegerNumberOfHoursError;
		}

		if (error.length()>0){
			throw new InvalidInputException(error);
		}

		Student student = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
		department.addAllStudent(student);
		PersistenceXStream.saveToXMLwithXStream(department);

	}

	public void addPreviousExperienceToStudent(Student student, Job previousJob){

	    student.addPreviousJobExperience(previousJob);
        PersistenceXStream.saveToXMLwithXStream(department);

    }


	/**
	 * Student will apply to a job posting through this method
	 *
	 * @param jobPosting
	 * @param applicant
	 * @throws InvalidInputException
	 */
	public void applyToJobPosting(Job jobPosting,Student applicant) throws InvalidInputException {
		
		String error = "";
		if (applicant == null)
  			error = error + "Applicant needs to be selected for registration! ";
		else if (!department.getAllStudents().contains(applicant))
  			error = error + "Applicant does not exist! ";
		if (applicant != null && jobPosting != null && (
				jobPosting.getApplicant().contains(applicant)||jobPosting.getAllocatedStudent().contains(applicant) || jobPosting.getEmployee().contains(applicant)))
			error = error + "Applicant already applied to Job!";
		if (jobPosting == null)
			error = error +"Job needs to be selected for registration!";
		else if(!department.getAllJobs().contains(jobPosting))
			error = error + "Job does not exist!";
		else{
			if(jobPosting.getState() != JobStatus.Posted && jobPosting.getState() != JobStatus.Allocated && jobPosting.getState() != JobStatus.AppliedTo){
				error = error +  " ("+jobPosting.getState().toString()+")" +" Job cannot be allocated at the moment!";
			}
		}
		error = error.trim();
		if(applicant.getJobsAppliedTo().size() >= 3){
			error = error + "Can't apply to more than 3 jobs!";
		}
		if (error.length()>0)
			throw new InvalidInputException(error);
		
		jobPosting.addApplicant(applicant);
		jobPosting.setState(JobStatus.AppliedTo);

		PersistenceXStream.saveToXMLwithXStream(department);
		
	}

	/**
	 * Student will be able to respond to a job offer with this method, answering with a accept or not accept
	 *
	 * @param student
	 * @param jobOffer
	 * @param accept
	 * @throws InvalidInputException
	 */
	public void respondToJobOffer(Student student,Job jobOffer, boolean accept) throws InvalidInputException {
		String error = "";
		int maximumWorkHoursForStudent = 180;
		if(student == null){
			error = error + "Student needs to be selected to respond to offer! ";
		}
		if(jobOffer == null){
			error = error + "Offer needs to be selected to respond to offer! ";
		}else if(!department.getAllJobs().contains(jobOffer))
			error = error + "Job does not exist!";




		if(accept&&error.length()==0){

			if(student.getNumberOfHours()+jobOffer.getCorrespondingCourse().getNumberOfHours()> maximumWorkHoursForStudent){
				error = error + "Sorry student can't accept anymore job. Work hours exceeding " +maximumWorkHoursForStudent;
			}

			if(error.length()>0){
				throw new InvalidInputException(error);
			}

			jobOffer.removeOfferReceiver(student);
			jobOffer.addEmployee(student);
			student.setNumberOfHours(jobOffer.getCorrespondingCourse().getNumberOfHours());

			int numberOfAllocated = 0;
			if(jobOffer.getPosType() == PositionType.Grader){
				numberOfAllocated = jobOffer.getCorrespondingCourse().getGradersNeeded();
			}else{
				numberOfAllocated = jobOffer.getCorrespondingCourse().getTasNeeded();
			}
			if(jobOffer.getEmployee().size() == numberOfAllocated){
				jobOffer.setState(JobStatus.JobFull);
			}

		}else{

			if(error.length()>0){
				throw new InvalidInputException(error);
			}

			jobOffer.removeOfferReceiver(student);
			if(jobOffer.getApplicant().size() > 0) {
				jobOffer.setState(JobStatus.AppliedTo);
			}else{
				jobOffer.setState(JobStatus.Posted);
			}
		}
		
		PersistenceXStream.saveToXMLwithXStream(department);
		
	}
}
