package extranet.proyecto.repositories;

import extranet.proyecto.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
