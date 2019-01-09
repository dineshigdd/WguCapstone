/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import Controllers.MainScreenController;
import Model.Freelancer;
import Model.Job;
import Model.PrgmLanguage;
import Model.User;
import Reports.Report;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                query = "select * from job where DATE("+ criteria + ") = "+ "'" + criteriaValue + "'";
                
            break;
            case "jobTitle": query = "select * from job where "+ criteria + " like "+ "'%" + criteriaValue + "%'";
            break;
            case "jobAppliedOrInvited": 
                    query = "select * from job, assignment "
                    + "where job.jobID = assignment.jobID and " 
                    + "job.jobPostedBy = assignment.contractorID and "
                    + "assignment.freelancerID ="+ Integer.parseInt(criteriaValue);
            break;
            case "all": query = "select * from job where jobPostedBy = " + Integer.parseInt(criteriaValue);
            break;
            default:query = "select * from job";
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
        String projection = " Freelancer.freelancerID,Freelancer.firstName,Freelancer.lastName,yearsOfExperience,selfDescription,otherTechSkills,nonTechSkills ";
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
            case "otherSkills":
                query = "select" + projection + "from Freelancer "
                        + "where freelancerID ="+ Integer.parseInt(criteriaValue);
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
                freelancer.setOtherTechSkills(sqlResult.getString("otherTechSkills"));      
                freelancer.setNonTechSkills(sqlResult.getString("nonTechSkills"));
                freelanecerList.add(freelancer);
            }
            
            
            
        conn.closeDBConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
       
        return freelanecerList;
      }

     public static ObservableList<PrgmLanguage>  searchLanguage(String criteria , String criteriaValue ){
           ObservableList<PrgmLanguage> prgmLanguageList =  FXCollections.observableArrayList();
    //        prgmLanguageList = FXCollections.observableArrayList(
    //                    "Java","C","Python","C++","Visual Basic .NET","C#","JavaScript","PHP",
    //                    "SQL","Objective-C","Delphi/Object Pascal","Assembly language","MATLAB",
    //                    "Swift","Go","R","RubyprgmLanguageList","Perl","Other"
    //         );
    //        
             PrgmLanguage progrmLanguage;
             DBConnection conn = new DBConnection();
             conn.connectDatabase();
             ResultSet sqlResult = null;
             String query = "";
        switch( criteria ) {
            case "all":          
                 query = "select " + criteriaValue + " from programlanguage";       
            break;
            case "freelancerLanguages":
                 query = "select programlanguage.progLanguageID, progLanguage from freelancerlanguage , programlanguage "
                         + "where freelancerlanguage.progLanguageID = programlanguage.progLanguageID and "
                         + "freelancerID = "+ Integer.parseInt(criteriaValue);
        
            break;
        }
        
        
         try {
              conn.setStatement(query);
              sqlResult = conn.getStatement();
                while( sqlResult.next()){
                    progrmLanguage = new PrgmLanguage();
                    progrmLanguage.setProgLanguageID(sqlResult.getInt("progLanguageID"));
                    progrmLanguage.setProgLanguage(sqlResult.getString("progLanguage"));               
                    prgmLanguageList.add(progrmLanguage);
                }
            } catch (SQLException ex) {
            }
        return prgmLanguageList;
     }  
     
   
     public static ObservableList<?> getReportData(String criteria , String criteriaValue ){
          ObservableList<Report> list =  FXCollections.observableArrayList();
          DBConnection conn = new DBConnection();
          conn.connectDatabase();
          String query = "";
          
          switch( criteria ){
              
              case "contractorJobOffer":
                      query = "select concat(freelancer.firstName,' ', freelancer.lastName) as 'Name', job.jobTitle, jobAssignedDate from freelancer, assignment , job " + 
                    " where Freelancer.freelancerID = assignment.freelancerID"
                     + " and assignment.jobID = job.jobID and contractStatus = 3"
                     + " and assignment.contractorID = " + Integer.parseInt(criteriaValue);
                      
                    break;
                    
              case "AllFreelancerCount":
                  
                    query = "select  concat(freelancer.firstName,' ', freelancer.lastName) as 'Name', count(contractStatus) As 'numberOfJobs' from assignment, freelancer" +
                            " where freelancer.freelancerID = assignment.freelancerID and contractStatus = 3 group by Name";
                    break;
              
              case "freelancerJob":
                    query = "select concat(contractor.firstName,' ',contractor.lastName) as 'Name' , jobTitle, jobDescription, jobPostDate, contractStatus from job , contractor , assignment" +
                            " where assignment.jobID = job.jobID and assignment.contractorID = contractor.contractorID and freelancerID =" + criteriaValue;
                    break;
          }
      
            conn.setStatement( query );
            ResultSet sqlResult = conn.getStatement();
            try {
                    while( sqlResult.next()){
                            Report report = new Report();
                            report.setName(sqlResult.getString("Name") );
                            
                            if( criteria.equals( "contractorJobOffer" )){
                                report.setJob(sqlResult.getString("jobTitle"));
                                report.setJobAssignedDate(sqlResult.getTimestamp("jobAssignedDate").toLocalDateTime());
                                
                            }else if( criteria.equals( "AllFreelancerCount" )){                                
                                report.setNumberOfJobs(sqlResult.getString("numberOfJobs"));                              
                            
                            }else if( criteria.equals( "freelancerJob" ) ){
                                report.setJob(sqlResult.getString("jobTitle"));
                                report.setJobDescription(sqlResult.getString("jobDescription"));
                                report.setJobPostDate(sqlResult.getTimestamp("jobPostDate").toLocalDateTime());
                                report.setContractStatus(sqlResult.getInt("contractStatus"));
                            }
                            
                            list.add(report);
                            
                    }
             } catch (SQLException ex) {
            
               }
            


            return list;
            
     }
}
