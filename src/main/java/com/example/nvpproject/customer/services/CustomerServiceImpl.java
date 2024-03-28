package com.example.nvpproject.customer.services;

import com.example.nvpproject.Seller.doa.SellerRepository;
import com.example.nvpproject.Seller.model.Seller;
import com.example.nvpproject.customer.doa.CustomerRepository;
import com.example.nvpproject.customer.dto.CustomerDto;
import com.example.nvpproject.customer.mappper.CustomerMapper;
import com.example.nvpproject.customer.model.Customer;
import com.example.nvpproject.transaction.dto.TransactionDTO;
import com.example.nvpproject.transaction.model.Transaction;
import com.example.nvpproject.transaction.dao.TransactionRepository;
import com.example.nvpproject.users.dao.UserRepository;
import com.example.nvpproject.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private UserRepository userRepository;
    private SellerRepository sellerRepository;
    private CustomerRepository customerRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,TransactionRepository transactionRepository,
                                SellerRepository sellerRepository,
                               UserRepository userRepository) {
        this.customerRepository=customerRepository;
        this.transactionRepository=transactionRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
    }
    public void saveCustomer() {
    }

    public List<Customer> list(){
        return null;
    }

    public CustomerDto updateCustomer(Integer amountTransaction, Integer idCustomer) {

        Customer customer = customerRepository.getOne(idCustomer);
        if(customer.getBalance()>=amountTransaction){
        customer.setBalance(customer.getBalance()-amountTransaction);
        if(customer.getDebtAmount()!=null){
            customer.setDebtAmount(customer.getDebtAmount()+amountTransaction);
        }else {
            customer.setDebtAmount(Double.valueOf(amountTransaction));
        }
        customer.setDateLastTransaction(new Date());
        customerRepository.save(customer);
        Transaction transaction= new Transaction();
            transaction.setIdSeller(customer.getIdSeller());
            transaction.setIdCustomer(customer.getIdCustomer());
            transaction.setDate(new Date());
            transaction.setAmount(Double.valueOf(amountTransaction));
            transactionRepository.save(transaction);
        }
        CustomerDto customerDto= new CustomerMapper().customerToCustomerDto(customer);
        return customerDto;
    }
    @Override
    @Transactional
    public List<CustomerDto> getListCustomers(Integer idUser){
        User user = userRepository.getOne(idUser);
        Seller seller = sellerRepository.getOne(user.getIdSeler());
        List<Customer> myCustomers = customerRepository.findCustomersBySeller(seller);

        List<CustomerDto> customerDtos = myCustomers.stream().map((Customer customer) -> mapCustomerToCustomerDTO(customer)).collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    @Transactional
    public CustomerDto getCustomer(Integer idCustomer) {
        Customer customer = customerRepository.findCustomerByIdBusiness(idCustomer);
        if(customer!= null){
            return mapCustomerToCustomerDTO(customer);
        }
        else {
            return null;
        }

    }
    @Override
    @Transactional
    public void addCustomer(CustomerDto customerDto) {

        Customer customer = customerRepository.findCustomerByIdBusiness(customerDto.getIdBusiness());
        customer.setIdSeller(customerDto.getIdSeller());
        customer.setBalance(customerDto.getBalance());
        customerRepository.save(customer);

    }

    @Override
    @Transactional
    public void payDebt(CustomerDto customerDto) {

        Customer customer = customerRepository.findCustomerByIdBusiness(customerDto.getIdBusiness());
        customer.setDebtAmountTotal(customerDto.getDebtAmountTotal());
        customer.setDebtAmount(0.0);
        customer.setBalance(customerDto.getBalance());
        customer.setDateLastPayement(new Date());
        customerRepository.save(customer);

    }




    private CustomerDto mapCustomerToCustomerDTO(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setIdBusiness(customer.getIdBusiness());
        customerDto.setIdCustomer(customer.getIdCustomer());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setBalance(customer.getBalance());
        customerDto.setDateLastPayement(customer.getDateLastPayement());
        customerDto.setDebtAmount(customer.getDebtAmount());
        customerDto.setDateLastTransaction(customer.getDateLastTransaction());
        customerDto.setIdSeller(customer.getIdSeller());
        return customerDto;
    }

}
