/**
 * 
 */
package in.quallit.hrmanager.api.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.quallit.hrmanager.api.entities.common.AbstractEntity;
import in.quallit.hrmanager.api.enums.StatusEnum;
import in.quallit.hrmanager.api.utilities.ObjectUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractService.
 *
 * @author JIGS
 * @param <E> the element type
 */
@Service
@Transactional(readOnly = true)
public abstract class AbstractService<E extends AbstractEntity> {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The repository. */
	@Autowired
	private JpaRepository<E, Long> repository;

	/**
	 * Save or update.
	 *
	 * @param obj the obj
	 * @return the e
	 */
	@Transactional
	public E saveOrUpdate(E obj) {
		return repository.save(obj);
	}

	/**
	 * Find all.
	 *
	 * @param mappingObjects the mapping objects
	 * @return the iterable
	 * @throws SecurityException the security exception
	 */
	@Transactional(readOnly = true)
	public List<E> findAll(List<String> mappingObjects) {
		List<E> returnValues = repository.findAll();
		initializeLazyObjects(mappingObjects, returnValues);
		return returnValues;
	}

	/**
	 * Find by id.
	 *
	 * @param id             the id
	 * @param mappingObjects the mapping objects
	 * @return the e
	 */
	@Transactional(readOnly = true)
	public E findById(Long id, List<String> mappingObjects) {
		E returnValue = repository.findById(id).orElse(null);
		initializeLazyObjects(mappingObjects, returnValue);
		return returnValue;
	}

	/**
	 * Initialize lazy objects.
	 *
	 * @param mappingObjects the mapping objects
	 * @param value          the value
	 */
	@SuppressWarnings("unchecked")
	public void initializeLazyObjects(List<String> mappingObjects, Object value) {
		if (ObjectUtil.isNotEmpty(mappingObjects) && ObjectUtil.isNotEmpty(value)) {
			if (value instanceof Iterable) {
				for (E val : (Iterable<E>) value) {
					initialize(mappingObjects, val);
				}
			} else if (value instanceof AbstractEntity) {
				initialize(mappingObjects, (E) value);
			}
		}
	}

	/**
	 * Initialize.
	 *
	 * @param mappingObjects the mapping objects
	 * @param value          the value
	 */
	private void initialize(List<String> mappingObjects, E value) {
		for (String mapping : mappingObjects) {
			final String methodName = "get" + mapping.substring(0, 1).toUpperCase() + mapping.substring(1);
			try {
				Hibernate.initialize(value.getClass().getMethod(methodName).invoke(value));
			} catch (HibernateException | NoSuchMethodException | InvocationTargetException | IllegalAccessException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Gets the criteria builder.
	 *
	 * @return the criteria builder
	 */
	public CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

	/**
	 * Gets the criteria query.
	 *
	 * @param c the c
	 * @return the criteria query
	 */
	public CriteriaQuery<? extends AbstractEntity> getCriteriaQuery(Class<? extends AbstractEntity> c) {
		return getCriteriaBuilder().createQuery(c);
	}

	/**
	 * Gets the criteria root entity.
	 *
	 * @param c the c
	 * @return the criteria root entity
	 */
	public Root<? extends AbstractEntity> getCriteriaRootEntity(Class<? extends AbstractEntity> c) {
		return getCriteriaQuery(c).from(c);
	}

	/**
	 * Delete.
	 *
	 * @param obj the obj
	 */
	@Transactional
	public void delete(E obj) {
		if (ObjectUtil.isNotEmpty(obj)) {
			obj.setStatus(StatusEnum.INACTIVE);
			repository.save(obj);
		}
	}

	/**
	 * Save or update.
	 *
	 * @param id the id
	 * @return the e
	 */
	@Transactional
	public void hardDelete(Long id) {
		repository.deleteById(id);
	}
}
