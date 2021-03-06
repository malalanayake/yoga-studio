package com.app.studio.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Entity class which holds the semester data
 *
 * @author jCalles
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findBySemesterSignUpDate", query = "select s from Semester s where s.signUpDate >= CURDATE()"),
    @NamedQuery(name = "findCurrentAndFutureSemesters", query = "select s from Semester s where CURDATE() <= s.startdate OR curdate() <= s.enddate")})
@Table(name = "SEMESTER")
public class Semester {

    public static interface Constants {

        public static final String NAME_QUERY_FIND_BY_SIGN_UP_DATE = "findBySemesterSignUpDate";
        public static final String NAME_QUERY_FIND_CURRENT_AND_FUTURE = "findCurrentAndFutureSemesters";
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String startdate;
    private String enddate;
    private String signUpDate;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "semester")
    @OrderBy
    private Set<Section> setOfSections;

    public Semester() {
        this.setOfSections = new HashSet<Section>();
    }

    public void addSection(Section section) {
        this.setOfSections.add(section);
    }

    public Set<Section> getSetOfSections() {
        return setOfSections;
    }

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
        return "Start:" + this.startdate + ",End:" + this.enddate + ",Signup:" + this.signUpDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        hash = 31 * hash + (this.startdate != null ? this.startdate.hashCode() : 0);
        hash = 31 * hash + (this.enddate != null ? this.enddate.hashCode() : 0);
        hash = 31 * hash + (this.signUpDate != null ? this.signUpDate.hashCode() : 0);
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
        final Semester other = (Semester) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.startdate == null) ? (other.startdate != null) : !this.startdate.equals(other.startdate)) {
            return false;
        }
        if ((this.enddate == null) ? (other.enddate != null) : !this.enddate.equals(other.enddate)) {
            return false;
        }
        if ((this.signUpDate == null) ? (other.signUpDate != null) : !this.signUpDate.equals(other.signUpDate)) {
            return false;
        }
        return true;
    }

}
