package io.active.pharmacy.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class BaseDto {

    private Boolean isActive = true;

    private Boolean isDeleted = false;

    private Date createdOn;

    private Date updatedOn;

}
