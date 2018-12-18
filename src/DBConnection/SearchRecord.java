/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

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
    
    
    public static ObservableList<Job> searchRecord(String criteria , String criteriaValue){
       
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
}
