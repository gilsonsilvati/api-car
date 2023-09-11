package com.store.car.service.impl;

import com.store.car.dto.OwnerDTO;
import com.store.car.entity.OwnerEntity;
import com.store.car.repository.OwnerRepository;
import com.store.car.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    protected OwnerRepository ownerRepository;

    @Override
    public void createOwnerPost(OwnerDTO ownerDTO) {
        OwnerEntity ownerEntity = getOwnerEntity(ownerDTO);

        ownerRepository.save(ownerEntity);
    }

    private static OwnerEntity getOwnerEntity(OwnerDTO ownerDTO) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setName(ownerDTO.getName());
        ownerEntity.setType(ownerDTO.getType());
        ownerEntity.setContactNumber(ownerDTO.getContactNumber());

        return ownerEntity;
    }
}
