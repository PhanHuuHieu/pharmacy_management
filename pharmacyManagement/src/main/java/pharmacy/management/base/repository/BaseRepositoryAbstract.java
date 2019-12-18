
package pharmacy.management.base.repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 *
 * @param <T>
 * @param <ID>
 */
public abstract class BaseRepositoryAbstract<T, ID extends Serializable>
    implements BaseRepository<T, ID> {
	
  @PersistenceContext
  private EntityManager entityManager;

  private final Class<T> persistentClass;

  private final Field fieldId;

  @SuppressWarnings("unchecked")
  public BaseRepositoryAbstract() {
    Class<T> clazz = null;
    Field field = null;
    try {
      clazz =
          (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
              .getActualTypeArguments()[0];
      Field[] fields = clazz.getDeclaredFields();
      for (Field f : fields) {
        if (f.getAnnotation(javax.persistence.Id.class) != null) {
          field = f;
          break;
        }
      }
    } catch (Exception e) {
      clazz = null;
      field = null;
    } finally {
      persistentClass = clazz;
      fieldId = field;
    }

  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> getAll() {
	Query query =
        (Query) this.entityManager.createNativeQuery("from " + persistentClass.getName());
    return query.list();

  }

  @SuppressWarnings("unchecked")
  @Override
  public T getById(ID id) {
    Query query =
        (Query) this.entityManager.createNativeQuery(
	    "from " + persistentClass.getName() + " where " + fieldId.getName()
	        + " = :idValue");
    query.setParameter("idValue", id);
    return (T) query.uniqueResult();
  }

  @Override
  public ID insert(T entity) {
	  return null;
  }

  @Override
  public void update(T entity) {
  }

  @Override
  public void delete(T entity) {
  }

  @Override
  public Timestamp getCurrentTime() {
	SQLQuery sqlQuery = (SQLQuery) this.entityManager.createNativeQuery("select current_timestamp");
    return (Timestamp) sqlQuery.uniqueResult();
  }

}
