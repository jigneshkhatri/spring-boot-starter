/**
 * 
 */
package in.quallit.springboot.starter.controllers;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.quallit.springboot.starter.controllers.common.AbstractController;
import in.quallit.springboot.starter.dtos.OrganizationDTO;
import in.quallit.springboot.starter.entities.Organization;
import in.quallit.springboot.starter.mappers.IOrganizationMapper;
import in.quallit.springboot.starter.mappers.common.IAbstractMapper;
import in.quallit.springboot.starter.services.OrganizationService;
import in.quallit.springboot.starter.services.common.AbstractService;
import in.quallit.springboot.starter.utilities.constants.AppEntityCodes;

/**
 * @author JIGS
 *
 */
@RestController
@RequestMapping(value = AppEntityCodes.ORGANIZATION)
public class OrganizationController extends AbstractController<Organization, OrganizationDTO> {

	@Autowired
	private OrganizationService organizationService;

	private IOrganizationMapper organizationMapper = Mappers.getMapper(IOrganizationMapper.class);

	@Override
	protected AbstractService<Organization> getService() {
		return this.organizationService;
	}

	@Override
	protected IAbstractMapper<Organization, OrganizationDTO> getMapper() {
		return this.organizationMapper;
	}

	@Override
	protected void responseCleanUp(Organization entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getEntityType() {
		return AppEntityCodes.ORGANIZATION;
	}

}
