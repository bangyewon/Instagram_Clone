package Instagram.example.Instagram.Controller.Image;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Instagram.example.Instagram.Service.Image.ImageService;
import Instagram.example.Instagram.domain.Image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
