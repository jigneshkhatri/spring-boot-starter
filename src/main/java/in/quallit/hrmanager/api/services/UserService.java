/**
 * 
 */
package in.quallit.hrmanager.api.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.quallit.hrmanager.api.entities.User;
import in.quallit.hrmanager.api.entities.common.TokenDetail;
import in.quallit.hrmanager.api.exceptions.InvalidPasswordException;
import in.quallit.hrmanager.api.repositories.UserRepository;
import in.quallit.hrmanager.api.utilities.ObjectUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 *
 * @author JIGS
 */
@Service
@Transactional(readOnly = true)
public class UserService extends AbstractService<User> {

	/** The url. */
	@Value("${oauth.url}")
	private String URL;

	/** The grant type. */
	@Value("${oauth.granttype}")
	private String GRANT_TYPE;

	/** The client id. */
	@Value("${client.id}")
	private String CLIENT_ID;

	/** The client secret. */
	@Value("${client.secret}")
	private String CLIENT_SECRET;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The password encoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Find by email or contact number.
	 *
	 * @param username the username
	 * @return the user
	 */
	public User findByEmailOrContactNumber(String username, List<String> mappingObjects) {
		User user = null;
		if (ObjectUtil.isNumeric(username)) {
			// contact number
			user = this.userRepository.findByContactNumber(Long.valueOf(username)).orElse(null);
		} else {
			// email
			user = this.userRepository.findByEmail(username).orElse(null);
		}
		super.initializeLazyObjects(mappingObjects, user);
		return user;
	}

	/**
	 * Save or update.
	 *
	 * @param user the user
	 * @return the user
	 */
	@Override
	@Transactional
	public User saveOrUpdate(User user) {
		if (ObjectUtil.isNotEmpty(user.getPassword())) {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		}
		return this.userRepository.save(user);
	}

	/**
	 * Authenticate user.
	 *
	 * @param email    the email
	 * @param password the password
	 * @return the user
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public User authenticateUser(String email, String password) throws IOException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found"));

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", GRANT_TYPE);
		map.add("client_id", CLIENT_ID);
		map.add("client_secret", CLIENT_SECRET);
		map.add("username", email);
		map.add("password", password);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		try {
			System.out.println("Calling --- " + URL);
			ResponseEntity<String> response = restTemplate.postForEntity(URL, request, String.class);
			TokenDetail tokenDetail = new ObjectMapper().readValue(response.getBody(), TokenDetail.class);
			user.setAccessToken(tokenDetail.getAccess_token());
			user.setRefreshToken(tokenDetail.getRefresh_token());
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidPasswordException("Email or password is invalid");
		}
		return user;
	}
}
