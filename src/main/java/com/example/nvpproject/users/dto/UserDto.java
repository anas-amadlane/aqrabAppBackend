package com.example.nvpproject.users.dto;
import com.example.nvpproject.users.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer idUser;
    private String lastName;
    private String firstName;
    private String phone;
    private String email;
    private String password;
    private String typeUser;
    private Double monthlyIncome;
    private Double balance;
    private Integer idCustomer;
    private  Integer idBusiness;
    private Integer idSeler;
    public static Function <User, UserDto> getFromUtilisateur() {
        return fromUtilisateur;
    }

    public static Function <User, UserDto> fromUtilisateur = u -> {
        UserDto userDto = new UserDto();
        userDto.setIdUser(u.getIdUser());
        userDto.setLastName(u.getLastName());
        userDto.setFirstName(u.getFirstName());
        userDto.setPhone(userDto.getPhone());
        userDto.setEmail(userDto.getEmail());
        userDto.setPassword(userDto.getPassword());
        userDto.setTypeUser(userDto.getTypeUser());
        userDto.setMonthlyIncome(userDto.getMonthlyIncome());
        return userDto;
    };
}
