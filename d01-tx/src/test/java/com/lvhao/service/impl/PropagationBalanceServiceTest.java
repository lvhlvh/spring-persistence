package com.lvhao.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropagationBalanceServiceTest {

  @Autowired private RequiredPropagationBalanceService requiredPropagationBalanceService;
  @Autowired private RequiresNewPropagationBalanceService requiresNewPropagationBalanceService;
  @Autowired private NestedPropagationBalanceService nestedPropagationBalanceService;

  @Test
  void testRequired() {
    requiredPropagationBalanceService.insertOne();
  }

  @Test
  void testRequiresNew() {
    requiresNewPropagationBalanceService.insertOne();
  }

  @Test
  void testNested() {
    nestedPropagationBalanceService.insertOne();
  }
}
