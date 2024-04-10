package Instagram.example.Instagram.Service.user;

import Instagram.example.Instagram.Repository.user.UserRepository;
import Instagram.example.Instagram.domain.user.User;
import Instagram.example.Instagram.web.dto.user.UserResponseDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
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
    public User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("user"); //세션에 사용자 정보 반환 -> 테스트 코드 사용
    }

    //회원 조회
    public User findUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }
    //회원 가입
    public User registerUser(UserResponseDTO userResponseDTO) {
        // 이미 존재하는 사용자인지 확인
        if (userRepository.existsByUsername(userResponseDTO.getUsername())) {
            // 이미 존재하는 사용자일 경우 null 반환
            return null;
        }
        User newUser = userResponseDTO.toEntity();
        return userRepository.save(newUser); // 저장 후 로그인 창으로 redirect ?
    }
    //회원 수정
    public User updateUser(UserResponseDTO userResponseDTO) {
        User existingUser = userRepository.findOneByUsername(userResponseDTO.getUsername());
        if (existingUser != null) {
            existingUser.setEmail(userResponseDTO.getEmail());
            existingUser.setName(userResponseDTO.getUsername());
            // 필요한 정보에 대해 추가적인 수정 작업을 수행할 수 있습니다.
            return userRepository.save(existingUser);
        } else {
            return null; // 존재하지 않는 사용자일 경우 null 반환
        }
    }
    // 회원 탈퇴
    public boolean DeletedUser(String username) {
        User existingUser = userRepository.findOneByUsername(username);
        if (existingUser != null) {
            userRepository.delete(existingUser);
            return true; // 탈퇴
        } else { //existingUser  == null -> 존재하지 않는 사용자
            return false; // 존재하지 않는 사용자일 경우 탈퇴 실패
        }
    }
}
