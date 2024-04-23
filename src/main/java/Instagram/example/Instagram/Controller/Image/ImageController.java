package Instagram.example.Instagram.Controller.Image;

import Instagram.example.Instagram.Repository.Image.ImageRepository;
import Instagram.example.Instagram.Service.Image.ImageService;
import Instagram.example.Instagram.domain.Image.Image;
import Instagram.example.Instagram.web.dto.Image.ImageUploadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/images")
public class ImageController {
    ImageService imageService;
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    //이미지 업로드
    @GetMapping("/upload/post")
    public String upload() {
        return "/upload/post";
    }
    @PostMapping("/upload")
    public String imageUpload(ImageUploadDTO imageUploadDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (imageUploadDTO.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }
        imageService.imageUpload(imageUploadDTO, principalDetails);

        return "redirect:/user/" + principalDetails.getUser().getId();
    }

    //태그로 이미지 조회
    @GetMapping("/ByTag")
    // "/api/v1/images/ByTags=태그명"으로 이미지 조회 가능
    public List<Image> getImagesByTag(@RequestParam String tagName) {
        return imageService.getImagesByTag(tagName);
    }
    // 상세 이미지를 조회
    @GetMapping("/{id}")
    //@PathVariable : 클라이언트 요청으로 url경로 추출할 수 있도록
    public ResponseEntity<Image> getImageById(@PathVariable int id) {
        // 이미지 서비스를 사용하여 이미지를 조회
        Optional<Image> image = imageService.getImageById(id);

        // 이미지가 존재 -> ResponseEntity로 감싸서 반환
        if (image.isPresent()) {
            return ResponseEntity.ok(image.get());
        } else {
            // 이미지가 존재하지 않는 경우 404 Not Found를 반환 : 다른 사용자가 이미지 삭제했을 경우
            return ResponseEntity.notFound().build();
        }
    }
}