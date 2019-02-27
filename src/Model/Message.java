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
    private int sender;
    private LocalDateTime msgCreateDate;
    private LocalDateTime msgUpdateDate; 
    private User user;
    private Job job;
    private String name;
    private String jobTitle;
    
    public Message() {
    }

    public Message(String message,int sender, LocalDateTime msgCreateDate,User user,Job job ) {
        this.message = message;
        this.sender = sender;
        this.msgCreateDate = msgCreateDate;        
        this.user = user;
        this.job = job;
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

    public void setFullName(String fullName) {
       name = fullName;
    }
    
    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }

   
    

    
    
    
    
    
}
