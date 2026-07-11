package com.springboot.demo2.repositories;

import com.springboot.demo2.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends JpaRepository<StudentEntity,Integer> {



}
