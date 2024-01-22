package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Product;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidProductIdException;
import mk.ukim.finki.wp.exam.example.repository.CategoryRepository;
import mk.ukim.finki.wp.exam.example.repository.ProductRepository;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * @return List of all products in the database
     */
    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    /**
     * returns an optional of the product with the given id, or an empty optional when there is no product with this id
     *
     * @param id The id of the product that we want to obtain
     * @return
     * @throws InvalidProductIdException when there is no product with the given id
     */
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(InvalidProductIdException::new);
    }

    /**
     * This method is used to create a new product, and save it in the database.
     *
     * @param name
     * @param price
     * @param quantity
     * @param categories The list of category ids to which the product belongs.
     * @return The product that is created. The id should be generated when the product is created.
     * @throws InvalidCategoryIdException when there is no category with the given id
     */
    @Override
    public Product create(String name, Double price, Integer quantity, List<Long> categories) {
        List<Category> categoryList = categoryRepository.findAllById(categories);
        Product product = new Product(name, price, quantity, categoryList);
        return productRepository.save(product);
    }

    /**
     * This method is used to create a new product, and save it in the database.
     *
     * @param id         The id of the product that is being edited
     * @param name
     * @param price
     * @param quantity
     * @param categories The list of category ids to which the product belongs.
     * @return The product that is updated.
     * @throws InvalidProductIdException  when there is no product with the given id
     * @throws InvalidCategoryIdException when there is no category with the given id
     */
    @Override
    public Product update(Long id, String name, Double price, Integer quantity, List<Long> categories) {
        List<Category> categoryList = categoryRepository.findAllById(categories);
        Product product = findById(id);

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategories(categoryList);
        return productRepository.save(product);
    }

    /**
     * Method that should delete a product. If the id is invalid, it should throw InvalidProductIdException.
     *
     * @param id
     * @return The product that is deleted.
     * @throws InvalidProductIdException when there is no product with the given id
     */
    @Override
    public Product delete(Long id) {
        Product product = findById(id);
        productRepository.deleteById(id);
        return product;
    }

    /**
     * The implementation of this method should use repository implementation for the filtering.
     *
     * @param name       String that is used to filter the products that contain it in their name.
     *                   This param can be null, and is not used for filtering in this case.
     * @param categoryId Used for filtering the products that belong to the category with id=categoryId.
     *                   This param can be null, and is not used for filtering in this case.
     * @return The products that meet the filtering criteria
     */
    @Override
    public List<Product> listProductsByNameAndCategory(String name, Long categoryId) {
        return null;
    }
}
