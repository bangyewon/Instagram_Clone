package Instagram.example.Instagram.Service.Image;

import Instagram.example.Instagram.Repository.Image.ImageRepository;
import Instagram.example.Instagram.domain.Image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

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
