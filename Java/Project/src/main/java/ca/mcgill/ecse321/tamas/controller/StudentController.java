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
	
	private Department department;
	
	public StudentController(Department department){
		
		this.department = department;
		
	}

    public void createStudent(int studentID, String name, String email, boolean isGrad, int year, String jobPreference, int numberOfHours){

        Student student = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
        department.addAllStudent(student);
        PersistenceXStream.saveToXMLwithXStream(department);

    }

	public void addPreviousExperienceToStudent(Student student, Job previousJob){

	    student.addPreviousJobExperience(previousJob);
        PersistenceXStream.saveToXMLwithXStream(department);

    }
	

	public void applyToJobPosting(Job jobPosting,Student applicant){
		
		String error = "";
		if (applicant == null)
  			error = error + "Applicant needs to be selected for registration! ";
		else if (!department.getAllStudents().contains(applicant))
  			error = error + "Applicant does not exist! ";
		if (job == null)
			error = error +"Job needs to be selected for registration!";
		else if(!department.getAllJobs().contains(job))
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
