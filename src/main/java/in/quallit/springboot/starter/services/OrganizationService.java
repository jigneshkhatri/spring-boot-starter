/**
 * 
 */
package in.quallit.springboot.starter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.quallit.springboot.starter.entities.Branch;
import in.quallit.springboot.starter.entities.Organization;
import in.quallit.springboot.starter.services.common.AbstractService;
import in.quallit.springboot.starter.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
@Service
@Transactional(readOnly = true)
public class OrganizationService extends AbstractService<Organization> {

	@Autowired
	private BranchService branchService;

	@Autowired
	private UserRoleService userRoleService;

	@Override
	@Transactional
	public Organization saveOrUpdate(Organization obj) {
		boolean isCreate = false;
		if (ObjectUtil.isEmpty(obj.getId())) {
			// create
			isCreate = true;
		}
		super.saveOrUpdate(obj);
		if (isCreate) {
			// Create default hidden branch
			Branch defaultBranch = this.branchService.createAndSaveDefaultBranch(obj);
			obj.setBranchId(defaultBranch.getId());

			// Assign creator of the organization to default branch
			this.userRoleService.assignSuperAdminRoleToDefaultBranch(defaultBranch);
		}

		return obj;
	}

}
