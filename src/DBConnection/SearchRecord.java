/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import Controllers.MainScreenController;
import Model.Assignment;
import Model.Freelancer;
import Model.Job;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dinesh
 */
public class SearchRecord {
    
    
    public static ObservableList<Job> searchJob(String criteria , String criteriaValue){
       
        ObservableList<Job> jobList = FXCollections.observableArrayList();
        DBConnection conn = new DBConnection();
        conn.connectDatabase();
        
//        SimpleDateFormat  pattern = new SimpleDateFormat("yyyy-mm-dd");
//        try {
//            pattern.parse(date);
//        } catch (ParseException ex) {
//           ex.printStackTrace();
//        }
        String query = null;
        
        switch( criteria ){
            case "jobPostDate": 
            case "jobCategory":
                query = "select * from job where "+ criteria + " = "+ "'" + criteriaValue + "'";
                System.out.println(query);
            break;
            case "jobTitle": query = "select * from job where "+ criteria + " like "+ "'%" + criteriaValue + "%'";
            break;
            case "jobAppliedOrInvited": 
                    query = "select * from job, assignment "
                    + "where job.jobID = assignment.jobID and " 
                    + "job.jobPostedBy = assignment.contractorID and "
                    + "assignment.freelancerID ="+ Integer.parseInt(criteriaValue);
            break;
            default :query = "select " + criteriaValue + " from job";
            break;
        }
       
        
       
        conn.setStatement( query );
        ResultSet sqlResult = conn.getStatement();
        
        try {
            while( sqlResult.next()){
               Job job = new Job(   
                sqlResult.getString("jobTitle"),
                sqlResult.getString("jobDescription"),
                sqlResult.getString("jobCategory"),
                sqlResult.getTimestamp("jobPostDate").toLocalDateTime()
               );
               
               job.setJobID(sqlResult.getInt("jobID"));
               job.setJobPostedBy(sqlResult.getInt("jobPostedBy"));
               jobList.add(job);
            }
            
        conn.closeDBConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
        return jobList;
    }
    
    
      public static ObservableList<Freelancer> searchFreelancer(String criteria , String criteriaValue){
        ObservableList<Freelancer> freelanecerList = FXCollections.observableArrayList();
        DBConnection conn = new DBConnection();
        conn.connectDatabase();
        

        String query = null;
        String projection = " Freelancer.freelancerID,Freelancer.firstName,Freelancer.lastName,yearsOfExperience,selfDescription ";
        switch( criteria ){
           
            case "progLanguage": 
                   query = "select" + projection +
                    "from freelancer, programlanguage , freelancerlanguage" +  
                    " where freelancer.freelancerID = freelancerlanguage.freelancerID "+
                    "and programlanguage.progLanguageID = freelancerlanguage.progLanguageID and " + criteria + " = '" + criteriaValue + "'";           
            break;
            
            case "yearsOfExperience":
                  query = "select" + projection + "from freelancer "+
                          "where " + criteria + "= '" + criteriaValue + "'";
            break;
            
            case "city":
                  query = "select" + projection +  "from freelancer, contact"
                          + " where freelancer.contactID = contact.contactID and " + criteria + "= '" + criteriaValue + "'";
            break;
            case "savedFreelancer":                
                 query = "select" + projection + "from savedFreelancer, Freelancer"
                         + " where Freelancer.freelancerID = savedFreelancer.freelancerID"
                         + " and contractorID =" + Integer.parseInt(criteriaValue);
            break;
            case "appliedFreelancer":
                  query = "select" + projection + "from Freelancer , Assignment "
                         + " where Freelancer.freelancerID = Assignment.freelancerID and"
                         + " contractorID =" + Integer.parseInt(criteriaValue)+ " and "
                         +  "contractStatus = " + MainScreenController.APPLIED_FREELANCER;
                
            break;    
            case "invitedFreelancer":
                 query = "select" + projection + "from Freelancer , Assignment "
                         + " where Freelancer.freelancerID = Assignment.freelancerID and"
                         + " contractorID =" + Integer.parseInt(criteriaValue)+ " and "
                         +  "contractStatus = " + MainScreenController.INVITED_FREELANCER;
            break;             
            case "all":
                query = "select" + projection + "from freelancer";
            break;
        }
               
       
        conn.setStatement( query );
        ResultSet sqlResult = conn.getStatement();
        
        try {
            while( sqlResult.next()){
                Freelancer freelancer = new Freelancer();           
                freelancer.setFreelancerID(sqlResult.getInt("freelancerID"));
                System.out.println("freelancer IDDDD :" + freelancer.getFreelancerID());
                freelancer.setFirstName(sqlResult.getString("firstName"));
                freelancer.setLastName(sqlResult.getString("lastName"));
                freelancer.setYearsOfExperince(sqlResult.getString("yearsOfExperience"));
                freelancer.setSelfDescription(sqlResult.getString("selfDescription"));               
                freelanecerList.add(freelancer);
            }
            
            
            
        conn.closeDBConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
        return freelanecerList;
      }

      
     public static Assignment searchAssignment(String criteria , Assignment assignment){
                ObservableList<Assignment> assignmentList = FXCollections.observableArrayList();
                DBConnection conn = new DBConnection();
                conn.connectDatabase();
        

                 String query = null;
                 
                 
                 switch( criteria ){
                     
                   case "jobAppliedOrInvited": 
                        query = "select assignmentID from assignment "
                        + "where assignment.jobID = " + assignment.getJobID() + " and " 
                        + "assignment.contractorID = " + assignment.getContractorID() + " and "
                        + "assignment.freelancerID ="+ assignment.getFreelancerID();
                    break;
                 }
                 
                conn.setStatement( query );
                ResultSet sqlResult = conn.getStatement();

                try {
                    while( sqlResult.next()){
                      assignment.setAssignmentID(sqlResult.getInt("assignmentID"));
                      
                    }

                conn.closeDBConnection();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                return assignment;
        }
}
