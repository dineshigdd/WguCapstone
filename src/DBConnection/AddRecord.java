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
import Model.FreelancerLanguage;
import Model.Job;
import Model.Message;
import Model.SavedFreelancer;
import Model.User;
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
public  class AddRecord {
  //  private DBConnection conn;
    private static final int  FREELANCER = 1;
    private  static final int  CONTRACTOR = 0;
    private static final int JOB = 3;
    private static final int MESSAGE = 4;
    public static final int FREELANCER_PRGM_LANGUAGE = 5;    
    public static final int SAVED_FREELANCER = 6;
    public static final int ASSIGNMENT = 7;
    public static final int  ERROR = -1;
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
    
    
    
    public static int setDbRecord( Object obj, int ObjectType ){
        int ID = 0;
        DBConnection  conn = new DBConnection();
        conn.connectDatabase();    
        
       
           // int contactID = setAddress( address );
            //System.out.println( "Fk key ID:" + contactID );
        switch( ObjectType ){
            
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
                query = "INSERT INTO Freelancer(firstName,lastName,DOB,yearsOfExperience,selfDescription,amountCharge,contactID,userID)" + 
                                            "VALUES( ? , ? , ? ,? ,? , ? , ? , ? );";
                                            
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, user.getFirstName());
                                ps.setString( 2, user.getLastName());
                                ps.setTimestamp(3,toTimeStamp( user.getDOB()));
                                ps.setString(4, freelancer.getYearsOfExperince());
                                ps.setString(5, freelancer.getSelfDescription());
                                ps.setInt(6, freelancer.getAmountCharge());
                                ps.setInt( 7, user.getContact().getContactId());
                                ps.setInt( 8, user.getUserAccount().getUserID());
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
                    query = "INSERT INTO Job(jobTitle,jobDescription,jobCategory, jobPostedBy ,jobPostDate)" + 
                                                "VALUES( ? , ? , ? ,?, ?);";


                    PreparedStatement ps = conn.insertRecord(query);
                                    ps.setString( 1, job.getJobTitle());
                                    ps.setString( 2, job.getJobDescription());
                                    ps.setString( 3, job.getJobCategory());
                                    ps.setInt(4, job.getJobPostedBy());
                                    ps.setTimestamp(5, toTimeStampWithTime(job.getPostDate()));
                                    ps.execute();

                    conn.setStatement("select LAST_INSERT_ID();");
                    ResultSet sqlResult  = conn.getStatement();

                            while( sqlResult.next() ){
                                     ID = sqlResult.getInt("LAST_INSERT_ID()");               
                                   
                            }
                    conn.closeDBConnection();      
                 }
                catch( SQLException e){
                    e.printStackTrace();
                }
           
            break;
            case MESSAGE:
                try{
                Message message = ( Message )obj;
                String query = "";
                query = "INSERT INTO Message( message,msgCreateDate,freelancerID,jobID )" + 
                                            "VALUES( ? , ? , ?, ? );";
                                            
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setString( 1, message.getMessage());
                                ps.setTimestamp(2, toTimeStampWithTime(message.getMsgCreateDate()));
                                ps.setInt( 3, message.getFreelancerID());
                                ps.setInt( 4, message.getJobID());                               
                                
                                ps.execute();
                 conn.closeDBConnection();       
                }catch( SQLException e){
                    e.printStackTrace();
                }
            break;    
            case FREELANCER_PRGM_LANGUAGE:
                try{
                FreelancerLanguage freelancerLanguage = ( FreelancerLanguage )obj;
                String query = "";
                query = "INSERT INTO FreelancerLanguage( freelancerID,progLanguageID)" + 
                                            "VALUES( ? , ? );";
                                            
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setInt( 1, freelancerLanguage.getFreelancerID());
                                ps.setInt( 2, freelancerLanguage.getProgLanguageID());                     
                                ps.execute();
                 conn.closeDBConnection();       
                }catch( SQLException e){
                    return ERROR;
                }
            break;
            case SAVED_FREELANCER:
                try{
                SavedFreelancer savedFreelancer = ( SavedFreelancer )obj;
                String query = "";
                query = "INSERT INTO savedFreelancer( contractorID, freelancerID )" + 
                                            "VALUES( ? , ?  );";
                                            
                                                
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setInt( 1, savedFreelancer.getContractorID());
                                ps.setInt( 2, savedFreelancer.getFreelancerID());
                                                                  
                                ps.execute();
                 conn.closeDBConnection();       
                }catch( SQLException e){
                    return ERROR;
                }
            break;
            case ASSIGNMENT:
                try{
                Assignment assignment = ( Assignment )obj;
                String query = "";
                
                if( assignment.getJobAssignedDate() != null ){
                      query = "INSERT INTO Assignment( contractorID, freelancerID, jobID, contractStatus,jobAssignedDate )" + 
                                            "VALUES( ? , ? , ?, ? ,? );";
                }else{
                       query = "INSERT INTO Assignment( contractorID, freelancerID, jobID, contractStatus )" + 
                                            "VALUES( ? , ? , ?, ? );";
                }
                                            
                 System.out.println(assignment.getContractorID());
                 System.out.println(assignment.getFreelancerID());
                 System.out.println(assignment.getJobID());
                 System.out.println(assignment.getJobAssignedDate());
               
                 PreparedStatement ps = conn.insertRecord(query);
                                ps.setInt( 1, assignment.getContractorID());
                                ps.setInt( 2, assignment.getFreelancerID());
                                ps.setInt( 3, assignment.getJobID());   
                                ps.setInt(4, assignment.getContractStatus());
                                
                                if(  assignment.getJobAssignedDate() != null ){
                                     ps.setTimestamp(5, toTimeStampWithTime(assignment.getJobAssignedDate()));
                                }
                                ps.execute();
                 conn.closeDBConnection();       
                }catch( SQLException e){
                    return ERROR;
                }    
        }  
       return ID; 
    }
    
    private static Timestamp toTimeStamp(LocalDate localDate){                
       return Timestamp.valueOf(localDate.atStartOfDay());
    }
    
    
    private static Timestamp toTimeStampWithTime(LocalDateTime localDateTime){                
       return Timestamp.valueOf(localDateTime);
    }
}
