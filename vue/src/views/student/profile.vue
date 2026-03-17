<script setup>
import { ref, onMounted } from 'vue'
import { registrationListApi } from '@/api/registration'
import { myAwardsApi } from '@/api/award'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()
const activeTab = ref('registration')
const registrationList = ref([])
const awardList = ref([])
const loading = ref(false)

const loadRegistrations = async () => {
  loading.value = true
  const user = JSON.parse(localStorage.getItem('loginUser'))
  const result = await registrationListApi({ studentId: user.id, page: 1, pageSize: 100 })
  if (result.code) {
    registrationList.value = result.data.rows
  }
  loading.value = false
}

const loadAwards = async () => {
  loading.value = true
  const result = await myAwardsApi()
  if (result.code) {
    awardList.value = result.data
  }
  loading.value = false
}

const handleRegistrationDetail = (id) => {
  router.push(`/student/registration/${id}`)
}

const handleLogout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('loginUser')
  router.push('/login')
}

onMounted(() => {
  loadRegistrations()
  loadAwards()
})
</script>

<template>
  <div class="container">
    <el-container>
      <el-header class="header">
        <div class="header-left">
          <h2>大学生竞赛活动管理平台</h2>
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="user-info">
              <el-icon><User /></el-icon>
              {{ JSON.parse(localStorage.getItem('loginUser')).realName }}
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="router.push('/student/home')">
                  <el-icon><HomeFilled /></el-icon>
                  返回首页
                </el-dropdown-item>
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
          <h2 class="page-title">个人中心</h2>
          
          <el-tabs v-model="activeTab" class="tabs">
            <el-tab-pane label="报名记录" name="registration">
              <el-card v-loading="loading">
                <el-table :data="registrationList" stripe>
                  <el-table-column prop="competitionTitle" label="竞赛名称" width="200"></el-table-column>
                  <el-table-column prop="teamName" label="团队名称" width="150">
                    <template #default="{ row }">
                      {{ row.teamName || '个人报名' }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="status" label="状态" width="120">
                    <template #default="{ row }">
                      <el-tag :type="row.status === 'approved' ? 'success' : row.status === 'rejected' ? 'danger' : 'warning'">
                        {{ row.status === 'approved' ? '已通过' : row.status === 'rejected' ? '已拒绝' : '待审核' }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="registrationTime" label="报名时间" width="180"></el-table-column>
                  <el-table-column prop="fee" label="报名费" width="100">
                    <template #default="{ row }">
                      ¥{{ row.fee }}
                    </template>
                  </el-table-column>
                  <el-table-column label="操作" width="150">
                    <template #default="{ row }">
                      <el-button type="primary" size="small" @click="handleRegistrationDetail(row.id)">
                        查看详情
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-card>
            </el-tab-pane>

            <el-tab-pane label="获奖记录" name="award">
              <el-card v-loading="loading">
                <el-table :data="awardList" stripe>
                  <el-table-column prop="competitionTitle" label="竞赛名称" width="200"></el-table-column>
                  <el-table-column prop="awardLevel" label="获奖等级" width="120">
                    <template #default="{ row }">
                      <el-tag :type="row.awardLevel === 'first' ? 'danger' : row.awardLevel === 'second' ? 'warning' : 'info'">
                        {{ row.awardLevel === 'first' ? '一等奖' : row.awardLevel === 'second' ? '二等奖' : row.awardLevel === 'third' ? '三等奖' : '优秀奖' }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="awardName" label="奖项名称" width="150"></el-table-column>
                  <el-table-column prop="score" label="分数" width="100">
                    <template #default="{ row }">
                      {{ row.score || '-' }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="ranking" label="排名" width="100">
                    <template #default="{ row }">
                      {{ row.ranking || '-' }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="awardTime" label="获奖时间" width="180"></el-table-column>
                </el-table>
              </el-card>
            </el-tab-pane>
          </el-tabs>
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

.page-title {
  margin-bottom: 20px;
  color: #303133;
}

.content {
  max-width: 1200px;
  margin: 0 auto;
}

.tabs {
  margin-top: 20px;
}
</style>
