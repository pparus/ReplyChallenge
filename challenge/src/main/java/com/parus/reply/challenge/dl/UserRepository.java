package com.parus.reply.challenge.dl;

import com.parus.reply.challenge.dl.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for basic CRUD operations in database for {@link User}
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
