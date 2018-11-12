package com.parus.reply.challenge.bl.scheduler;

import com.parus.reply.challenge.bl.VehicleService;
import com.parus.reply.challenge.dl.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class VehicleScheduler {

    private static final int MAX = 101;

    VehicleService vehicleService;
    Random rand = new Random();

    @Autowired
    public VehicleScheduler(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Scheduled(fixedRate = 2000)
    public void changeVehiclePosition() {
        List<Vehicle> vehicles = vehicleService.listAllVehicles();
        Vehicle vehicle = getRandomVehicleFromList(vehicles);
        vehicle.setCurrentPosition(rand.nextInt(MAX));
        vehicleService.updateVehicleDetails(vehicle);
    }

    private Vehicle getRandomVehicleFromList(List<Vehicle> vehicles) {
        return vehicles.get(rand.nextInt(vehicles.size()));
    }


}
