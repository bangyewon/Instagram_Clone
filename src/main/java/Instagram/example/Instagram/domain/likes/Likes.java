package Instagram.example.Instagram.domain.likes;

import Instagram.example.Instagram.domain.Image.Image;
import Instagram.example.Instagram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "imageId") // imageId 컬럼 외래키로 Image엔티티와 매핑
    @ManyToOne //하나의 이미지에 여러 좋아요 달리기 가능
    private Image image;

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name ="userId") // User 엔티티와 매핑
    @ManyToOne // 한명의 사용자는 여러 좋아요 누를 수 있음
    private User user;

    private LocalDateTime createDate;

    @PrePersist // db에 값 넣으면 자동실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
