package sayner.sandbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sayner.sandbox.model.Token;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    /**
     * Находит необходимый токен по значению
     *
     * @param value
     * @return
     */
    Optional<Token> findOneByValue(String value);
}
