package com.shalimov.onlineshop.security;

import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private String  token;
    private User user;
    private List<Product> cart;
    private LocalDateTime expireTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
