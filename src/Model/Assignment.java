/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Dinesh
 */
public class Assignment {
    private int contractorID;
    private int freelancerID;
    private int jobID;
    private boolean contractStatus;

    public Assignment(int contractorID, int freelancerID, int jobID) {
        this.contractorID = contractorID;
        this.freelancerID = freelancerID;
        this.jobID = jobID;
    }

    public Assignment() {
        
    }
 
    public int getContractorID() {
        return contractorID;
    }

    public void setContractorID(int contractorID) {
        this.contractorID = contractorID;
    }

    public int getFreelancerID() {
        return freelancerID;
    }

    public void setFreelancerID(int freelancerID) {
        this.freelancerID = freelancerID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public boolean isContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(boolean contractStatus) {
        this.contractStatus = contractStatus;
    }
    
    
}
