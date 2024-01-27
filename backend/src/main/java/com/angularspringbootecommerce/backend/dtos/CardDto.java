package com.angularspringbootecommerce.backend.dtos;

import lombok.Data;

/*
 * a DTO is a special object that carries information from one subsystem (our Angular application) to another (the backend). 
 * Its main and original purpose was to reduce the amount of data sent over a network. 
 * 
 * by using lombok here, we reduce boilerplate code with annotations and make code easier to read and less error-prone.
 */
@Data
public class CardDto {
    private String number;
    private String expDate;
    private String cvc;
    private String zip;
}
