package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Function;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IFunctionRepository extends CrudRepository<Function, Integer> {
    Optional<Function> findByFuncId(int funcId);
    Function save(Function Function);
    Iterable<Function> findAll();
    void delete(int funcId) throws Exception;
}
