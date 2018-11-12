package com.parus.reply.challenge.sl;

import com.parus.reply.challenge.dl.model.Demand;
import com.parus.reply.challenge.sl.impl.DemandMgmtImpl;
import com.parus.reply.challenge.sl.model.UserSO;
import com.parus.reply.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;

/**
 * This class contains integration test for {@link DemandMgmtImpl}.
 * It is full an example of full integration test with full web server and database in memory.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemandMgmtImplTest {

    private UserSO testUser =  null;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addDemandForUser_MissingPickUpTime() {

        //arrange
        Demand demand = TestUtils.createNewDemand(1,2, null, new Date());

        // act
        ResponseEntity<Demand> response = restTemplate.postForEntity("/demand/v1/users/1/demands",
                demand, Demand.class);

        //assert
        assertThat(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void addDemandForUser_MissingPickUpLocation() {

        Demand demand = TestUtils.createNewDemand(null,2, null, new Date());
        ResponseEntity<Demand> response = restTemplate.postForEntity("/demand/v1/users/1/demands",
                demand, Demand.class);

        assertThat(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void addDemandForUser_MissingDropOffTime() {

        Demand demand = TestUtils.createNewDemand(1,2, new Date(), null);
        ResponseEntity<Demand> response = restTemplate.postForEntity("/demand/v1/users/1/demands",
                demand, Demand.class);

        assertThat(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void addDemandForUser_MissingDropOffLocation() {

        Demand demand = TestUtils.createNewDemand(1,null, new Date(), new Date());
        ResponseEntity<Demand> response = restTemplate.postForEntity("/demand/v1/users/1/demands",
                demand, Demand.class);

        assertThat(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void addDemandForUser_DropOffBeforePickUp() {

        Demand demand = TestUtils.createNewDemand(1,2,TestUtils.addOneHourToDate(new Date()), new Date());
        ResponseEntity<Demand> response = restTemplate.postForEntity("/demand/v1/users/1/demands",
                demand, Demand.class);

        assertThat(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void addDemandForUser_UserNotExist() {

        Demand demand = TestUtils.createNewDemand();
        ResponseEntity<Demand> response = restTemplate.postForEntity("/demand/v1/users/1/demands",
                demand, Demand.class);

        assertThat(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));

    }

    @Test
    public void addDemandForUser_returnsDemandDetails() {

        UserSO testUser = createTestUser();
        assertThat(null != testUser);

        Demand demand = TestUtils.createNewDemand();
        ResponseEntity<Demand> response = restTemplate.postForEntity(
                "/demand/v1/users/"+ testUser.getUserId() +"/demands",
                demand, Demand.class);

        assertThat(response.getStatusCode().equals(HttpStatus.OK));
        assertThat(response.hasBody());


    }

    @Test
    public void getDemandDetails() {
    //TODO
    }

    @Test
    public void getAllDemandsForUser() {
    //TODO
    }

    @Test
    public void cancelDemand() {
    //TODO
    }

    @Test
    public void updateDemandDetails() {
    //TODO
    }

    @Test
    public void getDemands() {
    //TODO
    }

    private UserSO createTestUser() {

        if (null != testUser) {
            return testUser;
        }

        //setUpUserForTest
        UserSO newUser = TestUtils.createUserSO();
        ResponseEntity<UserSO> response = restTemplate.postForEntity("/user/v1/users", newUser, UserSO.class);
        return response.getBody();
    }

}