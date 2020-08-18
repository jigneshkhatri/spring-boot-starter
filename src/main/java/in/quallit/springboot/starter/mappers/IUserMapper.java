/**
 * 
 */
package in.quallit.springboot.starter.mappers;

import org.mapstruct.Mapper;

import in.quallit.springboot.starter.dtos.UserDTO;
import in.quallit.springboot.starter.entities.User;
import in.quallit.springboot.starter.mappers.common.IAbstractMapper;

/**
 * The Interface IUserMapper.
 *
 * @author JIGS
 */
@Mapper
public interface IUserMapper extends IAbstractMapper<User, UserDTO> {

}
