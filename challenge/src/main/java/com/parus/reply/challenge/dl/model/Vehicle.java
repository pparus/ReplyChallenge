package com.parus.reply.challenge.dl.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Contains information about vehicles defined mobility-on-demand application.
 * @author Przemylasw Parus
 */
@Getter
@Setter
@Entity(name = "vehicles")
public class Vehicle {

    /**
     * vehicleId used as primary key in database
     */
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    /**
     * Model
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleModelType model;

    /**
     * Engine type as {@link EngineType}
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineType engine;

    /**
     * Infotainment system as {@link InfotainmentType}
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InfotainmentType infotainment;

    /**
     * Interior design as {@link InteriorDesignType}
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InteriorDesignType interiorDesign;

    /**
     * Vehicle's current position.
     * Only values between 0 and 100 inclusive are allowed
     */
    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private Integer currentPosition;

}
