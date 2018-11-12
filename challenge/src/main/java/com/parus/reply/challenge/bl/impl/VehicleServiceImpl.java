package com.parus.reply.challenge.bl.impl;

import com.parus.reply.challenge.bl.VehicleService;
import com.parus.reply.challenge.dl.VehicleRepository;
import com.parus.reply.challenge.dl.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepo;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepo = vehicleRepository;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    @Override
    public Optional<Vehicle> getVehicle(Long vehicleId) {
        return vehicleRepo.findById(vehicleId);
    }

    @Override
    public List<Vehicle> listAllVehicles() {
        List<Vehicle> resultList = new LinkedList<>();
        Iterable<Vehicle> iterable = vehicleRepo.findAll();
        // add all entities to result list
        iterable.forEach(resultList::add);
        return resultList;
    }

    @Override
    public void removeVehicle(Long vehicleId) {
        vehicleRepo.deleteById(vehicleId);
    }

    @Override
    public Vehicle updateVehicleDetails(Vehicle vehicleToUpdate) {
        return vehicleRepo.save(vehicleToUpdate);
    }
}
