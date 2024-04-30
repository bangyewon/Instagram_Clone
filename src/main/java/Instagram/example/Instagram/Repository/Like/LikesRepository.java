package Instagram.example.Instagram.Repository.Like;

import Instagram.example.Instagram.domain.likes.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes,Integer> {
    @Modifying //삭제시 modifying 붙이기
    @Query(value = "INSERT INTO likes(imageId, userId) VALUES(:imageId, :userId)", nativeQuery = true)
    int Like(@Param("imageId") int imageId, @Param("userId") int userId);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE imageId = :imageId AND userId = :userId", nativeQuery = true)
    int UnLike(@Param("imageId") int imageId, @Param("userId") int userId);
}
