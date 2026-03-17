package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.MessageMapper;
import com.itheima.pojo.Message;
import com.itheima.pojo.PageResult;
import com.itheima.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public PageResult<Message> list(String targetRole, Integer targetUserId, Integer isRead, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Message> list = messageMapper.list(targetRole, targetUserId, isRead, page, pageSize);
        Page<Message> p = (Page<Message>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public Message findById(Integer id) {
        return messageMapper.findById(id);
    }

    @Override
    public void send(Message message) {
        message.setIsRead(0);
        messageMapper.insert(message);
    }

    @Override
    public void markAsRead(Integer id) {
        Message message = new Message();
        message.setId(id);
        message.setIsRead(1);
        messageMapper.update(message);
    }

    @Override
    public void delete(Integer id) {
        messageMapper.delete(id);
    }

    @Override
    public Integer countUnread(Integer targetUserId) {
        return messageMapper.countUnread(targetUserId);
    }

    @Override
    public List<Message> findByTargetUserId(Integer targetUserId) {
        return messageMapper.findByTargetUserId(targetUserId);
    }
}
