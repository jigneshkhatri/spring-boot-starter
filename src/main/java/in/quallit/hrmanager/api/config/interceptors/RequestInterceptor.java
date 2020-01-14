/**
 * 
 */
package in.quallit.hrmanager.api.config.interceptors;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import in.quallit.hrmanager.api.entities.Role;
import in.quallit.hrmanager.api.entities.RoleEntityAccess;
import in.quallit.hrmanager.api.entities.User;
import in.quallit.hrmanager.api.entities.mappingObjects.RoleEntityAccessMappingObjects;
import in.quallit.hrmanager.api.entities.mappingObjects.UserMappingObjects;
import in.quallit.hrmanager.api.services.RoleEntityAccessService;
import in.quallit.hrmanager.api.services.UserService;
import in.quallit.hrmanager.api.utilities.ObjectUtil;
import in.quallit.hrmanager.api.utilities.constants.CommonConstants;
import javassist.tools.web.BadHttpRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestInterceptor.
 *
 * @author JIGS
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RoleEntityAccessService roleEntityAccessService;

	@Autowired
	private UserService userService;

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
		if (ObjectUtil.isNotEmpty(requestURI) && ObjectUtil.isNotEmpty(CommonConstants.ROUTES_WITHOUT_BRANCH)) {
			isBranchIdHeaderRequired = !CommonConstants.ROUTES_WITHOUT_BRANCH.contains(requestURI);
		}

		System.out.println("PreHandle: Intercepting the Request");
		System.out.println("--------------------------------------------");
		System.out.println("Request URI:       " + requestURI);
		System.out.println("Request Method:    " + requestMethod);
		System.out.println("Controller Method: " + methodToBeCalled);
		System.out.println("Entity Code:       " + entityCode);
		System.out.println("Branch Id:         " + branchId);
		System.out.println("--------------------------------------------");

		if (isOpenRoute) {
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
			throw new RuntimeException("Missing branch information in headers");
		}
		User currentUser = ObjectUtil.getCurrentUser(userService, Arrays.asList(UserMappingObjects.USER_ROLES));
		if (ObjectUtil.isEmpty(currentUser)) {
			// Throw exception: Not authenticated
			throw new RuntimeException("Not authenticated");
		}
		Role branchWiseRole = currentUser.getBranchWiseRole(branchId);
		if (isBranchIdHeaderRequired && ObjectUtil.isEmpty(branchWiseRole)) {
			// Throw exception: Not authorized
			throw new RuntimeException("Not authorized");
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
			throw new RuntimeException("Not authorized");
		}

		if (ObjectUtil.isEmpty(roleEntityAccess.getPermittedOperations())
				|| !roleEntityAccess.getPermittedOperations().contains(requestMethod)) {
			// Throw exception: Not authorized
			throw new RuntimeException("Not authorized");
		}

		if (HttpMethod.GET.toString().equalsIgnoreCase(requestMethod) && !roleEntityAccess.getHasAllDataAccess()
				&& CommonConstants.FIND_ALL_METHOD_NAME.equalsIgnoreCase(methodToBeCalled)) {
			// Throw exception: Not authorized
			throw new RuntimeException("Not authorized");
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
		System.out.println("_________________________________________");
		System.out.println("In postHandle request processing " + "completed by @RestController");
		System.out.println("_________________________________________");
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
		System.out.println("________________________________________");
		System.out.println("In afterCompletion Request Completed");
		System.out.println("________________________________________");
	}
}
