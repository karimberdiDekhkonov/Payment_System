package uz.pdp.appjwttoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjwttoken.entity.Output;

import java.util.List;
public interface OutputRepository extends JpaRepository<Output,Integer> {
    List<Output> findAllByFromCardId(Integer cardId);
}
