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
public class FreelancerJob {
        private String name;
        private String job;
        private LocalDateTime jobAssignedDate;

    public FreelancerJob(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public FreelancerJob() {
       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       
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
        
   
        
}
