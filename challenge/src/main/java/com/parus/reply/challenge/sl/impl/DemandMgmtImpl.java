package com.parus.reply.challenge.sl.impl;

import com.parus.reply.challenge.bl.DemandService;
import com.parus.reply.challenge.dl.model.Demand;
import com.parus.reply.challenge.sl.DemandMgmt;
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
public class DemandMgmtImpl implements DemandMgmt {

    private final DemandService demandService;

    @Autowired
    public DemandMgmtImpl(DemandService demandService) {
        this.demandService = demandService;
    }

    @Override
    public ResponseEntity<Demand> addDemandForUser(@PathVariable final Long userId, @RequestBody final Demand demand) {

        ResponseEntity<Demand> response = null;
        validateDemandData(demand);
        removeDemandIdForNewDeamnds(demand);

        Demand addedDemand = demandService.addDemand(userId, demand);

        //create URL
        String stringUri = DemandMgmt.Constants.REQUEST_MAPPING_PATH + "/users/" + userId.toString() + "/demands/" +
                addedDemand.getDemandId().toString();
        //TODO: refactor
        try {
            URI resourceUri = new URI(stringUri);
            response = ResponseEntity.created(resourceUri).body(addedDemand);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }

    private void removeDemandIdForNewDeamnds(Demand demand) {
        if (null != demand.getDemandId()) {
            demand.setDemandId(null);
        }
    }

    private void validateDemandData(@RequestBody Demand demand) {
        Assert.notNull(demand.getDropOffLocation(), "Drop-off location is missing");
        Assert.notNull(demand.getPickUpLocation(), "Pick up location is missing");
        Assert.notNull(demand.getPickUpTime(), "Pick up time is missing");
        Assert.notNull(demand.getDropOffTime(), "Drop-off time is missing");
        Assert.isTrue(demand.getDropOffTime().after(demand.getPickUpTime()),
                "Drop-off time not after pick up time!");
    }

    @Override
    public ResponseEntity<Demand> getDemandDetails(@PathVariable Long userId, @PathVariable Long demandId) {

        Optional<Demand> result = demandService.getDemand(userId, demandId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Demand>> getAllDemandsForUser(@PathVariable Long userId) {

        List<Demand> resultList = demandService.listAllDemandsForUser(userId);
        return ResponseEntity.ok(resultList);
    }

    @Override
    public ResponseEntity<Void> cancelDemand(@PathVariable Long userId, @PathVariable Long demandId) {

        demandService.cancelDemand(userId, demandId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Demand> updateDemandDetails(@PathVariable Long userId, @PathVariable Long demandId,
                                                      @RequestBody Demand demand) {
        return ResponseEntity.ok(demandService.updateDemandDetails(userId, demand));
    }

    @Override
    public ResponseEntity<List<Demand>> getDemands() {
        return null;
    }
}
