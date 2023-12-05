package dev.waamir.hotelaluraapi.domain.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "booking_payment_types")
@Entity(name = "BookingPaymentType")
public class BookingPaymentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_payment_type_id")
	private Long id;
	@NotNull(message = "Payment percentage cannot be empty")
	@Column(nullable = false)
	private Double paymentPercentage;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "payment_type_id", nullable = false, foreignKey = @ForeignKey(name = "FK_booking_payment_types_payment_types"))
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private PaymentType paymentType;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "booking_id", nullable = false, foreignKey = @ForeignKey(name = "FK_booking_payment_types_bookings"))
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Booking booking;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPaymentPercentage() {
		return paymentPercentage;
	}

	public void setPaymentPercentage(double paymentPercentage) {
		this.paymentPercentage = paymentPercentage;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
}
