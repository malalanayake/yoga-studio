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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

/**
 * Entity class which is going to hold the faculty data
 *
 * @author malalanayake
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findByFacultyUserName", query = "select u from Faculty u where u.user.username=:userName")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "findNextAdvisor",
            query = "select * from faculty where id = (select id from ("
            + " select f.id, count(c.id) assignees"
            + " from faculty f left outer join customer c on f.id = c.advisor_id"
            + " group by f.id order by assignees limit 1) t)",
            resultClass = Faculty.class)
})
public class Faculty {

    /**
     * Interface which is provide the name queries and parameters
     */
    public static interface Constants {

        public static final String NAME_QUERY_FIND_BY_USER_NAME = "findByFacultyUserName";
        public static final String PARAM_USER_NAME = "userName";

        public static final String NAME_QUERY_FIND_NEXT_ADVISOR = "findNextAdvisor";
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "advisor")
    @OrderBy
    private Set<Customer> setOfCustomers;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "faculty")
    @OrderBy
    private Set<WaiverRequest> setOfWaiverRequests;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "faculty")
    @OrderBy
    private Set<Section> setOfSections;

    public Faculty() {
        this.setOfCustomers = new HashSet<Customer>();
        this.setOfWaiverRequests = new HashSet<WaiverRequest>();
        this.setOfSections = new HashSet<Section>();
    }

    public Faculty(User user) {
        this();
        this.setUser(user);

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.user.addRole(Roles.ROLE_FACULTY);
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
        return this.user.getFirstName() + " " + this.user.getLastName();
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
