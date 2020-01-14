/**
 * 
 */
package in.quallit.hrmanager.api.utilities.constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonConstants.
 *
 * @author JIGS
 */
public class CommonConstants {

	/**
	 * Instantiates a new common constants.
	 */
	private CommonConstants() {
	}

	/** The Constant HEADER_ORGANIZATION_ID. */
	public static final String HEADER_ORGANIZATION_ID = "OrganizationId";

	/** The Constant HEADER_BRANCH_ID. */
	public static final String HEADER_BRANCH_ID = "BranchId";

	/** The Constant DEFAULT_DATE_FORMAT. */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/** The Constant DEFAULT_DOCUMENT_ROOT_PATH. */
	public static final String DEFAULT_DOCUMENT_ROOT_PATH = File.separator + "documents" + File.separator;

	/** The Constant MULTIPART_API_ENDPOINT. */
	public static final String MULTIPART_API_ENDPOINT = "/multipart";

	/** The Constant DOWNLOAD_FILE_API_ENDPOINT. */
	public static final String DOWNLOAD_FILE_API_ENDPOINT = "/download";

	/** The Constant FIND_ALL_METHOD_NAME. */
	public static final String FIND_ALL_METHOD_NAME = "findAll";

	/** The Constant ROUTES_WITHOUT_ORG_AND_BRANCH. */
	public static final List<String> ROUTES_WITHOUT_BRANCH = new ArrayList<>();
}
