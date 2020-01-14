/**
 * 
 */
package in.quallit.hrmanager.api.controllers.common;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.quallit.hrmanager.api.dtos.common.AbstractDTO;
import in.quallit.hrmanager.api.entities.User;
import in.quallit.hrmanager.api.entities.common.AbstractEntity;
import in.quallit.hrmanager.api.entities.common.ResponseBody;
import in.quallit.hrmanager.api.mappers.IAbstractMapper;
import in.quallit.hrmanager.api.services.AbstractService;
import in.quallit.hrmanager.api.services.UserService;
import in.quallit.hrmanager.api.utilities.DatabaseKeyUtility;
import in.quallit.hrmanager.api.utilities.ObjectUtil;

/**
 * The Class AbstractController.
 *
 * @author JIGS
 * @param <E> the element type
 * @param <D> the generic type
 */
@RestController
public abstract class AbstractController<E extends AbstractEntity, D extends AbstractDTO> {

	/** The Constant SECURE_PATH. */
	protected static final String SECURE_PATH = "s";

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Save.
	 *
	 * @param obj the obj
	 * @return the response entity
	 */
	@PostMapping(SECURE_PATH)
	public ResponseEntity<ResponseBody<D>> save(@RequestBody D obj) {
		return saveOrUpdate(obj);
	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the response entity
	 */
	@PutMapping(SECURE_PATH)
	public ResponseEntity<ResponseBody<D>> update(@RequestBody D obj) {
		return saveOrUpdate(obj);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping(SECURE_PATH + "/{id}")
	public ResponseEntity<ResponseBody<D>> findById(@PathVariable("id") String strId) {
		Long id = DatabaseKeyUtility.decrypt(strId);
		E obj = getService().findById(id, null);
		D retObj = null;
		if (ObjectUtil.isNotEmpty(obj)) {
			responseCleanUp(obj);
			retObj = getMapper().toDto(obj);
		}
		return ResponseEntity.ok(new ResponseBody<>(retObj));
	}

	/**
	 * Find all.
	 *
	 * @return the response entity
	 */
	@GetMapping(SECURE_PATH)
	public ResponseEntity<ResponseBody<List<D>>> findAll() {
		List<E> objs = getService().findAll(null);
		if (ObjectUtil.isNotEmpty(objs)) {
			objs.forEach(single -> responseCleanUp(single));
		}
		List<D> retObjs = getMapper().toDTOList(objs);

		return ResponseEntity.ok(new ResponseBody<>(retObjs));
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping(SECURE_PATH + "/{id}")
	public ResponseEntity<ResponseBody<Void>> delete(@PathVariable("id") String strId) {
		Long id = DatabaseKeyUtility.decrypt(strId);
		E obj = getService().findById(id, null);
		if (ObjectUtil.isNotEmpty(obj)) {
			User currentUser = ObjectUtil.getCurrentUser(userService, null);
			if (ObjectUtil.isNotEmpty(currentUser)) {
				obj.setUpdatedBy(currentUser.getId());
			}
			obj.setUpdatedOn(new Date());
			getService().delete(obj);
			return ResponseEntity.ok(new ResponseBody<>(null, "SUCCESS"));
		}
		return ResponseEntity.ok(new ResponseBody<>(null, "FAIL"));
	}

	/**
	 * Save or update.
	 *
	 * @param obj the obj
	 * @return the response entity
	 */
	private ResponseEntity<ResponseBody<D>> saveOrUpdate(D obj) {
		createObjectForSaveOrUpdate(obj);
		E savedObj = getService().saveOrUpdate(getMapper().toEntity(obj));
		responseCleanUp(savedObj);
		D retObj = getMapper().toDto(savedObj);
		return ResponseEntity.ok(new ResponseBody<>(retObj));
	}

	/**
	 * Creates the object for save or update.
	 *
	 * @param obj the obj
	 */
	protected void createObjectForSaveOrUpdate(D obj) {
		User currentUser = ObjectUtil.getCurrentUser(userService, null);
		if (ObjectUtil.isNotEmpty(currentUser)) {
			ObjectUtil.setEntityMetadata(obj, currentUser.getId());
		}
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	protected abstract AbstractService<E> getService();

	/**
	 * Gets the mapper.
	 *
	 * @return the mapper
	 */
	protected abstract IAbstractMapper<E, D> getMapper();

	/**
	 * Response clean up.
	 *
	 * @param entity the entity
	 */
	protected abstract void responseCleanUp(E entity);

}
