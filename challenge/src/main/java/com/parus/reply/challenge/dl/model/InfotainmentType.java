package com.parus.reply.challenge.dl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

/**
 * Defines possible infotainment types in vehicles.
 * <ul>
 *     <li>RADIO - only simple radio</li>
 *     <li>NAVIGATION - radio with navigation system based on GPS</li>
 *     <li>ENTERTAINMENT - full digital entertainment system</li>
 * </ul>
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
@ToString
public enum InfotainmentType {
    NAVIGATION,
    RADIO,
    ENTERTAINMENT
}
