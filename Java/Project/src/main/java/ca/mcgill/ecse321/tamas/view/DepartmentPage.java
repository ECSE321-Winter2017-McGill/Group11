package ca.mcgill.ecse321.tamas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class DepartmentPage extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;


    /**
     * Create the frame.
     */
    public DepartmentPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Position type");
        lblNewLabel.setBounds(10, 35, 90, 16);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(105, 30, 100, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblSkillsRequired = new JLabel("Skills required");
        lblSkillsRequired.setBounds(10, 63, 90, 16);
        contentPane.add(lblSkillsRequired);

        textField_1 = new JTextField();
        textField_1.setBounds(105, 58, 100, 26);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblExpRequired = new JLabel("Exp. required");
        lblExpRequired.setBounds(10, 91, 90, 16);
        contentPane.add(lblExpRequired);

        textField_2 = new JTextField();
        textField_2.setBounds(105, 86, 100, 26);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblJobDescription = new JLabel("Job description");
        lblJobDescription.setBounds(10, 119, 90, 16);
        contentPane.add(lblJobDescription);

        textField_3 = new JTextField();
        textField_3.setBounds(105, 114, 100, 26);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JButton btnPublishJobPosting = new JButton("Publish job posting");
        btnPublishJobPosting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnPublishJobPosting.setBounds(10, 175, 190, 29);
        contentPane.add(btnPublishJobPosting);

        JLabel lblStudentName = new JLabel("Student name");
        lblStudentName.setBounds(215, 63, 90, 16);
        contentPane.add(lblStudentName);

        textField_4 = new JTextField();
        textField_4.setBounds(310, 58, 130, 26);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        JLabel lblYear = new JLabel("Email");
        lblYear.setBounds(215, 91, 61, 16);
        contentPane.add(lblYear);

        JLabel lblPublishAJob = new JLabel("Publish A Job Posting");
        lblPublishAJob.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        lblPublishAJob.setBounds(23, 6, 165, 20);
        contentPane.add(lblPublishAJob);

        JLabel lblApplyForA = new JLabel("Register a Student");
        lblApplyForA.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        lblApplyForA.setBounds(260, 7, 140, 20);
        contentPane.add(lblApplyForA);

        textField_5 = new JTextField();
        textField_5.setBounds(310, 86, 130, 26);
        contentPane.add(textField_5);
        textField_5.setColumns(10);

        JRadioButton rdbtnUndergraduate = new JRadioButton("Undergraduate");
        rdbtnUndergraduate.setBounds(217, 115, 124, 23);
        contentPane.add(rdbtnUndergraduate);

        JRadioButton rdbtnGraduate = new JRadioButton("Graduate");
        rdbtnGraduate.setBounds(339, 115, 101, 23);
        contentPane.add(rdbtnGraduate);

        JLabel lblYear_1 = new JLabel("Year");
        lblYear_1.setBounds(215, 147, 61, 16);
        contentPane.add(lblYear_1);

        textField_6 = new JTextField();
        textField_6.setBounds(310, 142, 130, 26);
        contentPane.add(textField_6);
        textField_6.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Job preference");
        lblNewLabel_1.setBounds(215, 175, 90, 16);
        contentPane.add(lblNewLabel_1);

        textField_7 = new JTextField();
        textField_7.setBounds(310, 170, 130, 26);
        contentPane.add(textField_7);
        textField_7.setColumns(10);

        JLabel lblStudentId = new JLabel("Student ID");
        lblStudentId.setBounds(215, 35, 90, 16);
        contentPane.add(lblStudentId);

        textField_8 = new JTextField();
        textField_8.setBounds(310, 30, 130, 26);
        contentPane.add(textField_8);
        textField_8.setColumns(10);

        JLabel lblCourseNumber = new JLabel("Course number");
        lblCourseNumber.setBounds(10, 147, 90, 16);
        contentPane.add(lblCourseNumber);

        textField_9 = new JTextField();
        textField_9.setBounds(105, 142, 100, 26);
        contentPane.add(textField_9);
        textField_9.setColumns(10);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(323, 204, 117, 29);
        contentPane.add(btnRegister);

        JLabel lblNewLabel_2 = new JLabel("Apply for a Job");
        lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        lblNewLabel_2.setBounds(60, 208, 110, 16);
        contentPane.add(lblNewLabel_2);

        JLabel lblStudentName_1 = new JLabel("Student Name");
        lblStudentName_1.setBounds(10, 233, 61, 16);
        contentPane.add(lblStudentName_1);

        textField_10 = new JTextField();
        textField_10.setBounds(87, 228, 130, 26);
        contentPane.add(textField_10);
        textField_10.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Job");
        lblNewLabel_3.setBounds(10, 256, 61, 16);
        contentPane.add(lblNewLabel_3);

        textField_11 = new JTextField();
        textField_11.setBounds(87, 251, 130, 26);
        contentPane.add(textField_11);
        textField_11.setColumns(10);

        JButton btnNewButton = new JButton("Apply!");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(225, 233, 100, 43);
        contentPane.add(btnNewButton);
    }
}
