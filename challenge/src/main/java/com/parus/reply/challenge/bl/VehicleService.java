package com.parus.reply.challenge.bl;


import com.parus.reply.challenge.dl.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle createVehicle(Vehicle vehicle);

    Optional<Vehicle> getVehicle(Long vehicleId);

    List<Vehicle> listAllVehicles();

    void removeVehicle(Long vehicleId);

    Vehicle updateVehicleDetails(Vehicle vehicleToUpdate);
}
