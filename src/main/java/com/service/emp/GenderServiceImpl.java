package com.service.emp;


import com.dto.emp.GenderDTO;
import com.service.emp.GenderMapper;
import com.model.emp.Gender;
import com.repository.emp.GenderRepository;
import com.service.emp.GenderService;


import com.model.emp.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;
    private final GenderMapper genderMapper;

    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository, GenderMapper genderMapper) {
        this.genderRepository = genderRepository;
        this.genderMapper = genderMapper;
    }

    @Override
    public GenderDTO getGenderById(Long id) {
        Gender gender = genderRepository.findById(id).orElse(null);
        return genderMapper.toDTO(gender);
    }
}