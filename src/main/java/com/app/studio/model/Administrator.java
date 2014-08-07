package com.app.studio.model;

import com.app.studio.security.Roles;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class which holds the administrator data
 *
 * @author Yen
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findByAdminUserName", query = "select u from Administrator u where u.user.username=:userName")})
@Table(name = "ADMINISTRATOR")
public class Administrator {

    /**
     * Interface which is provide the name queries and parameters
     */
    public static interface Constants {

        public static final String NAME_QUERY_FIND_BY_USER_NAME = "findByAdminUserName";
        public static final String PARAM_USER_NAME = "userName";
    }
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    public Administrator() {
    }

    public Administrator(User user) {
        this.setUser(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.user.addRole(Roles.ROLE_ADMIN);
    }

    @Override
    public String toString() {
        return "Administrator{" + "id=" + id + '}';
    }

}
