package Instagram.example.Instagram.domain.Image;

import Instagram.example.Instagram.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String caption;
    private String location;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER) // user와 다대일 관계 : 여러 이미지는 하나의 유저에 속하도록
    @JoinColumn(name = "userId") //userId 외래키 - user와 매핑
    private User user;


    @Column(nullable = false)
    private LocalDateTime createDate;
}
