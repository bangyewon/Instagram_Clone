package Instagram.example.Instagram.Service.Image;

import Instagram.example.Instagram.Repository.Image.ImageRepository;
import Instagram.example.Instagram.domain.Image.Image;
import Instagram.example.Instagram.domain.user.User;
import Instagram.example.Instagram.web.dto.Image.ImageUploadDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    private static final String Upload_image = "src/main/resources/UploadImages"; //업로드된 이미지들이 저장될 경로

    @Transactional
    // 이미지 업로드
    public void imageUpload(MultipartFile file, HttpServletRequest request) {
        try {
            // UUID로 사용자가 겹치지 않도록 고유 파일 이름 생성
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // 파일 저장 경로 설정
            Path filePath = Paths.get(Upload_image);

            // 파일 저장
            Files.copy(file.getInputStream(), filePath);

            // 이미지 엔티티 생성 및 저장
            Image image = new Image();
            imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드 중 오류가 발생했습니다.", e);
        }
    }

    // 이미지 ID를 사용하여 이미지를 조회 -> 상세 이미지
    public Optional<Image> getImageById(int id) {
        return imageRepository.findById(id);
    }

    // 모든 이미지 조회 - 테스트 용
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    // 태그로 이미지 조회
    public List<Image> getImagesByTag(String tag) {
        return imageRepository.findByTagsName(tag);
    }
    // 이미지(게시물) 삭제
    @Transactional
    public void deleteImage(int id,User user) throws Exception {
            imageRepository.deleteById(id);
    }
    // 이미지(게시물) 수정
    @Transactional
    public void updateImage(int id, String caption, String location,User user) throws Exception {
        Optional<Image> optionalImage = imageRepository.findById(id);
        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            if (image.getUser().equals(user)) {
                // 싱세설명,위치 업데이트
                image.setCaption(caption);
                image.setLocation(location);
                //이미지 저장
                imageRepository.save(image);
            } else {
                throw new Exception("게시물 작성자가 아닙니다.");
            }
        } else {
            throw new Exception("해당 게시물을 찾을 수 없습니다.");
        }
    }

}
