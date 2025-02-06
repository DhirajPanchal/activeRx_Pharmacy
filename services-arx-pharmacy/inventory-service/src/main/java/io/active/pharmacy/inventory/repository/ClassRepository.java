package io.active.pharmacy.inventory.repository;

import io.active.pharmacy.base.entity.DrugCategory;
import io.active.pharmacy.base.entity.DrugClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<DrugClass, Long> {

    List<DrugClass> findAllByClassNameLike(String className);

    @Query(value = "select cls from DrugClass cls join cls.drugCategory cat where cat.id =?1 and cls.className Like ?2")
    List<DrugClass> findAllByCategoryAndClassName(Long categoryId, String className );

}


