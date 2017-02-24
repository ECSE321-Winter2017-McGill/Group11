package ca.mcgill.ecse321.tamas.application;

import ca.mcgill.ecse321.tamas.view.DepartmentPage;

import java.awt.*;

/**
 * Created by matlapo on 2017-02-23.
 */
public class TamasMain {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DepartmentPage frame = new DepartmentPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
