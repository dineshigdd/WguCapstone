    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import java.time.LocalDateTime;


/**
 *
 * @author Dinesh
 */
public class Report {
    private String name;
    private String job;
    private LocalDateTime jobAssignedDate;
    private String numberOfJobs;
    private String description;
    private LocalDateTime postedDate;
    private int contractStatus;
    

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
         System.out.println("Get number of jobs:" + numberOfJobs);
        this.numberOfJobs = numberOfJobs;
    }

    public void setJobDescription(String description) {
        this.description = description;
    }

    public void setJobPostDate(LocalDateTime jobPostDate) {
        this.postedDate = jobPostDate;
    }

    public void setContractStatus(int contractStatus ) {
        this.contractStatus = contractStatus;
    }

    public int getContractStatus() {
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
        
   
        
}
