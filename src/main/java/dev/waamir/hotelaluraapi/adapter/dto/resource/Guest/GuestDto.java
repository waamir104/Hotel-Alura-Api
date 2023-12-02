package dev.waamir.hotelaluraapi.adapter.dto.resource.Guest;

import java.time.LocalDate;

import dev.waamir.hotelaluraapi.domain.model.Guest;

public record GuestDto(
	Long idNumber,
	String name,
	String lastName,
	LocalDate birthDate,
	Long phoneNumber,
	String email
) {
	
	public GuestDto(Guest guest) {
		this(
			guest.getIdNumber(),
			guest.getName(),
			guest.getLastName(),
			guest.getBirthDate(),
			guest.getPhoneNumber(),
			guest.getEmail());
	}
}
