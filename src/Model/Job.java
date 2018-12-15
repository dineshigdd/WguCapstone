/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Dinesh
 */
public class Job {
    private int jobID;
    private String jobTitle;
    private String jobDescription;    
    private String jobCategory;
    private LocalDateTime postDate;
    private LocalDateTime updateDate;

   

    public Job() {
    }

    
    public Job( String jobTitle, String jobDescription, String jobCategory ,LocalDateTime postDate) {
        this.jobID = jobID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobCategory = jobCategory;
        this.postDate = postDate;
       
    }

    
    
    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public String getJobType() {
        return jobCategory;
    }

    public void setJobType(String jobType) {
        this.jobCategory = jobCategory;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }
    
     public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
    
    
}
