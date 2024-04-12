package Instagram.example.Instagram.Controller.user;

import Instagram.example.Instagram.Repository.user.UserRepository;
import Instagram.example.Instagram.Service.user.UserService;
import Instagram.example.Instagram.domain.user.User;
import Instagram.example.Instagram.web.dto.user.UserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/member")
public class UserController {

    private final UserRepository userRepository;
    private UserService userService;
    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }



    @PostMapping("/login") //로그인 기능
    public ResponseEntity<String> login(@RequestBody UserResponseDTO userResponseDTO, HttpServletRequest request) {
        String username = userResponseDTO.getUsername();
        String password = userResponseDTO.getPassword();

        if (username != null && password != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return ResponseEntity.ok("로그인이 성공적으로 되었습니다. ");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("다시 로그인해 주세요");
        }
    }
    @PostMapping("/logout") // 로그아웃 기능
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션이 없으면 null 반환
        if (session != null) {
            session.invalidate(); // 세션 무효화 시키기
            return ResponseEntity.ok("로그아웃이 되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션이 없습니다.");
        }
    }
    @PostMapping("/findOne") // 회원 조회
    public ResponseEntity<User> findOneByUsername(@RequestParam("username") String username) {
        User user = userRepository.findOneByUsername(username);
        if(user != null) {
            return ResponseEntity.ok(user); //찾음
        }
        else {
            return ResponseEntity.notFound().build(); // 못찾음
        }

    }
    @PostMapping("/join") // 회원 가입
    public ResponseEntity<String> register(@RequestBody UserResponseDTO userResponseDTO) {
        // 사용자 정보를 User 엔티티로 변환하여 저장
        User newUser = userRepository.save(userResponseDTO.toEntity());
        if (newUser != null) {
            return ResponseEntity.ok("회원 가입이 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입에 실패하였습니다.");
        }
    }
    @PostMapping("/update") // 회원 수정
    public ResponseEntity<String> updateUser(@RequestBody UserResponseDTO userResponseDTO) {
        User existingUser = userService.findUserByUsername(userResponseDTO.getUsername());
        if (existingUser != null) {
            //유저 있으면 수정 가능
            existingUser.setEmail(userResponseDTO.getEmail());
            existingUser.setUsername(userResponseDTO.getUsername());
            existingUser.setPhone(userResponseDTO.getPhone());

            userRepository.save(existingUser);
            return ResponseEntity.ok("회원 정보가 수정되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }
    @PostMapping("/delete") // 회원 탈퇴
    public ResponseEntity<String> withdraw(@RequestParam("username") String username, HttpServletRequest request) {
        User existingUser = userService.findUserByUsername(username);
        if (existingUser != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); // 세션 무효화 시키기
            }
            userRepository.delete(existingUser);
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }
    //테스트 용
    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

}
