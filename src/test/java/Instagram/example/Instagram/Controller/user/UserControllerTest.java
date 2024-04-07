package Instagram.example.Instagram.Controller.user;

import Instagram.example.Instagram.Repository.user.UserRepository;
import Instagram.example.Instagram.Service.user.UserService;
import Instagram.example.Instagram.domain.user.User;
import Instagram.example.Instagram.web.dto.user.UserLoginDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
class UserControllerTest {


    private MockMvc mockMvc;
//@Autowired
    private UserService userService;

    private UserRepository userRepository;

    private MockHttpSession session;
    @BeforeEach
    void setUp() {
        // mockMvc 초기화 - 에러방지
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userRepository)).build();
    }
    @Test
    void login() throws Exception {
        String username = "user1";
        String password = "0000";
        User user = User.builder()
                .username(username)
                .password(password)
                .build();
        userRepository.save(user);

        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername(username);
        userLoginDTO.setPassword(password);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user1\",\"password\":\"0000\"}")
                        .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk());

        User loggedInUser = userService.getLoggedInUser(session);
        assert loggedInUser != null;
        assert loggedInUser.getUsername().equals(username);
    }

    @Test
    void logout() {
    }

    @Test
    void 회원조회() {
    }
}