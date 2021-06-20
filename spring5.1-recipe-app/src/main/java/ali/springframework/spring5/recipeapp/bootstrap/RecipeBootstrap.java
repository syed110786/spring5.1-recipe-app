package ali.springframework.spring5.recipeapp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ali.springframework.spring5.recipeapp.domain.Category;
import ali.springframework.spring5.recipeapp.domain.Difficulty;
import ali.springframework.spring5.recipeapp.domain.Ingredient;
import ali.springframework.spring5.recipeapp.domain.Notes;
import ali.springframework.spring5.recipeapp.domain.Recipe;
import ali.springframework.spring5.recipeapp.domain.UnitOfMeasure;
import ali.springframework.spring5.recipeapp.repositories.CategoryRepository;
import ali.springframework.spring5.recipeapp.repositories.RecipeRepository;
import ali.springframework.spring5.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	
	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
		log.debug("Loading Bootstrap Data");
	}
	
	private List<Recipe> getRecipes(){
		List<Recipe> recipes = new ArrayList<>(2);
		
		//get UOMs
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
		if(!eachUomOptional.isPresent()) {
			throw new RuntimeException("Exception1 UOM Not Found");
		}
		
		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		if(!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Exception2 UOM Not Found");
		}
		
		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		if(!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Exception3 UOM Not Found");
		}
		
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		if(!dashUomOptional.isPresent()) {
			throw new RuntimeException("Exception4 UOM Not Found");
		}
		
		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
		if(!pintUomOptional.isPresent()) {
			throw new RuntimeException("Exception8 UOM Not Found");
		}
		
		Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		if(!cupsUomOptional.isPresent()) {
			throw new RuntimeException("Exception5 UOM Not Found");
		}
		
		//get optionals
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tablespoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teaspoonUom = teaSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure cupsUom = cupsUomOptional.get();
		
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Exception6 UOM Not Found");
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		if(!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Exception7 UOM Not Found");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		
		//Tummy Guac
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guaava Each dash pint cup");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDirections("American Cup Teaspoon Dash");
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes("Mexican Tablespoon teaspoon Each Dash");
		//guacNotes.setRecipe(guacRecipe);
		guacRecipe.setNotes(guacNotes);
		
		guacRecipe.addIngredient(new Ingredient("ripe avacados",new BigDecimal(2),eachUom));
		guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(5),teaspoonUom));
		guacRecipe.addIngredient(new Ingredient("fresh lime juice",new BigDecimal(2),tablespoonUom));
		guacRecipe.addIngredient(new Ingredient("serrano chiles, stems seeds removed",new BigDecimal(2),eachUom));
		guacRecipe.addIngredient(new Ingredient("Cilantro",new BigDecimal(2),tablespoonUom));
		guacRecipe.addIngredient(new Ingredient("freshly grated black pepper",new BigDecimal(2),dashUom));
		guacRecipe.addIngredient(new Ingredient("ripe tomato",new BigDecimal(5),eachUom));
		
		guacRecipe.setUrl("www.hussainiat.com");
		guacRecipe.setServings(5);
		guacRecipe.setSource("Ghar ka khana");
		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);
		
		recipes.add(guacRecipe);
		
		//Tummy Tacos
		Recipe tacosRecipe= new Recipe();
		tacosRecipe.setDescription("Spicy Grilled Chicken Taco Each dash pint cup");
		tacosRecipe.setCookTime(9);
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);
		
		tacosRecipe.setDirections("American Mexican tablespoon cup dash");
		
		Notes tacoNotes = new Notes();
		tacoNotes.setRecipeNotes("We Mexican cup American dash");
		//tacoNotes.setRecipe(tacosRecipe);
		tacosRecipe.setNotes(tacoNotes);
		
		tacosRecipe.addIngredient(new Ingredient("Ancho Chill",new BigDecimal(2),tablespoonUom));
		tacosRecipe.addIngredient(new Ingredient("Dried Oregano",new BigDecimal(1),teaspoonUom));
		tacosRecipe.addIngredient(new Ingredient("Sugar",new BigDecimal(1),teaspoonUom));
		tacosRecipe.addIngredient(new Ingredient("Salt",new BigDecimal(1),teaspoonUom));
		tacosRecipe.addIngredient(new Ingredient("Clove of Garlic",new BigDecimal(1),eachUom));
		tacosRecipe.addIngredient(new Ingredient("Clove of Garlic",new BigDecimal(1),eachUom));
		tacosRecipe.addIngredient(new Ingredient("Clove of Garlic",new BigDecimal(1),eachUom));
		
		tacosRecipe.getCategories().add(americanCategory);
		tacosRecipe.getCategories().add(mexicanCategory);
		
		tacosRecipe.setUrl("www.hussainiat.com");
		tacosRecipe.setServings(5);
		tacosRecipe.setSource("Ghar ka khana");
		recipes.add(tacosRecipe);
		return recipes;
		
	}
	
	
}
