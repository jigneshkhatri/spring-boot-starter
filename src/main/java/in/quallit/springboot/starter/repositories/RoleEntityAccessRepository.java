/**
 * 
 */
package in.quallit.springboot.starter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.quallit.springboot.starter.entities.RoleEntityAccess;

// TODO: Auto-generated Javadoc
/**
 * The Interface RoleEntityAccessRepository.
 *
 * @author JIGS
 */
@Repository
public interface RoleEntityAccessRepository extends JpaRepository<RoleEntityAccess, Long> {

	/**
	 * Find by role id and entity id.
	 *
	 * @param roleId   the role id
	 * @param entityId the entity id
	 * @return the list
	 */
	Optional<RoleEntityAccess> findByRoleIdAndAppEntityCode(Long roleId, String entityCode);
}
