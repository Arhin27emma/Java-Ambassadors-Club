package com.JavaDevelopers;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class loginSignUp extends JFrame{
    private JPanel panel;
    private JLabel imageIcon;
    private JPanel loginPanel;
    private JPanel signupPanel;
    private JLabel UserName;
    private JTextField UserField;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton signButton;
    private JPanel signUpPanel;
    private JLabel fullName;
    private JTextField nameField;
    private JLabel signUsername;
    private JTextField signUserField;
    private JLabel signPassword;
    private JPasswordField signPassField;
    private JLabel signId;
    private JTextField signIdField;
    private JButton cancelBtn;
    private JButton signCancel;


    public static void main(String[] args) {
        new loginSignUp();

    }

    loginSignUp(){
        add(panel);
        setVisible(true);
        setBounds(100,130,1100,550);

        signCancel.setVisible(false);
        cancelBtn.setVisible(false);

        // Signup cancel button action to dispose application
        signCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // login Cancel button action to dispose application
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }
        });

        //signup MouseListener to display only signup when
        nameField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signupPanel.setVisible(true);
                loginPanel.setVisible(false);
                signCancel.setVisible(true);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //
            }
        });

        //Login MouseListeners to display login
        UserField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signupPanel.setVisible(false);
                loginPanel.setVisible(true);
                cancelBtn.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //
            }
        });


        // Login action button to log in
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String logNameField = UserField.getText();
                    String logPasswordText = String.valueOf(passwordField1.getPassword());

                    if (logNameField.isEmpty() || logPasswordText.isEmpty()){
                        JOptionPane.showMessageDialog(
                                null,
                                "All fields required",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    else {

                        String select = "SELECT * FROM signup WHERE Username = ? AND password = ?";
                        PreparedStatement statement = conn.getConnection().prepareStatement(select);

                        statement.setString(1, logNameField);
                        statement.setString(2, logPasswordText);

                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(null, "Login successfully");
                            setVisible(false);
                            new ListMembers();
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Username and Password are invalid",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Signup button action to signup
        signButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String fName = nameField.getText();
                String SUsername = signUserField.getText();
                String signPasswordText = String.valueOf(signPassField.getPassword());
                String id_no = signIdField.getText();


                if (fName.isEmpty() || signPasswordText.isEmpty() || id_no.isEmpty() || SUsername.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "All fields required",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE
                    );

                }

                else {
                    try {
                        // Checking for duplicate username
                        String multiple = "SELECT * FROM signup WHERE Full_Name = ? AND Username = ?";
                        PreparedStatement statement = conn.getConnection().prepareStatement(multiple);

                        statement.setString(1, fName);
                        statement.setString(2, SUsername);
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(null, "Name already exit");
                            signUpPanel.setVisible(true);
                            loginPanel.setVisible(false);
                        }

                        else {

                            // Inserting into the database
                            String insert = "INSERT INTO signup VALUES(?,?,?,?,?)";
                            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(insert);

                            preparedStatement.setInt(1, 0);
                            preparedStatement.setString(2, fName);
                            preparedStatement.setString(3, SUsername);
                            preparedStatement.setString(4, signPasswordText);
                            preparedStatement.setString(5, id_no);

                            int dataInserted = preparedStatement.executeUpdate();

                            if (dataInserted > 0) {
                                JOptionPane.showMessageDialog(null, "Data Inserted successfully");
                                signUpPanel.setVisible(false);
                                loginPanel.setVisible(true);

                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Data Inserted Unsuccessfully",
                                        "Error inserting",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                        }
                        } catch(Exception ae){
                            ae.printStackTrace();
                        }

                }


            }


        });

    }



}





















