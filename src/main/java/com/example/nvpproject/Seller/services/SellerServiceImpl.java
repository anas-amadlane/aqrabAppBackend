package com.example.nvpproject.Seller.services;
import com.example.nvpproject.Seller.doa.SellerRepository;
import com.example.nvpproject.Seller.model.Seller;
import com.example.nvpproject.customer.doa.CustomerRepository;
import com.example.nvpproject.customer.model.Customer;
import com.example.nvpproject.users.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private SellerRepository sellerRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public SellerServiceImpl( SellerRepository sellerRepository,CustomerRepository customerRepository){
        this.sellerRepository=sellerRepository;
        this.customerRepository=customerRepository;
    }

    @Override
    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public List<Seller> list(){
        return sellerRepository.findAll();
    }

    @Override
    public List<Customer> getListCustomer(UserDto user) {
        return  customerRepository.findCustomerByIdSeller(user.getIdUser());
    }

    public Optional<Seller> GetAllCustomer(Integer id){
        return sellerRepository.findById(id)  ;
    }
}
