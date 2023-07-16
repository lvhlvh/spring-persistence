package com.lvhao.controller;

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
  public DataSource dataSource() {
    return dataSource;
  }

  @GetMapping("/txManager")
  public PlatformTransactionManager txManager() {
    return platformTransactionManager;
  }
}
