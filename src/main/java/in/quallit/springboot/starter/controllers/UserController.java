/**
 * 
 */
package in.quallit.springboot.starter.controllers;

import java.io.IOException;

import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.quallit.springboot.starter.controllers.common.AbstractController;
import in.quallit.springboot.starter.dtos.UserDTO;
import in.quallit.springboot.starter.entities.User;
import in.quallit.springboot.starter.entities.common.ResponseBody;
import in.quallit.springboot.starter.mappers.IUserMapper;
import in.quallit.springboot.starter.mappers.common.IAbstractMapper;
import in.quallit.springboot.starter.services.UserService;
import in.quallit.springboot.starter.services.common.AbstractService;
import in.quallit.springboot.starter.utilities.ObjectUtil;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 *
 * @author JIGS
 */
@RestController
@RequestMapping(value = AppEntityCodes.USER)
public class UserController extends AbstractController<User, UserDTO> {

	// Routes start
	/** The Constant REGISTER_ENDPOINT. */
	private static final String REGISTER_ENDPOINT = "register";

	/** The Constant LOGIN_ENDPOINT. */
	private static final String LOGIN_ENDPOINT = "login";
	// Routes end

	/** The user service. */
	private UserService userService;

	/** The user mapper. */
	private IUserMapper userMapper;

	/**
	 * Instantiates a new user controller.
	 *
	 * @param userService the user service
	 */
	public UserController(UserService userService) {
		this.userService = userService;
		this.userMapper = Mappers.getMapper(IUserMapper.class);
	}

	/**
	 * Save.
	 *
	 * @param obj the obj
	 * @return the response entity
	 */
	@Override
	@PostMapping(REGISTER_ENDPOINT)
	public ResponseEntity<ResponseBody<UserDTO>> save(@RequestBody UserDTO obj) {
		ObjectUtil.setEntityMetadata(obj, Long.valueOf(-1));
		return super.save(obj);
	}

	/**
	 * Authenticate user.
	 *
	 * @param obj the obj
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping(LOGIN_ENDPOINT)
	public ResponseEntity<ResponseBody<UserDTO>> authenticateUser(@RequestBody UserDTO obj) throws IOException {
		User user = userService.authenticateUser(obj.getEmail(), obj.getPassword());
		responseCleanUp(user);
		UserDTO userDTO = userMapper.toDto(user);

		return ResponseEntity.ok(new ResponseBody<>(userDTO));
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	@Override
	protected AbstractService<User> getService() {
		return userService;
	}

	/**
	 * Gets the mapper.
	 *
	 * @return the mapper
	 */
	@Override
	protected IAbstractMapper<User, UserDTO> getMapper() {
		return userMapper;
	}

	/**
	 * Response clean up.
	 *
	 * @param entity the entity
	 */
	@Override
	protected void responseCleanUp(User entity) {
		entity.setPassword(null);
	}

	@Override
	protected String getEntityType() {
		return AppEntityCodes.USER;
	}

}
