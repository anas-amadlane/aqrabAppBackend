package com.example.nvpproject.Seller.controller;
import com.example.nvpproject.Seller.model.Seller;
import com.example.nvpproject.Seller.services.SellerService;
import com.example.nvpproject.customer.model.Customer;
import com.example.nvpproject.users.dto.UserDto;
import com.example.nvpproject.users.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/aqrab/api")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class SellerController {

    @Autowired
    private SellerService sellerService;


    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService=sellerService;
    }

    @PostMapping("/saveSeller")
    public List<Seller> saveSeller() {
        sellerService.saveSeller(new Seller());
       return sellerService.list();
    }
    @RequestMapping(value = "/listCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> listCustomer(@RequestBody UserDto user) throws Exception{
        try {
            List<Customer> list=sellerService.getListCustomer(user);
            return list;
        }catch (Exception e){
            throw  new Exception("Message :"+e.getMessage());
        }
    }

}
