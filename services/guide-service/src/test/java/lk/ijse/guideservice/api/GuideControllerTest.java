package lk.ijse.guideservice.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GuideControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void registerGuide() {
//        mockMvc.perform();
    }

    @Test
    void updateGuide() {
    }

    @Test
    void getAllGuides() {
    }

    @Test
    void deleteGuide() {
    }
}