<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const handleLogout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('loginUser')
  router.push('/login')
}
</script>

<template>
  <div class="container">
    <el-container>
      <el-header class="header">
        <div class="header-left">
          <h2>大学生竞赛活动管理平台 - 管理员端</h2>
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="user-info">
              <el-icon><User /></el-icon>
              {{ JSON.parse(localStorage.getItem('loginUser')).realName }}
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="handleLogout">
                  <el-icon><SwitchButton /></el-icon>
                  退出登录
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>

      <el-main class="main">
        <div class="content">
          <el-card>
            <h2>欢迎来到管理员端</h2>
            <p>管理员端功能正在开发中...</p>
            <p>即将支持：</p>
            <ul>
              <li>审核报名</li>
              <li>录入获奖信息</li>
              <li>管理用户</li>
              <li>数据统计</li>
              <li>系统设置</li>
            </ul>
          </el-card>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.container {
  min-height: 100vh;
  background-color: #f5f7fa;
}

.header {
  background-color: #409eff;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.header-left h2 {
  margin: 0;
  font-size: 20px;
}

.header-right {
  display: flex;
  align-items: center;
}

.user-info {
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 16px;
}

.main {
  padding: 20px;
}

.content {
  max-width: 1200px;
  margin: 0 auto;
}

.content h2 {
  margin-top: 0;
  color: #303133;
}

.content ul {
  margin-top: 20px;
  padding-left: 20px;
}

.content li {
  margin: 10px 0;
  color: #606266;
}
</style>
