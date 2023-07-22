package com.lvhao.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InvokeWithinSameClassAccountServiceTest {

  @Autowired private InvokeWithinSameClassAccountService invokeWithinSameClassAccountService;

  @Test
  void insertOne() {
    invokeWithinSameClassAccountService.insertOne();
  }
}
