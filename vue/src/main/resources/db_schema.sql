-- ============================================
-- 大学生竞赛活动管理平台数据库表结构
-- 数据库名: tlias
-- ============================================

-- 1. 角色表
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '角色ID',
  `name` VARCHAR(50) NOT NULL UNIQUE COMMENT '角色名称：学生、老师、管理员、评审员',
  `code` VARCHAR(50) NOT NULL UNIQUE COMMENT '角色代码：STUDENT、TEACHER、ADMIN、REVIEWER',
  `description` VARCHAR(255) COMMENT '角色描述',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 2. 用户表（统一管理学生、老师、管理员、评审员）
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名（登录账号）',
  `password` VARCHAR(255) NOT NULL COMMENT '密码（加密存储）',
  `name` VARCHAR(50) NOT NULL COMMENT '姓名',
  `gender` TINYINT COMMENT '性别：1-男，2-女',
  `phone` VARCHAR(20) COMMENT '手机号',
  `email` VARCHAR(100) COMMENT '邮箱',
  `student_no` VARCHAR(50) COMMENT '学号（学生专用）',
  `college` VARCHAR(100) COMMENT '学院',
  `major` VARCHAR(100) COMMENT '专业',
  `grade` VARCHAR(20) COMMENT '年级',
  `avatar` VARCHAR(255) COMMENT '头像URL',
  `status` TINYINT DEFAULT 1 COMMENT '状态：1-启用，0-禁用',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 3. 用户角色关联表（多对多关系）
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `user_id` INT NOT NULL COMMENT '用户ID',
  `role_id` INT NOT NULL COMMENT '角色ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`role_id`) REFERENCES `role`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 4. 竞赛表
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '竞赛ID',
  `title` VARCHAR(200) NOT NULL COMMENT '竞赛标题',
  `description` TEXT COMMENT '竞赛描述',
  `type` VARCHAR(50) COMMENT '竞赛类型：如程序设计、数学建模、创新创业等',
  `level` VARCHAR(50) COMMENT '竞赛级别：校级、省级、国家级、国际级',
  `organizer` VARCHAR(200) COMMENT '主办单位',
  `sponsor` VARCHAR(200) COMMENT '承办单位',
  `start_time` DATETIME COMMENT '报名开始时间',
  `end_time` DATETIME COMMENT '报名结束时间',
  `competition_time` DATETIME COMMENT '竞赛时间',
  `location` VARCHAR(200) COMMENT '竞赛地点',
  `max_participants` INT COMMENT '最大参赛人数',
  `current_participants` INT DEFAULT 0 COMMENT '当前报名人数',
  `team_size` INT DEFAULT 1 COMMENT '团队人数限制（1为个人赛，>1为团队赛）',
  `requirements` TEXT COMMENT '参赛要求',
  `awards` TEXT COMMENT '奖项设置',
  `status` TINYINT DEFAULT 0 COMMENT '状态：0-待发布，1-报名中，2-报名结束，3-进行中，4-已结束，5-已取消',
  `publisher_id` INT COMMENT '发布者ID（老师）',
  `approver_id` INT COMMENT '审核者ID（管理员）',
  `approval_status` TINYINT DEFAULT 0 COMMENT '审核状态：0-待审核，1-已通过，2-已拒绝',
  `approval_time` DATETIME COMMENT '审核时间',
  `approval_remark` VARCHAR(500) COMMENT '审核备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  FOREIGN KEY (`publisher_id`) REFERENCES `user`(`id`) ON DELETE SET NULL,
  FOREIGN KEY (`approver_id`) REFERENCES `user`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='竞赛表';

-- 5. 报名表
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '报名ID',
  `competition_id` INT NOT NULL COMMENT '竞赛ID',
  `student_id` INT NOT NULL COMMENT '学生ID',
  `team_name` VARCHAR(100) COMMENT '团队名称（团队赛时使用）',
  `team_leader_id` INT COMMENT '队长ID（团队赛时使用）',
  `contact_phone` VARCHAR(20) COMMENT '联系电话',
  `contact_email` VARCHAR(100) COMMENT '联系邮箱',
  `application_reason` TEXT COMMENT '申请理由',
  `status` TINYINT DEFAULT 0 COMMENT '报名状态：0-待审核，1-已通过，2-已拒绝，3-已取消',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  FOREIGN KEY (`competition_id`) REFERENCES `competition`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`student_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`team_leader_id`) REFERENCES `user`(`id`) ON DELETE SET NULL,
  UNIQUE KEY `uk_competition_student` (`competition_id`, `student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报名表';

-- 6. 团队成员表（团队赛时使用）
DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  `registration_id` INT NOT NULL COMMENT '报名ID',
  `student_id` INT NOT NULL COMMENT '学生ID',
  `role` VARCHAR(50) COMMENT '团队成员角色：队长、队员',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  FOREIGN KEY (`registration_id`) REFERENCES `registration`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`student_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
  UNIQUE KEY `uk_registration_student` (`registration_id`, `student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='团队成员表';

-- 7. 作品表
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '作品ID',
  `registration_id` INT NOT NULL COMMENT '报名ID',
  `competition_id` INT NOT NULL COMMENT '竞赛ID',
  `student_id` INT NOT NULL COMMENT '提交者ID（学生）',
  `title` VARCHAR(200) NOT NULL COMMENT '作品标题',
  `description` TEXT COMMENT '作品描述',
  `file_url` VARCHAR(500) COMMENT '作品文件URL（可存储多个文件，用逗号分隔）',
  `file_name` VARCHAR(500) COMMENT '作品文件名',
  `file_size` BIGINT COMMENT '文件大小（字节）',
  `submit_time` DATETIME COMMENT '提交时间',
  `status` TINYINT DEFAULT 0 COMMENT '状态：0-待评审，1-评审中，2-已评审，3-已退回',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  FOREIGN KEY (`registration_id`) REFERENCES `registration`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`competition_id`) REFERENCES `competition`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`student_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='作品表';

-- 8. 评分表
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '评分ID',
  `work_id` INT NOT NULL COMMENT '作品ID',
  `reviewer_id` INT NOT NULL COMMENT '评审员ID',
  `score` DECIMAL(5,2) COMMENT '评分（0-100分）',
  `score_detail` TEXT COMMENT '评分详情（JSON格式，可存储多个评分项）',
  `comment` TEXT COMMENT '评审意见',
  `review_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '评审时间',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  FOREIGN KEY (`work_id`) REFERENCES `work`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`reviewer_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
  UNIQUE KEY `uk_work_reviewer` (`work_id`, `reviewer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评分表';

-- 9. 获奖记录表
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '获奖ID',
  `competition_id` INT NOT NULL COMMENT '竞赛ID',
  `registration_id` INT NOT NULL COMMENT '报名ID',
  `work_id` INT COMMENT '作品ID',
  `student_id` INT NOT NULL COMMENT '获奖学生ID',
  `award_level` VARCHAR(50) NOT NULL COMMENT '获奖等级：一等奖、二等奖、三等奖、优秀奖等',
  `award_rank` INT COMMENT '获奖排名',
  `certificate_url` VARCHAR(500) COMMENT '证书URL',
  `prize` VARCHAR(200) COMMENT '奖品',
  `total_score` DECIMAL(5,2) COMMENT '总得分',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  FOREIGN KEY (`competition_id`) REFERENCES `competition`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`registration_id`) REFERENCES `registration`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`work_id`) REFERENCES `work`(`id`) ON DELETE SET NULL,
  FOREIGN KEY (`student_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='获奖记录表';

-- 10. 操作日志表（可选，用于记录系统操作）
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '日志ID',
  `user_id` INT COMMENT '操作用户ID',
  `operation` VARCHAR(100) NOT NULL COMMENT '操作类型',
  `method` VARCHAR(200) COMMENT '操作方法',
  `params` TEXT COMMENT '请求参数',
  `result` TEXT COMMENT '返回结果',
  `ip` VARCHAR(50) COMMENT 'IP地址',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- ============================================
-- 初始化数据
-- ============================================

-- 插入角色数据
INSERT INTO `role` (`name`, `code`, `description`) VALUES
('学生', 'STUDENT', '学生角色，可以报名竞赛、提交作品、查询成绩'),
('老师', 'TEACHER', '老师角色，可以发布竞赛信息'),
('管理员', 'ADMIN', '管理员角色，可以统计学生人数、管理老师发布的竞赛'),
('评审员', 'REVIEWER', '评审员角色，可以对学生提交的作品进行评分');

-- 插入默认管理员账号（密码需要加密，这里仅作示例）
-- 实际使用时，密码应该使用BCrypt等加密算法加密
-- 默认密码：admin123
INSERT INTO `user` (`username`, `password`, `name`, `phone`, `email`, `status`) VALUES
('admin', 'admin123', '系统管理员', '13800138000', 'admin@example.com', 1);

-- 为管理员分配角色
INSERT INTO `user_role` (`user_id`, `role_id`) 
SELECT u.id, r.id FROM `user` u, `role` r 
WHERE u.username = 'admin' AND r.code = 'ADMIN';

-- ============================================
-- 创建索引（优化查询性能）
-- ============================================

-- 用户表索引
CREATE INDEX `idx_user_username` ON `user`(`username`);
CREATE INDEX `idx_user_student_no` ON `user`(`student_no`);
CREATE INDEX `idx_user_status` ON `user`(`status`);

-- 竞赛表索引
CREATE INDEX `idx_competition_status` ON `competition`(`status`);
CREATE INDEX `idx_competition_publisher` ON `competition`(`publisher_id`);
CREATE INDEX `idx_competition_approval` ON `competition`(`approval_status`);

-- 报名表索引
CREATE INDEX `idx_registration_competition` ON `registration`(`competition_id`);
CREATE INDEX `idx_registration_student` ON `registration`(`student_id`);
CREATE INDEX `idx_registration_status` ON `registration`(`status`);

-- 作品表索引
CREATE INDEX `idx_work_competition` ON `work`(`competition_id`);
CREATE INDEX `idx_work_student` ON `work`(`student_id`);
CREATE INDEX `idx_work_status` ON `work`(`status`);

-- 评分表索引
CREATE INDEX `idx_score_work` ON `score`(`work_id`);
CREATE INDEX `idx_score_reviewer` ON `score`(`reviewer_id`);

-- 获奖记录表索引
CREATE INDEX `idx_award_competition` ON `award`(`competition_id`);
CREATE INDEX `idx_award_student` ON `award`(`student_id`);


