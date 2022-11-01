package com.flightapp.meal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.meal.entity.MealType;

public interface MealRepository extends JpaRepository<MealType, Integer> {

}
