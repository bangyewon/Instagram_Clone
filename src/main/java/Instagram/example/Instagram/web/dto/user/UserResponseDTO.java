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

public class UserResponseDTO {
    private String email;
    private String password;
    private String phone;
    private String username;

    public User toEntity() {
        return User .builder()
                .email(email)
                .phone(phone)
                .password(password)
                .username(username)
                .build();
    }
}
