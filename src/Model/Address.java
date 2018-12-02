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
public class Address {
    private int id;
    private String streetAddress;
    private String apt;
    private String city;
    private String zip;
    private String state;
    private String country;     
    
    public Address() {
    }
    
    public Address(String streetAddress, String apt, String city, String zip, String state, String country) {
        this.streetAddress = streetAddress;
        this.apt = apt;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
    }
    
    
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }


    
    
}
