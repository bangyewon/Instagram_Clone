package Instagram.example.Instagram.web.dto.Image;

import Instagram.example.Instagram.domain.Image.Image;
import Instagram.example.Instagram.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ImageUploadDTO {
    //생성 시 어떤걸 받아야 하는가 ?
    private MultipartFile file;
    private String caption;

    public Image toEntity(String imageUrl, User user) {
        return Image.builder()
                .caption(caption)
                .imageUrl(imageUrl)
                .user(user)
                .build();
    }
}
