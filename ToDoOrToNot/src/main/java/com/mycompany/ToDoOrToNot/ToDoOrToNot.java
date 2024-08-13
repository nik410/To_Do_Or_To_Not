/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ToDoOrToNot;

import java.awt.*;
import java.util.*;
import java.io.FileOutputStream;
import java.io.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DARETHEVIL SANKU
 */
public class ToDoOrToNot extends javax.swing.JFrame {

    /**
     * Creates new form Main_Page
     * 
     */
    public ToDoOrToNot() {
        initComponents();
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setDataInTable();
    }
    
    private void setDataInTable() {
    String[] columnName = {"Task", "Time", "Date"};
    DefaultTableModel dtm = new DefaultTableModel(columnName, 0);
    jTable1.setModel(dtm);

    try (FileInputStream fis = new FileInputStream("tasks.txt");
         Scanner sc = new Scanner(fis)) {

        while (sc.hasNextLine()) {
            String taskWithTime = sc.nextLine();
            // Task length will be string length - time length and - date length - 2 for spaces
            String task = taskWithTime.substring(0, taskWithTime.length() - 11 - 8 - 2);
            String time = taskWithTime.substring(taskWithTime.length() - 11 - 8 - 2, taskWithTime.length() - 8 - 4);
            String date = taskWithTime.substring(taskWithTime.length() - 8 - 4, taskWithTime.length());
            Vector<String> singleTask = new Vector<>();
            singleTask.add(task);
            singleTask.add(time);
            singleTask.add(date);
            dtm.addRow(singleTask);
        }

    } catch (FileNotFoundException ex) {
        Logger.getLogger(ToDoOrToNot.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(ToDoOrToNot.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        jTable1.repaint(); // or jTable1.revalidate()
    }
}
    
    public void deleteTask(){
        int selectedRow = jTable1.getSelectedRow();
//        JOptionPane.showMessageDialog(jButton2, "Row is" + selectedRow);
        
        if(selectedRow != -1){
            
            ArrayList<String> taskList = new ArrayList();
            
            try {
                FileOutputStream fos = new FileOutputStream("temp.txt");
                PrintStream p = new PrintStream(fos);
                
                FileInputStream fis = new FileInputStream("tasks.txt");
                Scanner sc = new Scanner(fis);
                int index = 0;
                while(true){
                    try{
                    String s = sc.nextLine();
                    if(index != selectedRow){
                        p.println(s);
                        taskList.add(s);
                        
                      }
                    index ++;
                   }
                    catch(Exception e){
                        break;
                    }                
                }              
                fos.close();
                p.close();
                fis.close();
                sc.close();
                
                File fTask = new File("tasks.txt");
                fTask.delete();
                File temp = new File("temp.txt");
                File newTask = new File("tasks.txt");
                temp.renameTo(newTask);
//                JOptionPane.showMessageDialog(jButton2, "Row is" + taskList);                
                
            } catch (Exception ex) {
                Logger.getLogger(ToDoOrToNot.class.getName()).log(Level.SEVERE, null, ex);
            }       
        }  
    }
    
    public void addTask(){
    
    String getTaskFromTheJTextField = jTextField1.getText();
        Date date = new Date();
        
        String dateAsString = date.toString();
       
         String dateToBeInsertedInTable = dateAsString.substring(8, 10) + " " +
                dateAsString.substring(4, 7) + " " +
                dateAsString.substring(dateAsString.length() - 4, dateAsString.length());
        //  Length of dateToBeInsertedInTable string is 11
        // This is a important as while printing or showing in table 
        // We have to preprocess the string to show correct content of columns
        
//        int hours = date.getHours();
//        int minutes = date.getMinutes();
//        int secs = date.getSeconds();
//        
        String time =dateAsString.substring(11, 19);
        
         //  Length of time string is 8
        // This is a imporyant as while printing or showing in table 
        // We have to preprocess the string to show correct content of columns

        String toBeEntered = getTaskFromTheJTextField + " " 
                            + time + " " 
                            + dateToBeInsertedInTable;
        
        

        FileOutputStream fos;
        try {
            fos = new FileOutputStream("tasks.txt", true);
            PrintStream p = new PrintStream(fos);
            
            if(getTaskFromTheJTextField.equals("")){
                JOptionPane.showMessageDialog(jButton1, "Error: Empty String");
                return;
            }
            p.println(toBeEntered);
            
            // Clearing out the text from textField
            
            jTextField1.setText("");
            
            
            
            JOptionPane.showMessageDialog(jButton2, "Added!!! " + toBeEntered);
            p.close();
            fos.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ToDoOrToNot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ToDoOrToNot.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    
//    private void setDataInTable(){
//        String[] columnName = {"Task", "Time"};
//        DefaultTableModel dtm = new DefaultTableModel(columnName, 0);
//        jTable1.setModel(dtm);
//         
//         int rowCount = dtm.getRowCount();
//         while(rowCount -- > 0){
//             dtm.removeRow(0);
//         }
//
//        FileInputStream fis;
//        try {
//           
//            fis = new FileInputStream("tasks.txt");
//            Scanner sc = new Scanner(fis);
//            while(true){
//                
//                try{
//                    
//                    while(true){
//                        
//                        Vector<String> singleTask = new Vector<String>();
//                        String taskWithTime = sc.nextLine();
//                        String task = taskWithTime.substring(0, taskWithTime.length() - 8); 
//                        String time = taskWithTime.substring(taskWithTime.length() - 8, taskWithTime.length()); 
//                        singleTask.add(task);
//                        singleTask.add(time);
//                        
//                        dtm.addRow(singleTask);
//                        
//                    }
//                   
//                }
//                catch(Exception e){
//                    break;
//                }
//            }
//             sc.close();
//             fis.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(SamayAayojak.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(SamayAayojak.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//          
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("To Do or To Not");

        jButton1.setBackground(new java.awt.Color(102, 204, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("To do Or To not");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel2.setBackground(new java.awt.Color(102, 255, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Task:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jTextField1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 228, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        deleteTask();
        setDataInTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
//        jTextField1.setFont();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addTask();
        // Filling it in the table
        setDataInTable();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ToDoOrToNot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ToDoOrToNot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ToDoOrToNot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ToDoOrToNot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ToDoOrToNot().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
