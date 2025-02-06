package io.active.pharmacy.base.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_drug")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Drug extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drug_label_name")
    private String drugLabelName;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private DrugCategory drugCategory;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private DrugClass drugClass;

}
