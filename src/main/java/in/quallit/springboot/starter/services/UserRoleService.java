/**
 * 
 */
package in.quallit.springboot.starter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.quallit.springboot.starter.entities.Branch;
import in.quallit.springboot.starter.entities.Role;
import in.quallit.springboot.starter.entities.User;
import in.quallit.springboot.starter.entities.UserRole;
import in.quallit.springboot.starter.enums.StatusEnum;
import in.quallit.springboot.starter.services.common.AbstractService;
import in.quallit.springboot.starter.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
@Service
@Transactional(readOnly = true)
public class UserRoleService extends AbstractService<UserRole> {

	@Autowired
	private RoleService roleService;

	public UserRole assignSuperAdminRoleToDefaultBranch(Branch defaultBranch) {
		if (ObjectUtil.isEmpty(defaultBranch)) {
			return null;
		}
		UserRole userRole = new UserRole();
		userRole.setBranchId(defaultBranch.getId());
		userRole.setCreatedBy(defaultBranch.getCreatedBy());
		userRole.setRole(roleService.findByCode(Role.Codes.ADMIN, null));
		userRole.setStatus(StatusEnum.ACTIVE);
		userRole.setUpdatedBy(defaultBranch.getUpdatedBy());
		userRole.setUser(new User(defaultBranch.getCreatedBy()));
		return super.saveOrUpdate(userRole);
	}
}
