package com.jorojala.toolshare.repositories;

import com.jorojala.toolshare.models.Tool;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ToolRepository extends JpaRepository <Tool, Long> {

    public Sort findByName(String name);
}
