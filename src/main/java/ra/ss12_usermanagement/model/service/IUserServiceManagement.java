package ra.ss12_usermanagement.model.service;

import java.util.List;

public interface IUserServiceManagement<T,V> {
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(V id);
    T findById(V id);
}
