package com.componentxProcessing.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.componentxProcessing.main.model.ProcessRequest;



@Repository
public interface ProcessRequestRepo extends JpaRepository<ProcessRequest, String> {


}
