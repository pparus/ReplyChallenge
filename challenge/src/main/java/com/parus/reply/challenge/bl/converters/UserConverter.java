package com.parus.reply.challenge.bl.converters;

import com.parus.reply.challenge.dl.model.GenderType;
import com.parus.reply.challenge.dl.model.User;
import com.parus.reply.challenge.sl.model.GenderTypeSO;
import com.parus.reply.challenge.sl.model.UserSO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Converts between user service object {@link UserSO} and user entity {@link User}
 */
@Service
public class UserConverter {

    /**
     * Converts user service object {@link UserSO} to user entity {@link User}
     * @param userSO user service object
     * @return user entity
     */
    public User convertToUserEntity(@NonNull UserSO userSO) {

        User result = new User();
        result.setName(userSO.getName());
        result.setMail(userSO.getMail());

        // optional properties
        if (null != userSO.getAge()) {
            result.setAge(userSO.getAge());
        }
        if (null != userSO.getGender()) {
            result.setGender(GenderType.valueOf(userSO.getGender().name()));
        }
        if (null != userSO.getUserId()) {
            result.setUserId(userSO.getUserId());
        }
        return result;
    }

    /**
     * Converts user entity {@link User} to user service object {@link UserSO}
     * @param user user entity
     * @return user service object
     */
    public UserSO convertToUserSO(@NonNull User user) {

        UserSO result = new UserSO();
        result.setUserId(user.getUserId());
        result.setName(user.getName());
        result.setMail(user.getMail());
        // optional properties
        if (null != user.getAge()) {
            result.setAge(user.getAge());
        }
        if (null != user.getGender()) {
            result.setGender(GenderTypeSO.valueOf(user.getGender().name()));
        }

        result.getDemands().addAll(user.getDemands());

        return result;
    }

    public List<UserSO> convertListToUserSoList(@NonNull List<User> users) {
        List<UserSO> result = new LinkedList<>();
        for (User user: users) {
            UserSO userSO = convertToUserSO(user);
            result.add(userSO);
        }
        return result;
    }
}
