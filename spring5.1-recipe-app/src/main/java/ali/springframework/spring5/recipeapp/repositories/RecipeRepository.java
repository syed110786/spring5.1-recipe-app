package ali.springframework.spring5.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import ali.springframework.spring5.recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe,Long>{

}
