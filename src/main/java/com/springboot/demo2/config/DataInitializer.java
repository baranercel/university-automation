package com.springboot.demo2.config;

import com.springboot.demo2.entities.TeacherEntity;
import com.springboot.demo2.repositories.TeachersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(TeachersRepository teachersRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Eğer veritabanında bu maile sahip bir admin öğretmen yoksa, yeni oluştur.
            if (teachersRepository.findByEmailAddress("admin@teacher.com").isEmpty()) {
                TeacherEntity adminTeacher = new TeacherEntity();
                adminTeacher.setName("Admin");
                adminTeacher.setSurname("Teacher");
                adminTeacher.setEmailAddress("admin@teacher.com");
                adminTeacher.setPassword(passwordEncoder.encode("123456")); // Şifreyi güvenli hashliyoruz

                teachersRepository.save(adminTeacher);
            }
        };
    }
}