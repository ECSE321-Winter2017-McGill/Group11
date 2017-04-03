package ca.mcgill.ecse321.tamas.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.tamas.controller.TestDepartmentController;
import ca.mcgill.ecse321.tamas.controller.TestInstructorController;
import ca.mcgill.ecse321.tamas.controller.TestStudentController;

@RunWith(Suite.class)
@SuiteClasses({TestDepartmentController.class, TestInstructorController.class, TestStudentController.class})
public class AllTests {

}
