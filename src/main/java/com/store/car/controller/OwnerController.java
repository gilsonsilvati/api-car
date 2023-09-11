package com.store.car.controller;

import com.store.car.dto.OwnerDTO;
import com.store.car.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class OwnerController {

    @Autowired
    protected OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Void> createOwner(@RequestBody OwnerDTO ownerDTO) {
        ownerService.createOwnerPost(ownerDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
