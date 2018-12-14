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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

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
                Contractor contractor = ( Contractor )obj;
                
                String query = "";
               
                query = "INSERT INTO Contractor(firstName,lastName,DOB,contractorType,contactID,userID)" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? );";
              
                 System.out.println( "USER ID:" + contractor.getUserAccount().getUserID() );   
                 
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, contractor.getFirstName());
                                ps.setString( 2, contractor.getLastName());
                                ps.setTimestamp(3, toTimeStamp(contractor.getDOB()));
                                ps.setString( 4, contractor.getTypeOfContractor());
                                ps.setInt( 5, contractor.getContact().getContactId());
                                ps.setInt( 6, contractor.getUserAccount().getUserID());
                                ps.execute();
                 conn.closeDBConnection();                    
                }catch(SQLException e){
                    e.printStackTrace();
                }
                break;
                
            case FREELANCER:
                try{
                Freelancer freelancer = ( Freelancer )obj;
                
                String query = "";
                query = "INSERT INTO Freelancer(firstName,lastName,DOB,yearsOfExperience,selfDescription,contactID,userID)" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? , ? );";
                                            
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, freelancer.getFirstName());
                                ps.setString( 2, freelancer.getLastName());
                                ps.setTimestamp(3,toTimeStamp( freelancer.getDOB()));
                                ps.setString(4, freelancer.getYearsOfExperince());
                                ps.setString(5, freelancer.getSelfDescription());
                                ps.setInt( 6, freelancer.getContact().getContactId());
                                ps.setInt( 7, freelancer.getUserAccount().getUserID());
                                ps.execute();
                 conn.closeDBConnection();       
                }catch( SQLException e){
                    e.printStackTrace();
                }
            
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
                                ps.setTimestamp(4, toTimeStamp(job.getPostDate()));
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
}
