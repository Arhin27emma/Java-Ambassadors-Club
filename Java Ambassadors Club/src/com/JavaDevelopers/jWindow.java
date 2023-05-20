package com.JavaDevelopers;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class jWindow {
    public static void createWindow() {
        // Code for creating and displaying the JWindow goes here
        JWindow window1;
        window1 = new JWindow();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Displaying copyright info in JTable form
        JLabel label = new JLabel("OBJECT ORIENTED SOFTWARE DEVELOPMENT JAVA II");
        label.setFont(new Font("Tahoma", Font.BOLD,20));
        label.setLocation(0,100);
        panel.add(label);

        JLabel year = new JLabel("------------------Academic Year: 2023-------------------");
        year.setFont(new Font("Times", Font.PLAIN,15));
        panel.add(year);

        // Displaying copyright info in JTable form
        String[][] data = {
                {"GROUP MEMBERS", "INDEX NUMBER\n\t"},
                {"Emmanuel Arhinful", "01210403D"},
                {"Theresa Somiah", "01212563D"},
                {"Antwi Osborn Sarpong", "01210040D"},
                {"Asare Bernard", "01210270D"},
                {"Amada Mawumedoalor Kwaku", "01210849D"},
                {"Archer Roland", "01213582D"},
        };

        String[] colName = {"NAME", "INDEX NUMBER"};


        JTable table = new JTable(data,colName);
        table.setPreferredSize(new Dimension(500,300));

        table.setFont(new Font("Tahoma",Font.PLAIN,18));
        table.setRowHeight(400,500);
        table.setLocation(100,400);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLUE);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Tahoma", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(100,50));

        panel.setPreferredSize(new Dimension(600,400));
        panel.setBackground(Color.white);
        panel.add(table);
        window1.add(panel);
        window1.setBounds(300,130,600,400);
        window1.setVisible(true);

        //Mouse listener to click JWindow and dispose it
        window1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                window1.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                window1.dispose();
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
    }
    public static void main(String[] args) {
        jWindow.createWindow();
    }
}
