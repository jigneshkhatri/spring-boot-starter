/**
 * 
 */
package in.quallit.springboot.starter.config.interceptors;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import in.quallit.springboot.starter.entities.Role;
import in.quallit.springboot.starter.entities.RoleEntityAccess;
import in.quallit.springboot.starter.entities.User;
import in.quallit.springboot.starter.entities.mappingObjects.RoleEntityAccessMappingObjects;
import in.quallit.springboot.starter.entities.mappingObjects.UserMappingObjects;
import in.quallit.springboot.starter.exceptions.BusinessRuleValidationException;
import in.quallit.springboot.starter.services.RoleEntityAccessService;
import in.quallit.springboot.starter.services.UserService;
import in.quallit.springboot.starter.utilities.ObjectUtil;
import in.quallit.springboot.starter.utilities.constants.CommonConstants;
import javassist.tools.web.BadHttpRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestInterceptor.
 *
 * @author JIGS
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LogManager.getLogger(RequestInterceptor.class);

	@Autowired
	private RoleEntityAccessService roleEntityAccessService;

	@Autowired
	private UserService userService;

	/**
	 * Add API routes in this list, which do not require BranchID in their header
	 */
	private static final List<String> ROUTES_WITHOUT_BRANCH = Arrays.asList("/hrmanager-api/organization/s");

	/**
	 * Pre handle.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param object   the object
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		if (object instanceof ResourceHttpRequestHandler) {
			return true;
		}
		final String requestMethod = request.getMethod();

		final HandlerMethod method = (HandlerMethod) object;
		final String methodToBeCalled = method.getMethod().getName();
		final String requestURI = request.getRequestURI();
		Long branchId = ObjectUtil.getBranchIdFromHeader(request);

		final StringTokenizer stringtokenizer = new StringTokenizer(request.getServletPath(), "/");
		String tempEntityCode = null;
		if (ObjectUtil.isNotEmpty(stringtokenizer) && stringtokenizer.hasMoreTokens()) {
			tempEntityCode = stringtokenizer.nextToken();
		}
		final String entityCode = tempEntityCode;

		boolean isOpenRoute = true;
		if (ObjectUtil.isNotEmpty(stringtokenizer)) {
			while (stringtokenizer.hasMoreTokens()) {
				String token = stringtokenizer.nextToken();
				if ("s".equalsIgnoreCase(token)) {
					isOpenRoute = false;
					break;
				}
			}
		}

		boolean isBranchIdHeaderRequired = true;
		if (ObjectUtil.isNotEmpty(requestURI) && ObjectUtil.isNotEmpty(ROUTES_WITHOUT_BRANCH)) {
			isBranchIdHeaderRequired = !ROUTES_WITHOUT_BRANCH.contains(requestURI);
		}

		LOGGER.trace("PreHandle: Intercepting the Request");
		LOGGER.trace("--------------------------------------------");
		LOGGER.trace("Request URI:       " + requestURI);
		LOGGER.trace("Request Method:    " + requestMethod);
		LOGGER.trace("Controller Method: " + methodToBeCalled);
		LOGGER.trace("Entity Code:       " + entityCode);
		LOGGER.trace("Branch Id:         " + branchId);
		LOGGER.trace("--------------------------------------------");

		if (isOpenRoute || !isBranchIdHeaderRequired) {
			// This is an open route, so no safety checks are required.
			return true;
		}

		// Safety checks - start

		if (ObjectUtil.isEmpty(entityCode)) {
			// Throw exception: Bad request
			throw new BadHttpRequest();
		}
		if (ObjectUtil.isEmpty(branchId) && isBranchIdHeaderRequired) {
			// Throw exception: Missing branch information in headers
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_003);
		}
		User currentUser = ObjectUtil.getCurrentUser(userService, Arrays.asList(UserMappingObjects.USER_ROLES));
		if (ObjectUtil.isEmpty(currentUser)) {
			// Throw exception: Not authenticated
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_004);
		}
		Role branchWiseRole = currentUser.getBranchWiseRole(branchId);
		if (isBranchIdHeaderRequired && ObjectUtil.isEmpty(branchWiseRole)) {
			// Throw exception: Not authorized
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_005);
		}

		if (Role.Codes.ADMIN.equalsIgnoreCase(branchWiseRole.getCode())) {
			return true;
		}

		List<RoleEntityAccess> roleEntityAccessList = this.roleEntityAccessService
				.findAll(Arrays.asList(RoleEntityAccessMappingObjects.APP_ENTITY, RoleEntityAccessMappingObjects.ROLE));

		if (ObjectUtil.isEmpty(roleEntityAccessList)) {
			// Throw exception:
			throw new RuntimeException("Internal Server Error");
		}

		RoleEntityAccess roleEntityAccess = roleEntityAccessList.stream()
				.filter(single -> entityCode.equalsIgnoreCase(single.getAppEntity().getCode())
						&& single.getRole().getId().equals(branchWiseRole.getId()))
				.findAny().orElse(null);

		if (ObjectUtil.isEmpty(roleEntityAccess)) {
			// Throw exception: Not authorized
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_005);
		}

		if (HttpMethod.GET.toString().equalsIgnoreCase(requestMethod) && !roleEntityAccess.getHasAllDataAccess()
				&& CommonConstants.FIND_ALL_METHOD_NAME.equalsIgnoreCase(methodToBeCalled)) {
			// Throw exception: Not authorized
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_005);
		}

		if (ObjectUtil.isEmpty(roleEntityAccess.getPermittedOperations())
				|| !roleEntityAccess.getPermittedOperations().contains(requestMethod)) {
			// Throw exception: Not authorized
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_005);
		}

		// Safety checks - end

		return true;
	}

	/**
	 * Post handle.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param object   the object
	 * @param model    the model
	 * @throws Exception the exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		LOGGER.trace("_________________________________________");
		LOGGER.trace("In postHandle request processing " + "completed by @RestController");
		LOGGER.trace("_________________________________________");
	}

	/**
	 * After completion.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param object   the object
	 * @param arg3     the arg 3
	 * @throws Exception the exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		LOGGER.trace("________________________________________");
		LOGGER.trace("In afterCompletion Request Completed");
		LOGGER.trace("________________________________________");
	}
}
