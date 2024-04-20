package Instagram.example.Instagram.domain.Image;

import Instagram.example.Instagram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"images"}) // 호출 받을 시 images 무시하고 호출받기 - 연관있을 때만 사용(무한 응답 방지 위해)
    @ManyToOne(fetch = FetchType.EAGER) // user와 다대일 관계 : 여러 이미지는 하나의 유저에 속하도록
    @JoinColumn(name = "userId") //userId 외래키 - user와 매핑
    private User user;

    //좋아요 기능


    @Column(nullable = false)
    private LocalDateTime createDate;
}
