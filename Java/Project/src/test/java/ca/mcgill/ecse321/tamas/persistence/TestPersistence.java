package ca.mcgill.ecse321.tamas.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.model.Instructor;

public class TestPersistence {
	private Department department;

	@Before
	public void setUp() throws Exception {
		department = new Department();

		Instructor instructor1 = new Instructor("James",123456,"james@mcgill.ca");
		Instructor instructor2 = new Instructor("Elizabeth",654321,"elizabeth@mcgill.ca");

		department.addAllInstructor(instructor1);
		department.addAllInstructor(instructor2);

	}

	@After
	public void tearDown() throws Exception {
		department.delete();
	}

	@Test
	public void test() {
		// initialize model file
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
		// save model that is loaded during test setup
		if (!PersistenceXStream.saveToXMLwithXStream(department))
			fail("Could not save file.");

		// clear the model in memory
		department.delete();
		assertEquals(0, department.getAllInstructors().size());

		// load model
		department = (Department) PersistenceXStream.loadFromXMLwithXStream();
		if (department == null)
			fail("Could not load file.");

		//check Instructor
		assertEquals(2, department.getAllInstructors().size());
	    assertEquals("James", department.getAllInstructor(0).getName());
	    assertEquals("Elizabeth", department.getAllInstructor(1).getName());
	}

}
