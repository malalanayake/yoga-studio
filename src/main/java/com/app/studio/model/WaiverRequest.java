package com.app.studio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity which is going to hold the Waiver Request
 *
 * @author malalanayake
 */
@Entity
@Table(name = "WAIVER_REQUEST")
public class WaiverRequest {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty faculty;
    
    public Faculty getFaculty() {
        return faculty;
    }
    
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
        this.faculty.addWaiverRequest(this);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "WaiverRequest{" + "id=" + id + ", status=" + status + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 17 * hash + (this.faculty != null ? this.faculty.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WaiverRequest other = (WaiverRequest) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if (this.faculty != other.faculty && (this.faculty == null || !this.faculty.equals(other.faculty))) {
            return false;
        }
        return true;
    }
    
}
