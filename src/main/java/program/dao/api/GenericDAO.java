package program.dao.api;

import java.io.Serializable;

public interface GenericDAO<T, ID extends Serializable> {

    void persist(T entity);
    void update(T entity);
    void delete(T entity);
}
