package com.angularspringbootecommerce.backend.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularspringbootecommerce.backend.dtos.CartDto;
import com.angularspringbootecommerce.backend.dtos.OrderDto;
import com.angularspringbootecommerce.backend.dtos.PaymentDto;
import com.angularspringbootecommerce.backend.models.Order;
import com.angularspringbootecommerce.backend.services.CartService;
import com.angularspringbootecommerce.backend.services.OrderService;
import com.angularspringbootecommerce.backend.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import lombok.RequiredArgsConstructor;

//this rest controller handles all HTTP requests to the /api/v1/orders endpoint and sends back responses
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final PaymentService paymentService;

    @GetMapping("/{userId}")
    //The Authentication object is used to get information about the authenticated user.
    public List<OrderDto> getOrdersByUserId(@PathVariable Long userId, Authentication authentication) {
        return orderService.getOrdersByUserId(userId, authentication);
    }

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<PaymentDto> checkout(@PathVariable Long userId, Authentication authentication) throws StripeException {
        CartDto cart = cartService.getCartByUserId(userId);
        BigDecimal totalPrice = cart.getTotalPrice();
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(totalPrice);

        Order createdOrder = orderService.createOrderFromCart(cart, userId, authentication);

        cartService.clearCart(userId);

        PaymentDto paymentDto = new PaymentDto(paymentIntent.getClientSecret(), totalPrice, "usd", createdOrder.getId());

        return ResponseEntity.ok().body(paymentDto);
    }
}
