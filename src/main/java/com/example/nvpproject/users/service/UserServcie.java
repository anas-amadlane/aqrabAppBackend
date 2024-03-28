package com.example.nvpproject.users.service;

import com.example.nvpproject.Seller.dto.SellerDto;
import com.example.nvpproject.users.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServcie {
    public UserDto createUser(UserDto userDto);

    UserDto login(String email, String password);

    List<SellerDto> getListSeller();
}
