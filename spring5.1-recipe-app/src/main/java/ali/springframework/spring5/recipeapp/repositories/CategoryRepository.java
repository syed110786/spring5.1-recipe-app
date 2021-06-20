package ali.springframework.spring5.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ali.springframework.spring5.recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
	
	Optional<Category> findByDescription(String description);

}
