package io.active.pharmacy.base.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_drug_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DrugCategory extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

}
