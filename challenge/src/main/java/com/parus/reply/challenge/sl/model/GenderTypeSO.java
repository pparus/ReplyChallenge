package com.parus.reply.challenge.sl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

@JsonFormat(shape = JsonFormat.Shape.STRING)
@ToString
public enum GenderTypeSO {

    MALE,

    FEMALE,

    UNKNOWN
}
