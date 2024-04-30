package Instagram.example.Instagram.Service.Like;

import Instagram.example.Instagram.Repository.Like.LikesRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;
    private HttpSession httpSession;

    @Transactional
    public int Like(int imageId) {
        int userId = (int) httpSession.getAttribute("userId");
        return likesRepository.Like(imageId, userId);
    }

    @Transactional
    public int UnLike(int imageId) {
        int userId = (int) httpSession.getAttribute("userId");
        return likesRepository.UnLike(imageId, userId);
    }


}
