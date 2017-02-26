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

	private final String createJobPostingNullJobErrorMessage = " Must select Job!";
	private final String createJobPostingNullJobDescriptionErrorMessage = " Job description cannot be empty!";
	private final String createJobPostingNullSkillsRequiredErrorMessage = " Skills required cannot be empty!";
	private final String createJobPostingNullExperienceRequiredErrorMessage = " Experience required cannot be empty!";
	private final String createJobPostingNullPostDeadlineErrorMessage = " Job post deadline date cannot be empty!";
	//private final String createJobPostingNullOfferDeadlineErrorMessage = "Job offer deadline date cannot be empty!";




	private Department department;
	
	public InstructorController(Department department) {
		
		this.department = department;
		
	}

	public void createInstructor(String name, int instructorID, String email){

		Instructor instructor = new Instructor(name, instructorID, email);
		department.addAllInstructor(instructor);
		PersistenceXStream.saveToXMLwithXStream(department);

	}
	
	/**
	 * @param job
	 * @param jobDescription
	 * @param skillsRequired
	 * @param experienceRequired
	 * @param offerDeadlineDate
	 */
	public void createJobPosting(Job job, String jobDescription, String skillsRequired, String experienceRequired, Date offerDeadlineDate) throws InvalidInputException{

		String error = "";

		if(job == null){
			error = error + createJobPostingNullJobErrorMessage;
		}
		if(jobDescription == null || jobDescription.trim().length() == 0){
			error = error + createJobPostingNullJobDescriptionErrorMessage;
		}
		if(skillsRequired == null || skillsRequired.trim().length() == 0){
			error = error + createJobPostingNullSkillsRequiredErrorMessage;
		}
		if(experienceRequired == null || experienceRequired.trim().length() == 0){
			error = error + createJobPostingNullExperienceRequiredErrorMessage;
		}
		if(offerDeadlineDate == null){
			error = error + createJobPostingNullPostDeadlineErrorMessage;
		}
		if (error.length()>0){
			throw new InvalidInputException(error);
		}

		job.setJobDescription(jobDescription);
		job.setSkillsRequired(skillsRequired);
		job.setExperienceRequired(experienceRequired);
		job.setOfferDeadlineDate(offerDeadlineDate);

		job.setState(JobStatus.Posted);
		PersistenceXStream.saveToXMLwithXStream(department);

	}
	
	/**
	 * @param reviewer
	 * @param reviewee
	 * @param content
	 */
	public void createReview(Instructor reviewer, Student reviewee, String content, Job reviewedJob){
		Review review = new Review(content, reviewee, reviewedJob, reviewer);
		
		PersistenceXStream.saveToXMLwithXStream(department);
	}
	
	/**
	 * @param job
	 * @param applicant
	 */
	public void modifyAllocation(Job job, Student applicant){

		boolean validApplicant = false;
		//Check if student is an applicant
		for(Student st: job.getApplicant()){

			if(st==applicant){
				validApplicant = true;
				break;
			}

		}

		if(validApplicant){
			job.removeApplicant(applicant);
			job.addAllocatedStudent(applicant);
		}else{
			//// IMPLEMENT EXCEPTION \\\\\\\\\\\\\\\\\
			return;
		}

		PersistenceXStream.saveToXMLwithXStream(department);

	}
	

}
