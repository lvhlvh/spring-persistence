package com.lvhao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/inspect")
@RequiredArgsConstructor
public class InspectController {

  private final DataSource dataSource;
  private final PlatformTransactionManager platformTransactionManager;

  @GetMapping("/dataSource")
  public String dataSource() {
    return dataSource.getClass().getName();
  }

  @GetMapping("/txManager")
  public String txManager() {
    return platformTransactionManager.getClass().getName();
  }
}
