package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    @Autowired
    private RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() throws Exception {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setDescription("Test Recipe");

        recipeReactiveRepository.save(recipe).block();

        assertEquals(Long.valueOf(1L), recipeReactiveRepository.count().block());
    }
}