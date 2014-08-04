package com.app.studio.model;

import com.app.studio.security.Roles;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class which is going to hold the faculty data
 *
 * @author malalanayake
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findByFacultyUserName", query = "select u from Faculty u where u.user.username=:userName")})
@Table(name = "FACULTY")
public class Faculty {

    /**
     * Interface which is provide the name queries and parameters
     */
    public static interface Constants {

        public static final String NAME_QUERY_FIND_BY_USER_NAME = "findByFacultyUserName";
        public static final String PARAM_USER_NAME = "userName";
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "advisor")
    private Set<Customer> setOfCustomers;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<WaiverRequest> setOfWaiverRequests;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<Section> setOfSections;

    public Faculty(User user) {
        this.user = user;
        this.setOfCustomers = new HashSet<Customer>();
        this.setOfWaiverRequests = new HashSet<WaiverRequest>();
        this.setOfSections = new HashSet<Section>();
        this.user.addRole(Roles.ROLE_FACULTY);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Customer> getSetOfCustomers() {
        return setOfCustomers;
    }

    public void addCustomer(Customer customer) {
        this.setOfCustomers.add(customer);
    }

    public Set<WaiverRequest> getSetOfWaiverRequests() {
        return setOfWaiverRequests;
    }

    public void addWaiverRequest(WaiverRequest waiverRequest) {
        this.setOfWaiverRequests.add(waiverRequest);
    }

    public Set<Section> getSetOfSections() {
        return setOfSections;
    }

    public void addsection(Section section) {
        this.setOfSections.add(section);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Faculty{" + "id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id;
        hash = 13 * hash + (this.user != null ? this.user.hashCode() : 0);
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
        final Faculty other = (Faculty) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

}
