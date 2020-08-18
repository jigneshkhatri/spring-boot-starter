/**
 * 
 */
package in.quallit.springboot.starter.mappers;

import org.mapstruct.Mapper;

import in.quallit.springboot.starter.dtos.BranchDTO;
import in.quallit.springboot.starter.entities.Branch;
import in.quallit.springboot.starter.mappers.common.IAbstractMapper;

/**
 * @author JIGS
 *
 */
@Mapper
public interface IBranchMapper extends IAbstractMapper<Branch, BranchDTO> {

}
