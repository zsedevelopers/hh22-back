package com.zse.hh22.civicproject.endpoint;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zse.hh22.civicproject.api.CreateCivicProjectDTO;
import com.zse.hh22.civicproject.service.CreateCivicProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/civicproject")
@RequiredArgsConstructor
class CreateCivicProjectController {

    private final CreateCivicProjectService createCivicProjectService;

    @PostMapping
    public ResponseEntity<Void> createCivicProject(@Valid @RequestBody CreateCivicProjectDTO requestDTO) {
        createCivicProjectService.createProject(requestDTO);
        return ResponseEntity.ok().build();
    }
}
