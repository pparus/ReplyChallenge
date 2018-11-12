package com.parus.reply.challenge.dl.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Contains information about users of mobility-on-demand app.
 * @author Przemylasw Parus
 */
@Getter
@Setter
@Entity(name = "users")
public class User {

    /**
     * userId used as primary key in database
     */
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * User name
     */
    @Column(nullable = false)
    private String name;

    /**
     * User age
     */
    private Integer age;


    /**
     * Gender as {@link GenderType}
     * Default value UNKNOWN
     */
    @Enumerated(EnumType.STRING)
    private GenderType gender = GenderType.UNKNOWN;

    /**
     * Email address
     */
    @Column(nullable = false)
    private String mail;

    /**
     * Demands requested by user.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "user")
    @JsonManagedReference // to solve the infinite recursion problem by json marshalling
    private Set<Demand> demands = new HashSet<>();

}
