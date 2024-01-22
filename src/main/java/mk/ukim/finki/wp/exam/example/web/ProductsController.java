package mk.ukim.finki.wp.exam.example.web;

import mk.ukim.finki.wp.exam.example.model.Product;
import mk.ukim.finki.wp.exam.example.model.User;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductsController {

    private final ProductService service;

    public ProductsController(ProductService service) {
        this.service = service;
    }


    @GetMapping({"/", "/products"})
    public String showProducts(@RequestParam(required = false) String nameSearch,
                               @RequestParam(required = false) Long categoryId,
                               Model model) {
        List<Product> products;
        if (nameSearch == null && categoryId == null) {
            products = this.service.listAllProducts();
        } else {
            products = this.service.listProductsByNameAndCategory(nameSearch, categoryId);
        }

        model.addAttribute("products", products);
        return "list.html";
    }

    public String showAdd() {

        return "";
    }

    public String showEdit(Long id) {
        this.service.findById(id);
        return "";
    }


    public String create(String name, Double price, Integer quantity, List<Long> categories) {
        this.service.create(name, price, quantity, categories);
        return "";
    }

    public String update(Long id, String name, Double price, Integer quantity, List<Long> categories) {
        this.service.update(id, name, price, quantity, categories);
        return "";
    }

    public String delete(Long id) {
        this.service.delete(id);
        return "";
    }
}
