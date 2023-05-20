package com.JavaDevelopers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ListMembers extends JFrame {


    private JPanel mainPanel;
    private JLabel regDescription;
    private JPanel tPanel;
    private JScrollPane tscrollPane;
    private JTable showTable;
    private JPanel seachPanel;
    private JLabel enterLabel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton deleteButton;
    private JSpinner txtId;

    DefaultTableModel tableModel;
    public static void main(String[] args) {
        new ListMembers();
    }

    ListMembers(){

        add(mainPanel);
        setBounds(100,150,1010,550);
        setVisible(true);

        try {

            // Creating a table to display all registered members

            String select = "SELECT * FROM signup";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int colCount = resultSetMetaData.getColumnCount();

            tableModel = (DefaultTableModel) showTable.getModel();

            // Styling the heading of the table
            JTableHeader header = showTable.getTableHeader();
            header.setBackground(Color.BLUE);
            header.setForeground(Color.WHITE);
            header.setFont(new Font("Tahoma", Font.BOLD, 15));
            header.setPreferredSize(new Dimension(100,50));

            // Center all cells in the table
            DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) showTable.getDefaultRenderer(Object.class);
            renderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Display information in JTable
            Object[] column = {"Id Number", "Full Name", "Username", "Password", "Email Address"};
            String[] colName = new String[colCount];
            for (int i = 0; i < colCount; i++) {
                colName[i] = resultSetMetaData.getColumnName(i+1);
                tableModel.setColumnIdentifiers(column);

                String No, Full_Name, Username, Password, Index_Number;
                while (resultSet.next()){
                    No = resultSet.getString(1);
                    Full_Name = resultSet.getString(2);
                    Username = resultSet.getString(3);
                    Password = resultSet.getString(4);
                    Index_Number = resultSet.getString(5);

                    String[] rows = {No,Full_Name,Username,Password,Index_Number};
                    tableModel.addRow(rows);

                }
                showTable.setModel(tableModel);
            }


        } catch (Exception e) {
            System.out.println("//");
        }


        // Search for a particular registered members
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String full_name = searchField.getText();
                search(full_name);

            }
        });

        // Delete a registered member
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/club","root","");
                    int row = showTable.getSelectedRow();
                    String value = (showTable.getModel().getValueAt(row, 0).toString());
                    String query = "DELETE FROM signup WHERE id = " + value;
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel) showTable.getModel();
                    model.setRowCount(0);
                    JOptionPane.showMessageDialog(null,"Deleted Successfully");


                }catch (Exception e2){
                    e2.printStackTrace();
                }

            }
        });
    }

    // Search for a particular registered members
    private void search(String full_name){
        TableRowSorter<DefaultTableModel> searchItem = new TableRowSorter<>(tableModel);
        showTable.setRowSorter(searchItem);
        searchItem.setRowFilter(RowFilter.regexFilter(full_name));
    }


}
