/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import Model.Contact;
import Model.Contractor;
import Model.Freelancer;
import Model.User;

import Model.UserAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import javafx.scene.control.SpinnerValueFactory;

/**
 *
 * @author Dinesh
 */
public class UpdateRecord {
    public final int  FREELANCER = 1;
    public final int  CONTRACTOR = 0;
    
    
    public Object getUpdateRecord(String username){       
       
       int userID = 0; 
       int contactID = 0;
       int userType = -1;
       Object obj = new Object();
       UserAccount userAccount = new UserAccount();
       DBConnection conn = new DBConnection();
       conn.connectDatabase();     
       String query = "select userID,userType, User.userName from User where User.userName ="+ "'" + username + "'";       
       conn.setStatement( query );
       ResultSet sqlResult = conn.getStatement();
        
     
       
       try{
            while( sqlResult.next()){
                userID = sqlResult.getInt("userID");
                userType = sqlResult.getInt("userType");
            }
            
            
            userAccount.setUserID(userID);
            userAccount.setUserType(userType);
        }catch(SQLException e){

         }
       
       System.out.println("UserID:" + userID); 
       System.out.println("username:" + username); 
       System.out.println("userType:" + userType); 
       Contact contact = null;   
       Freelancer freelancer = null;
       Contractor contractor = null;
       
       if( userType == FREELANCER ){
                freelancer = new Freelancer();              

                   
                 try{
                     query = "select * from user, freelancer where user.userID = " + userID;
                     conn.setStatement(query);
                     sqlResult = conn.getStatement();                
                      while( sqlResult.next()){
                              freelancer.setFirstName( sqlResult.getString("firstName"));
                              freelancer.setLastName(sqlResult.getString("lastName"));
                              freelancer.setDOB(sqlResult.getTimestamp("DOB").toLocalDateTime().toLocalDate());
                              freelancer.setYearsOfExperince(sqlResult.getString("yearsOfExperience"));
                              freelancer.setSelfDescription(sqlResult.getString("selfDescription"));                      
                              contactID =  sqlResult.getInt("contactID");                    
                        }
                      
                      
                      freelancer.setUserAccount(userAccount);
                      
                 }catch(SQLException e){

                 }
                 
                 
//                 try{
//                      query = "select * from contact, freelancer where contact.contactID = " + contactID;
//                      conn.setStatement(query);
//                      sqlResult = conn.getStatement(); 
//
//                      while( sqlResult.next()){
//                             contact = new Contact(
//                              sqlResult.getString("streetAddress"),
//                              sqlResult.getString("apt"),
//                              sqlResult.getString("city"),
//                              sqlResult.getString("zip"), 
//                              sqlResult.getString("state"), 
//                              sqlResult.getString("country"),                             
//                              sqlResult.getString("phone"), 
//                              sqlResult.getString("email")
//                           );
//                      }
//                      
//                      freelancer.setContact(contact);
//                      obj = freelancer;
//                 }catch( SQLException sql){
//
//                 }

//            System.out.println("Freelancer city:" + freelancer.getContact().getCity());
       }else if( userType == CONTRACTOR ){
                contractor = new Contractor();                       

                   
                 try{
                     query = "select * from user, contractor where user.userID = " + userID;
                     conn.setStatement(query);
                     sqlResult = conn.getStatement();                
                      while( sqlResult.next()){
                              contractor.setFirstName( sqlResult.getString("firstName"));
                              contractor.setLastName(sqlResult.getString("lastName"));
                              contractor.setDOB(sqlResult.getTimestamp("DOB").toLocalDateTime().toLocalDate());       
                              contractor.setTypeOfContractor(sqlResult.getString("contractorType"));
                              contactID =  sqlResult.getInt("contactID");                    
                        }
                      
                    contractor.setUserAccount(userAccount);  
                      
                 }catch(SQLException e){

                 }          
       }       
   
       
       
        try{
            
                   if( userType == FREELANCER ){
                      query = "select * from contact, freelancer where contact.contactID = " + contactID;
                   }else{
                      query = "select * from contact, contractor where contact.contactID = " + contactID;

                   }
                   
                   
                      conn.setStatement(query);
                      sqlResult = conn.getStatement(); 

                      while( sqlResult.next()){
                           contact = new Contact(                                     
                              sqlResult.getString("streetAddress"),
                              sqlResult.getString("apt"),
                              sqlResult.getString("city"),
                              sqlResult.getString("zip"), 
                              sqlResult.getString("state"), 
                              sqlResult.getString("country"),                             
                              sqlResult.getString("phone"), 
                              sqlResult.getString("email")
                           );
                      }
                      
                      contact.setContactId(contactID);
                      
                 }catch( SQLException sql){

                 }
        
        
                 if( userType == FREELANCER ){
                     freelancer.setContact(contact);
                     obj = freelancer;
                 }else{
                     contractor.setContact(contact);
                     obj = contractor;
                 }
                 
                 
       return obj;
    }
    
    
    public void setUpdateContactRecord(Contact contact){
              
        DBConnection  conn = new DBConnection();
        conn.connectDatabase();
         try{
             String query = "";
             
                  query = "Update Contact set streetAddress = ?," 
                          + "apt = ?," 
                          + "city = ?," 
                          + "state = ?," 
                          + "zip = ?," 
                          + "country = ?," 
                          + "phone = ?," 
                          + "email = ?" 
                          + " where contactID = ?;";
                  
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, contact.getStreetAddress());
                                ps.setString( 2, contact.getApt());
                                ps.setString( 3, contact.getCity());
                                ps.setString( 4, contact.getState());
                                ps.setString( 5, contact.getZip());
                                ps.setString( 6, contact.getCountry());
                                ps.setString( 7, contact.getPhone());
                                ps.setString( 8, contact.getEmail());
                                ps.setInt(9, contact.getContactId());
                                ps.execute();
                                
                                conn.closeDBConnection();
                }catch(SQLException e){
                    e.printStackTrace();
                }               
                           
                     
    }
    
    public void setUpdateRecord( Object obj, int userType ){
        
        DBConnection  conn = new DBConnection();
        conn.connectDatabase();    
        
       
           // int contactID = setAddress( address );
        
        switch( userType ){
            
            case CONTRACTOR: 
                try{
                Contractor contractor = ( Contractor )obj;
//                    System.out.println( "Fk key ID:" + contractor.getContact().getContactId() );
                String query;
               
                query = "Update Contractor set "
                        + "firstName = ?," 
                        + "lastName = ?,"
                        + "DOB = ?,"
                        + "contractorType = ?,"
                        + "contactID = ?"  
                        + " where userID = ?" ; 
                                            
              
                 System.out.println( "First Name:" +  contractor.getFirstName());   
              
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, contractor.getFirstName());
                                ps.setString( 2, contractor.getLastName());
                                ps.setTimestamp(3, toTimeStamp(contractor.getDOB()));
                                ps.setString( 4, contractor.getTypeOfContractor());
                                ps.setInt( 5, contractor.getContact().getContactId());
                                ps.setInt( 6, contractor.getUserAccount().getUserID());
                                ps.executeUpdate();
                 conn.closeDBConnection();                    
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
                
            case FREELANCER:
                try{
                Freelancer freelancer = ( Freelancer )obj;
                
                String query;
                query = "Update Freelancer set "
                        + "firstName = ?," 
                        + "lastName = ?,"
                        + "DOB = ?,"
                        + "YearsOfExperience = ?,"
                        + "SelfDescription = ?,"
                        + "contactID = ?"  
                        + " where userID = ?";
                
                                    
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, freelancer.getFirstName());
                                ps.setString( 2, freelancer.getLastName());
                                ps.setTimestamp(3,toTimeStamp( freelancer.getDOB()));
                                ps.setString(4, freelancer.getYearsOfExperince());
                                ps.setString(5, freelancer.getSelfDescription());
                                ps.setInt( 6, freelancer.getContact().getContactId());
                                ps.setInt( 7, freelancer.getUserAccount().getUserID());
                                ps.executeUpdate();
                 conn.closeDBConnection();       
                }catch( SQLException e){
                    e.printStackTrace();
                }
               
        }
}
    
     private Timestamp toTimeStamp(LocalDate localDate){                
                 return Timestamp.valueOf(localDate.atStartOfDay());
    }
}