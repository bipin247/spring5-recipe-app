package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
