package in.quallit.hrmanager.api.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.quallit.hrmanager.api.entities.User;
import in.quallit.hrmanager.api.enums.StatusEnum;
import in.quallit.hrmanager.api.exceptions.AccountNotActiveException;
import in.quallit.hrmanager.api.repositories.UserRepository;

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
			throw new AccountNotActiveException();
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));
	}
}
