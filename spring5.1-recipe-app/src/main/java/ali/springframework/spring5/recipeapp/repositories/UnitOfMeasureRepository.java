package ali.springframework.spring5.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ali.springframework.spring5.recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
	
	Optional<UnitOfMeasure> findByDescription(String description);

}
