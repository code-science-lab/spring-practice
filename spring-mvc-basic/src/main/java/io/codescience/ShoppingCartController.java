package io.codescience;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCartController {

    private List<String> products = new ArrayList<>();
    private Map<String, Integer> cart = new HashMap<>();

    public ShoppingCartController() {
        products.add("Apple");
        products.add("Banana");
        products.add("Orange");
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/cart/add/{product}")
    public String addToCart(@PathVariable String product, Model model) {
        cart.put(product, cart.getOrDefault(product, 0) + 1);
        model.addAttribute("message", product + " added to cart.");
        return "redirect:/products";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }
}