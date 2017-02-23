package ca.mcgill.ecse321.project.controller;

import ca.mcgill.ecse321.project.model.Department;
import ca.mcgill.ecse321.project.model.Job;
import ca.mcgill.ecse321.project.model.Student;
import ca.mcgill.ecse321.project.persistence.PersistenceXStream;

/**
 * @author Tharsan Ponnampalam
 *
 */
public class StudentController {
	
	private Department department;
	
	public StudentController(Department department){
		
		this.department = department;
		
	}
	
	
	
	
	/**
	 * @param jobPosting
	 * @param applicant
	 */
	public void applyToJobPosting(Job jobPosting,Student applicant){
		
		jobPosting.addApplicant(applicant);
		PersistenceXStream.saveToXMLwithXStream(department);
		
	}
	
	
	/**
	 * @param student
	 * @param jobOffer
	 * @param accept
	 */
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
