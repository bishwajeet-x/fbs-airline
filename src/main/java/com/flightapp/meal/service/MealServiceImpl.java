package com.flightapp.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.meal.entity.MealType;
import com.flightapp.meal.repo.MealRepository;

@Service
public class MealServiceImpl implements MealService {
	
	@Autowired private MealRepository repo;

	@Override
	public MealType addMealType(MealType meal) {
		System.out.println("Adding meal "+ meal.toString());
		return repo.save(meal);
	}

	

}
