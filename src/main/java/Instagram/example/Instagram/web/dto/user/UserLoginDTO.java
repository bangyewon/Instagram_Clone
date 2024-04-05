package Instagram.example.Instagram.web.dto.user;
import Instagram.example.Instagram.domain.user.User;
import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
//Controller에서 User정보 전달 위한 DTO

public class UserLoginDTO {
    private String email;
    private String password;
    private String phone;
    private String username;

    public User toEntity() {
        return User .builder()
                .email(email)
                .phone(phone)
                .passward(password)
                .username(username)
                .build();
    }
}
