package Controller;

import Entity.Order;
import Entity.Product;
import Entity.User;
import Service.ProductService;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("viewAvailableProducts")
    public ResponseEntity<?> viewAvailableProductItems() {
        List<Product> availableItems = productService.getAvailableGroceryItems();
        return ResponseEntity.ok(availableItems);
    }

    @PostMapping("bookOrders")
    public ResponseEntity<?> bookProductItems(@RequestBody List<Long> itemIds) {
        Order order = productService.bookItems(itemIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
