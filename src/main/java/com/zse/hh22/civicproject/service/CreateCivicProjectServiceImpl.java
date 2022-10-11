package com.zse.hh22.civicproject.service;

import com.zse.hh22.civicproject.api.CreateCivicProjectDTO;
import com.zse.hh22.civicproject.api.CreateEstimateDTO;
import com.zse.hh22.civicproject.api.CreateScheduleOfActivityDTO;
import com.zse.hh22.civicproject.domain.CivicProjectEntity;
import com.zse.hh22.civicproject.domain.EstimateEntity;
import com.zse.hh22.civicproject.domain.ScheduleOfActivityEntity;
import com.zse.hh22.civicproject.exception.CivicProjectWithGivenTitleAlreadyExistsException;
import com.zse.hh22.civicproject.repository.CivicProjectRepository;
import com.zse.hh22.user.domain.UserEntity;
import com.zse.hh22.user.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class CreateCivicProjectServiceImpl implements CreateCivicProjectService {

    private final CivicProjectRepository civicProjectRepository;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public void createProject(@Valid CreateCivicProjectDTO requestDTO) {
        if(civicProjectRepository.findByTitle(requestDTO.title()).isPresent()){
            throw new CivicProjectWithGivenTitleAlreadyExistsException();
        }
        civicProjectRepository.save(new CivicProjectEntity(requestDTO, getUserEntities(requestDTO), getEstimateEntity(requestDTO.estimate()), getScheduleOfActivityEntities(requestDTO.schedulesOfActivities())));
    }

    private List<UserEntity> getUserEntities(CreateCivicProjectDTO requestDTO) {
        List<UserEntity> authors = new ArrayList<>();
        requestDTO.authors().forEach(dto -> {
            authors.add((UserEntity) userDetailsService.loadUserByUsername(dto));
        });

        return authors;
    }

    private static List<ScheduleOfActivityEntity> getScheduleOfActivityEntities(List<CreateScheduleOfActivityDTO> dtos) {
        List<ScheduleOfActivityEntity> scheduleOfActivities = new ArrayList<>();
        dtos.forEach(dto -> {
            scheduleOfActivities.add(new ScheduleOfActivityEntity(dto));
        });

        return scheduleOfActivities;
    }

    private static EstimateEntity getEstimateEntity(CreateEstimateDTO dto) {
        return new EstimateEntity(dto);
    }
}