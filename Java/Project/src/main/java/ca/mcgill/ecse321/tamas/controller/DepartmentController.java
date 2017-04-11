package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.*;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

import java.sql.Date;
import java.util.ArrayList;
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

    private final String createCourseInvalidHourError = "Please input valid number of hourse ranging between 45 hours to 180 hours!<br>";

    private final String createJobNullJobPositionTypeError = " Must select a Position!<br>";
    private final String createJobNullDateError = " Posting deadline cannot be empty!<br>";
    private final String createJobNullCourse = " Selected course cannot be empty!<br>";
    private final String createJobInvalidDateError = " Posting deadline cannot be before today!<br>";

    private final String createAllocationNullJobError = " Job cannot be empty!<br>";
    private final String createAllocationNullStudentError = " Select student from the allocated table!<br>";
    private final String createAllocationStudentAlreadyAllocatedError = " Student is already allocated!<br>";
    private final String createAllocationStudentNotApplicantError = " Student has not applied to this job!<br>";
    private final String createAllocationStudentMaxAllocationError = " Allocation maxed out! Please wait for availability!<br>";
    private final String createAllocationWrongJobStatusError = " Job cannot be allocated at the moment!<br>";
    private final String removeAllocationStudentNotAllocatedError = " Student is not allocated for this job!<br>";

    private final String createJobOfferOfferNotAddedError = " Offer already made to this student!<br>";

    private final String autoAllocationNullJobError = " Job cannot be empty!<br>";
    private final String autoAllocationWrongJobStatusError = " Job cannot be allocated at the moment!<br>";
    private final String autoAllocationFullError = " Allocation for current job is full!<br>";

    private final String finalizeAllocationNullJobError = " Job cannot be empty!<br>";


    private Department department;

	public DepartmentController(Department department){

		this.department = department;

	}

    /**
     * This methods is used to create a course by the department. All inputs are strings but most will be converted to integers
     * and therefore they must be convertible to type int (and must be strictly greater than 0). Moreover, the number of hours must
     * be in the range ]45,180[ and a non-empty course name must be provided along with the instructor teaching the course.
     *
     * @param code
     * @param name
     * @param semester
     * @param numberOfCreditsString
     * @param numberOfLabsString
     * @param numberOfTutorialsString
     * @param numberOfHoursString
     * @param studentsEnrolledString
     * @param tasNeededString
     * @param gradersNeededString
     * @param taHourlyRateString
     * @param graderHourlyRateString
     * @param budgetString
     * @param instructor
     * @throws InvalidInputException
     */
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

        if(isNumberOfHours && (numberOfHours < 45 || numberOfHours >180)){
            error += createCourseInvalidHourError;
        }

        if (error.length()>0){
            throw new InvalidInputException(error);
        }


		Course course = new Course(code, name, semester, numberOfCredits, numberOfLabs, numberOfTutorials, numberOfHours, studentsEnrolled, tasNeeded, gradersNeeded, taHourlyRate, graderHourlyRate, budget, instructor);
		department.addAllCourse(course);
		PersistenceXStream.saveToXMLwithXStream(department);
	}

    /**
     * Can create (initialize) a TA or grader job using the method. The posting deadline cannot be before the job is
     * created.
     *
     * @param posType
     * @param postingDeadlineDate
     * @param course
     * @throws InvalidInputException
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

        Calendar c = Calendar.getInstance();
        long current = c.getTimeInMillis();

        if (postingDeadlineDate != null && current > postingDeadlineDate.getTime()) {
            error += createJobInvalidDateError;
        }

        if (error.length()>0){
            throw new InvalidInputException(error);
        }

		Job job = new Job(posType,postingDeadlineDate, course);
		department.addAllJob(job);
		PersistenceXStream.saveToXMLwithXStream(department);

	}


    /**
     *
     * The department can create an allocation by linking a job and a student that applied for the job through
     * this method.
     *
     * @param job
     * @param student
     * @throws InvalidInputException
     */
	public void createAllocation(Job job, Student student) throws InvalidInputException {

        String error = "";

        if (job == null) {
            error += createAllocationNullJobError;
        }
        if (student == null) {
            error += createAllocationNullStudentError;
        }

        if (job != null) {
            for (Student s : job.getAllocatedStudent()) {
                if (s.getStudentID() == student.getStudentID()) {
                    error += createAllocationStudentAlreadyAllocatedError;
                }
            }

            boolean isApplicant = false;
            for (Student s : job.getApplicant()) {
                if (s.getStudentID() == student.getStudentID()) {
                    isApplicant = true;
                    break;
                }
            }

            if(!isApplicant){
                error += createAllocationStudentNotApplicantError;
            }

            if (job.getState() != JobStatus.AppliedTo && job.getState() != JobStatus.Allocated) {
                error += " ("+job.getState().toString()+")" + createAllocationWrongJobStatusError;
            }

            int numberOfAllocated = 0;
            if(job.getPosType() == PositionType.Grader){
                numberOfAllocated = job.getCorrespondingCourse().getGradersNeeded();
            }else{
                numberOfAllocated = job.getCorrespondingCourse().getTasNeeded();

            }

            if(job.getAllocatedStudent().size() +job.getOfferReceiver().size()+ job.getEmployee().size()>= numberOfAllocated){
                error += createAllocationStudentMaxAllocationError;
            }
        }

        if (error.length()>0) {
            throw new InvalidInputException(error);
        }

        job.addAllocatedStudent(student);
        job.removeApplicant(student);

		PersistenceXStream.saveToXMLwithXStream(department);

	}

    /**
     * The department can remove an allocation by removing the student that was already allocated for the particular
     * job through this method.
     *
     * @param job
     * @param student
     * @throws InvalidInputException
     */
	public void removeAllocation(Job job, Student student) throws InvalidInputException {

        String error = "";
        boolean studentNotFound = true;

        if (job == null) {
            error += createAllocationNullJobError;
        }
        if (student == null) {
            error += createAllocationNullStudentError;
        }

        //cannot do that if the job is null
        if (job != null && student != null) {
            for (Student s : job.getAllocatedStudent()) {
                if (s.getStudentID() == student.getStudentID()) {
                    studentNotFound = false;
                }
            }
        }

        if (job != null && student != null &&studentNotFound) {
            error += removeAllocationStudentNotAllocatedError;
        }

        if (error.length()>0) {
            throw new InvalidInputException(error);
        }

        job.removeAllocatedStudent(student);

        job.addApplicant(student);

		PersistenceXStream.saveToXMLwithXStream(department);

	}


	public void autoAllocation(Job job) throws InvalidInputException {

        String error = "";
        if (job == null) {
            error += autoAllocationNullJobError;
        } else {

            if (job.getState() != JobStatus.AppliedTo && job.getState() != JobStatus.Allocated) {
                error += " ("+job.getState().toString()+")" +  autoAllocationWrongJobStatusError;
            }
        }

        if (error.length()>0) {
            throw new InvalidInputException(error);
        }

        ArrayList<Student> tmpUndergraduateList = new ArrayList<>();
        ArrayList<Student> tmpGraduateList = new ArrayList<>();

        for(Student s: job.getAllocatedStudent()) {
            job.addApplicant(s);
        }

        ArrayList<Student> studentList = new ArrayList<>(job.getAllocatedStudent());

        for(Student s: studentList){
            job.removeAllocatedStudent(s);
        }

        for(Student s: job.getApplicant()){
            if(s.isIsGrad() == true){
                tmpGraduateList.add(s);
            }else{
                tmpUndergraduateList.add(s);
            }
        }

        int numberOfAllocated = 0;
        if (job.getPosType() == PositionType.Grader){
            numberOfAllocated = job.getCorrespondingCourse().getGradersNeeded();
        } else {
            numberOfAllocated = job.getCorrespondingCourse().getTasNeeded();
        }

        for (int i = 0; job.getAllocatedStudent().size() + job.getOfferReceiver().size() + job.getEmployee().size() < numberOfAllocated; i++){
            if (tmpGraduateList.size() > 0) {
                try {
                    createAllocation(job, tmpGraduateList.get(0));
                }catch (InvalidInputException e){
                    e.getMessage();
                }
                tmpGraduateList.remove(0);
            } else if (tmpUndergraduateList.size() > 0){
                try {
                    createAllocation(job, tmpUndergraduateList.get(0));
                }catch (InvalidInputException e){

                }
                tmpUndergraduateList.remove(0);
            } else {
                break;
            }
        }

        PersistenceXStream.saveToXMLwithXStream(department);

    }


    public void finalizeAllocation(Job job) throws  InvalidInputException {

        String error = "";
        boolean wasAdded = false;

        if (job == null) {
            error += finalizeAllocationNullJobError;
        } else {
            int numberOfAllocated = 0;
            if (job.getPosType() == PositionType.Grader) {
                numberOfAllocated = job.getCorrespondingCourse().getGradersNeeded();
            } else {
                numberOfAllocated = job.getCorrespondingCourse().getTasNeeded();
            }

            if (job.getAllocatedStudent().size() +job.getOfferReceiver().size() + job.getEmployee().size() < numberOfAllocated){
                error += " Allocate "+ (numberOfAllocated - job.getAllocatedStudent().size() +job.getOfferReceiver().size()+ job.getEmployee().size()) + " more students!<br>" ;
            }
        }

        if (error.length()>0) {
            throw new InvalidInputException(error);
        }

        ArrayList<Student> tmpAllocatedStudentList = new ArrayList<>(job.getAllocatedStudent());

        for(Student s: tmpAllocatedStudentList){
            job.removeAllocatedStudent(s);
            job.addOfferReceiver(s);
        }

        job.setState(JobStatus.Offered);

        PersistenceXStream.saveToXMLwithXStream(department);
    }

}
