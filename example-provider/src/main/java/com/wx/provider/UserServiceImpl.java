package com.wx.provider;

import com.wx.common.model.User;
import com.wx.common.service.UserService;

/**
 * @author wangx
 * @apiNote 用户服务实现类
 * @since 2024-03-19
 */
public class UserServiceImpl implements UserService {
   @Override
   public User getUser(User user) {
      System.out.println("USERNAME: " + user.getUsername());
      return user;
   }
}
