package com.lvhao.service.impl;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionTemplateAccountServiceTest {

  @Autowired private TransactionTemplateAccountService balanceService;

  @Test
  void transfer() {
    balanceService.transfer(1, 2, 50);
  }
}
