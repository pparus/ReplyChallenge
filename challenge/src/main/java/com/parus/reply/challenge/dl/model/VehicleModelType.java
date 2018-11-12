package com.parus.reply.challenge.dl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

/**
 * Defines vehicles model types.
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
@ToString
public enum VehicleModelType {

    SEDAN,
    HATCHBACK,
    SUV,
    VAN,
    CONVERTIBLE

}
