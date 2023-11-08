package dev.waamir.hotelaluraapi.domain.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "bookings")
@Entity(name = "Booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long id;
	@NotEmpty(message = "Check in Date cannot be empty")
	@Column(nullable = false)
	private LocalDate checkIn;
	@Column(nullable = true)
	private LocalDate checkOut;
	@Column(nullable = true)
	private double totalPrice;
	
	@NotEmpty
	@ManyToOne
	@JoinColumn(name = "guest_id", nullable = false, foreignKey = @ForeignKey(name = "FK_bookings_guests"))
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Guest guest;
	
	@NotEmpty
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false, foreignKey = @ForeignKey(name = "FK_bookings_rooms"))
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Room room;
	
	@OneToMany(mappedBy = "booking", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<BookingPaymentType> bookingPaymentTypes;

}
