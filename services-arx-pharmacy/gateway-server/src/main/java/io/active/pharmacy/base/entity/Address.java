package io.active.pharmacy.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "t_user_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Address {

    @Id
    private Long id;

    @Column("address_1")
    private String address1;

    @Column("address_2")
    private String address2;

    @Column("city")
    private String city;

    @Column("state")
    private String state;

    @Column("country")
    private String country;

    @Column("zip_code")
    private String zipCode;

    @Column("email")
    private String email;

    @Column("phone_number")
    private String phoneNumber;


}