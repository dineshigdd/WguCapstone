    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;


/**
 *
 * @author Dinesh
 */
public class Report {
    private String name;
    private String job;
    private LocalDateTime jobAssignedDate;
    private int amountCharge;
    private String experience;
    private String numberOfJobs;
    private String numberOfassignment;
    private String description;
    private LocalDateTime postedDate;
    private String contractStatus;
    private BigDecimal rate;

    public void setRate(){
             
         
    }

   
    
       
    public Report(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public Report() {
       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       System.out.println("Full name:" + name);
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDateTime getJobAssignedDate() {
        return jobAssignedDate;
    }

    public void setJobAssignedDate(LocalDateTime jobAssignedDate) {
        this.jobAssignedDate = jobAssignedDate;
    }

    public String getNumberOfJobs() {
        return numberOfJobs;
    }

    public void setNumberOfJobs(String numberOfJobs) {         
        this.numberOfJobs = numberOfJobs;
    }

    public String getNumberOfassignment() {
        return numberOfassignment;
    }

    public void setNumberOfassignment(String numberOfassignment) {
        this.numberOfassignment = numberOfassignment;
    }

    
    
    public void setJobDescription(String description) {
        this.description = description;
    }

    public void setJobPostDate(LocalDateTime jobPostDate) {
        this.postedDate = jobPostDate;
    }

    public void setContractStatus(String contractStatus ) {
        this.contractStatus = contractStatus;
    }

    public String getContractStatus() {
        return contractStatus;
    }
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

     public BigDecimal getRate() {
        return rate;
    }

    public void setRate(double rate) {
                 
         this.rate = new BigDecimal(Double.toString(rate)).setScale(2, RoundingMode.DOWN); 
    }

    public int getAmountCharge() {
        return amountCharge;
    }

    public void setAmountCharge(int amountCharge) {
        this.amountCharge = amountCharge;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
    
    
   
   
        
}
