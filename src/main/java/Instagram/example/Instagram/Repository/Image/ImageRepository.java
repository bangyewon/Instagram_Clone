package Instagram.example.Instagram.Repository.Image;

import Instagram.example.Instagram.domain.Image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    //태그로 게시물 검색
    List<Image> findByTagsName(String tagName);
    //하나의 게시물 조회
}
