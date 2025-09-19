package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.User;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void join(User user){
        userDao.insert(user);
    }

    public User getUser(String mid) {
    User User = userDao.selectByAccount(mid);
    return User;
  }

  public User modify(User user) {
    User dbUser = userDao.selectByAccount(user.getAccount());
    if(dbUser == null) {
      return null;
    } else {
      if (StringUtils.hasText(user.getPassword())){
        dbUser.setPassword(user.getPassword());
      } 
      if (StringUtils.hasText(user.getEmail())){
        dbUser.setEmail(user.getEmail());
      } 
    }
    // 조건에 맞지 않다면 0 리턴. 에러 아님
    //int rows = UserDao.update(dbUser);
    //return dbUser;

    userDao.update(dbUser);
    dbUser = userDao.selectByAccount(user.getAccount());
    return dbUser;
    
  }

  public enum RemoveResult {
    SUCCESS,
    FAIL
  }

  public RemoveResult remove(String account) {
    int rows = userDao.delete(account);
    if(rows == 0) {
      return RemoveResult.FAIL;
    } else {
      return RemoveResult.SUCCESS;
    }
  }
    
}
