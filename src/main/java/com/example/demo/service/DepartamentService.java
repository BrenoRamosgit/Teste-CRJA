package com.example.demo.service;

import com.example.demo.dto.response.DepartamentResponse;
import com.example.demo.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartamentService {

    Page<DepartamentResponse> list(Pageable pageable);

    Department find(Long id);
}
