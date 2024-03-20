package com.wx.common.model;

import java.io.Serializable;

/**
 * @author wangx
 * @apiNote 用户信息模块
 * @since 2024-03-19
 */
public class User implements Serializable {

   private String username;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }
}
