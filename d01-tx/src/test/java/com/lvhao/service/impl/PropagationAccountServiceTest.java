package com.lvhao.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropagationAccountServiceTest {

  @Autowired private RequiredPropagationAccountService requiredPropagationAccountService;
  @Autowired private RequiresNewPropagationAccountService requiresNewPropagationAccountService;
  @Autowired private NestedPropagationAccountService nestedPropagationAccountService;

  @Test
  void testRequired() {
    requiredPropagationAccountService.insertOne();
  }

  @Test
  void testRequiresNew() {
    requiresNewPropagationAccountService.insertOne();
  }

  @Test
  void testNested() {
    nestedPropagationAccountService.insertOne();
  }
}
