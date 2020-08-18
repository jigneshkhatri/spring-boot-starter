/**
 * 
 */
package in.quallit.springboot.starter.services;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.quallit.springboot.starter.entities.User;
import in.quallit.springboot.starter.entities.common.TokenDetail;
import in.quallit.springboot.starter.exceptions.BusinessRuleValidationException;
import in.quallit.springboot.starter.repositories.UserRepository;
import in.quallit.springboot.starter.services.common.AbstractService;
import in.quallit.springboot.starter.utilities.ObjectUtil;
import in.quallit.springboot.starter.utilities.constants.CommonConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 *
 * @author JIGS
 */
@Service
@Transactional(readOnly = true)
public class UserService extends AbstractService<User> {

	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

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
		if (ObjectUtil.isEmpty(user.getId())) {
			// Create
			boolean isEmailAlreadyExists = findByEmailOrContactNumber(user.getEmail(), null) != null;
			if (isEmailAlreadyExists) {
				throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_001);
			}
			boolean isContactAlreadyExists = findByEmailOrContactNumber(user.getContactNumber().toString(),
					null) != null;
			if (isContactAlreadyExists) {
				throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_002);
			}

		}
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
				.orElseThrow(() -> new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_008));

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
			LOGGER.info("Calling --- " + URL);
			ResponseEntity<String> response = restTemplate.postForEntity(URL, request, String.class);
			TokenDetail tokenDetail = new ObjectMapper().readValue(response.getBody(), TokenDetail.class);
			user.setAccessToken(tokenDetail.getAccess_token());
			user.setRefreshToken(tokenDetail.getRefresh_token());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_007);
		}
		return user;
	}

	@Cacheable("general_cache")
	public User getQuallitAdmin() {
		// Password: cge&^9GERVplcWrc09564#
		return this.userRepository.findByEmail(CommonConstants.QUALLIT_ADMIN_EMAIL).orElse(null);
	}
}
