package com.parus.reply.challenge.dl;

import com.parus.reply.challenge.dl.model.Demand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for basic CRUD operations in database for {@link Demand}
 */
public interface DemandRepository extends CrudRepository<Demand, Long> {

    /**
     * Find demands by user id.
     *
     * @param userId
     *            Identifier of user.
     * @return List of demands entities requested by given user.
     */
    List<Demand> findByUserUserId(@Param("user_id") Long userId);

    /**
     * Cancel demand by id for given user.
     *
     * @param userId
     *            Identifier of user.
     */
    void deleteByDemandIdAndUserUserId(@Param("demand_id") Long demandId, @Param("user_id") Long userId);


}
