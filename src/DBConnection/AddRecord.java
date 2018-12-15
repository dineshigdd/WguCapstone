/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import Model.Contact;
import Model.Contractor;
import Model.Freelancer;
import Model.Job;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 *
 * @author Dinesh
 */
public  class AddRecord {
  //  private DBConnection conn;
    private static final int  FREELANCER = 1;
    private  static final int  CONTRACTOR = 0;
    private static final int JOB = 3;
    public AddRecord() {
    }
    
    
    public static void setAddress(Contact contact){   //Inserting address        
        DBConnection  conn = new DBConnection();
        conn.connectDatabase();
         try{
             String query = "";
             
                  query = "INSERT INTO Contact( streetAddress,apt,city,state,zip,country,phone,email)" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? ,?, ? );";
                                         
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, contact.getStreetAddress());
                                ps.setString( 2, contact.getApt());
                                ps.setString( 3, contact.getCity());
                                ps.setString( 4, contact.getState());
                                ps.setString( 5, contact.getZip());
                                ps.setString( 6, contact.getCountry());
                                ps.setString( 7, contact.getPhone());
                                ps.setString( 8, contact.getEmail());
                                ps.execute();
                                
                                //conn.closeDBConnection();
                }catch(SQLException e){
                    e.printStackTrace();
                }
    
                //conn.connectDatabase();
                conn.setStatement("select LAST_INSERT_ID();");
                ResultSet sqlResult  = conn.getStatement();
                
                int contactID = 0;
                try{
                while( sqlResult.next() ){
                    contact.setContactId(sqlResult.getInt("LAST_INSERT_ID()"));
                }
                conn.closeDBConnection();
                }catch(Exception e){
                    e.printStackTrace();
                }
                  // System.out.println( "Address ID:" + contact.getContactId() );
               // return addressID;
               
    }
    
    
    
    public static void setDbRecord( Object obj, int userType ){
        
        DBConnection  conn = new DBConnection();
        conn.connectDatabase();    
        
       
           // int contactID = setAddress( address );
            //System.out.println( "Fk key ID:" + contactID );
        switch( userType ){
            
            case CONTRACTOR: 
               try{
                User user = ( Contractor )obj;
                Contractor contractor = ( Contractor )obj;
//                System.out.println( user.getFirstName()); System.out.println( user.getFirstName());
//                  System.out.println( user.getLastName());
//                  System.out.println( toTimeStamp(user.getDOB()));
//                    System.out.println( contractor.getTypeOfContractor());
//               System.out.println( user.getContact().getContactId());   
//                 System.out.println(user.getUserAccount().getUserID()); 
                
                
                String query = "";
               
                query = "INSERT INTO Contractor(firstName,lastName,DOB,contractorType,contactID,userID)" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? );";
              
                 System.out.println( "USER ID:" + user.getUserAccount().getUserID() );   
                 
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, user.getFirstName());
                                ps.setString( 2, user.getLastName());
                                ps.setTimestamp(3, toTimeStamp(user.getDOB()));
                                ps.setString( 4, contractor.getTypeOfContractor());
                                ps.setInt( 5, user.getContact().getContactId());
                                ps.setInt( 6, user.getUserAccount().getUserID());
                                ps.execute();
                 conn.closeDBConnection();                    
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
                
            case FREELANCER:
                try{
                    
                User user = ( Freelancer )obj;    
                Freelancer freelancer = ( Freelancer )obj;
                
                String query = "";
                query = "INSERT INTO Freelancer(firstName,lastName,DOB,yearsOfExperience,selfDescription,contactID,userID)" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? , ? );";
                                            
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, user.getFirstName());
                                ps.setString( 2, user.getLastName());
                                ps.setTimestamp(3,toTimeStamp( user.getDOB()));
                                ps.setString(4, freelancer.getYearsOfExperince());
                                ps.setString(5, freelancer.getSelfDescription());
                                ps.setInt( 6, user.getContact().getContactId());
                                ps.setInt( 7, user.getUserAccount().getUserID());
                                ps.execute();
                 conn.closeDBConnection();       
                }catch( SQLException e){
                    e.printStackTrace();
                }
            break;
            case JOB:
                try{
                Job job = ( Job )obj;
                String query = "";
                query = "INSERT INTO Job(jobTitle,jobDescription,jobCategory,jobPostDate)" + 
                                            "VALUES( ? , ? , ? ,? );";
                                            
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, job.getJobTitle());
                                ps.setString( 2, job.getJobDescription());
                                ps.setString( 3, job.getJobCategory());
                                ps.setTimestamp(4, toTimeStampWithTime(job.getPostDate()));
                                ps.execute();
                 conn.closeDBConnection();       
                }catch( SQLException e){
                    e.printStackTrace();
                }
        }  
        
    }
    
    private static Timestamp toTimeStamp(LocalDate localDate){                
       return Timestamp.valueOf(localDate.atStartOfDay());
    }
    
    
    private static Timestamp toTimeStampWithTime(LocalDateTime localDateTime){                
       return Timestamp.valueOf(localDateTime);
    }
}
