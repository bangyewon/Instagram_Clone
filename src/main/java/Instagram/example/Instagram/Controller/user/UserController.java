package Instagram.example.Instagram.Controller.user;

import Instagram.example.Instagram.web.dto.user.UserLoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/member")
public class UserController {

    @PostMapping("/login") //로그인 기능
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
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
            session.invalidate(); // 세션 무효화
            return ResponseEntity.ok("로그아웃이 되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션이 없습니다.");
        }
    }
}
