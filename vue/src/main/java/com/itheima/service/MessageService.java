package com.itheima.service;

import com.itheima.pojo.Message;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface MessageService {
    
    PageResult<Message> list(String targetRole, Integer targetUserId, Integer isRead, Integer page, Integer pageSize);
    
    Message findById(Integer id);
    
    void send(Message message);
    
    void markAsRead(Integer id);
    
    void delete(Integer id);
    
    Integer countUnread(Integer targetUserId);
    
    List<Message> findByTargetUserId(Integer targetUserId);
}
