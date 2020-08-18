/**
 * 
 */
package in.quallit.springboot.starter.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.quallit.springboot.starter.entities.Branch;
import in.quallit.springboot.starter.entities.Organization;
import in.quallit.springboot.starter.enums.StatusEnum;
import in.quallit.springboot.starter.services.common.AbstractService;
import in.quallit.springboot.starter.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
@Service
@Transactional(readOnly = true)
public class BranchService extends AbstractService<Branch> {

	public Branch createAndSaveDefaultBranch(Organization organization) {
		if (ObjectUtil.isEmpty(organization)) {
			return null;
		}
		Branch defaultBranch = new Branch();
		defaultBranch.setAddress(organization.getAddress());
		defaultBranch.setContactNumber(organization.getContactNumber());
		defaultBranch.setCreatedBy(organization.getCreatedBy());
		defaultBranch.setEmail(organization.getEmail());
		defaultBranch.setIsDefault(true);
		defaultBranch.setName(organization.getName());
		defaultBranch.setOrganizationId(organization.getId());
		defaultBranch.setStatus(StatusEnum.ACTIVE);
		defaultBranch.setUpdatedBy(organization.getUpdatedBy());
		return super.saveOrUpdate(defaultBranch);
	}
}
