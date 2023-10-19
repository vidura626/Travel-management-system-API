package lk.ijse.userservice.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerUser() throws Exception {
        MockMultipartFile proPicFile = new MockMultipartFile("proPic", "test-proPic.jpg", "image/jpeg", "proPicData".getBytes());
        MockMultipartFile frontImgFile = new MockMultipartFile("frontImg", "test-frontImg.jpg", "image/jpeg", "frontImgData".getBytes());
        MockMultipartFile backImgFile = new MockMultipartFile("backImg", "test-backImg.jpg", "image/jpeg", "backImgData".getBytes());
//        Test
        /*        MockMultipartFile file = new MockMultipartFile("proPic", "test-file.txt", "text/plain", "Hello, World!".getBytes());

         Create a CustomerDto with test data
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("John Doe");

         Perform the request and simulate the file upload
        MockMvcRequestBuilders.multipart("/api/user/register")
                .file(file)
                .param("name", customerDto.getName())
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/user/register")
                .file(file)
                .file(frontImgFile)
                .param("name", "Vidura")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());*/

//        Save
        /*ResultActions perform = mockMvc.perform(multipart("/api/user/register")
                .file(proPicFile)
                .file(frontImgFile)
                .file(backImgFile)
                .param("userId", "1")
                .param("username", "johndoe123")
                .param("name", "John Doe")
                .param("gender", "male")
                .param("dob", "1/1/1990")
                .param("remarks", "Some remarks")
                .param("nicOrPassport", "123456789V")
                .param("password", "johndoe123")
                .param("email", "johndoe@example.com")
                .param("address", "123 Main Street")
                .param("contact", "0771234567")
                .param("role", "user")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE));
        perform.andExpect(status().isOk());*/

//        Check Conflict
       /* ResultActions perform2 = mockMvc.perform(multipart("/api/user/register")
                .file(proPicFile)
                .file(frontImgFile)
                .file(backImgFile)
                .param("userId", "1")
                .param("username", "johndoe123")
                .param("name", "John Doe")
                .param("gender", "male")
                .param("dob", "1/1/1990")
                .param("remarks", "Some remarks")
                .param("nicOrPassport", "123456789V")
                .param("password", "johndoe123")
                .param("email", "johndoe@example.com")
                .param("address", "123 Main Street")
                .param("contact", "0771234567")
                .param("role", "user")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE));

        perform2.andExpect(status().isConflict());*/

//        Check with some missing data
        /*ResultActions perform3 = mockMvc.perform(multipart("/api/user/register")
                .file(proPicFile)
                .file(frontImgFile)
                .file(backImgFile)
                .param("userId", "2")
                .param("username", "johndoe123")
                .param("gender", "male")
                .param("dob", "1/1/1990")
                .param("remarks", "Some remarks")
                .param("password", "johndoe123")
                .param("email", "johndoe@example.com")
                .param("address", "123 Main Street")
                .param("contact", "0771234567")
                .param("role", "user")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE));
        perform3.andExpect(status().isBadRequest()).andDo(print());*/
    }


    @Test
    void updateUser() throws Exception {
        MockMultipartFile proPicFile = new MockMultipartFile("proPic", "test-proPic.jpg", "image/jpeg", "proPicData".getBytes());
        MockMultipartFile frontImgFile = new MockMultipartFile("frontImg", "test-frontImg.jpg", "image/jpeg", "frontImgData".getBytes());
        MockMultipartFile backImgFile = new MockMultipartFile("backImg", "test-backImg.jpg", "image/jpeg", "backImgData".getBytes());

        ResultActions perform = mockMvc.perform(multipart("/api/user/update")
                .file(proPicFile)
                .file(frontImgFile)
                .file(backImgFile)
                .param("userId", "1")
                .param("username", "johndoe123")
                .param("name", "John Dsefef")
                .param("gender", "male")
                .param("dob", "1/1/1990")
                .param("remarks", "Some remarks")
                .param("nicOrPassport", "123456789V")
                .param("password", "johndoe123")
                .param("email", "johndoe@example.com")
                .param("address", "123 Main Street")
                .param("contact", "0771234567")
                .param("role", "user")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE));
        perform.andExpect(status().isOk());
    }

    @Test
    void changePassword() {
    }

    @Test
    void changeEmail() {
    }

    @Test
    void changeContact() {
    }

    @Test
    void login() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void deleteUser() {
    }
}