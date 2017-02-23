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
