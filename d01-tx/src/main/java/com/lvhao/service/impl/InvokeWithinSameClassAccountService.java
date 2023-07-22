package com.lvhao.service.impl;

import com.lvhao.dao.AccountMapper;
import com.lvhao.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** 在同一个类中调用 */
@Service
@RequiredArgsConstructor
public class InvokeWithinSameClassAccountService {

  private final AccountMapper accountMapper;

  @Transactional
  public void insertOne() {
    accountMapper.insert(new Account(1, 100));
    insertAnotherOne();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void insertAnotherOne() {
    accountMapper.insert(new Account(2, 100));
  }
}


