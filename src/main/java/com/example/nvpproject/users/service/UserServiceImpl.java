package com.example.nvpproject.users.service;
import com.example.nvpproject.Seller.doa.SellerRepository;
import com.example.nvpproject.Seller.dto.SellerDto;
import com.example.nvpproject.Seller.model.Seller;
import com.example.nvpproject.Seller.model.SellerMapper;
import com.example.nvpproject.customer.doa.CustomerRepository;
import com.example.nvpproject.customer.model.Customer;
import com.example.nvpproject.transaction.dao.TransactionRepository;
import com.example.nvpproject.transaction.model.Transaction;
import com.example.nvpproject.users.dao.UserRepository;
import com.example.nvpproject.users.dto.UserDto;
import com.example.nvpproject.users.dto.UserDtoMapperImpl;
import com.example.nvpproject.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class UserServiceImpl implements UserServcie {

    private UserRepository userRepository;
    private SellerRepository sellerRepository;
    private CustomerRepository customerRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,SellerRepository sellerRepository,CustomerRepository customerRepository,TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.sellerRepository=sellerRepository;
        this.customerRepository=customerRepository;
        this.transactionRepository=transactionRepository;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User userExist = userRepository.findByEmail(userDto.getEmail());
        if(userExist==null){
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPhone(userDto.getPhone());
            user.setTypeUser(userDto.getTypeUser());
            user.setPassword(userDto.getPassword());
            Integer idTypeUser=  saveSellerOrCustomer(userDto);
            if(userDto.getTypeUser().equals("seller")){
                user.setIdSeler(idTypeUser);
            }else {
                user.setIdCustomer(idTypeUser);
            }
            userRepository.save(user);
            UserDto userDtoRes= new UserDtoMapperImpl().userToUserDto(user);
            return userDtoRes;
        }else {
            return null;
        }

    }

    @Transactional
    public Integer saveSellerOrCustomer(UserDto userDto){
        if(userDto.getTypeUser().equals("seller")){
            Seller seller= new Seller();
            seller.setFirstName(userDto.getFirstName());
            seller.setLastName(userDto.getLastName());
            seller.setEmail(userDto.getEmail());
            seller.setPhone(userDto.getPhone());
            sellerRepository.save(seller);
            return seller.getIdSeller();
        }else {
            Customer customer= new Customer();
            customer.setFirstName(userDto.getFirstName());
            customer.setLastName(userDto.getLastName());
            customer.setEmail(userDto.getEmail());
            customer.setPhone(userDto.getPhone());
            customer.setMonthlyIncome(userDto.getMonthlyIncome());
            customer.setIdBusiness(generateUserId());
            customerRepository.save(customer);
            return customer.getIdCustomer();
        }
    }
    private Integer generateUserId() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }
    @Override @Transactional
    public UserDto login(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        UserDto userDto= new UserDto();
        if(user!=null ){
            userDto.setIdUser(user.getIdUser());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setPhone(user.getPhone());
            userDto.setTypeUser(user.getTypeUser());
            if (user.getIdCustomer() != null){
                Customer customer = customerRepository.getOne(user.getIdCustomer());
                if(customer.getBalance()!=null) {
                    userDto.setBalance(customer.getBalance());
                }
                userDto.setIdCustomer(customer.getIdCustomer());
                userDto.setIdBusiness(customer.getIdBusiness());
            }
            userDto.setIdSeler(user.getIdSeler());
            return userDto;
        }else {
        return null;
        }
    }

    @Override
    public List<SellerDto> getListSeller() {
        List<Seller> sellers= sellerRepository.findAll();
        List<SellerDto> sellersDtoList = new ArrayList<>();
        for (Seller seller : sellers) {
            SellerDto sellerDto = new SellerMapper().sellerToUsellerDto(seller);
            List<Customer> listCustomer=customerRepository.findCustomerByIdSeller(seller.getIdSeller());
            sellerDto.setNumberOfCustomers(listCustomer.size());

           // total number of transactions for all customers of the seller
            int totalTransactions=0;
            double debtTotalAmountPayed=0;
            double debtTotalAmountNotPayed=0;
            for (Customer customer : listCustomer) {
                List<Transaction> transactionList=transactionRepository.findTransactionByIdCustomer(customer.getIdCustomer());
                 totalTransactions=totalTransactions +transactionList.size();

                 //get debt amount for every customer and add it here the total debt already payed
                if(customer.getDebtAmountTotal()!=null){
                    debtTotalAmountPayed =debtTotalAmountPayed+customer.getDebtAmountTotal();
                }

                //get debt amount for every customer and add it here the total debt not payed  yet
                if(customer.getDebtAmount()!=null){
                    debtTotalAmountNotPayed =debtTotalAmountNotPayed+customer.getDebtAmount();
                }

            }
            sellerDto.setDebtTotalAmountPayed(debtTotalAmountPayed);
            sellerDto.setDebtTotalAmount(debtTotalAmountNotPayed);

            sellerDto.setTotalTransactions(totalTransactions);

            sellersDtoList.add(sellerDto);
        }
        //List<SellerDto> sellersDtoList= new SellerMapper().sellerToUsellerDto(sellers);
        return sellersDtoList;
    }

    @Transactional
    public UserDto findUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return null;
    }


/*    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
              //  .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), // Use email for authentication
                user.getPassword(),
                new ArrayList<>()
        );
    }*/
}