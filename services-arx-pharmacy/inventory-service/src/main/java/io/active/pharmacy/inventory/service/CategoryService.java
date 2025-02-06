package io.active.pharmacy.inventory.service;

import io.active.pharmacy.base.dto.ListItem;
import io.active.pharmacy.base.entity.DrugCategory;
import io.active.pharmacy.base.exception.EntityNotFoundException;
import io.active.pharmacy.inventory.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public DrugCategory getDrugCategory(Long id) {
        log.info("[INVENTORY] CategoryService.getDrugCategory() : {}", id);
        DrugCategory drugCategory = this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DrugCategory", id));
        return drugCategory;
    }

    public DrugCategory addDrugCategory(DrugCategory drugCategory) {
        return this.repository.save(drugCategory);
    }

    public List<ListItem> listCategories(String categoryName) {
        //System.out.println(" SRV listCategories ");
        log.info("[INVENTORY] CategoryService.listCategories() : {}", categoryName);
        return this.repository
                .findAllByCategoryNameLike(("%" + categoryName + "%"))
                .stream()
                .map(entity -> new ListItem(entity.getId(), entity.getCategoryName()))
                .collect(Collectors.toList());
    }


}
