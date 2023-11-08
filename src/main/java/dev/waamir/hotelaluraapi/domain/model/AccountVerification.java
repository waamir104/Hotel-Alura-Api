package dev.waamir.hotelaluraapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(
		name = "account_verifications",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "url")
		}
)
@Entity(name = "AccountVerification")
public class AccountVerification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_verification_id")
	private Long id;
	@NotEmpty(message = "Account verification url cannot be empty")
	@Column(name = "url")
	private String url;
	
	@NotEmpty
	@OneToOne
	@JoinColumn(name = "user_id", unique = true, nullable = false, foreignKey = @ForeignKey(name = "FK_account_verifications_users"))
	private User user;

}
