package com.parus.reply.challenge.sl.impl;

import com.parus.reply.challenge.bl.VehicleService;
import com.parus.reply.challenge.dl.model.Vehicle;
import com.parus.reply.challenge.sl.VehicleMgmt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class VehicleMgmtImpl implements VehicleMgmt {

    private VehicleService vehicleService;

    @Autowired
    public VehicleMgmtImpl(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle newVehicle) {

        ResponseEntity<Vehicle> response = null;
        validateVehicleData(newVehicle);
        removeVehicleIdForNewVehicles(newVehicle);
        Vehicle createdVehicle = vehicleService.createVehicle(newVehicle);

        //create URL
        String stringUri = VehicleMgmt.Constants.REQUEST_MAPPING_PATH + "/vehicles/" +
                createdVehicle.getVehicleId().toString();
        //TODO: refactor
        try {
            URI resourceUri = new URI(stringUri);
            response = ResponseEntity.created(resourceUri).body(createdVehicle);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    private void removeVehicleIdForNewVehicles(Vehicle vehicle) {
        if (null != vehicle.getVehicleId()) {
            vehicle.setVehicleId(null);
        }

    }

    @Override
    public ResponseEntity<Vehicle> getVehicleDetails(@PathVariable Long vehicleId) {

        Optional<Vehicle> foundVehicle = vehicleService.getVehicle(vehicleId);
        if (foundVehicle.isPresent()) {
            return ResponseEntity.ok(foundVehicle.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Vehicle>> listAllVehicles() {
        return ResponseEntity.ok(vehicleService.listAllVehicles());
    }

    @Override
    public ResponseEntity<Void> removeVehicle(@PathVariable Long vehicleId) {
        vehicleService.removeVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Vehicle> updateVehicleDetails(@PathVariable Long vehicleId,
                                                        @RequestBody Vehicle vehicleToUpdate) {
        validateVehicleData(vehicleToUpdate);
        setVehicleIdFromURL(vehicleId, vehicleToUpdate);
        return ResponseEntity.ok(vehicleService.updateVehicleDetails(vehicleToUpdate));

    }

    private void setVehicleIdFromURL(Long vehicleId, Vehicle vehicle) {
        vehicle.setVehicleId(vehicleId);
    }

    private void validateVehicleData(Vehicle vehicle) {
        Assert.notNull(vehicle.getModel(), "Vehicle model has to be defined");
        Assert.notNull(vehicle.getEngine(), "Vehicle engine type has to be defined");
        Assert.notNull(vehicle.getInfotainment(), "Vehicle infotainment type has to be defined");
        Assert.notNull(vehicle.getInteriorDesign(), "Vehicle interior design has to be defined");
        Assert.notNull(vehicle.getCurrentPosition(), "Vehicle current position has to be defined");
    }
}
