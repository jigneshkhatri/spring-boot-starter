/**
 * 
 */
package in.quallit.springboot.starter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.quallit.springboot.starter.entities.Role;
import in.quallit.springboot.starter.repositories.RoleRepository;
import in.quallit.springboot.starter.services.common.AbstractService;
import in.quallit.springboot.starter.utilities.ObjectUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class RoleService.
 *
 * @author JIGS
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends AbstractService<Role> {

	/** The role repository. */
	@Autowired
	private RoleRepository roleRepository;

	/**
	 * Find by code.
	 *
	 * @param code           the code
	 * @param mappingObjects the mapping objects
	 * @return the role
	 */
	public Role findByCode(String code, List<String> mappingObjects) {
		if (ObjectUtil.isNotEmpty(code)) {
			Role role = this.roleRepository.findByCode(code);
			if (ObjectUtil.isNotEmpty(role)) {
				super.initializeLazyObjects(mappingObjects, role);
			}
			return role;
		}
		return null;
	}
}
