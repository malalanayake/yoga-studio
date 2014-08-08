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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This is the entity class for Yoga Class
 *
 * @author jCalles
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findByYogaClassName", query = "select u from YogaClass u where u.name like:yogaClassName")})
@Table(name = "YOGA_CLASS")
public class YogaClass {

    /**
     * Interface which is provide the name queries and parameters
     */
    public static interface Constants {

        public static final String NAME_QUERY_FIND_BY_NAME = "findByYogaClassName";
        public static final String PARAM_NAME = "yogaClassName";
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "yogaClass")
    private Set<Section> setOfSections;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<YogaClass> setOfPrerequisites;

    public void setSetOfSections(Set<Section> setOfSections) {
        this.setOfSections = setOfSections;
    }

    public void setSetOfPrerequisites(Set<YogaClass> setOfPrerequisites) {
        this.setOfPrerequisites = setOfPrerequisites;
    }

    public YogaClass() {
        this.setOfSections = new HashSet<Section>();
        this.setOfPrerequisites = new HashSet<YogaClass>();
    }

    public void addPrerequisite(YogaClass yogaClass) {
        this.setOfPrerequisites.add(yogaClass); 
    }

    public Set<YogaClass> getSetOfPrerequisites() {
        return setOfPrerequisites;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
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
        final YogaClass other = (YogaClass) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+this.name+":"+this.price;
    }

}
