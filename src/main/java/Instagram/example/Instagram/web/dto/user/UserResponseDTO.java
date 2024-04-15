package Instagram.example.Instagram.web.dto.user;
import Instagram.example.Instagram.domain.user.User;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
//Controller에서 User정보 전달 위한 DTO
//서버에서 클라이언트에게 전달할 데이터 들어있음
//주로 컨트롤러에서 생성되 클라이언트에게 반환

public class UserResponseDTO {
    private String email;
    private String password;
    private String phone;
    private String username;

}
