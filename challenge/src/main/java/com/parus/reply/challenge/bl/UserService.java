package com.parus.reply.challenge.bl;

import com.parus.reply.challenge.dl.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Creates new user in database.
     *
     * @param user user to create
     * @return created user
     */
    User createUser(User user);

    /**
     * Retrieves user data from database for given user identifier.
     *
     * @param userId user identifier
     * @return found user
     */
    Optional<User> getUser(Long userId);

    /**
     * Lists all users from database.
     * @return list of users
     */
    List<User> listAllUsers();

    /**
     * Removes user from database.
     *
     * @param userId user identifier
     */
    void removeUser(Long userId);

    /**
     * Updates user details.
     *
     * @param changedUser Changed user data provided as {@link User}.
     * @return updated user data record
     */
    User updateUserDetails(User changedUser);
}
