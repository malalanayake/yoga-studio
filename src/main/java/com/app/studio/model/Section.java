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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity class which holds the section data
 *
 * @author Yen
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findBySectionLocationAndSchedule", query = "select u from Section u where u.location=:locationName and  u.schedule=:scheduleTime and u.yogaClass.id=:classID")})
@Table(name = "SECTION")
public class Section {

    /**
     * Interface which is provide the name queries and parameters
     */
    public static interface Constants {

        public static final String NAME_QUERY_FIND_BY_LOCATION_AND_SCHEDULE = "findBySectionLocationAndSchedule";
        public static final String PARAM_LOCATION_NAME = "locationName";
        public static final String PARAM_SCHEDULE_TIME = "scheduleTime";
        public static final String PARAM_CLASS_ID = "classID";
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int maxStudents;
    private String location;
    private String schedule;
    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty faculty;
    @ManyToOne(fetch = FetchType.EAGER)
    private Semester semester;
    @ManyToOne(fetch = FetchType.EAGER)
    private YogaClass yogaClass;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "section")
    private Set<EnrolledSection> setOfEnrolledSections;

    public Section() {
        this.setOfEnrolledSections = new HashSet<EnrolledSection>();
    }

    public Section(Semester semester, YogaClass yogaClass, Faculty faculty) {
        this();
        this.setSemester(semester);
        this.setYogaClass(yogaClass);
        this.setFaculty(faculty);
    }

    public void addEnrolledSection(EnrolledSection enrolledSection) {
        this.setOfEnrolledSections.add(enrolledSection);
    }

    public Set<EnrolledSection> getSetOfEnrolledSections() {
        return setOfEnrolledSections;
    }

    public YogaClass getYogaClass() {
        return yogaClass;
    }

    public void setYogaClass(YogaClass yogaClass) {
        this.yogaClass = yogaClass;
        this.yogaClass.addSection(this);
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
        this.semester.addSection(this);
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
        this.faculty.addsection(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Section{" + "id=" + id + ", maxStudents=" + maxStudents + ", location=" + location + ", schedule=" + schedule + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.maxStudents;
        hash = 17 * hash + (this.location != null ? this.location.hashCode() : 0);
        hash = 17 * hash + (this.schedule != null ? this.schedule.hashCode() : 0);
        hash = 17 * hash + (this.faculty != null ? this.faculty.hashCode() : 0);
        hash = 17 * hash + (this.semester != null ? this.semester.hashCode() : 0);
        hash = 17 * hash + (this.yogaClass != null ? this.yogaClass.hashCode() : 0);
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
        final Section other = (Section) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.maxStudents != other.maxStudents) {
            return false;
        }
        if ((this.location == null) ? (other.location != null) : !this.location.equals(other.location)) {
            return false;
        }
        if ((this.schedule == null) ? (other.schedule != null) : !this.schedule.equals(other.schedule)) {
            return false;
        }
        if (this.faculty != other.faculty && (this.faculty == null || !this.faculty.equals(other.faculty))) {
            return false;
        }
        if (this.semester != other.semester && (this.semester == null || !this.semester.equals(other.semester))) {
            return false;
        }
        if (this.yogaClass != other.yogaClass && (this.yogaClass == null || !this.yogaClass.equals(other.yogaClass))) {
            return false;
        }
        return true;
    }
}
