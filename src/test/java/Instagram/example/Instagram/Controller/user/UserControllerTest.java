package Instagram.example.Instagram.Controller.user;

import Instagram.example.Instagram.Repository.user.UserRepository;
import Instagram.example.Instagram.Service.user.UserService;
import Instagram.example.Instagram.domain.user.User;
import Instagram.example.Instagram.web.dto.user.UserResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(username);
        userResponseDTO.setPassword(password);


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
    void 전체_회원조회() {
        User user1 = new User(1, "user1", "0000", "user1@email", "user1", "profile", "010-0000-0000", LocalDateTime.now());
        User user2 = new User(2, "user2", "0000", "user2@email", "user2", "profile", "010-0000-0000", LocalDateTime.now());

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> result = userRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2); // 갯수 2개인지
        Assertions.assertThat(result).contains(user1, user2); // 안에 들어있는 member들이 맞는지
    }
}
