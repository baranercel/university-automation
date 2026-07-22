package com.springboot.demo2.repositories;

import com.springboot.demo2.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeachersRepository  extends JpaRepository<TeacherEntity,Integer> {

    Optional<TeacherEntity> findByEmailAddress(String email);



}
