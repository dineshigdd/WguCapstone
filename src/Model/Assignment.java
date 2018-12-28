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
public class Assignment extends SavedFreelancer{    
  
    private int assignmentID;   
    private int jobID;
    private int contractStatus;
   

    public Assignment(int contractorID, int freelancerID, int jobID) {
        this.contractorID = contractorID;
        this.freelancerID = freelancerID;
        this.jobID = jobID;
    }

    public Assignment() {
        
    }
    
   public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }
    
    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(int contractStatus) {
        this.contractStatus = contractStatus;
    }

}