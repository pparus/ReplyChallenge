package com.parus.reply.challenge.sl.impl;

import com.parus.reply.challenge.bl.impl.UserServiceImpl;
import com.parus.reply.challenge.bl.converters.UserConverter;
import com.parus.reply.challenge.dl.model.User;
import com.parus.reply.challenge.sl.UserMgmt;
import com.parus.reply.challenge.sl.model.UserSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of User Management Interface {@link UserMgmt}
 */

@CrossOrigin("*")
@RestController
public class UserMgmtImpl implements UserMgmt {

    UserServiceImpl userService;
    UserConverter userConverter;

    @Autowired
    public UserMgmtImpl(UserServiceImpl userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @Override
    public ResponseEntity<UserSO> createUser(@RequestBody UserSO user) {

        ResponseEntity<UserSO> response = null;
        validateUserObject(user);
        // if client sent userId it will be ignored
        removeUserIdForNewUser(user);

        if (null != user.getUserId()) {
            user.setUserId(null);
        }

        User createdUser = userService.createUser(userConverter.convertToUserEntity(user));
        //create URL
        String stringUri = Constants.REQUEST_MAPPING_PATH + "/users/" + createdUser.getUserId().toString();
        //TODO: refactor
        try {
            URI resourceUri = new URI(stringUri);
            response = ResponseEntity.created(resourceUri).body(userConverter.convertToUserSO(createdUser));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    /**
     * Set userId to null.
     * If for some reason the userId is set for new user then it should be removed.
     * In other case the user with given id will be updated in database and none usr will be created.
     * @param user
     */
    private void removeUserIdForNewUser(UserSO user) {
        if (null != user.getUserId()) {
            user.setUserId(null);
        }

    }

    @Override
    public ResponseEntity<UserSO> getUserDetails(@PathVariable Long userId) {

        Optional<User> foundUser = userService.getUser(userId);
        if (foundUser.isPresent()) {
            UserSO result = userConverter.convertToUserSO(foundUser.get());
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<UserSO>> listAllUsers() {

        List<User> users = userService.listAllUsers();
        List<UserSO> resultList = userConverter.convertListToUserSoList(users);

        return ResponseEntity.ok(resultList);

    }

    //TODO: extend implementation to catch EmptyResultDataAccessException and response with HTTP 400
    @Override
    public ResponseEntity<Void> removeUser(@PathVariable Long userId) {
        userService.removeUser(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserSO> updateUserData(Long userId, UserSO userToUpdate) {
        validateUserObject(userToUpdate);
        setUserIdFromURL(userId, userToUpdate);

        User updatedUser = userService.updateUserDetails(userConverter.convertToUserEntity(userToUpdate));
        return ResponseEntity.ok(userConverter.convertToUserSO(updatedUser));
    }

    /**
     * Set user id from URL in user object to avoid user data corruption.
     *
     * @param userId user identifier from URL
     * @param user user object in body
     */
    private void setUserIdFromURL(Long userId, UserSO user) {
        user.setUserId(userId);
    }


    /**
     * Validates if mandatory parameters are set in {@link UserSO} object.
     * @param user
     */
    private void validateUserObject(UserSO user) {
        Assert.notNull(user.getName(), "User name is missing");
        Assert.notNull(user.getMail(), "User mail is missing");
    }

}
