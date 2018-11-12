package com.parus.reply.challenge.bl;

import com.parus.reply.challenge.dl.model.Demand;

import java.util.List;
import java.util.Optional;

public interface DemandService {

    /**
     * Adds demand reported by a user.
     *
     * @param userId identifier of user who requests demand
     * @param demand reported demand
     * @return created demand in system
     */
    Demand addDemand(Long userId, Demand demand);

    /**
     * Retrieves demand details from database.
     *
     * @param userId user who requested demand
     * @param demandId demand identifier
     * @return demand details
     */
    Optional<Demand> getDemand(Long userId, Long demandId);

    /**
     * Lists all demands for selected user
     *
     * @param userId user who requested demand
     * @return list of demands
     */
    List<Demand> listAllDemandsForUser(Long userId);

    /**
     * Cancels user's demand.
     *
     * @param userId user who requested demand
     * @param demandId identifier
     */
    void cancelDemand(Long userId, Long demandId);

    /**
     * Updates demand's details.
     *
     * @param changedDemand Changed demand details provided as {@link Demand}.
     * @return updated user data record
     */
    Demand updateDemandDetails(Long userId, Demand changedDemand);
}