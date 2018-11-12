package com.parus.reply.challenge.sl;

import com.parus.reply.challenge.sl.model.UserSO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Defines REST endpoint for user management.
 * @author Przemyslaw Parus
 */
public interface UserMgmt  {

    /**
     * Set of constants used in interface
     */
    public final class Constants {
        public static final String API_NAME = "user";
        public static final String REQUEST_MAPPING_PATH = "/" + API_NAME + "/v1";

        private Constants() {};
    }

    /**
     * Create user.
     * @param user user details {@link UserSO}
     * @return created user
     */
    @RequestMapping(value = Constants.REQUEST_MAPPING_PATH + "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    ResponseEntity<UserSO> createUser(@RequestBody UserSO user);

    /**
     * Get information about single user.
     * If user can't be found in the database a response with HTTP 404 (Not Found) will be sent.
     *
     * @param userId user identifier
     * @return user data or http code 404
     */
//    {userId:+}
    @RequestMapping(value = Constants.REQUEST_MAPPING_PATH + "/users/{userId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    ResponseEntity<UserSO> getUserDetails(@PathVariable Long userId);

    /**
     * List all users from database.
     * @return list of users
     */
    @RequestMapping(value = Constants.REQUEST_MAPPING_PATH + "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<List<UserSO>> listAllUsers();

    /**
     * Remove single user from application
     * @param userId user identifier
     * @return null
     */
    @RequestMapping(value = Constants.REQUEST_MAPPING_PATH + "/users/{userId}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> removeUser(@PathVariable(value = "userId") Long userId);

    /**
     * Change user data.
     * @param userId user identifier
     * @return updated user data
     */
    @RequestMapping(value = Constants.REQUEST_MAPPING_PATH + "/users/{userId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
    ResponseEntity<UserSO> updateUserData(@PathVariable(value = "userId") Long userId, @RequestBody UserSO userToUpdate);





}
