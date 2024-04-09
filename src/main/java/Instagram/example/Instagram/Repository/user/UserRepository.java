package Instagram.example.Instagram.Repository.user;

import Instagram.example.Instagram.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> { //<object : User,pk : Integer>
    User findOneByUsername(String username);
    boolean existsByUsername(String username);
    User save(User user);
}
