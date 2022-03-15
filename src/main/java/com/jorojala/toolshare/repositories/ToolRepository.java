package com.jorojala.toolshare.repositories;

import com.jorojala.toolshare.models.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository <Tool, Long> {

}
