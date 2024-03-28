package com.example.nvpproject.Seller.model;

import com.example.nvpproject.Seller.dto.SellerDto;
import com.example.nvpproject.users.dto.UserDto;
import com.example.nvpproject.users.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellerMapper {
    public SellerDto sellerToUsellerDto(Seller seller) {
        if(seller==null){
            return null;}
        SellerDto sellerDto =new SellerDto();
        sellerDto.setFirstName(seller.getFirstName());
        sellerDto.setLastName(seller.getLastName());
        sellerDto.setEmail(seller.getEmail());
        sellerDto.setPhone(seller.getPhone());
        return sellerDto;
    }

    public List<SellerDto> sellerToUsellerDto(List<Seller> sellers){
        List<SellerDto> sellerDtoList= new ArrayList<>();
        sellers.stream().map(seller -> sellerDtoList.add(sellerToUsellerDto(seller))).collect(Collectors.toList());
        return sellerDtoList;
    }
}
