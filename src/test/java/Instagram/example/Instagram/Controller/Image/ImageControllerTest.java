package Instagram.example.Instagram.Controller.Image;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Instagram.example.Instagram.Service.Image.ImageService;
import Instagram.example.Instagram.domain.Image.Image;
import Instagram.example.Instagram.domain.user.User;
import Instagram.example.Instagram.web.dto.Image.ImageUploadDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

class ImageControllerTest {

    private ImageService imageService;
    private ImageController imageController;

    @BeforeEach
    void setUp() {
        imageService = mock(ImageService.class);
        imageController = new ImageController(imageService);
    }
    @Test
    void 게시물_업로드() {
        // G
        User user = new User(); // 사용자 객체 생성
        MultipartFile mockFile = new MockMultipartFile("test.jpg", new byte[0]); // 테스트용 빈 이미지 파일 생성
        ImageUploadDTO imageUploadDTO = new ImageUploadDTO();
        imageUploadDTO.setFile(mockFile);

        // W
        doNothing().when(imageService).imageUpload(mockFile, user);

        // 테스트할 메서드 호출
        String result = imageController.imageUpload(imageUploadDTO, null); // 세션 객체는 null로 전달

        // T
        verify(imageService, times(1)).imageUpload(mockFile, user);
        // 업로드 성공 시 리다이렉트 페이지 확인
        assertEquals("redirect:/upload/{id}", result);
    }

    @Test
    void 게시물_태그검색() {
        //G
        String tagName = "nature";
        List<Image> expectedImages = new ArrayList<>();
        expectedImages.add(new Image());
        expectedImages.add(new Image());

        when(imageService.getImagesByTag(tagName)).thenReturn(expectedImages);

        // W
        List<Image> actualImages = imageController.getImagesByTag(tagName);

        // T
        assertEquals(expectedImages.size(), actualImages.size());
        assertEquals(expectedImages, actualImages);
    }
}
