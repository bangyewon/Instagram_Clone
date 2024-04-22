package Instagram.example.Instagram.Controller.Image;

import Instagram.example.Instagram.Repository.Image.ImageRepository;
import Instagram.example.Instagram.Service.Image.ImageService;
import Instagram.example.Instagram.domain.Image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    ImageService imageService;
    //태그로 이미지 조회
    @GetMapping("/api/images/ByTag")
    // "/api/images/ByTags=태그명"으로 이미지 조회 가능
    public List<Image> getImagesByTag(@RequestParam String tagName) {
        return imageService.getImagesByTag(tagName);

    }
}
