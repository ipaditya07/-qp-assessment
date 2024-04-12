package Service;

import Entity.Order;
import Entity.Product;
import Repo.OrderRepository;
import Repo.ProductRepository;
import com.amazonaws.services.redshift.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import Exception.OutOfStockException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    public Product addGroceryItem(Product item) {
         return productRepository.save(item);
    }

    public List<Product> getAllProductItems() {
        return productRepository.findAll();
    }

    public void removeGroceryItem(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("Product item not found with id: " + productId);
        }
    }

    public Product updateGroceryItem(Long itemId, Product item) {
        Optional<Product> optionalItem = productRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Product productToUpdate = optionalItem.get();
            productToUpdate.setProductName(item.getProductName());
            productToUpdate.setPrice(item.getPrice());
            return productRepository.save(productToUpdate);
        } else {
            throw new ResourceNotFoundException("Unable to update! Product item not found with id: " + itemId);
        }
    }

    public Order bookItems(List<Long> itemIds) {
        List<Product> items = productRepository.findAllById(itemIds);
        items.forEach(item -> {
            int inventory = item.getInventory();
            if (inventory <= 0) {
                throw new OutOfStockException("Item " + item.getProductName() + " is out of stock.");
            }
            item.setInventory(inventory - 1);
        });
        productRepository.saveAll(items);

        // Creating an order for the booked items
        Order order = new Order();
        order.setProducts(items);
        orderService.saveOrders(order);

        return order;
    }

    public List<Product> getAvailableGroceryItems() {
        return productRepository.findByInventoryGreaterThan(0);
    }
}

