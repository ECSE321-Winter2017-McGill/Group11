package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.Course;
import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.PositionType;
import ca.mcgill.ecse321.tamas.model.Student;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

import java.sql.Date;

public class DepartmentController {

    private final String createCourseNotIntegerNumberOfCreditsError = " Input a numeric number of credits!";
    private final String createCourseNotIntegerNumberOfLabsError = " Input a numeric number of labs!";
    private final String createCourseNotIntegerNumberOfTutorialsError = " Input a numeric number of tutorials!";
    private final String createCourseNotIntegerNumberOfHoursError = " Input a numeric number of hours!";
    private final String createCourseNotIntegerNumberOfStudentEnrolledError = " Input a numeric number of student enrolled!";
    private final String createCourseNotIntegerNumberOfTAsNeededError = " Input a numeric number of TAs needed!";
    private final String createCourseNotIntegerNumberOfGradersNeededError = " Input a numeric number of graders needed!";
    private final String createCourseNotIntegerTAHourlyRateError = " Input a numeric TA hourly rate!";
    private final String createCourseNotIntegerGraderHourlyRateError = " Input a numeric Grader hourly rate!";
    private final String createCourseNotIntegerBudgetError = " Input a numeric budget!";
    private final String createCourseNullCourseCodeError = " Course code cannot be empty!";
    private final String createCourseNullCourseNameError = " Course name cannot be empty!";

	private Department department;

	public DepartmentController(Department department){

		this.department = department;

	}


	public void createCourse(String code, String name, String semester, String numberOfCreditsString, String numberOfLabsString, String numberOfTutorialsString, String numberOfHoursString, String studentsEnrolledString, String tasNeededString, String gradersNeededString, String taHourlyRateString, String graderHourlyRateString, String budgetString, Instructor instructor) throws InvalidInputException {

	    String error = "";
	    int numberOfCredits = -999;
	    int numberOfLabs = -999;
	    int numberOfTutorials = -999;
	    int numberOfHours = -999;
	    int studentsEnrolled = -999;
	    int tasNeeded = -999;
	    int gradersNeeded = -999;
	    int taHourlyRate = -999;
	    int graderHourlyRate = -999;
	    int budget = -999;

        boolean isNumberOfCredits = true;
        boolean isNumberOfLabs = true;
        boolean isNumberOfTutorials = true;
        boolean isNumberOfHours = true;
        boolean isStudentsEnrolled = true;
        boolean isTasNeeded = true;
        boolean isGraderNeeded = true;
        boolean isTaHourlyRate = true;
        boolean isGraderHourlyRate = true;
        boolean isBudget = true;

        try{
            numberOfCredits = Integer.parseInt(numberOfCreditsString);
        }catch (NumberFormatException e){
            isNumberOfCredits = false;
        }

        try{
            numberOfLabs = Integer.parseInt(numberOfLabsString);
        }catch (NumberFormatException e){
            isNumberOfLabs = false;
        }

        try{
            numberOfTutorials = Integer.parseInt(numberOfTutorialsString);
        }catch (NumberFormatException e){
            isNumberOfTutorials = false;
        }

        try{
            numberOfHours = Integer.parseInt(numberOfHoursString);
        }catch (NumberFormatException e){
            isNumberOfHours = false;
        }

        try{
            studentsEnrolled = Integer.parseInt(studentsEnrolledString);
        }catch (NumberFormatException e){
            isStudentsEnrolled = false;
        }

        try{
            tasNeeded = Integer.parseInt(tasNeededString);
        }catch (NumberFormatException e){
            isTasNeeded = false;
        }

        try{
            gradersNeeded = Integer.parseInt(gradersNeededString);
        }catch (NumberFormatException e){
            isGraderNeeded = false;
        }

        try{
            taHourlyRate = Integer.parseInt(taHourlyRateString);
        }catch (NumberFormatException e){
            isTaHourlyRate = false;
        }

        try{
            graderHourlyRate = Integer.parseInt(graderHourlyRateString);
        }catch (NumberFormatException e){
            isGraderHourlyRate = false;
        }

        try{
            budget = Integer.parseInt(budgetString);
        }catch (NumberFormatException e){
            isBudget = false;
        }


        if (!isNumberOfCredits) {
            error += createCourseNotIntegerNumberOfCreditsError;
        }
        if (!isNumberOfLabs) {
            error += createCourseNotIntegerNumberOfLabsError;
        }
        if (!isNumberOfTutorials) {
            error += createCourseNotIntegerNumberOfTutorialsError;
        }
        if (isNumberOfHours) {
            error += createCourseNotIntegerNumberOfHoursError;
        }
        if (!isStudentsEnrolled) {
            error += createCourseNotIntegerNumberOfStudentEnrolledError;
        }
        if (!isTasNeeded) {
            error += createCourseNotIntegerNumberOfTAsNeededError;
        }
        if (!isGraderNeeded) {
            error += createCourseNotIntegerNumberOfGradersNeededError;
        }
        if (!isTaHourlyRate) {
            error += createCourseNotIntegerTAHourlyRateError;
        }
        if (!isGraderHourlyRate) {
            error += createCourseNotIntegerGraderHourlyRateError;
        }
        if (!isBudget) {
            error += createCourseNotIntegerBudgetError;
        }
        if (code == null || code.length() == 0) {
            error += createCourseNullCourseCodeError;
        }
        if (name == null || name.length() == 0) {
            error += createCourseNullCourseNameError;
        }

        if (error.length()>0){
            throw new InvalidInputException(error);
        }

		Course course = new Course(code, name, semester, numberOfCredits, numberOfLabs, numberOfTutorials, numberOfHours, studentsEnrolled, tasNeeded, gradersNeeded, taHourlyRate, graderHourlyRate, budget, instructor);
		department.addAllCourse(course);
		PersistenceXStream.saveToXMLwithXStream(department);
	}

	/**
	 * @param posType
	 * @param postingDeadlineDate
	 * @param course
	 *
	 */
	public void createJob(PositionType posType, Date postingDeadlineDate, Course course){

		Job job = new Job(posType,postingDeadlineDate, course);
		department.addAllJob(job);
		PersistenceXStream.saveToXMLwithXStream(department);

	}

	public void registerAStudent(int studentID, String studentName, String email, Boolean isGrad, int year, String jobPreference, int numberOfHours) {

	    Student student = new Student(studentID,studentName,email,isGrad,year,jobPreference,numberOfHours);
	    department.addAllStudent(student);
	    PersistenceXStream.saveToXMLwithXStream(department);
    }

	/**
	 * @param job
	 * @param student
	 */
	public void createAllocation(Job job, Student student){


		PersistenceXStream.saveToXMLwithXStream(department);

	}

	/**
	 * @param job
	 * @param student
	 */
	public void removeAllocation(Job job, Student student){
		PersistenceXStream.saveToXMLwithXStream(department);

	}

	/**
	 * @param job
	 * @param student
	 */
	public void createJobOffer(Job job, Student student){

	    PersistenceXStream.saveToXMLwithXStream(department);
	}

	

}
