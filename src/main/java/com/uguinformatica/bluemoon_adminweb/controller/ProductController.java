package com.uguinformatica.bluemoon_adminweb.controller;

import com.uguinformatica.bluemoon_adminweb.model.Product;
import com.uguinformatica.bluemoon_adminweb.model.User;
import com.uguinformatica.bluemoon_adminweb.service.product.IProductService;
import com.uguinformatica.bluemoon_adminweb.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/app/product")
public class ProductController {

    private final IUserService userService;
    private final IProductService productService;

    @Autowired
    public ProductController(IUserService userService, IProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts().orElse(null));
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);
        return "/app/product/product_list";
    }

    @GetMapping("/edit/{id}")
    public String getProductEdit(Model model, @PathVariable("id") long id) {
        model.addAttribute("product", productService.getProductByID(id).orElse(null));
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);
        return "/app/product/product_edit";
    }

    @PostMapping("/edit/{id}")
    public String postProductEdit(
            Model model,
            @PathVariable long id,
            @ModelAttribute("name") String name,
            @ModelAttribute("description") String description,
            @ModelAttribute("img") String img,
            @ModelAttribute("price") double price) {
        Product updatedProduct = productService.getProductByID(id).orElse(null);
        if (updatedProduct != null) {
            updatedProduct.setName(name);
            updatedProduct.setDescription(description);
            updatedProduct.setImg(img);
            updatedProduct.setPrice(price);
            productService.updateProduct(updatedProduct);
        }
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);
        return "redirect:/app/product/list";
    }

    @GetMapping("/new")
    public String getProductNew(Model model) {
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);
        return "/app/product/product_new";
    }

    @PostMapping("/delete/{id}")
    public String postProductDelete(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return "redirect:/app/product/list";
    }

    @PostMapping("/new")
    public String postProductNew(
            Model model,
            @ModelAttribute("name") String name,
            @ModelAttribute("description") String description,
            @ModelAttribute("img") String img,
            @ModelAttribute("price") double price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImg(img);
        product.setPrice(price);
        productService.createProduct(product);
        User user = userService.getCurrentUser().orElse(null); // Get current user.
        model.addAttribute("user", user);
        return "redirect:/app/product/list";
    }

}
