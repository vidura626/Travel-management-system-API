package lk.ijse.vehicleservice.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerVehicle() throws Exception {
        MockMultipartFile licenceFrontImg = new MockMultipartFile("licenceFrontImg", "test-proPic.jpg", "image/jpeg", "proPicData".getBytes());
        MockMultipartFile licenceBackImg = new MockMultipartFile("licenceBackImg", "test-frontImg.jpg", "image/jpeg", "frontImgData".getBytes());
        MockMultipartFile frontImg = new MockMultipartFile("frontImg", "test-backImg.jpg", "image/jpeg", "backImgData".getBytes());
        MockMultipartFile backImg = new MockMultipartFile("backImg", "test-backImg.jpg", "image/jpeg", "backImgData".getBytes());
        MockMultipartFile rearImg = new MockMultipartFile("rearImg", "test-backImg.jpg", "image/jpeg", "backImgData".getBytes());

//        Save
        ResultActions perform = mockMvc
                .perform(multipart("/api/vehicle/update")
                .file(licenceFrontImg)
                .file(licenceBackImg)
                .file(frontImg)
                .file(backImg)
                .file(rearImg)
                .param("vehicleId", "1")
                .param("name", "bmw")
                .param("fuelCapacity", "20")
                .param("fuelUsage", "12")
                .param("fuelType", "DIESEL")
                .param("isHybrid", Boolean.TRUE.toString())
                .param("isAuto", "0")
                .param("seatCapacity", "5")
                .param("driverName", "John")
                .param("remarks", "johnd owef ewef om")
                .param("vehicleType", "Auto")
                .param("vehicleCategory", "Large")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateVehicle() {
    }

    @Test
    void getAllVehicles() {
    }

    @Test
    void deleteVehicle() {
    }
}