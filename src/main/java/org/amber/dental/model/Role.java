package org.amber.dental.model;

import javax.persistence.*;
import org.hibernate.annotations.NaturalId;


/**
 * Created by zhuoqihe on 4/15/18.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int id;

    @Column(length = 60)
    private String role;

    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

