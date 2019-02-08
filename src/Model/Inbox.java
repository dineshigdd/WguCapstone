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
public class Inbox {
        
        User user;
        Job job;
        Message messageObj;
        String jobTitle;
        String name;
        String message;

    public Inbox(Message msg) {
        this.user = msg.getUser();
        this.job = msg.getJob();
        this.messageObj = msg;
//        jobTitle = msg.getJob().getJobTitle();
//        name = msg.getUser().getFullName();
//        message = msg.getMessage();
    }

    
    public String getJobTitle() {
        return job.getJobTitle();
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = job.getJobTitle();
    }

    public String getName() {
        return user.getFullName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return messageObj.getMessage();
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Message getMessageObj() {
        return messageObj;
    }

    public void setMessageObj(Message messageObj) {
        this.messageObj = messageObj;
    }
        
        
        
}