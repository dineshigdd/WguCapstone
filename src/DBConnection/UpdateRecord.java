/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import Model.Assignment;
import Model.Contact;
import Model.Contractor;
import Model.Freelancer;
import Model.Job;
import Model.User;

import Model.UserAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Dinesh
 */
public class UpdateRecord {
    private static final int  FREELANCER = 1;
    private static final int  CONTRACTOR = 0;
    private static final int  JOB = 3;
    public static final int  ASSIGNMENT = 4;
    
    public static User getUpdateRecord(String username){       
       
       int userID = 0; 
       int contactID = 0;
       int userType = -1;
       User user = null;
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
                user = new Freelancer();              

                   
                 try{
                     query = "select * from freelancer where freelancer.userID = " + userID;
                     conn.setStatement(query);
                     sqlResult = conn.getStatement();                
                      while( sqlResult.next()){
                              user.setFirstName( sqlResult.getString("firstName"));
                              user.setLastName(sqlResult.getString("lastName"));
                              user.setDOB(sqlResult.getTimestamp("DOB").toLocalDateTime().toLocalDate());
                              freelancer = (Freelancer)user;
                              freelancer.setYearsOfExperince(sqlResult.getString("yearsOfExperience"));
                              freelancer.setSelfDescription(sqlResult.getString("selfDescription"));         
                              freelancer.setAmountCharge(sqlResult.getInt("amountCharge"));         
                              contactID =  sqlResult.getInt("contactID");                    
                        }
                      
                      
                      user.setUserAccount(userAccount);
                      
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
                user = new Contractor();                       

                   
                 try{
                     query = "select * from contractor where contractor.userID = " + userID;
                     conn.setStatement(query);
                     sqlResult = conn.getStatement();                
                      while( sqlResult.next()){
                              user.setFirstName( sqlResult.getString("firstName"));
                              user.setLastName(sqlResult.getString("lastName"));
                              user.setDOB(sqlResult.getTimestamp("DOB").toLocalDateTime().toLocalDate());    
                              contractor = (Contractor)user;
                              contractor.setTypeOfContractor(sqlResult.getString("contractorType"));
                              contactID =  sqlResult.getInt("contactID");                    
                        }
                      
                    user.setUserAccount(userAccount);  
                      
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
        
                 user.setContact(contact);
//                 if( userType == FREELANCER ){
//                     user.setContact(contact);
//                     //obj = freelancer;
//                 }else{
//                     contractor.setContact(contact);
//                     obj = contractor;
//                 }
//                 
                 
       return user;
    }
    
    
    public static void setUpdateContactRecord(Contact contact){
              
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
    
    public static boolean setUpdateRecord( Object obj, int userType ){
        
        boolean isUpdated = false;
        DBConnection  conn = new DBConnection();
        conn.connectDatabase();    
        
       
           // int contactID = setAddress( address );
        
        switch( userType ){
            
            case CONTRACTOR: 
                try{
                User user = ( Contractor )obj;
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
                                            
              
                 System.out.println( "First for upsaye Name:" +  contractor.getFirstName());   
                  System.out.println( "User ID:" + user.getContact().getContactId());
              
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, user.getFirstName());
                                ps.setString( 2, user.getLastName());
                                ps.setTimestamp(3, toTimeStamp(user.getDOB()));
                                ps.setString( 4, contractor.getTypeOfContractor());
                                ps.setInt( 5, user.getContact().getContactId());
                                ps.setInt( 6, user.getUserAccount().getUserID());
                                ps.executeUpdate();
                 conn.closeDBConnection();        
                 isUpdated = true;
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
                
            case FREELANCER:
                try{
                User user = ( Freelancer )obj;
                Freelancer freelancer = ( Freelancer )obj;
                
                String query;
                query = "Update Freelancer set "
                        + "firstName = ?," 
                        + "lastName = ?,"
                        + "DOB = ?,"
                        + "YearsOfExperience = ?,"
                        + "SelfDescription = ?,"
                        + "amountCharge = ?,"
                        + "contactID = ?"  
                        + " where userID = ?";
                
                                    
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, user.getFirstName());
                                ps.setString( 2, user.getLastName());
                                ps.setTimestamp(3,toTimeStamp( user.getDOB()));
                                ps.setString(4, freelancer.getYearsOfExperince());
                                ps.setString(5, freelancer.getSelfDescription());
                                ps.setInt(6, freelancer.getAmountCharge());
                                ps.setInt( 7, user.getContact().getContactId());
                                ps.setInt( 8, user.getUserAccount().getUserID());
                                ps.executeUpdate();
                 conn.closeDBConnection();    
                 isUpdated = true;
                }catch( SQLException e){
                    e.printStackTrace();
                }
            break;
            case JOB:
                try{
                    Job job = (Job)obj;
                    String query;
                    query = "Update Job set "
                        + "jobTitle = ?," 
                        + "jobDescription = ?,"
                        + "jobCategory = ?,"
                        + "updatedDate = ?"
                        + " where jobID = " + job.getJobID();
                
                                    
                    PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, job.getJobTitle());
                                ps.setString( 2, job.getJobDescription());
                                ps.setString(3,job.getJobCategory());
                                ps.setTimestamp(4, toTimeStampWithTime(job.getUpdateDate()));  
                                
                                ps.executeUpdate();
                   conn.closeDBConnection();       
                   isUpdated = true;
                }catch(SQLException e){
                     e.printStackTrace();
                }
            break; 
            case ASSIGNMENT:
                try{
                    Assignment assignment = (Assignment)obj;
                    String query;
                    query = "Update Assignment set "
                        + "contractStatus = ?," 
                        + "jobAssignedDate = ?"
                        + " where contractorID = ? and freelancerID = ? and jobID = ?" ;
                
                    
                    PreparedStatement ps = conn.insertRecord(query);
                                ps.setInt( 1, assignment.getContractStatus());
                                ps.setTimestamp(2, toTimeStampWithTime(assignment.getJobAssignedDate()));
                                ps.setInt( 3, assignment.getContractorID());
                                ps.setInt(4, assignment.getFreelancerID());
                                ps.setInt( 5, assignment.getJobID());
                               
                                ps.executeUpdate();
                   conn.closeDBConnection();       
                   isUpdated = true;
                }catch(SQLException e){
                     e.printStackTrace();
                }
                break;
        }
        
        return  isUpdated;
}
    
    
    
    private static Timestamp toTimeStamp(LocalDate localDate){                
       return Timestamp.valueOf(localDate.atStartOfDay());
    }
    
     private static Timestamp toTimeStampWithTime(LocalDateTime localDateTime){                
                 return Timestamp.valueOf(localDateTime);
    }
}
