package dev.waamir.hotelaluraapi.infrastructure.rest.spring.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import dev.waamir.hotelaluraapi.adapter.repository.Token.ITokenJpaRepository;
import dev.waamir.hotelaluraapi.domain.model.Token;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.GenericException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

	private final ITokenJpaRepository tokenRepository;

	@Override
	public void logout(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication
	) {
		final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.replace("Bearer ", "");
		Token storedToken = tokenRepository.findByToken(jwt).orElseThrow(
			() -> {
				throw new GenericException("Invalid token", HttpStatus.BAD_REQUEST, null);
			}
		);
		if (storedToken != null) {
			storedToken.setExpired(true);
			storedToken.setRevoked(true);
			tokenRepository.save(storedToken);
		}
	}
	
}
