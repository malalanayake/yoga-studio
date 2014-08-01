package com.app.studio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jCalles
 */
@Entity
@Table(name = "SEMESTER")
public class Semester {

 
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String startdate;
    private String enddate;
    private String signUpDate;

      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }

    @Override
    public String toString() {
        return "Semester{" + "id=" + id + ", startdate=" + startdate + ", enddate=" + enddate + ", signUpDate=" + signUpDate + '}';
    }
    
    
    
}
