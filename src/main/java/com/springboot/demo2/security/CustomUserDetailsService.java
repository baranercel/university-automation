package com.springboot.demo2.security;

import com.springboot.demo2.entities.StudentEntity;
import com.springboot.demo2.entities.TeacherEntity;
import com.springboot.demo2.repositories.StudentRepository;
import com.springboot.demo2.repositories.TeachersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TeachersRepository teachersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<StudentEntity> student = studentRepository.findByEmailAddress(username);
        if (student.isPresent()){ //Username is an email address
            return student.get();
        }
        Optional<TeacherEntity> teacher = teachersRepository.findByEmailAddress(username);
        if (teacher.isPresent()){
            return teacher.get();
        }

        throw new UsernameNotFoundException("Email address not found");
    }

}
