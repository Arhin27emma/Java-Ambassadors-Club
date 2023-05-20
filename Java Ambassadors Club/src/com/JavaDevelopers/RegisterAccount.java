package com.JavaDevelopers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterAccount extends JFrame {

    private JPanel panel;
    private JLabel textMessage, label;
    private JButton createAccountButton;
    JMenuBar menuBar;
    JMenu file, help;
    JMenuItem New, open, exit, about;


    public static void main(String[] args) {
        new RegisterAccount();
    }



    RegisterAccount(){
        //JPanel
        panel.setLayout(new GridBagLayout());

        // Adding a background image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Images/bgImage.jpg"));
        Image background = icon.getImage().getScaledInstance(1500,1000, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(background);
        JLabel bgImage = new JLabel(imageIcon);
        bgImage.setBounds(0, 0, 1500,1000);
        //JPanel just added a background image
        panel.add(bgImage);

        // Adding a JMenuBar to the application
        menuBar = new JMenuBar();
        menuBar.setBounds(0,0,1550,30);
        bgImage.add(menuBar);

        // Adding JMenu's to the JMenuBar
        file = new JMenu("File");
        menuBar.add(file);
        help = new JMenu("Help");
        menuBar.add(help);

        // Adding JMenu Items to the JMenu(file, help)
        //File contains three menuItems new, open, exit.
        New = new JMenuItem("New");
        file.add(New);
        open = new JMenuItem("Open");
        file.add(open);
        exit = new JMenuItem("Exit");
        file.add(exit);
        //Help contains only about menuItem
        about = new JMenuItem("About");
        help.add(about);


        // Adding Mnemonics to the JMenu's
        file.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_H);

        // Setting Accelerator KeyStroke Event for JMenuItems
        New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        // Open to display registered members
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListMembers();
            }
        });

        // AWT action event(for exit to close application)
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // AWT action event for about
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jWindow.createWindow();
            }
        });

        // Welcome message
        textMessage.setBounds(100,35,1000,50);
        textMessage.setForeground(Color.WHITE);
        bgImage.add(textMessage);

        // Create button to display registration layout  component
        createAccountButton.setBounds(950,620,200,50);
        createAccountButton.setBackground(Color.WHITE);
        createAccountButton.setForeground(Color.BLACK);
        bgImage.add(createAccountButton);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loginSignUp();
                conn.getConnection();
                conn conn = new conn();
                conn.createNewTable();
            }
        });

        Image icons = Toolkit.getDefaultToolkit().getImage("Images/login5.png");
        setIconImage(icons);

        //JFrame
        setBounds(0,0,1500,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);

        // Display welcome message animation
        while (true){
            textMessage.setVisible(false);
            try {
                Thread.sleep(800);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            textMessage.setVisible(true);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
