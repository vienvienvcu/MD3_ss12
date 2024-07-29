package ra.ss12_usermanagement.model.dao;

import java.util.List;

public interface IUserManagementDao <T,V>{
    List<T> findAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(V id);
    T findById(V id);
}
