package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    RecipeServiceImpl recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setDescription("test by id");

        when(recipeRepository.findById(any())).thenReturn(java.util.Optional.of(recipe));

        Recipe retReciped = recipeService.getRecipeById(1L);

        assertNotNull(retReciped);

        verify(recipeRepository,times(1)).findById(any());
        verify(recipeRepository,never()).findAll();

    }

    @Test
    public void getAllRecipes() {
        Set<Recipe> recipeData = new HashSet<>();
        Recipe recipe = new Recipe();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getAllRecipes();
        assertEquals(recipes.size(),1);

        verify(recipeRepository, times(1)).findAll();
    }
}