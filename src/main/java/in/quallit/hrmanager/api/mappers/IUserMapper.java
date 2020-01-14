/**
 * 
 */
package in.quallit.hrmanager.api.mappers;

import org.mapstruct.Mapper;

import in.quallit.hrmanager.api.dtos.UserDTO;
import in.quallit.hrmanager.api.entities.User;

/**
 * The Interface IUserMapper.
 *
 * @author JIGS
 */
@Mapper
public interface IUserMapper extends IAbstractMapper<User, UserDTO> {

}
