package com.example.nvpproject.customer.controller;
import com.example.nvpproject.Seller.model.Seller;
import com.example.nvpproject.Seller.services.SellerServiceImpl;
import com.example.nvpproject.customer.dto.CustomerDto;
import com.example.nvpproject.customer.model.Customer;
import com.example.nvpproject.customer.services.CustomerServiceImpl;
import com.example.nvpproject.transaction.dto.TransactionDTO;
import com.example.nvpproject.users.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/aqrab/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController {
    private CustomerServiceImpl customerService;

    private SellerServiceImpl sellerService;
    private UserServcie userServcie  ;

    @Autowired
    public CustomerController(UserServcie userServcie  ,SellerServiceImpl sellerService,CustomerServiceImpl customerService){
        this.userServcie=userServcie;
        this.sellerService=sellerService;
        this.customerService=customerService;
    }
    @RequestMapping(value = "/customer/updateCustomer", method = RequestMethod.GET)
    public CustomerDto updateCutomer(@RequestParam("amountTransaction") Integer amountTransaction, @RequestParam("idCustomer") Integer idCustomer) throws Exception{
        try {
            CustomerDto customerDto=customerService.updateCustomer(amountTransaction,idCustomer);
            return customerDto;
        }catch (Exception e){
            throw  new Exception("Message :"+e);
        }
    }
   @RequestMapping(value = "/save")
    public List<Customer> list(){
            customerService.saveCustomer();
            customerService.list();
            return customerService.list();
        }

    @RequestMapping(value = "/list")
    public List<Seller> listSeller(){
        customerService.saveCustomer();
        return sellerService.list();
    }

    @RequestMapping(value = "/customer/customers", method = RequestMethod.GET)
    public List<CustomerDto> findCustomersBySeller(@RequestParam("idUser") Integer idUser) {
        return customerService.getListCustomers(idUser);
    }
    @RequestMapping(value = "/customer/find-customer", method = RequestMethod.GET)
    public CustomerDto findCustomersByID(@RequestParam("idCustomer") Integer idCustomer) {
        return customerService.getCustomer(idCustomer);
    }

    @RequestMapping(value = "/customer/add-customer", method = RequestMethod.POST)
    public void addCustomer(@RequestBody CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
    }

    @RequestMapping(value = "/customer/add-balance", method = RequestMethod.POST)
    public void addBalance(@RequestBody CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
    }

    @RequestMapping(value = "/customer/pay-debt", method = RequestMethod.POST)
    public void payDebt(@RequestBody CustomerDto customerDto) {
        customerService.payDebt(customerDto);
    }

}
