package com.itheima.controller;

import com.itheima.pojo.Message;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.MessageService;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public Result list(@RequestParam(required = false) String targetRole,
                      @RequestParam(required = false) Integer targetUserId,
                      @RequestParam(required = false) Integer isRead,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询消息列表：targetRole={}, targetUserId={}, isRead={}", targetRole, targetUserId, isRead);
        
        Integer currentUserId = CurrentHolder.getCurrentId();
        if (targetUserId == null) {
            targetUserId = currentUserId;
        }
        
        PageResult<Message> pageResult = messageService.list(targetRole, targetUserId, isRead, page, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询消息详情：{}", id);
        Message message = messageService.findById(id);
        return Result.success(message);
    }

    @PostMapping
    public Result send(@RequestBody Message message) {
        log.info("发送消息：{}", message.getTitle());
        messageService.send(message);
        return Result.success("发送成功");
    }

    @PutMapping("/{id}/read")
    public Result markAsRead(@PathVariable Integer id) {
        log.info("标记消息已读：{}", id);
        messageService.markAsRead(id);
        return Result.success("标记成功");
    }

    @GetMapping("/unread/count")
    public Result countUnread() {
        Integer currentUserId = CurrentHolder.getCurrentId();
        Integer count = messageService.countUnread(currentUserId);
        
        Map<String, Object> data = new HashMap<>();
        data.put("count", count);
        
        return Result.success(data);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除消息：{}", id);
        messageService.delete(id);
        return Result.success("删除成功");
    }
}
