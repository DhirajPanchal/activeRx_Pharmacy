package io.active.pharmacy.inventory.service;

import io.active.pharmacy.base.dto.DrugDto;
import io.active.pharmacy.base.dto.ListResponse;
import io.active.pharmacy.base.entity.Drug;
import io.active.pharmacy.base.entity.DrugCategory;
import io.active.pharmacy.base.entity.DrugClass;
import io.active.pharmacy.base.exception.EntityNotFoundException;
import io.active.pharmacy.base.util.EntityDtoUtil;
import io.active.pharmacy.inventory.repository.DrugRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DrugService {

    private final DrugRepository drugRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ClassService classService;

    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public DrugDto getDrug(Long id) {

        log.info("[INVENTORY] DrugService.getDrug() : {}", id);

        Drug drug = this.drugRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Drug", id));
        DrugDto drugDto = EntityDtoUtil.toDrugDto(drug);
        return drugDto;
    }

    public DrugDto addDrug(Long categoryId, Long classId, Drug drug) {
        //System.out.println("__addDrug");
        //System.out.println(drug);

        log.info("[INVENTORY] DrugService.addDrug() : {}, {} ", categoryId, classId);

        DrugClass drugClass = this.classService.getDrugClass(classId);
        DrugCategory drugCategory = drugClass.getDrugCategory();
        drug.setDrugCategory(drugCategory);
        drug.setDrugClass(drugClass);
        drug = drugRepository.save(drug);
        DrugDto drugDto = EntityDtoUtil.toDrugDto(drug);

        return drugDto;
    }

    public ListResponse<DrugDto> listDrugs1(Long categoryId,
                                            Long classId,
                                            String drugLabelName) {
        //System.out.println("SRV listDrugs");

        log.info("[INVENTORY] DrugService.listDrugs1() : {}, {}, {} ", categoryId, classId, drugLabelName);
        List<Drug> list = new ArrayList<>();
        String search = ("%" + drugLabelName + "%");
        if (categoryId > 0 && classId == 0) {
            //System.out.println("by CAT");
            list = this.drugRepository.findAllByCategory(categoryId, search);
        } else if (classId > 0) {
            //System.out.println("by CLS");
            list = this.drugRepository.findAllByClass(classId, search);
        } else {
            //System.out.println("by Name");
            list = this.drugRepository.findAllByDrugLabelNameLike(search);
        }

        List<DrugDto> dtoList = list
                .stream()
                .map(entity -> EntityDtoUtil.toDrugDto(entity))
                .collect(Collectors.toList());

        ListResponse<DrugDto> response = new ListResponse<>(dtoList, dtoList.size());

        return response;

    }


    public ListResponse<DrugDto> listDrugs(Long categoryId,
                                           Long classId,
                                           String drugLabelName,
                                           int index,
                                           int size) {
        //System.out.println("SRV listDrugs 2");

        log.info("[INVENTORY] DrugService.listDrugs() : {}, {}, {}, {}, {} ", categoryId, classId, drugLabelName, index, size);

        Pageable pageable = PageRequest.of(index, size);
        Page<Drug> page = null;

        String search = ("%" + drugLabelName + "%");
        if (categoryId > 0 && classId == 0) {
            //System.out.println("by CAT");
            page = this.drugRepository.findAllByCategory(categoryId, search, pageable);
        } else if (classId > 0) {
            //System.out.println("by CLS");
            page = this.drugRepository.findAllByClass(classId, search, pageable);
        } else {
            //System.out.println("by Name");
            page = this.drugRepository.findAllByDrugLabelNameLike(search, pageable);
        }

        ListResponse<DrugDto> response = new ListResponse<>();

        if (page != null && page.hasContent()) {
            List<Drug> list = page.getContent();
            List<DrugDto> dtoList = list
                    .stream()
                    .map(entity -> EntityDtoUtil.toDrugDto(entity))
                    .collect(Collectors.toList());

            response = new ListResponse<>(dtoList, page.getTotalElements());
        }

        return response;

    }

}
