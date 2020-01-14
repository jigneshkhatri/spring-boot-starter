/**
 * 
 */
package in.quallit.hrmanager.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.quallit.hrmanager.api.entities.RoleEntityAccess;
import in.quallit.hrmanager.api.repositories.RoleEntityAccessRepository;
import in.quallit.hrmanager.api.utilities.ObjectUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleEntityAccessService.
 *
 * @author JIGS
 */
@Service
@Transactional(readOnly = true)
public class RoleEntityAccessService extends AbstractService<RoleEntityAccess> {

	/** The role entity access repository. */
	@Autowired
	private RoleEntityAccessRepository roleEntityAccessRepository;

	/**
	 * Find by role id and entity id.
	 *
	 * @param roleId   the role id
	 * @param entityId the entity id
	 * @return the role entity access
	 */
	public RoleEntityAccess findByRoleIdAndAppEntityCode(Long roleId, String entityCode) {
		if (ObjectUtil.isEmpty(roleId) || ObjectUtil.isEmpty(entityCode)) {
			return null;
		}
		return roleEntityAccessRepository.findByRoleIdAndAppEntityCode(roleId, entityCode).orElse(null);
	}
}
