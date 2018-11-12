package com.parus.reply.challenge.dl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * Contains information about user's demands registered in mobility-on-demand service.
 * @author Przemylasw Parus
 */
@Getter
@Setter
@Entity(name = "demands")
public class Demand {

    /**
     * vehicleId used as primary key in database
     */
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandId;

    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private Integer pickUpLocation;

    @Column(nullable = false)
    private Date pickUpTime;

    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private Integer dropOffLocation;

    @Column(nullable = false)
    private Date dropOffTime;

    @Enumerated(EnumType.STRING)
    private VehicleModelType model;

    @Enumerated(EnumType.STRING)
    private EngineType engine;

    @Enumerated(EnumType.STRING)
    private InfotainmentType infotainment;

    @Enumerated(EnumType.STRING)
    private InteriorDesignType interiorDesign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonBackReference // to solve the infinite recursion problem by json marshalling
    private User user;

}
