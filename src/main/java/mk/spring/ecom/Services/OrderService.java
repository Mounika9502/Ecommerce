package mk.spring.ecom.Services;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import mk.spring.ecom.Order;
import mk.spring.ecom.Repositories.OrderRepository;



@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + id));
    }

    public Order createOrder(Order order) {
        // Perform additional logic/validation if needed
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + id));
        existingOrder.setOrderNumber(order.getOrderNumber());
        existingOrder.setTotalAmount(order.getTotalAmount());

        // Update order attributes...

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + id));

        orderRepository.delete(order);
    }
}

