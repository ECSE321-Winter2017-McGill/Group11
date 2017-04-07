package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.Student;
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

    public void createStudent(int studentID, String name, String email, boolean isGrad, int year, String jobPreference, int numberOfHours){
		String error = "";

        Student student = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        department.addAllStudent(student);
        PersistenceXStream.saveToXMLwithXStream(department);

    }

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
	

	public void applyToJobPosting(Job jobPosting,Student applicant) throws InvalidInputException {
		
		String error = "";
		if (applicant == null)
  			error = error + "Applicant needs to be selected for registration! ";
		else if (!department.getAllStudents().contains(applicant))
  			error = error + "Applicant does not exist! ";
		if (applicant != null && jobPosting != null &&
				jobPosting.getApplicant().contains(applicant))
			error = error + "Applicant already applied to Job!";
		if (jobPosting == null)
			error = error +"Job needs to be selected for registration!";
		else if(!department.getAllJobs().contains(jobPosting))
			error = error + "Job does not exist!";
		error = error.trim();
		if(applicant.getJobsAppliedTo().size() >= 3){
			error = error + "Can't apply to more than 3 jobs!";
		}
		if (error.length()>0)
			throw new InvalidInputException(error);
		
		jobPosting.addApplicant(applicant);
		PersistenceXStream.saveToXMLwithXStream(department);
		
	}
	
	

	public void respondToJobOffer(Student student,Job jobOffer, boolean accept) throws InvalidInputException {
		String error = "";
		int maximumWorkHoursForStudent = 180;
		if(student == null){
			error = error + "Student needs to be selected to respond to offer! ";
		}
		if(jobOffer == null){
			error = error + "Offer needs to be selected to respond to offer! ";
		}




		if(accept&&error.length()==0){

			if(student.getNumberOfHours()+jobOffer.getCorrespondingCourse().getNumberOfHours()> maximumWorkHoursForStudent){
				error = error + "Sorry student can't accept anymore job. Wor hours exceeding " +maximumWorkHoursForStudent;
			}

			if(error.length()>0){
				throw new InvalidInputException(error);
			}

			jobOffer.removeOfferReceiver(student);
			jobOffer.addEmployee(student);
			student.setNumberOfHours(jobOffer.getCorrespondingCourse().getNumberOfHours());
		}else{

			if(error.length()>0){
				throw new InvalidInputException(error);
			}

			jobOffer.removeOfferReceiver(student);
		}
		
		PersistenceXStream.saveToXMLwithXStream(department);
		
	}
}
