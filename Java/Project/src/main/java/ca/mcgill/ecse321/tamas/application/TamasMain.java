package ca.mcgill.ecse321.tamas.application;

import ca.mcgill.ecse321.tamas.model.Department;
import ca.mcgill.ecse321.tamas.persistence.PersistenceXStream;
import ca.mcgill.ecse321.tamas.view.BetaDepartmentPage;

import java.awt.*;

/**
 * Created by matlapo on 2017-02-23.
 */
public class TamasMain {
    private static String fileName = "data.xml";

    public static void main(String[] args) {

        final Department department = PersistenceXStream.initializeModelManager(fileName);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BetaDepartmentPage frame = new BetaDepartmentPage(department);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
