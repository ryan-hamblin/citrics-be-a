package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The entity allowing interaction with the users table
 */
@Entity
@Table(name = "users")
public class User
    extends Auditable
{
    /**
     * The primary key (long) of the users table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    /**
     * The username (String). Cannot be null and must be unique
     */
    @NotNull
    @Column(unique = false)
    private String username;

    /**
    *The list that holds the users favorite cities
    */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<UserCities> favcities = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",allowSetters = true)
    private Set<UserCategories> categories = new HashSet<>();

    /**
     * Default constructor used primarily by the JPA.
     */
    public User()
    {
    }

    /**
     * Given the params, create a new user object
     * <p>
     * userid is autogenerated
     *
     * @param username The username (String) of the user
     */
    public User(String username)
    {
        setUsername(username);
    }

    /**
     * Getter for userid
     *
     * @return the userid (long) of the user
     */
    public long getUserid()
    {
        return userid;
    }

    /**
     * Setter for userid. Used primary for seeding data
     *
     * @param userid the new userid (long) of the user
     */
    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    /**
     * Getter for username
     *
     * @return the username (String) lowercase
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * setter for username
     *
     * @param username the new username (String) converted to lowercase
     */
    public void setUsername(String username)
    {
        this.username = username.toLowerCase();
    }

    /**
     *
     * getter and setters for user's fav cities
     */

    public List<UserCities> getFavcities()
    {
        return favcities;
    }

    public void setFavcities(List<UserCities> favCities)
    {
        this.favcities = favCities;
    }

    public Set<UserCategories> getCategories() {
        return categories;
    }

    public void setCategories(Set<UserCategories> categories) {
        this.categories = categories;
    }

    /**
     * ToString override method
     */
    @Override
    public String toString()
    {
        return "User{" +
            "userid=" + userid +
            ", username='" + username + '\'' +
            ", favCities=" + favcities +
            '}';
    }

    /**
     * Keeping this commented out code for future feature User Authentication
     *
     * Internally, user security requires a list of authorities, roles, that the user has. This method is a simple way to provide those.
     * Note that SimpleGrantedAuthority requests the format ROLE_role name all in capital letters!
     *
     * @return The list of authorities, roles, this user object has
     */
//    @JsonIgnore
//    public List<SimpleGrantedAuthority> getAuthority()
//    {
//        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();
//
//        for (UserRoles r : this.roles)
//        {
//            String myRole = "ROLE_" + r.getRole()
//                .getName()
//                .toUpperCase();
//            rtnList.add(new SimpleGrantedAuthority(myRole));
//        }
//
//        return rtnList;
//    }
}
