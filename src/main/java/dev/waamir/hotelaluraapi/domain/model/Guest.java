package dev.waamir.hotelaluraapi.domain.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guests", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity(name = "Guest")
@EqualsAndHashCode(of = "id")
public class Guest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "guest_id")
	private Long id;
	@Column(name = "id_number")
	private Long idNumber;
	@Column(nullable = true)
	private String name;
	@Column(nullable = true)
	private String lastName;
	@Column(nullable = true)
	private LocalDate birthDate;
	@Column(nullable = true)
	private Long phoneNumber;
	@NotEmpty(message = "Guest email cannot be empty")
	@Email(message = "Invalid email. Please enter a valid email.")
	@Column(nullable = false)
	private String email;

	@OneToMany(mappedBy = "guest", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Booking> bookings;

}
