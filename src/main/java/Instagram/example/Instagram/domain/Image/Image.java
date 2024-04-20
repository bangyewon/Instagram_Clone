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
    private String caption; // 상세설명
    private String location; //위치
    private String imageUrl; //사진 전송받아 폴더 저장위함

    @ManyToOne(fetch = FetchType.EAGER) // user와 다대일 관계 : 여러 이미지는 하나의 유저에 속하도록
    @JoinColumn(name = "userId") //userId 외래키 - user와 매핑
    private User user;


    @Column(nullable = false)
    private LocalDateTime createDate;
}
