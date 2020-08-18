package in.quallit.springboot.starter.services.common;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.quallit.springboot.starter.entities.User;
import in.quallit.springboot.starter.enums.StatusEnum;
import in.quallit.springboot.starter.exceptions.BusinessRuleValidationException;
import in.quallit.springboot.starter.repositories.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomUserDetailsService.
 *
 * @author JIGS
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Gets the authorities.
	 *
	 * @param user the user
	 * @return the authorities
	 */
	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		Collection<GrantedAuthority> authorities = Collections.EMPTY_LIST;
		return authorities;
	}

	/**
	 * Load user by username.
	 *
	 * @param userName the user name
	 * @return the user details
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) {
		User user = userRepository.findByEmail(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
		if (user.getStatus() != StatusEnum.ACTIVE) {
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_006);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));
	}
}
