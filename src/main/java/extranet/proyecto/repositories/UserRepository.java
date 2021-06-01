package extranet.proyecto.repositories;

import extranet.proyecto.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Collection<User> findByUserNameAndUserPhone(String userName, String userPhone); Unir dos query
    Collection<User> findByfirstname(String firstname);
    Collection<User> findByPhone(String phone);
    @Query(
            value = "SELECT * FROM user ORDER BY phone",
            nativeQuery = true
    )
    Collection<User> getUsersOrderedByUserPhone();

}
