package com.major.global;


import com.major.model.Cart;

public class GlobalDataCart {
    public static Cart cart;
    static {
        cart= new Cart();
    }
}
