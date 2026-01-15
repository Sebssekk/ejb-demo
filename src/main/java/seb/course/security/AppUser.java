package seb.course.security;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_users")
public class AppUser {
    @Id
    private String username;

    @Column(name = "password_hash")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // Load roles immediately
    @CollectionTable(
            name = "app_user_groups", // The table name defined in SQL above
            joinColumns = @JoinColumn(name = "username") // The FK column in that table
    )
    @Column(name = "group_name") // The column containing the actual role string
    private Set<String> groups = new HashSet<>();

    public AppUser(){};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }
}
