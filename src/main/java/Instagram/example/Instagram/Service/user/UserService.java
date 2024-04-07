package Instagram.example.Instagram.Service.user;

import Instagram.example.Instagram.Repository.user.UserRepository;
import Instagram.example.Instagram.domain.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User login(String username,String password){
        User user = userRepository.findOneByUsername(username);
        if(username == null) {
            return null; // 유저없으면 오류 보내기
        }
        if(!user.getPassword().equals(password)) {
            return null; // 비밀번호 비교
        }
        return user;
    }

    public void logout(HttpSession session) {
        session.removeAttribute("user"); //로그아웃하면 user삭제
    }

    //회원 조회
    public User findUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }
}
