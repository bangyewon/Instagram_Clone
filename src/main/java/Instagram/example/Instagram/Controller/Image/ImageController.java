package Instagram.example.Instagram.Controller.Image;

import Instagram.example.Instagram.Repository.Image.ImageRepository;
import Instagram.example.Instagram.Service.Image.ImageService;
import Instagram.example.Instagram.domain.Image.Image;
import Instagram.example.Instagram.domain.user.User;
import Instagram.example.Instagram.web.dto.Image.ImageUploadDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/images")
public class ImageController {
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    //이미지 업로드
    @PostMapping("/upload")
    //user와 연결시켜야함  ImageUploadDTO
    public String imageUpload(ImageUploadDTO imageUploadDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (imageUploadDTO.getFile().isEmpty()) {
            String errorMessage = "이미지가 첨부되지 않음";
            return "redirect:/upload?error=" + errorMessage; //에러 메세지 url 파라미터로 전달
        }
        // 세션에서 사용자 정보 가져오기
        User user = (User) session.getAttribute("currentUser");

        // 이미지 업로드 호출
        imageService.imageUpload(imageUploadDTO.getFile(), request);

        return "redirect:/upload/{id}"; //업로드된 게시물로
    }

    // 게시물 삭제
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) { // 해당 user가 삭제할 수 있도록
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션이 만료되었습니다. 다시 로그인해주세요.");
        }

        try {
            imageService.deleteImage(id, user);
            return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시물 삭제 중 오류가 발생했습니다.");
        }
    }

// 태그로 이미지 조회
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
    // 이미지 수정
    @PutMapping("update/{id}") // 리소스 상태,데이터 업데이트시 PutMapping
    //@PathVariable로 고유 url경로에서 이미지id 갖고오기
    //caption,location은 필수 수정이 아닌 선택 수정 매개변수
    public ResponseEntity<String> updateImage(@PathVariable int id,
                                              @RequestParam(value = "caption", required = false) String caption,
                                              @RequestParam(value = "location", required = false) String location,
                                              HttpSession session) {
        // 세션에서 정보 가져옴
        User user = (User) session.getAttribute("currentUser");

        try {
            imageService.updateImage(id, caption, location, user);
            return ResponseEntity.ok("게시물이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            // 오류나면 404 반환
            return ResponseEntity.badRequest().body("게시물 수정 중 오류가 발생했습니다.");
        }
    }

}

