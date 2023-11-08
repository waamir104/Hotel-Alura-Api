package dev.waamir.hotelaluraapi.domain.model;

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
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "rooms", uniqueConstraints = @UniqueConstraint(columnNames = "number"))
@Entity(name = "Room")
@EqualsAndHashCode(of = "id")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private Long id;
	@NotEmpty(message = "Room number cannot be empty")
	private Long number;
	@Column(nullable = true)
	private String description;
	
	@NotEmpty
	@ManyToOne
	@JoinColumn(name = "room_type_id", nullable = false, foreignKey = @ForeignKey(name = "FK_rooms_room_types"))
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private RoomType roomType;
	
	@OneToMany(mappedBy = "room", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Booking> bookings;

}
