package com.parus.reply.challenge.bl.impl;

import com.parus.reply.challenge.bl.DemandService;
import com.parus.reply.challenge.bl.UserService;
import com.parus.reply.challenge.dl.DemandRepository;
import com.parus.reply.challenge.dl.model.Demand;
import com.parus.reply.challenge.dl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DemandServiceImpl implements DemandService {

    private DemandRepository demandRepo;
    private UserService userService;

    @Autowired
    public DemandServiceImpl(DemandRepository demandRepo, UserService userService) {
        this.demandRepo = demandRepo;
        this.userService = userService;
    }

    @Override
    public Demand addDemand(Long userId, Demand demand) {

        User user = getRequestedUser(userId);
        demand.setUser(user);

        return demandRepo.save(demand);
    }

    /**
     * Retrieves user object.
     * If user can't be found {@link IllegalArgumentException} is thrown.
     *
     * @param userId user id
     * @return found user
     */
    private User getRequestedUser(Long userId) {

        Optional<User> user = userService.getUser(userId);
        // user doesn't exist
        if (!user.isPresent()) {
            throw new IllegalArgumentException("User doesn't exist!");
        }

        return user.get();
    }

    @Override
    public Optional<Demand> getDemand(Long userId, Long demandId) {

        getRequestedUser(userId);
        return demandRepo.findById(demandId);
    }

    @Override
    public List<Demand> listAllDemandsForUser(Long userId) {

        getRequestedUser(userId);
        return demandRepo.findByUserUserId(userId);
    }

    @Override
    @Transactional
    public void cancelDemand(Long userId, Long demandId) {

        getRequestedUser(userId);
        demandRepo.deleteByDemandIdAndUserUserId(demandId, userId);
    }

    @Override
    public Demand updateDemandDetails(Long userId, Demand changedDemand) {

        User user = getRequestedUser(userId);
        changedDemand.setUser(user);
        return demandRepo.save(changedDemand);
    }
}
