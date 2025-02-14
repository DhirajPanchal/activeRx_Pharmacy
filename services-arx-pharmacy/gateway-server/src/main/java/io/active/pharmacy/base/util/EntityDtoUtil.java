package io.active.pharmacy.base.util;

import io.active.pharmacy.base.dto.UserDto;
import io.active.pharmacy.base.entity.Address;
import io.active.pharmacy.base.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EntityDtoUtil {


    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static User toUser(UserDto dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRole("ROLE_USER");
        return entity;
    }

    public static UserDto toUserDto(User entity, Address add) {

        UserDto dto = new UserDto();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setRole(entity.getRole());

        if (add != null && add.getId() != null) {
            dto.setAddress(add);
        }

        return dto;

    }

}
