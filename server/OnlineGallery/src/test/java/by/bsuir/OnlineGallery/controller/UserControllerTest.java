package by.bsuir.OnlineGallery.controller;

import by.bsuir.OnlineGallery.model.User;
import by.bsuir.OnlineGallery.repository.UserRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findUserAlbumsByUsername() {
    }

    @Test
    void getCurrentUser() throws Exception {
        User user = new User(
                "Matthew Jackson",
                "matthew3000",
                "mr.jackson.m@gmail.com",
                "y@ut$#2tASds"
        );

        userRepository.save(user);

//        TODO: finish the test
//        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.get("/api/users/{username}",
//                user.getUsername()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username", is("matthew3000")))
//                .andExpect(jsonPath("$.name", is("Matthew Jackson")))
//                .andDo(print());
    }

    @Test
    void getUserProfile() {
    }

    @Test
    void findAllPublicAlbumsByCreatedBy() {
    }

    @Test
    void findAllPublicImagesByUsername() {
    }
}