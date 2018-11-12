package com.parus.reply.challenge.sl;

import com.parus.reply.challenge.bl.impl.DemandServiceImpl;
import com.parus.reply.challenge.sl.impl.DemandMgmtImpl;
import com.parus.reply.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class contains integration test for {@link DemandMgmtImpl}.
 * It is top-down testing approach.
 * WebMvc with mock is faster than full integration test (class: DemandMgmtImplTest)
 * and can be set only for one controller.
 * It is usually used for Test Driven Development.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DemandMgmtImpl.class)
public class DemandMgmtImplTestMvc {

    @MockBean
    private DemandServiceImpl demandServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addDemandForUser_returnsDemandDetails() throws Exception {

        given(demandServiceMock.getDemand(anyLong(), anyLong())).willReturn(Optional.of(TestUtils.createNewDemand()));

        mockMvc.perform(MockMvcRequestBuilders.get("/demand/v1/users/1/demands/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void addDemandForUser_serviceReturnsEmptyOptional() throws Exception {

        given(demandServiceMock.getDemand(anyLong(), anyLong())).willReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/demand/v1/users/1/demands/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void addDemandForUser_UserNotExists() throws Exception {

        given(demandServiceMock.getDemand(anyLong(), anyLong())).willThrow(IllegalArgumentException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/demand/v1/users/1/demands/1"))
                .andExpect(status().isBadRequest());
    }

    // provide other tests



}