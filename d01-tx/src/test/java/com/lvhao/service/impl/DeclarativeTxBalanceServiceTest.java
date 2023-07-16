package com.lvhao.service.impl;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeclarativeTxBalanceServiceTest {

  @Autowired private DeclarativeTxBalanceService balanceService;

  @Test
  void transfer() throws IOException {
     balanceService.transfer(1, 2, 50);
  }
}
