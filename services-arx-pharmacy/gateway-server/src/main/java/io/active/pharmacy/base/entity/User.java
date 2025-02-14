package io.active.pharmacy.base.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    private Long id;

    @Column("email")
    private String email;

    @Column("role")
    private String role;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("phone_number")
    private String phoneNumber;

    @Column("address_id")
    private Long addressId;

    @Column("password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
