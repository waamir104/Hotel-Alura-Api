package dev.waamir.hotelaluraapi.domain.model;

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "room_types", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Entity(name = "RoomType")
@EqualsAndHashCode(of = "id")
public class RoomType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_type_id")
	private Long id;
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "Name of room type cannot be null")
	private String name;
	@Column(nullable = true)
	private String description;
	@Column(nullable = false)
	@NotNull(message = "Price of room type cannot be null")
	private Double dailyPrice;
	
	@OneToMany(mappedBy = "roomType", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Room> rooms;

}
