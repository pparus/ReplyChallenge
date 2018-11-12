package com.parus.reply.challenge.dl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

/**
 * Defines vehicles engine types
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
@ToString
public enum EngineType {

    DIESEL,
    GASOLINE,
    HYBRID,
    ELECTRIC
}
