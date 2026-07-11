package com.springboot.demo2.repositories;

import com.springboot.demo2.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository  extends JpaRepository<TeacherEntity,Integer> {



}
