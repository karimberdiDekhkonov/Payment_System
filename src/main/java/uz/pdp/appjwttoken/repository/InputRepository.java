package uz.pdp.appjwttoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjwttoken.entity.Input;

import java.util.List;
public interface InputRepository extends JpaRepository<Input,Integer> {
    List<Input> findAllByFromCardId(Integer cardId);
}
