package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe getRecipeById(Long id){
        Optional<Recipe> optionRecipe = recipeRepository.findById(id);
        if(!optionRecipe.isPresent()){
            throw new RuntimeException("recipe not found");
        }
        return optionRecipe.get();
    }

    public Set<Recipe> getAllRecipes(){
        System.out.println("In RecipeServiceImpl");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
