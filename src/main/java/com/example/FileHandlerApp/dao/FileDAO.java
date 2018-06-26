package com.example.FileHandlerApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FileHandlerApp.model.FileData;

@Repository
public interface FileDAO extends JpaRepository<FileData, Long> {

}
