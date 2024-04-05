package Instagram.example.Instagram.domain.user;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity // DB에 자동 테이블 생성 -> 실행하면 user 테이블 생성됨
@Builder
@Data //Lombok getter,setter 생성
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 전체 생성자
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 -> db를 따라가도록
    private int id;

    @Column(length = 10, unique = true)
    private String username;

    @Column(length = 10, nullable = false)
    private String passward;

    @Column(nullable = false)
    private String email;
    private String name;

    @Column(nullable = true)
    private String profileImage;
    private String phone;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @PrePersist // db에 넣을때 가입날짜가  - db에 값 넣으면 자동실행됨
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }







}
