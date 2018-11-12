package com.parus.reply.challenge.sl.model;

import com.parus.reply.challenge.dl.model.Demand;
import com.parus.reply.challenge.dl.model.GenderType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserSO {

    /**
     * User identifier. Set ony if user was already stored in database.
     */
    private Long userId;

    /**
     * User name
     */
    private String name;

    /**
     * User age
     */
    private Integer age;

    /**
     * Gender as {@link GenderType}
     * Default value UNKNOWN
     */
    private GenderTypeSO gender;

    /**
     * Optional mail address
     */
    private String mail;

    /**
     * Demands requested by user.
     */
    private List<Demand> demands = new LinkedList<>();
}
