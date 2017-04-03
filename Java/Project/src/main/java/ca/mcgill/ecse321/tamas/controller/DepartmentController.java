package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.Course;
import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.PositionType;
import ca.mcgill.ecse321.tamas.model.Student;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

import java.sql.Date;
import java.util.Calendar;

public class DepartmentController {

    private final String createCourseNotIntegerNumberOfCreditsError = " Input a numeric number of credits!<br>";
    private final String createCourseNotIntegerNumberOfLabsError = " Input a numeric number of labs!<br>";
    private final String createCourseNotIntegerNumberOfTutorialsError = " Input a numeric number of tutorials!<br>";
    private final String createCourseNotIntegerNumberOfHoursError = " Input a numeric number of hours!<br>";
    private final String createCourseNotIntegerNumberOfStudentEnrolledError = " Input a numeric number of student enrolled!<br>";
    private final String createCourseNotIntegerNumberOfTAsNeededError = " Input a numeric number of TAs needed!<br>";
    private final String createCourseNotIntegerNumberOfGradersNeededError = " Input a numeric number of graders needed!<br>";
    private final String createCourseNotIntegerTAHourlyRateError = " Input a numeric TA hourly rate!<br>";
    private final String createCourseNotIntegerGraderHourlyRateError = " Input a numeric Grader hourly rate!<br>";

    private final String createCourseNotIntegerBudgetError = " Input a numeric budget!<br>";
    private final String createCourseNullCourseCodeError = " Course code cannot be empty!<br>";
    private final String createCourseNullCourseNameError = " Course name cannot be empty!<br>";
    private final String createCourseNullInstructorError = " Invalid instructor!<br>";

    private final String createCourseNegativeNumberOfCredits = " Input a non-negative number of credits!<br>";
    private final String createCourseNegativeNumberOfLabsError = " Input a non-negative number of labs!<br>";
    private final String createCourseNegativeNumberOfTutorialsError = " Input a non-negative number of tutorials!<br>";
    private final String createCourseNegativeNumberOfHoursError = " Input a non-negative number of hours!<br>";
    private final String createCourseNegativeNumberOfStudentEnrolledError = " Input a non-negative number of student enrolled!<br>";
    private final String createCourseNegativeNumberOfTAsNeededError = " Input a non-negative number of TAs needed!<br>";
    private final String createCourseNegativeNumberOfGradersNeededError = " Input a non-negative number of graders needed!<br>";
    private final String createCourseNegativeNumberTAHourlyRateError = " Input a non-negative TA hourly rate!<br>";
    private final String createCourseNegativeNumberGraderHourlyRateError = " Input a non-negative grader hourly rate!<br>";
    private final String createCourseNegativeBudgetError = " Input a non-negative budget!<br>";

    private final String createJobNullJobPositionTypeError = " Must select a Position!<br>";
    private final String createJobNullDateError = " Posting deadline cannot be empty!<br>";
    private final String createJobNullCourse = " Selected course cannot be empty!<br>";
    private final String createJobInvalidDateError = " Posting deadline cannot be before today!<br>";

    private final String createAllocationNullJobError = " Job cannot be empty!<br>";
    private final String createAllocationNullStudentError = " Student cannot be empty!<br>";

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
        } else if (numberOfCredits < 0) {
            error += createCourseNegativeNumberOfCredits;
        }

        if (!isNumberOfLabs) {
            error += createCourseNotIntegerNumberOfLabsError;
        } else if (numberOfLabs < 0) {
            error += createCourseNegativeNumberOfLabsError;
        }

        if (!isNumberOfTutorials) {
            error += createCourseNotIntegerNumberOfTutorialsError;
        } else if (numberOfTutorials < 0) {
            error += createCourseNegativeNumberOfTutorialsError;
        }

        if (!isNumberOfHours) {
            error += createCourseNotIntegerNumberOfHoursError;
        } else if (numberOfHours < 0) {
            error += createCourseNegativeNumberOfHoursError;
        }

        if (!isStudentsEnrolled) {
            error += createCourseNotIntegerNumberOfStudentEnrolledError;
        } else if (studentsEnrolled < 0) {
            error += createCourseNegativeNumberOfStudentEnrolledError;
        }

        if (!isTasNeeded) {
            error += createCourseNotIntegerNumberOfTAsNeededError;
        } else if (tasNeeded < 0) {
            error += createCourseNegativeNumberOfTAsNeededError;
        }

        if (!isGraderNeeded) {
            error += createCourseNotIntegerNumberOfGradersNeededError;
        } else if (gradersNeeded < 0) {
            error += createCourseNegativeNumberOfGradersNeededError;
        }

        if (!isTaHourlyRate) {
            error += createCourseNotIntegerTAHourlyRateError;
        } else if (taHourlyRate < 0) {
            error += createCourseNegativeNumberTAHourlyRateError;
        }

        if (!isGraderHourlyRate) {
            error += createCourseNotIntegerGraderHourlyRateError;
        } else if (graderHourlyRate < 0) {
            error += createCourseNegativeNumberGraderHourlyRateError;
        }

        if (!isBudget) {
            error += createCourseNotIntegerBudgetError;
        } else  if (budget < 0) {
            error += createCourseNegativeBudgetError;
        }

        if (code == null || code.length() == 0) {
            error += createCourseNullCourseCodeError;
        }
        if (name == null || name.length() == 0) {
            error += createCourseNullCourseNameError;
        }

        if (instructor == null) {
            error += createCourseNullInstructorError;
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
	public void createJob(PositionType posType, Date postingDeadlineDate, Course course) throws InvalidInputException {

	    String error = "";

        if (posType == null) {
            error += createJobNullJobPositionTypeError;
        }
        if (postingDeadlineDate == null) {
            error += createJobNullDateError;
        }
        if (course == null) {
            error += createJobNullCourse;
        }

//        Calendar c = Calendar.getInstance();
//        Date currentDate = new Date(c.getTimeInMillis());
//
//        if (postingDeadlineDate.before(currentDate)) {
//            error += createJobInvalidDateError;
//        }

        if (error.length()>0){
            throw new InvalidInputException(error);
        }

		Job job = new Job(posType,postingDeadlineDate, course);
		department.addAllJob(job);
		PersistenceXStream.saveToXMLwithXStream(department);

	}


	/**
	 * @param job
	 * @param student
	 */
	public void createAllocation(Job job, Student student) throws InvalidInputException {

        String error = "";

        if (job == null) {
            error += createAllocationNullJobError;
        }
        if (student == null) {
            error += createAllocationNullStudentError;
        }

        if (error.length()>0) {
            throw new InvalidInputException(error);
        }

        //TODO

		PersistenceXStream.saveToXMLwithXStream(department);

	}

	/**
	 * @param job
	 * @param student
	 */
	public void removeAllocation(Job job, Student student) throws InvalidInputException {

        String error = "";

        if (job == null) {
            error += createAllocationNullJobError;
        }
        if (student == null) {
            error += createAllocationNullStudentError;
        }

        if (error.length()>0) {
            throw new InvalidInputException(error);
        }

        //TODO

		PersistenceXStream.saveToXMLwithXStream(department);

	}

	/**
	 * @param job
	 * @param student
	 */
	public void createJobOffer(Job job, Student student) throws  InvalidInputException {

        String error = "";

        if (job == null) {
            error += createAllocationNullJobError;
        }
        if (student == null) {
            error += createAllocationNullStudentError;
        }

        if (error.length()>0) {
            throw new InvalidInputException(error);
        }

        //TODO

	    PersistenceXStream.saveToXMLwithXStream(department);
	}

	

}
