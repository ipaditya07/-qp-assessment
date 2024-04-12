package Controller;

import Entity.Product;
import Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("addProductItems")
    public ResponseEntity<?> addGroceryItem(@RequestBody Product itemDto) {
        Product newItem = productService.addGroceryItem(itemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
    }

    @GetMapping("viewProductItems")
    public ResponseEntity<?> viewAvailableProductItems() {
        List<Product> availableItems = productService.getAllProductItems();
        return ResponseEntity.ok(availableItems);
    }

    @DeleteMapping("removeItems/{productId}")
    public ResponseEntity<?> removeGroceryItem(@PathVariable Long productId) {
        productService.removeGroceryItem(productId);
        return ResponseEntity.ok("Grocery item removed successfully");
    }

    @PutMapping("updateItems/{itemId}")
    public ResponseEntity<?> updateGroceryItem(@PathVariable Long itemId, @RequestBody Product itemDto) {
        Product updatedItem = productService.updateGroceryItem(itemId, itemDto);
        return ResponseEntity.ok(updatedItem);
    }
}
