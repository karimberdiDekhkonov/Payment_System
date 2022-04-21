package uz.pdp.appjwttoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appjwttoken.entity.Card;
@RepositoryRestResource(path = "card")
public interface CardRepository extends JpaRepository<Card,Integer> {
}
