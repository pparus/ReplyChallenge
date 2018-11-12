package com.parus.reply.challenge.sl;

import com.parus.reply.challenge.dl.model.Vehicle;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Defines REST endpoint for vehicle management.
 *
 * @author Przemyslaw Parus
 */
public interface VehicleMgmt {

    /**
     * Set of constants used in interface
     */
    public final class Constants {
        public static final String API_NAME = "vehicle";
        public static final String REQUEST_MAPPING_PATH = "/" + API_NAME + "/v1";

        private Constants() {
        }
    }

    /**
     * Adds new vehicle to system.
     *
     * @param vehicle vehicle object {@link Vehicle}
     * @return created user
     */
    @RequestMapping(value = VehicleMgmt.Constants.REQUEST_MAPPING_PATH + "/vehicles",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle);

    /**
     * Get information about single vehicle.
     * If vehicle can't be found in the database a response with HTTP 404 (Not Found) will be returned.
     *
     * @param vehicleId vehicle identifier
     * @return user data or http code 404
     */
    @RequestMapping(value = VehicleMgmt.Constants.REQUEST_MAPPING_PATH + "/vehicles/{vehicleId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    ResponseEntity<Vehicle> getVehicleDetails(@PathVariable Long vehicleId);

    /**
     * List all vehicles from database.
     *
     * @return list of vehicles
     */
    @RequestMapping(value = VehicleMgmt.Constants.REQUEST_MAPPING_PATH + "/vehicles",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    ResponseEntity<List<Vehicle>> listAllVehicles();

    /**
     * Remove vehicle from application
     *
     * @param vehicleId vehicle identifier
     * @return empty response
     */
    @RequestMapping(value = VehicleMgmt.Constants.REQUEST_MAPPING_PATH + "/vehicles/{vehicleId}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> removeVehicle(@PathVariable(value = "vehicleId") Long vehicleId);

    /**
     * Change vehicle data.
     *
     * @param vehicleId vehicle identifier
     * @return updated vehicle data
     */
    @RequestMapping(value = VehicleMgmt.Constants.REQUEST_MAPPING_PATH + "/vehicles/{vehicleId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
    ResponseEntity<Vehicle> updateVehicleDetails(@PathVariable(value = "vehicleId") Long vehicleId,
                                                 @RequestBody Vehicle vehicleToUpdate);

}
