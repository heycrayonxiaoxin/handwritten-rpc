package com.wx.server.serializer;

import java.io.IOException;

/**
 * @author wangx
 * @apiNote 自定义序列化接口
 * @since 2024-03-20
 */
public interface Serializer {

   /**
    * 序列化
    *
    * @param object
    * @return
    * @param <T>
    * @throws Exception
    */
   <T> byte [] serialize(T object) throws IOException;

   /**
    * 反序列化
    *
    * @param bytes
    * @param type
    * @param <T>
    * @return
    * @throws IOException
    */
   <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;

}
