package com.parus.reply.challenge.dl;

import com.parus.reply.challenge.dl.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for basic CRUD operations in database for {@link Vehicle}
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
