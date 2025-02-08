package io.active.pharmacy.inventory.service;

import io.active.pharmacy.base.dto.ListItem;
import io.active.pharmacy.base.entity.DrugCategory;
import io.active.pharmacy.base.entity.DrugClass;
import io.active.pharmacy.base.exception.EntityNotFoundException;
import io.active.pharmacy.inventory.repository.ClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClassService {

    private final ClassRepository repository;

    @Autowired
    private CategoryService categoryService;

    public ClassService(ClassRepository repository) {
        this.repository = repository;
    }

    public DrugClass getDrugClass(Long id) {

        log.info("ClassService.getDrugClass() : {}", id);

        DrugClass drugClass = this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DrugClass", id));

        return drugClass;

    }

    public DrugClass addDrugClass(Long categoryId, DrugClass drugClass) {
        log.info("ClassService.addDrugClass() : {}, {}", categoryId, drugClass);

        DrugCategory drugCategory = categoryService.getDrugCategory(categoryId);

        drugClass.setDrugCategory(drugCategory);

        return repository.save(drugClass);
    }


    public List<ListItem> listClasses(Long categoryId, String className) {
        log.info("ClassService.listClasses() : {}, {}", categoryId, className);

        List<DrugClass> list = new ArrayList<>();

        if (categoryId == 0) {
            list = this.repository.findAllByClassNameLike(("%" + className + "%"));
        } else {
            list = this.repository.findAllByCategoryAndClassName(categoryId, ("%" + className + "%"));
        }

        return list
                .stream()
                .map(entity ->
                        new ListItem(entity.getId(), entity.getClassName())
                )
                .collect(Collectors.toList());

    }
}
