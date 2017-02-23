package ca.mcgill.ecse321.tamas.controller;

import ca.mcgill.ecse321.tamas.model.Course;
import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;
import ca.mcgill.ecse321.tamas.model.Job;
import ca.mcgill.ecse321.tamas.model.PositionType;
import ca.mcgill.ecse321.tamas.model.Student;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;

public class DepartmentController {

	private Department department;

	public DepartmentController(Department department){

		this.department = department;

	}

	/**
	 * @param courseStringInfo
	 * @param courseIntegerInfo
	 */
	public void createCourse(String[] courseStringInfo, int[] courseIntegerInfo){

		Course course = new Course(courseStringInfo[0],courseStringInfo[1], courseStringInfo[2], courseIntegerInfo[0], courseIntegerInfo[1],courseIntegerInfo[2],courseIntegerInfo[3],courseIntegerInfo[4],courseIntegerInfo[5],courseIntegerInfo[6],courseIntegerInfo[7],courseIntegerInfo[8],courseIntegerInfo[9]);
		department.addAllCourse(course);
		PersistenceXStream.saveToXMLwithXStream(department);


	}

	/**
	 * @param Code
	 * @param Name
	 * @param Semester
	 * @param NumberOfCredits
	 * @param NumberOfLabs
	 * @param NumberOfTutorials
	 * @param NumberOfHours
	 * @param StudentsEnrolled
	 * @param TasNeeded
	 * @param GradersNeeded
	 * @param TaHourlyRate
	 * @param GraderHourlyRate
	 * @param Budget
	 *
	 */
	public void createCourse(String code, String name, String semester, int numberOfCredits, int numberOfLabs, int numberOfTutorials, int numberOfHours, int studentsEnrolled, int tasNeeded, int gradersNeeded, int taHourlyRate, int graderHourlyRate, int budget){

		Course course = new Course(code, name, semester, numberOfCredits, numberOfLabs, numberOfTutorials, numberOfHours, studentsEnrolled, tasNeeded, gradersNeeded, taHourlyRate, graderHourlyRate, budget);
		department.addAllCourse(course);
		PersistenceXStream.saveToXMLwithXStream(department);


	}

	/**
	 * @param instructorStringInfo
	 * @param instructorID
	 *
	 */
	public void createInstructor(String[] instructorStringInfo, int instructorID){

		Instructor instructor = new Instructor(instructorStringInfo[0], instructorID, instructorStringInfo[1]);
		department.addAllInstructor(instructor);
		PersistenceXStream.saveToXMLwithXStream(department);


	}

	/**
	 * @param name
	 * @param instructorID
	 * @param email
	 *
	 */
	public void createInstructor(String name, int instructorID, String email){

		Instructor instructor = new Instructor(name, instructorID, email);
		department.addAllInstructor(instructor);
		PersistenceXStream.saveToXMLwithXStream(department);



	}

	/**
	 * @param studentID
	 * @param name
	 * @param email
	 * @param isGrad
	 * @param year
	 * @param jobPreference
	 * @param numberOfHours
	 *
	 */
	public void createStudent(int studentID, String name, String email, boolean isGrad, int year, String jobPreference, int numberOfHours){

		Student student = new Student(studentID, name, email, isGrad, year, jobPreference, numberOfHours);
		department.addAllStudent(student);
		PersistenceXStream.saveToXMLwithXStream(department);

	}

	/**
	 * @param studentStringInfo
	 * @param studentIntegerInfo
	 * @param isGrad
	 *
	 */
	public void createStudent(String[] studentStringInfo, int[] studentIntegerInfo, boolean isGrad){

		Student student = new Student(studentIntegerInfo[0], studentStringInfo[0], studentStringInfo[1], isGrad, studentIntegerInfo[1], studentStringInfo[3], studentIntegerInfo[2]);
		department.addAllStudent(student);
		PersistenceXStream.saveToXMLwithXStream(department);


	}

	/**
	 * @param posType
	 * @param course
	 *
	 */
	public void createJob(PositionType posType, Course course){

		Job job = new Job(posType, course);
		department.addAllJob(job);
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
