package Instagram.example.Instagram.web.dto.user;

import Instagram.example.Instagram.domain.user.User;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//RequestDTO : 주로 컨트롤러에서 사용
//클라이언트가 보낸 데이터를 컨트롤러에서 받아와 비즈니스 로직 따라 처리
//클라이언트 요청따라 db조회 변경 작업 요청시 사용
public class UserRequestDTO {

    private String username;
    private String password;
    private String email;
    private String phone;

    public User toEntity() {
        return User .builder()
                .email(email)
                .phone(phone)
                .password(password)
                .username(username)
                .build();
    }

}
