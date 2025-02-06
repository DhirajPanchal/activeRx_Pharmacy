package io.active.pharmacy.inventory.repository;

import io.active.pharmacy.base.entity.Drug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

    List<Drug> findAllByDrugLabelNameLike(String drugLabelName);

    @Query(value = "select drug from Drug drug join drug.drugCategory cat where cat.id =?1 and drug.drugLabelName Like ?2")
    List<Drug> findAllByCategory(Long categoryId, String drugLabelName);

    @Query(value = "select drug from Drug drug join drug.drugClass cls where cls.id =?1 and drug.drugLabelName Like ?2")
    List<Drug> findAllByClass(Long classId, String drugLabelName);

    Page<Drug> findAllByDrugLabelNameLike(String drugLabelName, Pageable pageable);

    @Query(value = "select drug from Drug drug join drug.drugCategory cat where cat.id =?1 and drug.drugLabelName Like ?2")
    Page<Drug> findAllByCategory(Long categoryId, String drugLabelName, Pageable pageable);

    @Query(value = "select drug from Drug drug join drug.drugClass cls where cls.id =?1 and drug.drugLabelName Like ?2")
    Page<Drug> findAllByClass(Long classId, String drugLabelName, Pageable pageable);

}
