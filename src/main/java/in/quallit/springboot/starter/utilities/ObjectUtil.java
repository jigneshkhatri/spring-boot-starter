/**
 * 
 */
package in.quallit.springboot.starter.utilities;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import in.quallit.springboot.starter.dtos.common.AbstractDTO;
import in.quallit.springboot.starter.entities.User;
import in.quallit.springboot.starter.enums.StatusEnum;
import in.quallit.springboot.starter.services.UserService;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;
import in.quallit.springboot.starter.utilities.constants.CommonConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectUtil.
 *
 * @author JIGS
 */
public class ObjectUtil {

	/**
	 * Instantiates a new object util.
	 */
	private ObjectUtil() {
	}

	/**
	 * Checks if is not null.
	 *
	 * @param obj the obj
	 * @return true, if is not null
	 */
	public static boolean isNotNull(Object obj) {
		return obj != null;
	}

	/**
	 * Checks if is null.
	 *
	 * @param obj the obj
	 * @return true, if is null
	 */
	public static boolean isNull(Object obj) {
		return !isNotNull(obj);
	}

	/**
	 * Checks if is not empty.
	 *
	 * @param obj the obj
	 * @return true, if is not empty
	 */
	public static boolean isNotEmpty(Object obj) {
		if (isNotNull(obj)) {
			if (obj instanceof Collection) {
				return !((Collection) obj).isEmpty();
			} else if (obj instanceof String) {
				return ((String) obj).length() > 0;
			}
			return true;
		}
		return false;
	}

	/**
	 * Checks if is empty.
	 *
	 * @param obj the obj
	 * @return true, if is empty
	 */
	public static boolean isEmpty(Object obj) {
		return !isNotEmpty(obj);
	}

	/**
	 * Collection stream.
	 *
	 * @param obj the obj
	 * @return the stream
	 */
	// TODO: JIGS: Need to check this method. Not working.
	public static Stream<Collection> collectionStream(Collection obj) {
		if (isNotEmpty(obj)) {
			return obj.stream();
		}
		return Stream.empty();
	}

	/**
	 * Gets the safe string value.
	 *
	 * @param str the str
	 * @return the safe string value
	 */
	public static String getSafeStringValue(String str) {
		return isEmpty(str) ? "" : str;
	}

	/**
	 * Gets the authorization header.
	 *
	 * @param httpServletRequest the http servlet request
	 * @return the authorization header
	 */
	public static String getAuthorizationHeader(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getHeader("Authorization");
	}

	/**
	 * Gets the organization id from header.
	 *
	 * @param httpServletRequest the http servlet request
	 * @return the organization id from header
	 */
	public static Long getOrganizationIdFromHeader(HttpServletRequest httpServletRequest) {
		return DatabaseKeyUtility.decrypt(
				getSafeStringValue(httpServletRequest.getHeader(CommonConstants.HEADER_ORGANIZATION_ID)),
				AppEntityCodes.ORGANIZATION);
	}

	/**
	 * Gets the branch id from header.
	 *
	 * @param httpServletRequest the http servlet request
	 * @return the branch id from header
	 */
	public static Long getBranchIdFromHeader(HttpServletRequest httpServletRequest) {
		return DatabaseKeyUtility.decrypt(
				getSafeStringValue(httpServletRequest.getHeader(CommonConstants.HEADER_BRANCH_ID)),
				AppEntityCodes.BRANCH);
	}

	/**
	 * Was collection initialized.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public static boolean wasCollectionInitialized(Object c) {
		if (!(c instanceof PersistentCollection)) {
			return true;
		}

		PersistentCollection pc = (PersistentCollection) c;
		return pc.wasInitialized();
	}

	/**
	 * Was object initialized.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public static boolean wasObjectInitialized(Object c) {
		if (!(c instanceof HibernateProxy)) {
			return true;
		}

		HibernateProxy pc = (HibernateProxy) c;
		return !pc.getHibernateLazyInitializer().isUninitialized();
	}

	/**
	 * Parses the date.
	 *
	 * @param date   the date
	 * @param format the format
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date parseDate(String date, String format) throws ParseException {
		if (ObjectUtil.isEmpty(format)) {
			format = CommonConstants.DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}

	/**
	 * Format date to string.
	 *
	 * @param date   the date
	 * @param format the format
	 * @return the string
	 */
	public static String formatDateToString(Date date, String format) {
		if (ObjectUtil.isEmpty(format)) {
			format = CommonConstants.DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * Format date.
	 *
	 * @param date   the date
	 * @param format the format
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date formatDate(Date date, String format) throws ParseException {
		if (ObjectUtil.isEmpty(format)) {
			format = CommonConstants.DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(sdf.format(date));
	}

	/**
	 * Sets the entity metadata.
	 *
	 * @param entity the entity
	 * @param userId the user id
	 */
	public static void setEntityMetadata(AbstractDTO entity, Long userId) {
		if (ObjectUtil.isNotEmpty(entity)) {
			try {
				Field[] fields = entity.getClass().getFields();
				if (ObjectUtil.isNotEmpty(fields)) {
					for (Field field : fields) {
						if (field.getType().isAssignableFrom(AbstractDTO.class)) {
							setEntityMetadata((AbstractDTO) field.get(entity), userId);
						}
					}
					if (ObjectUtil.isEmpty(entity.getId())) {
						// create
						entity.setCreatedBy(userId);
//						entity.setCreatedOn(new Date());
						entity.setUpdatedBy(userId);
//						entity.setUpdatedOn(new Date());
						if (ObjectUtil.isEmpty(entity.getStatus())) {
							entity.setStatus(StatusEnum.ACTIVE);
						}
					} else {
						// update
						entity.setUpdatedBy(userId);
//						entity.setUpdatedOn(new Date());
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Checks if is numeric.
	 *
	 * @param str the str
	 * @return true, if is numeric
	 */
	public static boolean isNumeric(String str) {
		return StringUtils.isNumeric(str);
	}

	/**
	 * Checks if is email.
	 *
	 * @param str the str
	 * @return true, if is email
	 */
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher mat = pattern.matcher(str);
		return mat.matches();
	}

	/**
	 * Gets the current user.
	 *
	 * @param userService    the user service
	 * @param mappingObjects the mapping objects
	 * @return the current user
	 */
	public static User getCurrentUser(UserService userService, List<String> mappingObjects) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (ObjectUtil.isNotNull(authentication) && !(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return userService.findByEmailOrContactNumber(currentUserName, mappingObjects);
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println("Current timestamp: " + Calendar.getInstance().getTimeInMillis());
	}
}
