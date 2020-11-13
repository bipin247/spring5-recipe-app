package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    public Set<Recipe> getAllRecipes();

    public Recipe getRecipeById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

}
