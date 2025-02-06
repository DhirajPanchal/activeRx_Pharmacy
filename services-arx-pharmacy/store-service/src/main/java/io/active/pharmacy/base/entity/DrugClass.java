package io.active.pharmacy.base.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_drug_class")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class DrugClass extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name")
    private String className;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private DrugCategory drugCategory;

}
