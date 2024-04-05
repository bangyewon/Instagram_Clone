package Instagram.example.Instagram.Service.user;

import Instagram.example.Instagram.Repository.user.UserRepository;
import Instagram.example.Instagram.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User login(String username,String passward){
        User user = userRepository.findOneByUsername(username);
        if(username == null) {
            return null; // 유저없으면 오류 보내기
        }
        if(user.getPassward().equals(passward) == false) {
            return null; // 비밀번호 비교
        }
        return user;
    }



}
