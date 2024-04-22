package Instagram.example.Instagram.domain.Image;

import Instagram.example.Instagram.domain.Image.Image;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne //하나의 이미지에 여러 태그 달리기 가능
    @JoinColumn(name = "image_id") //ManyToOne에만 붙는 어노테이션 -> @OneToMany엔 mappedBy로 관계 지정함
    private Image image;


}
