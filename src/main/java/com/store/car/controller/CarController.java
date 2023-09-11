package com.store.car.controller;

import com.store.car.dto.CarDTO;
import com.store.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class CarController {

    @Autowired
    protected CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getCarSales() {

        return ResponseEntity.status(HttpStatus.FOUND).body(carService.getCarSales());
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<Void> changeCarSale(@RequestBody CarDTO carDTO, @PathVariable String id) {
        carService.changeCarSale(carDTO, Long.parseLong(id));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Void> deleteCarSale(@PathVariable String id) {
        carService.removeCarSale(Long.parseLong(id));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
