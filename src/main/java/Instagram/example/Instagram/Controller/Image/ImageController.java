package Instagram.example.Instagram.Controller.Image;

import Instagram.example.Instagram.Repository.Image.ImageRepository;
import Instagram.example.Instagram.Service.Image.ImageService;
import Instagram.example.Instagram.domain.Image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/api/v1/images")
public class ImageController {
    ImageService imageService;
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    //태그로 이미지 조회
    @GetMapping("/ByTag")
    // "/api/v1/images/ByTags=태그명"으로 이미지 조회 가능
    public List<Image> getImagesByTag(@RequestParam String tagName) {
        return imageService.getImagesByTag(tagName);

    }
}
