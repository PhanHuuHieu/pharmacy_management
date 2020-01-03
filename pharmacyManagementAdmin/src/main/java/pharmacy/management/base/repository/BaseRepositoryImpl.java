package pharmacy.management.base.repository;

import java.io.Serializable;


/**
 *
 *
 * @param <T>
 * @param <ID>
 */
public abstract class BaseRepositoryImpl<T, ID extends Serializable>
extends BaseRepositoryAbstract<T, ID> implements BaseRepository<T, ID> {
}

