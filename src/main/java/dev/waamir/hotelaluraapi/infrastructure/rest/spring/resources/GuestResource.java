package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Guest.GuestDto;
import dev.waamir.hotelaluraapi.application.enumeration.EmailType;
import dev.waamir.hotelaluraapi.application.model.EmailDetails;
import dev.waamir.hotelaluraapi.domain.model.Guest;
import dev.waamir.hotelaluraapi.domain.port.IEmailService;
import dev.waamir.hotelaluraapi.domain.port.IGuestRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.DuplicateRecordException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiNotFoundException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/guest")
public class GuestResource {

	@Value("${application.front-end.host}")
	private String frontHost;

	@Autowired
	private IGuestRepository<Guest> guestRepository;
	@Autowired
	private IEmailService emailService;
	
	@PostMapping("/register")
	public ResponseEntity<MessageResponse> register(
		@RequestBody GuestDto request
	) {
		System.out.println(guestRepository.countByEmail(request.email()));
		if (guestRepository.countByEmail(request.email()) != 0) throw new DuplicateRecordException("Guest's email is already in use.");
		Guest guest = Guest.builder()
			.name(request.name())
			.lastName(request.lastName())
			.email(request.email())
			.phoneNumber(request.phoneNumber())
			.idNumber(request.idNumber())
			.birthDate(request.birthDate())
			.build();
		guest = guestRepository.create(guest);
		EmailDetails emailDetails = EmailDetails.builder()
            .recipient(guest.getEmail())
            .subject("Welcome!!")
            .msgBody(emailService.getRegistrationMessage(guest, getRegistrationUrl()))
            .type(EmailType.REGISTRATION)
            .build();
        emailService.sendEmail(emailDetails);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				MessageResponse.builder()
					.message(String.format("Guest registered with id: %d", guest.getId()))	
					.build()
			);
	}

	@PutMapping("/update")
	public ResponseEntity<MessageResponse> update(
		@RequestBody GuestDto request
	) {
		Guest guest;
		Guest updatedGuest;
		Optional<Guest> guestOp = guestRepository.getByEmail(request.email());
		if (!guestOp.isPresent()){
			guest = guestRepository.getByIdNumber(request.idNumber()).orElseThrow(
				() -> {
					throw new ApiNotFoundException("Guest not found");
				}
			);
		} else {
			guest = guestOp.get();
		}
		if (Objects.isNull(guest.getIdNumber())) {
			updatedGuest = Guest.builder()
				.id(guest.getId())
				.idNumber(request.idNumber())
				.name(request.name())
				.lastName(request.lastName())
				.email(request.email())
				.phoneNumber(request.phoneNumber())
				.birthDate(request.birthDate())
				.bookings(guest.getBookings())
				.build();
		} else {
			updatedGuest = Guest.builder()
				.id(guest.getId())
				.idNumber(guest.getIdNumber())
				.name(request.name())
				.lastName(request.lastName())
				.email(request.email())
				.phoneNumber(request.phoneNumber())
				.birthDate(request.birthDate())
				.bookings(guest.getBookings())
				.build();
		}
		guestRepository.update(updatedGuest);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				MessageResponse.builder()
					.message("Guest updated successfully")	
					.build()
			);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<GuestDto>> list(
		@PageableDefault(size = 10) Pageable pagination
	) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				guestRepository.list(pagination).map(GuestDto::new)
			);
	}

	@GetMapping("/idNumber/{idNumber}")
	public ResponseEntity<GuestDto> fetchByIdNumber(@PathVariable @NotNull @Positive Long idNumber) {
		Guest guest = guestRepository.getByIdNumber(idNumber).orElseThrow(
			() -> {
				throw new ApiNotFoundException("");
			}
		);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				new GuestDto(guest)
			);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<GuestDto> fetchByIdNumber(@PathVariable @NotNull @Email String email) {
		Guest guest = guestRepository.getByEmail(email).orElseThrow(
			() -> {
				throw new ApiNotFoundException("");
			}
		);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(
				new GuestDto(guest)
			);
	}

	private String getRegistrationUrl() {
        return frontHost.concat("/register");
    }
}
