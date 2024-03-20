package com.wx.common.service;

import com.wx.common.model.User;

/**
 * @author wangx
 * @apiNote 用户服务接口
 * @since 2024-03-19
 */
public interface UserService {

   /**
    * 获取用户信息
    * @param user
    * @return
    */
   User getUser(User user);

}
