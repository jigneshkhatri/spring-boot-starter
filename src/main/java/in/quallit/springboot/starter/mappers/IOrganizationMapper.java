/**
 * 
 */
package in.quallit.springboot.starter.mappers;

import org.mapstruct.Mapper;

import in.quallit.springboot.starter.dtos.OrganizationDTO;
import in.quallit.springboot.starter.entities.Organization;
import in.quallit.springboot.starter.mappers.common.IAbstractMapper;

/**
 * @author JIGS
 *
 */
@Mapper
public interface IOrganizationMapper extends IAbstractMapper<Organization, OrganizationDTO> {

}
