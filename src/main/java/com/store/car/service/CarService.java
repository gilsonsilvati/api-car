package com.store.car.service;

import com.store.car.dto.CarDTO;

import java.util.List;

public interface CarService {

    void newPostDetails(CarDTO carDTO);

    List<CarDTO> getCarSales();

    void changeCarSale(CarDTO carDTO, Long postId);

    void removeCarSale(Long postId);
}
