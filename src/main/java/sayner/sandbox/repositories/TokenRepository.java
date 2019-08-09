package sayner.sandbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sayner.sandbox.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
