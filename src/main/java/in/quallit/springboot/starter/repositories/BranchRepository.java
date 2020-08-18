/**
 * 
 */
package in.quallit.springboot.starter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.quallit.springboot.starter.entities.Branch;

/**
 * @author JIGS
 *
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
