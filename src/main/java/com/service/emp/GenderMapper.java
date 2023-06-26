package com.service.emp;

import com.dto.emp.GenderDTO;

import com.model.emp.Gender;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper {

    public GenderDTO toDTO(Gender gender) {
        if (gender == null) {
            return null;
        }

        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(gender.getId());
        genderDTO.setName(gender.getName());
        return genderDTO;
    }

    public Gender toEntity(GenderDTO genderDTO) {
        if (genderDTO == null) {
            return null;
        }

        Gender gender = new Gender();
        gender.setId(genderDTO.getId());
        gender.setName(genderDTO.getName());
        return gender;
    }
}