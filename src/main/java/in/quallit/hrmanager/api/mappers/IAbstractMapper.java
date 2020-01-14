/**
 * 
 */
package in.quallit.hrmanager.api.mappers;

import java.util.List;

import in.quallit.hrmanager.api.dtos.common.AbstractDTO;
import in.quallit.hrmanager.api.entities.common.AbstractEntity;

/**
 * The Interface IAbstractMapper.
 *
 * @author JIGS
 * @param <E> the element type
 * @param <D> the generic type
 */
public interface IAbstractMapper<E extends AbstractEntity, D extends AbstractDTO> {

	/**
	 * To entity.
	 *
	 * @param dto the dto
	 * @return the e
	 */
	E toEntity(D dto);

	/**
	 * To dto.
	 *
	 * @param entity the entity
	 * @return the d
	 */
	D toDto(E entity);

	/**
	 * To entity list.
	 *
	 * @param dtos the dtos
	 * @return the list
	 */
	List<E> toEntityList(List<D> dtos);

	/**
	 * To DTO list.
	 *
	 * @param entities the entities
	 * @return the list
	 */
	List<D> toDTOList(List<E> entities);

	/**
	 * To entity iterable.
	 *
	 * @param dtos the dtos
	 * @return the iterable
	 */
	Iterable<E> toEntityIterable(Iterable<D> dtos);

	/**
	 * To DTO iterable.
	 *
	 * @param entities the entities
	 * @return the iterable
	 */
	Iterable<D> toDTOIterable(Iterable<E> entities);
}
