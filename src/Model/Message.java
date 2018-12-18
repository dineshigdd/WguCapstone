/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author Dinesh
 */
public class Message {
    private int messageID;
    private String message;
    private LocalDateTime msgCreateDate;
    private LocalDateTime msgUpdateDate;
    private int JobID;
    private int freelancerID;
  

    public Message() {
    }

    public Message(String message, LocalDateTime msgCreateDate,int freelancerID,int jobID ) {
        this.message = message;
        this.msgCreateDate = msgCreateDate;        
        this.freelancerID = freelancerID;
        this.JobID = jobID;      
    }
  

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getMsgCreateDate() {
        return msgCreateDate;
    }

    public void setMsgCreateDate(LocalDateTime msgCreateDate) {
        this.msgCreateDate = msgCreateDate;
    }

    public LocalDateTime getMsgUpdateDate() {
        return msgUpdateDate;
    }

    public void setMsgUpdateDate(LocalDateTime msgUpdateDate) {
        this.msgUpdateDate = msgUpdateDate;
    }

    public int getJobID() {
        return JobID;
    }

    public void setJobID(int JobID) {
        this.JobID = JobID;
    }
    
    public int getFreelancerID() {
        return freelancerID;
    }

    public void setFreelancerID(int freelancerID) {
        this.freelancerID = freelancerID;
    }

    
    
    
    
    
}
