package ru.geekbrains.lesson7_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.lesson7_2.persist.Product;
import ru.geekbrains.lesson7_2.persist.ProductRepository;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam Optional<String> nameFilter,
                           @RequestParam Optional<BigDecimal> minPriceFilter,
                           @RequestParam Optional<BigDecimal> maxPriceFilter) {

        // Первый способ

//        List<Product> products = productRepository.findByFilterByQuery(
//                nameFilter.orElse(null),
//                minPriceFilter.orElse(null),
//                maxPriceFilter.orElse(null)
//        );

        // Второй способ

        Specification<Product> spec = Specification.where(null);

        List<Product> products = productRepository.findAll(spec
                .and(nameFilter.<Specification<Product>>map(s -> (root, query, cb) -> cb.like(root.get("name"), "%" + s + "%")).orElse(null))
                .and(minPriceFilter.<Specification<Product>>map(s -> (root, query, cb) -> cb.ge(root.get("price"), s)).orElse(null))
                .and(maxPriceFilter.<Specification<Product>>map(s -> (root, query, cb) -> cb.le(root.get("price"), s)).orElse(null))
        );

        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
