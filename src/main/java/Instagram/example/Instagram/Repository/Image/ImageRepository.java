package Instagram.example.Instagram.Repository.Image;

import Instagram.example.Instagram.domain.Image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    //태그로 게시물 검색
    List<Image> findByTagsName(String tagName);
    //상세 게시물 조회
    Optional<Image> findImageById(int id);

    // 이미지 업로드
    void uploadImage(MultipartFile file, String title, String description, List<String> tags, String category, String uploaderId) throws IOException;
}
