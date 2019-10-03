package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {
    private static final String CATEGORY_DESC = "Test Category";

    @Autowired
    private CategoryReactiveRepository categoryReactiveRepository;

    private Category testCategory;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();

        testCategory = new Category();
        testCategory.setDescription(CATEGORY_DESC);
    }

    @Test
    public void testSave() throws Exception {
        categoryReactiveRepository.save(testCategory).block();

        assertEquals(Long.valueOf(1L), categoryReactiveRepository.count().block());
    }

    @Test
    public void testFindByDescription() throws Exception {
        categoryReactiveRepository.save(testCategory).block();

        Category found = categoryReactiveRepository.findByDescription(CATEGORY_DESC).block();
        assertNotNull(found.getId());
        assertEquals(CATEGORY_DESC, found.getDescription());
    }
}
