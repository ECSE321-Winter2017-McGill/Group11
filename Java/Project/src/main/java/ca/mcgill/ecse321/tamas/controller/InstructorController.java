package ca.mcgill.ecse321.tamas.controller;

import java.sql.Date;

import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.JobStatus;
import ca.mcgill.ecse321.tamas.model.Review;
import ca.mcgill.ecse321.tamas.model.Student;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

public class InstructorController {
	
	private Department department;
	
	public InstructorController(Department department) {
		
		this.department = department;
		
	}
	
	/**
	 * @param job
	 * @param jobDescription
	 * @param skillsRequired
	 * @param experienceRequired
	 * @param postingDeadlineDate
	 * @param offerDeadlineDate
	 */
	public void createJobPosting(Job job, String jobDescription, String skillsRequired, String experienceRequired, Date postingDeadlineDate, Date offerDeadlineDate){
		
		job.setJobDescription(jobDescription);
		job.setSkillsRequired(skillsRequired);
		job.setExperienceRequired(experienceRequired);
		job.setPostingDeadlineDate(postingDeadlineDate);
		job.setOfferDeadlineDate(offerDeadlineDate);
		
		job.setState(JobStatus.Posted);
		PersistenceXStream.saveToXMLwithXStream(department);

	}
	
	/**
	 * @param reviewer
	 * @param reviewee
	 * @param content
	 * @param associatedJobID
	 */
	public void createReview(Instructor reviewer, Student reviewee, String content, int associatedJobID){
		Review review = new Review(content, associatedJobID, reviewee, reviewer);
		
		PersistenceXStream.saveToXMLwithXStream(department);
	}
	
	/**
	 * @param job
	 * @param student
	 */
	public void modifyAllocation(Job job, Student student){
		
		PersistenceXStream.saveToXMLwithXStream(department);

	}
	

}
