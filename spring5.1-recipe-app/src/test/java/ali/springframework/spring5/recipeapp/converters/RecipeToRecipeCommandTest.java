package ali.springframework.spring5.recipeapp.converters;


public class RecipeToRecipeCommandTest {

	public static final Long RECIPE_ID = 1L;
	public static final Integer COOK_TIME = Integer.valueOf("5");
	public static final Integer PREP_TIME = Integer.valueOf("7");
	public static final String DESCRIPTION = "My Recipe";
	public static final String DIRECTIONS = "Directions";
	public static final Difficulty DIFFICULTY = Difficulty.EASY;
	public static final Integer SERVINGS = Integer.valueOf("7");
	public static final Integer SOURCE = "Source";
	public static final Integer URL = "Some url";
	public static final Integer CAT_ID_1 = 1L;
	public static final Integer CAT_ID_2 = 2L;
	public static final Integer INGRED_ID_1 = 3L;
	public static final Integer INGRED_ID_2 = 4L;
	RecipeToRecipeCommand converter;
	
	@BeforeEach
	public void setUp() throws Exception {
		converter = new RecipeToRecipeCommand(),
				new CategoryToCategoryCommand(),
				new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
				new NotesToNotesCommand();
	}
	
	@Test
	public void testNullObject() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new Recipe()));
	}
	
	@Test
	public void convert() throws Exception {
		//given
		Recipe recipe = new Recipe();
		recipe.setId(RECIPE_ID);
		recipe.setCookTime(COOK_TIME);
		recipe.setPrepTime(PREP_TIME);
		recipe.setDescription(DESCRIPTION);
		recipe.setDifficulty(DIFFICULTY);
		recipe.setDirections(SERVINGS);
		recipe.setSource(SOURCE);
		recipe.setUrl(URL);
		
		Notes notes = new Notes();
		notes.setId(NOTES_ID);
		
		recipe.setNotes(notes);
		
		Category category = new Category();
		category.setId(CAT_ID_1);
	}
}
