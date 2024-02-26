package com.asn2.cmpt276.asn2.models;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findByName(String name);
    List<Student> findAll(Sort sort); // Copilot suggested. What it does is basically find All from database and then sorts by whatever property it is given

    // Must add these annotations in order to delete by name or else Entity Manager will throw an error
    @Transactional
    @Modifying
    @Query("delete from Student s where s.name = ?1")
    void deleteByName(String name); // Deletes the student by name from the database
}
