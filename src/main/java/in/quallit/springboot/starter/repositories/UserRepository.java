package in.quallit.springboot.starter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.quallit.springboot.starter.entities.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 *
 * @author JIGS
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Find by contact number.
	 *
	 * @param contactNumber the contact number
	 * @return the optional
	 */
	Optional<User> findByContactNumber(Long contactNumber);
}
