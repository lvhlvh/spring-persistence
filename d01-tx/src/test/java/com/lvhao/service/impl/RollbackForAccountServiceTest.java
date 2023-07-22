package com.lvhao.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RollbackForAccountServiceTest {

  @Autowired private RollbackForAccountService accountService;

  @Test
  void insert() {
    accountService.insert();
  }
}
