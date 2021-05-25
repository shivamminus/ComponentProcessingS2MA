package com.componentxProcessing.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.componentxProcessing.main.model.ProcessResponse;

@Repository
public interface ProcessResponseRepo extends JpaRepository<ProcessResponse, String> {


}