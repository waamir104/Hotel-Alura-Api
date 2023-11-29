package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Guest.GuestResponse;
import dev.waamir.hotelaluraapi.domain.model.Guest;
import dev.waamir.hotelaluraapi.domain.port.IGuestRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/guest")
public class GuestResource {

	@Autowired
	private IGuestRepository<Guest> guestRepository;
	
	@PostMapping("/register")
	public ResponseEntity<MessageResponse> register() {
		// TODO implement the logic to register a guest
		return ResponseEntity
			.status(0)
			.body(
				MessageResponse.builder()
					.message("Guest registered successfully")	
					.build()
			);
	}

	@PostMapping("/update")
	public ResponseEntity<MessageResponse> update() {
		// TODO implement the logic to update a guest
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				MessageResponse.builder()
					.message("Guest updated successfully")	
					.build()
			);
	}

	@PostMapping("/list")
	public ResponseEntity<Page<GuestResponse>> list(
		@PageableDefault(size = 10) Pageable pagination
	) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				guestRepository.list(pagination).map(GuestResponse::new)
			);
	}

	@PostMapping("/idNumber/{idNumber}")
	public ResponseEntity<GuestResponse> fetchByIdNumber(@PathVariable @NotNull @Positive Long idNumber) {
		Guest guest;
		// TODO implement the logic to fetch guest by id number
		return ResponseEntity
			.status(0)
			.body(
				new GuestResponse(guest)
			);
	}
}
