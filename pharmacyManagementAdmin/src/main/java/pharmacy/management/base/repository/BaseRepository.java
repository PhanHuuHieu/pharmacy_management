package pharmacy.management.base.repository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 *
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseRepository<T, ID extends Serializable> {

  /**
   * Get all
   *
   * @return list entity
   */
  List<T> getAll();

  /**
   * Get entity by ID
   *
   * @param id
   * @return entity
   */
  T getById(ID id);

  /**
   * Insert data
   *
   * @param entity
   * @return ID
   */
  ID insert(T entity);

  /**
   * Update entity
   *
   * @param entity
   */
  void update(T entity);

  /**
   * Delete entity
   *
   * @param entity
   */
  void delete(T entity);

  /**
   * Get timestamp from the database server in UTC.
   *
   * @return timestamp
   */
  Timestamp getCurrentTime();

}
