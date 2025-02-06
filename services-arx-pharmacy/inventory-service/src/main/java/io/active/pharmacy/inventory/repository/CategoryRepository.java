package io.active.pharmacy.inventory.repository;

import io.active.pharmacy.base.entity.DrugCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<DrugCategory, Long> {

    List<DrugCategory> findAllByCategoryNameLike(String categoryName);

}
