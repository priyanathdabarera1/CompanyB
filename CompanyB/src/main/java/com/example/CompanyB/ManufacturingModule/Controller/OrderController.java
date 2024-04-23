package com.example.CompanyB.ManufacturingModule.Controller;

import com.example.CompanyB.ManufacturingModule.Model.OnGoingOrder;
import com.example.CompanyB.ManufacturingModule.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> checkQuantity(@PathVariable String orderId) {
        try {
            OnGoingOrder onGoingOrder = orderService.AssignOrderDetails(orderId);
            return ResponseEntity.ok().body(onGoingOrder.getTotalNumber() +" No of items have in the order no of "+ onGoingOrder.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with ID: " + orderId);
        }
    }
}
