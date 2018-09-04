package com.shalimov.onlineshop.web.controller;

import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.service.ProductService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAll(Model model, HttpSession session) {
        List<Product> list = productService.getAll();
        model.addAttribute("products", list);
        if (session.getAttribute("loggedUser") != null) {
            model.addAttribute("login", session.getAttribute("loggedUser"));
        }
        return "products";
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.POST)
    public ResponseEntity addProduct(@RequestBody String json, HttpSession session) {
        if (session.getAttribute("loggedUser") != null) {
            productService.add(getProductFromJson(json));
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private Product getProductFromJson(String json) {
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(json);
            JSONObject jsonObject = (JSONObject) object;
            Product product = new Product();
            product.setTitle(String.valueOf(jsonObject.get("title")));
            product.setDescription(String.valueOf(jsonObject.get("description")));
            product.setPrice(Integer.parseInt(String.valueOf(jsonObject.get("price"))));
            product.setImage(String.valueOf(jsonObject.get("image")));
            return product;
        } catch (ParseException e) {
            throw new RuntimeException("Error occurred while converting json to product", e);
        }

    }
}
