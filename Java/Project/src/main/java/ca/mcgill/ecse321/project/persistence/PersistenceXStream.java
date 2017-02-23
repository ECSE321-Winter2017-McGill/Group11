package ca.mcgill.ecse321.project.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.project.model.Course;
import ca.mcgill.ecse321.project.model.Department;
import ca.mcgill.ecse321.project.model.Instructor;
import ca.mcgill.ecse321.project.model.Job;
import ca.mcgill.ecse321.project.model.Review;
import ca.mcgill.ecse321.project.model.Student;

public class PersistenceXStream {

	 private static XStream xstream = new XStream();
	    private static String filename = "data.xml";

	    public static Department initializeModelManager(String fileName) {
	        // Initialization for persistence
	        Department department;
	        setFilename(fileName);
	        setAlias("Course", Course.class);
	        setAlias("Instructor", Instructor.class);
	        setAlias("Job", Job.class);
	        setAlias("Review", Review.class);
	        setAlias("Student", Student.class);

	        // load model if exists, create otherwise
	        File file = new File(fileName);
	        if (file.exists()) {
	            department = (Department) loadFromXMLwithXStream();
	        } else {
	            try {
	                file.createNewFile();
	            } catch (IOException e) {
	                e.printStackTrace();
	                System.exit(1);
	            }
	            department = new Department();
	            saveToXMLwithXStream(department);
	        }
	        return department;

	    }

	    public static boolean saveToXMLwithXStream(Object obj) {
	        xstream.setMode(XStream.ID_REFERENCES);
	        String xml = xstream.toXML(obj); // save our xml file

	        try {
	            FileWriter writer = new FileWriter(filename);
	            writer.write(xml);
	            writer.close();
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public static Object loadFromXMLwithXStream() {
	        xstream.setMode(XStream.ID_REFERENCES);
	        try {
	            FileReader fileReader = new FileReader(filename); // load our xml file
	            return xstream.fromXML(fileReader);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    public static void setAlias(String xmlTagName, Class<?> className) {
	        xstream.alias(xmlTagName, className);
	    }

	    public static void setFilename(String fn) {
	        filename = fn;
	    }

	
}
