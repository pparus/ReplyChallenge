package com.parus.reply.challenge.sl;

import com.parus.reply.challenge.dl.model.Demand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Defines REST Endpoint for demand management.
 * Demands are reported by users and contains information about time, place and desired vehicle properties.
 *
 * @author Przemyslaw Parus
 */
public interface DemandMgmt {

    /**
     * Set of constants used in interface
     */
    public final class Constants {
        public static final String API_NAME = "demand";
        public static final String REQUEST_MAPPING_PATH = "/" + API_NAME + "/v1";

        private Constants() {};
    }

    /**
     * Add new demand for user.
     *
     * @param userId identifier of user who requests a demand
     * @param demand demand details {@link Demand}
     *
     * @return added demand
     */
    @RequestMapping(value = DemandMgmt.Constants.REQUEST_MAPPING_PATH + "/users/{userId}/demands",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    ResponseEntity<Demand> addDemandForUser(@PathVariable Long userId, @RequestBody Demand demand);

    /**
     * Get demand details.
     *
     * @param userId identifier of user who requested the selected demand
     * @param demandId demand identifier
     * @return demand details {@link Demand}
     */
    @RequestMapping(value = DemandMgmt.Constants.REQUEST_MAPPING_PATH + "/users/{userId}/demands/{demandId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    ResponseEntity<Demand> getDemandDetails(@PathVariable Long userId, @PathVariable Long demandId);

    /**
     * Get all demands for user.
     *
     * @param userId identifier of user who requested the demands
     * @return added demand
     */
    @RequestMapping(value = DemandMgmt.Constants.REQUEST_MAPPING_PATH + "/users/{userId}/demands",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    ResponseEntity<List<Demand>> getAllDemandsForUser(@PathVariable Long userId);

    /**
     * Cancel single demand.
     *
     * @param userId identifier of user who requested the selected demand
     * @param demandId demand identifier
     * @return empty responseS
     */
    @RequestMapping(value = DemandMgmt.Constants.REQUEST_MAPPING_PATH + "/users/{userId}/demands/{demandId}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> cancelDemand(@PathVariable Long userId, @PathVariable Long demandId);

    /**
     * Update demand details.
     *
     * @param userId identifier of user who requested the demand
     * @param demandId demand identifier
     * @param demandToUpdate updated demand details
     * @return added demand
     */
    @RequestMapping(value = DemandMgmt.Constants.REQUEST_MAPPING_PATH + "/users/{userId}/demands/{demandId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
    ResponseEntity<Demand> updateDemandDetails(@PathVariable Long userId, @PathVariable Long demandId,
                                               @RequestBody Demand demandToUpdate);

    /**
     * Get all demands.
     *
     * @return added demand
     */
    @RequestMapping(value = DemandMgmt.Constants.REQUEST_MAPPING_PATH + "/demands/",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    ResponseEntity<List<Demand>> getDemands();


}
