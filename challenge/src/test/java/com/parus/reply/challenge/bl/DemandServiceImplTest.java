package com.parus.reply.challenge.bl;

import com.parus.reply.challenge.bl.impl.DemandServiceImpl;
import com.parus.reply.challenge.bl.impl.UserServiceImpl;
import com.parus.reply.challenge.dl.DemandRepository;
import com.parus.reply.challenge.dl.model.Demand;
import com.parus.reply.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

/**
 * jUnit test for Services (only an example)
 * Without Spring context.
 */
@RunWith(MockitoJUnitRunner.class)
public class DemandServiceImplTest {

    private static final Long USER_ID = 1L;
    private static final Long USER_ID_NOT_EXISTS = 2L;
    private static final Long DEMAND_ID = 1L;
    private static final Long DEMAND_ID_NOT_EXISTS = 2L;

    @Mock
    private DemandRepository repository;

    @Mock
    private UserServiceImpl userService;

    private DemandService demandService;

    @Before
    public void setUp() {
        demandService = new DemandServiceImpl(repository, userService);
    }

    @Test
    public void addDemand() {
//        given(repository)

    }

    @Test
    public void getDemand_ok() {

        Demand demand = TestUtils.createNewDemand();

        given(repository.findById(DEMAND_ID)).willReturn(Optional.of(demand));
        given(userService.getUser(USER_ID)).willReturn(Optional.of(TestUtils.createUser()));

        Optional<Demand> result = demandService.getDemand(USER_ID, DEMAND_ID);

        assertThat(result.isPresent());

    }

    @Test(expected = IllegalArgumentException.class)
    public void getDemand_UserNotFound() {

        Demand demand = TestUtils.createNewDemand();

        // given(repository.findById(DEMAND_ID)).willReturn(Optional.of(demand));
        // given(userService.getUser(USER_ID_NOT_EXISTS)).willReturn(Optional.empty());
        Optional<Demand> result = demandService.getDemand(USER_ID, DEMAND_ID);
    }

    @Test
    public void getDemand_DemandNotExists() {

        // given(repository.findById(DEMAND_ID_NOT_EXISTS)).willReturn(Optional.empty());
        given(userService.getUser(USER_ID)).willReturn(Optional.of(TestUtils.createUser()));
        Optional<Demand> result = demandService.getDemand(USER_ID, DEMAND_ID);

        assertThat(!result.isPresent());
    }

    @Test
    public void listAllDemandsForUser() {
    // TODO
    }

    @Test
    public void cancelDemand() {
    //TODO
    }

    @Test
    public void updateDemandDetails() {
    //TODO
    }

}