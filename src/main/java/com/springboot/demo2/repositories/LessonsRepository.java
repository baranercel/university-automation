package com.springboot.demo2.repositories;

import com.springboot.demo2.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonsRepository extends JpaRepository<LessonEntity,Integer> {



}
