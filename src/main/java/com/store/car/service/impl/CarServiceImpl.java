package com.store.car.service.impl;

import com.store.car.dto.CarDTO;
import com.store.car.entity.CarEntity;
import com.store.car.repository.CarRepository;
import com.store.car.repository.OwnerRepository;
import com.store.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    protected CarRepository carRepository;

    @Autowired
    protected OwnerRepository ownerRepository;

    @Override
    public void newPostDetails(CarDTO carDTO) {
        CarEntity carEntity = mapCarDTOToEntity(carDTO);

        carRepository.save(carEntity);
    }

    @Override
    public List<CarDTO> getCarSales() {
        List<CarDTO> listCarsSales = new ArrayList<>();

        carRepository.findAll().forEach(entity -> listCarsSales.add(mapCarEntityToDTO(entity)));

        return listCarsSales;
    }

    @Override
    public void changeCarSale(CarDTO carDTO, Long postId) {
        carRepository.findById(postId).ifPresentOrElse(entity -> {
            entity.setDescription(carDTO.getDescription());
            entity.setContact(carDTO.getContact());
            entity.setPrice(carDTO.getPrice());
            entity.setBrand(carDTO.getBrand());
            entity.setEngineVersion(carDTO.getEngineVersion());
            entity.setModel(carDTO.getModel());

            carRepository.save(entity);
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    public void removeCarSale(Long postId) {
        carRepository.deleteById(postId);
    }

    private CarEntity mapCarDTOToEntity(CarDTO carDTO) {
        CarEntity carEntity = new CarEntity();

        ownerRepository.findById(carDTO.getOwnerId()).ifPresentOrElse(ownerEntity -> {
            carEntity.setOwner(ownerEntity);
            carEntity.setContact(ownerEntity.getContactNumber());
        }, ()-> {
            throw new RuntimeException();
        });

        carEntity.setModel(carDTO.getModel());
        carEntity.setBrand(carDTO.getBrand());
        carEntity.setPrice(carDTO.getPrice());
        carEntity.setCity(carDTO.getCity());
        carEntity.setDescription(carDTO.getDescription());
        carEntity.setEngineVersion(carDTO.getEngineVersion());
        carEntity.setCreatedDate(String.valueOf(new Date()));

        return carEntity;
    }


    private CarDTO mapCarEntityToDTO(CarEntity entity) {

        return CarDTO.builder()
                .brand(entity.getBrand())
                .city(entity.getCity())
                .model(entity.getModel())
                .description(entity.getDescription())
                .engineVersion(entity.getEngineVersion())
                .createdDate(entity.getCreatedDate())
                .ownerName(entity.getOwnerName())
                .price(entity.getPrice())
                .build();
    }
}
