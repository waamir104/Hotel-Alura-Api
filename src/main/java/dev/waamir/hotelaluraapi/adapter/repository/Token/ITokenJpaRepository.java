package dev.waamir.hotelaluraapi.adapter.repository.Token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelaluraapi.domain.model.Token;

@Repository
public interface ITokenJpaRepository extends JpaRepository<Token, Long> {
	
	@Query("""
			SELECT t FROM Token t INNER JOIN User u on t.user.id = u.id
			WHERE u.id = :userId and (t.expired = false or t.revoked = false)
			""")
	List<Token> findAllValidTokensByUser(Long userId);

	Optional<Token> findByToken(String token);

}
