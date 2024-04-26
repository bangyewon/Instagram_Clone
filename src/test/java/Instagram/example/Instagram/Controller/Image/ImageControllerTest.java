package Instagram.example.Instagram.Controller.Image;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Instagram.example.Instagram.Repository.Image.ImageRepository;
import Instagram.example.Instagram.Service.Image.ImageService;
import Instagram.example.Instagram.domain.Image.Image;
import Instagram.example.Instagram.domain.user.User;
import Instagram.example.Instagram.web.dto.Image.ImageUploadDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

class ImageControllerTest {
    @Mock
    private ImageRepository imageRepository;


    private ImageService imageService;
    private ImageController imageController;

    @BeforeEach
    void setUp() {
        imageService = mock(ImageService.class);
        imageController = new ImageController(imageService);
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void 게시물_업로드() {
        // G
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image".getBytes());

        // W
        imageService.imageUpload(file, null); // Passing null as user since it's not used in the test

        // T
        verify(imageRepository, times(1)).save(any(Image.class));
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
