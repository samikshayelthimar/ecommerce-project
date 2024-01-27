package com.angularspringbootecommerce.backend.controllers;

import com.angularspringbootecommerce.backend.dtos.CartDto;
import com.angularspringbootecommerce.backend.exceptions.AppException;
import com.angularspringbootecommerce.backend.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//this is a Lombok annotation that creates a constructor for the class that initializes all final fields for the class
@RequiredArgsConstructor
@RestController
//this allows for cross-origin requests from the specified origin (in this case, the Angular app) 
//we need CORS because browsers allow requests only from the same origin as the API for security purposes
@CrossOrigin(origins = "http://localhost:4200")
//the base path for all endpoints in this controller is /api/v1/cart
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService cartService;
    //this is the mapping for HTTP GET requests to the /userId endpoint, where userId is a path variable
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getCartByUserId(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        //calls the cartService to get the cart for the user with the specified userId
        CartDto cartDto = cartService.getCartByUserId(userId);
        //if the cart exisits, add it to the response along with the number of items in the cart
        if (cartDto != null) {
            response.put("cart", cartDto);
            response.put("numberOfItemsInCart", cartService.getNumberOfItemsInCart(userId));
            //return the HTTP response with a 200 OK status code
            return ResponseEntity.ok().body(response);
        } else {
            throw new AppException("User's cart not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/{productId}/{quantity}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long userId, @PathVariable Long productId, @PathVariable int quantity) {
        CartDto cartDto = cartService.addItemToCart(userId, productId, quantity);
        return ResponseEntity.ok().body(cartDto);
    }

    //this is for HTTP Delete Requests with the path /userId/productId endpoint
    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        CartDto cartDto = cartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok().body(cartDto);
    }
}
