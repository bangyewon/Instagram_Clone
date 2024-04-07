package Instagram.example.Instagram.Service;
import Instagram.example.Instagram.Repository.user.UserRepository;
import Instagram.example.Instagram.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password, HttpSession session) {
        User user = userRepository.findOneByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // 로그인 성공 시 세션에 사용자 정보를 저장
            session.setAttribute("user", user);
            return true;
        }
        return false;
    }

    public void logout(HttpSession session) {
        session.removeAttribute("user"); // 세션에서 사용자 정보를 삭제
    }

    public boolean isLoggedIn(HttpSession session) {
        // 세션에 사용자 정보가 있는지 여부를 확인 / 로그인 상태 여부를 반환
        return session.getAttribute("user") != null;
    }
}
