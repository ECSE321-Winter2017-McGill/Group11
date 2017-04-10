package ca.mcgill.ecse321.tamas.controller;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.JobStatus;
import ca.mcgill.ecse321.tamas.model.Review;
import ca.mcgill.ecse321.tamas.model.Student;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

public class InstructorController {

	private final String createJobPostingNullJobErrorMessage = " Must select Job!<br>";
	private final String createJobPostingNullJobDescriptionErrorMessage = " Job description cannot be empty!<br>";
	private final String createJobPostingNullSkillsRequiredErrorMessage = " Skills required cannot be empty!<br>";
	private final String createJobPostingNullExperienceRequiredErrorMessage = " Experience required cannot be empty!<br>";
	//private final String createJobPostingNullPostDeadlineErrorMessage = " Job post deadline date cannot be empty!";
	private final String createJobPostingNullOfferDeadlineErrorMessage = "Job offer deadline date cannot be empty!<br>";

	private final String createInstructorNotIntegerIDError = " Input a valid 9 digit ID number!<br>";
	private final String createInstructorDuplicateIDError = " Instructor ID already registered!<br>";
    private final String createInstructorNullInstructorNameError = " Instructor name cannot be empty!<br>";
	private final String createInstructorNullInstructorEmailError = " Instructor email cannot be empty!<br>";
	private final String createInstructorInvalidInstructorEmailError = " Please input a valid email address!<br>";

	private final String createReviewNullInstructorError = " Please select an instructor!<br>";
	private final String createReviewNullStudentError = " Please select a student!<br>";
	private final String createReviewNullContentError = " Content cannot be empty!<br>";
	private final String createReviewNullJobError = " Please select a job!<br>";


	private Department department;
	
	public InstructorController(Department department) {
		
		this.department = department;
		
	}

    /**
     * This method creates an instructor, which is represented by a name, a unique ID number (9 digits) and
     * a valid McGill email which ends with either @mail.mcgill.ca or simply @mcgill.ca.
     *
     * @param name
     * @param instructorIDString
     * @param email
     * @throws InvalidInputException
     */
	public void createInstructor(String name, String instructorIDString, String email) throws InvalidInputException {

	    String error = "";
		int instructorID = -999;
		boolean isInstructorID = true;

        try{
            instructorID = Integer.parseInt(instructorIDString);
        }catch (NumberFormatException e){
            isInstructorID = false;
        }

        if (!isInstructorID|| instructorID/100000000 == 0) {
            error += createInstructorNotIntegerIDError;
        }else{
			for(Instructor i: department.getAllInstructors()){
				if(instructorID == i.getInstructorID()){
					error += createInstructorDuplicateIDError;
					break;
				}
			}
		}
        if (name == null || name.length() == 0) {
            error += createInstructorNullInstructorNameError;
        }
        if (email == null || email.length() == 0) {
            error += createInstructorNullInstructorEmailError;
        }else{
				String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
				Pattern p = Pattern.compile(emailPattern);
				Matcher m = p.matcher(email);
				if(!m.matches()){
					error += createInstructorInvalidInstructorEmailError;
				}
		}

        if (error.length()>0){
            throw new InvalidInputException(error);
        }

		Instructor instructor = new Instructor(name, instructorID, email);
		department.addAllInstructor(instructor);
		PersistenceXStream.saveToXMLwithXStream(department);

	}
	
	/**
     * This method creates a job posting, the department must choose a valid associated job and fill in some information
     * about the job posting for the students. Note that the offer deadline must be later than the time at which it is created.
     *
	 * @param job
	 * @param jobDescription
	 * @param skillsRequired
	 * @param experienceRequired
	 * @param offerDeadlineDate
     * @throws InvalidInputException
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
			error = error + createJobPostingNullOfferDeadlineErrorMessage;
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
     * An instructor (or the department) can write a review to a student using this method.
     *
	 * @param reviewer
	 * @param reviewee
	 * @param content
     * @throws InvalidInputException
	 */
	public void createReview(Instructor reviewer, Student reviewee, String content, Job reviewedJob) throws InvalidInputException{

	    String error = "";

	    if (reviewer == null) {
	        error += createReviewNullInstructorError;
        }
        if (reviewee == null) {
	        error += createReviewNullStudentError;
        }

        if (content == null || content.trim().length() == 0) {
	        error += createReviewNullContentError;
        }

        if (reviewedJob == null) {
	        error += createReviewNullJobError;
        }

        if (error.length() > 0) {
	        throw new InvalidInputException(error);
        }

		Review review = new Review(content, reviewee, reviewedJob, reviewer);
	    department.addAllReview(review);
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
			if(st == applicant){
				validApplicant = true;
				break;
			}
		}

		if(validApplicant) {
			job.removeApplicant(applicant);
			job.addAllocatedStudent(applicant);
		} else {
			//// IMPLEMENT EXCEPTION \\\\\\\\\\\\\\\\\
			return;
		}

		PersistenceXStream.saveToXMLwithXStream(department);

	}
	

}
