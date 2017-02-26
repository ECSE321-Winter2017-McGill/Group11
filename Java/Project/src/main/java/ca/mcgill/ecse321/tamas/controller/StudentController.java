package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.Student;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

/**
 * @author Tharsan Ponnampalam
 *
 */
public class StudentController {

	private final String createStudentNotIntegerStudentIDError = "Input a numeric student ID!";
	private final String createStudentNotIntegerYearError = "Input a valid numeric year!";
	private final String createStudentNotIntegerNumberOfHoursError = "Input a valid numeric number of hours!";
	private final String createStudentNullNameError = "Student name cannot be empty!";
	private final String createStudentNullEmailError = "Student email cannot be empty!";
	private final String createStudentNullJobPreferenceError = "Job Preference cannot be empty!";


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


		if(isIntegerStudentID){
			error += createStudentNotIntegerStudentIDError;
		}
		if(name == null ||  name.trim().length() == 0){
			error += createStudentNullNameError;
		}
		if(email == null || email.trim().length() == 0){
			error += createStudentNullEmailError;
		}
		if(isIntegerYear){
			error += createStudentNotIntegerYearError;
		}
		if(jobPreference == null || jobPreference.trim().length() == 0){
			error += createStudentNullJobPreferenceError;
		}
		if(isIntegerNumberOfHours){
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
		else if (jobPosting.getApplicant().contains(applicant))
			error = error + "Applicant already applied to Job!";
		if (jobPosting == null)
			error = error +"Job needs to be selected for registration!";
		else if(!department.getAllJobs().contains(jobPosting))
			error = error + "Job does not exist!";
		error = error.trim();
		if (error.length()>0)
			throw new InvalidInputException(error);
		
		jobPosting.addApplicant(applicant);
		PersistenceXStream.saveToXMLwithXStream(department);
		
	}
	
	

	public void respondToJobOffer(Student student,Job jobOffer, boolean accept){
		
		if(accept){
			jobOffer.removeOfferReceiver(student);
			jobOffer.addEmployee(student);
		}else{
			jobOffer.removeOfferReceiver(student);
		}
		
		PersistenceXStream.saveToXMLwithXStream(department);
		
	}
	
	
	
	
}
