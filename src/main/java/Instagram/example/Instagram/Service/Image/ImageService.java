package Instagram.example.Instagram.Service.Image;

import Instagram.example.Instagram.Repository.Image.ImageRepository;
import Instagram.example.Instagram.domain.Image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    //이미지 업로드
    public void imageUpload(ImageUploadDTO imageUploadDTO, PrincipalDetails principalDetails) {
        try {
            // 클라이언트로부터 받은 이미지 파일
            MultipartFile imageFile = imageUploadDTO.getFile();

            // 클라이언트로부터 받은 이미지와 관련된 정보
            String title = imageUploadDTO.getTitle();
            String description = imageUploadDTO.getDescription();
            List<String> tags = imageUploadDTO.getTags();
            String category = imageUploadDTO.getCategory();
            String uploaderId = principalDetails.getUser().getId();

            // 이미지 업로드 처리
            imageRepository.uploadImage(imageFile, title, description, tags, category, uploaderId);
        } catch (IOException e) {
            // 이미지 업로드 중 오류 발생 시 예외 처리
            e.printStackTrace();
            // 예외 처리 코드 추가
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


}
