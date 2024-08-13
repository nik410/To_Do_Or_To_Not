/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ToDoOrToNot;

import java.util.Date;

/**
 *
 * @author DARETHEVIL SANKU
 */
public class TestingClass {
    public static void main(String args[]){
        
        Date date = new Date();
        System.out.println(date +" :date");
//        String dateStr = date.toString();
////        System.out.println(dateStr +" : dateAsStr");
//        
//        String dateToBeInsertedInTable = dateStr.substring(8, 10) + " " +
//                dateStr.substring(4, 7) + " " +
//                dateStr.substring(dateStr.length() - 4, dateStr.length());
//        
//        System.out.println(dateToBeInsertedInTable.length() + " length printed in the table");
//        
////        System.out.println(dateStr.length() + "length");
////        Date dateStrToDate = (Date) (dateStr);        
////        int compareDates = date.compareTo(dateStrToDate);
        
        
        String time = date.toString().substring(11, 19);
        System.out.println( "String time: "+time);
        
        
        
    
    }
}
