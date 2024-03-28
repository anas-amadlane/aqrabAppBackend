package com.example.nvpproject.customer.doa;

import com.example.nvpproject.Seller.model.Seller;
import com.example.nvpproject.customer.dto.CustomerDto;
import com.example.nvpproject.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findCustomerByIdSeller(Integer idSeller);

    List<Customer> findCustomersBySeller(Seller seller);

    Customer findCustomerByIdBusiness(Integer idBusiness);
}
