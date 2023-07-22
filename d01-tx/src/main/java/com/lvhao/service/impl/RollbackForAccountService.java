package com.lvhao.service.impl;

import com.lvhao.dao.AccountMapper;
import com.lvhao.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RollbackForAccountService {
  private final AccountMapper accountMapper;

  @Transactional(rollbackFor = MyException1.class)
  //  @Transactional(rollbackFor = MyException1.class, noRollbackFor = MyException2.class)
  public void insert() {
    accountMapper.insert(new Account(1, 100));
    throw new MyException2();
  }
}

class MyException1 extends RuntimeException {}

class MyException2 extends RuntimeException {}
