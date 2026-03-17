<script setup>
import { ref, onMounted } from 'vue'
import { competitionListApi } from '@/api/competition'
import { registrationApi } from '@/api/registration'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()
const competitionList = ref([])
const loading = ref(false)
const queryParams = ref({
  page: 1,
  pageSize: 10,
  status: 'published'
})

const loadCompetitions = async () => {
  loading.value = true
  const result = await competitionListApi(queryParams.value)
  if (result.code) {
    competitionList.value = result.data.rows
  }
  loading.value = false
}

const handleRegister = async (competition) => {
  const data = {
    competitionId: competition.id,
    teamId: null,
    remark: '个人报名'
  }
  const result = await registrationApi(data)
  if (result.code) {
    ElMessage.success('报名成功，请等待审核')
    loadCompetitions()
  } else {
    ElMessage.error(result.msg)
  }
}

const handleDetail = (id) => {
  router.push(`/student/competition/${id}`)
}

const handleLogout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('loginUser')
  router.push('/login')
}

onMounted(() => {
  loadCompetitions()
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
                <el-dropdown-item @click="router.push('/student/profile')">
                  <el-icon><User /></el-icon>
                  个人中心
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
          <h2 class="page-title">竞赛列表</h2>
          
          <el-card v-loading="loading" class="competition-card" v-for="item in competitionList" :key="item.id">
            <template #header>
              <div class="card-header">
                <span class="title">{{ item.title }}</span>
                <el-tag :type="item.status === 'published' ? 'success' : 'info'">
                  {{ item.status === 'published' ? '报名中' : '已结束' }}
                </el-tag>
              </div>
            </template>
            
            <div class="card-content">
              <p class="description">{{ item.description }}</p>
              <div class="info">
                <p><el-icon><Calendar /></el-icon> 报名时间：{{ item.registrationStartTime }} ~ {{ item.registrationEndTime }}</p>
                <p><el-icon><Clock /></el-icon> 比赛时间：{{ item.startTime }} ~ {{ item.endTime }}</p>
                <p><el-icon><Location /></el-icon> 比赛地点：{{ item.location }}</p>
                <p><el-icon><Money /></el-icon> 报名费：¥{{ item.fee }}</p>
                <p><el-icon><User /></el-icon> 类型：{{ item.competitionType === 'individual' ? '个人赛' : '团队赛' }}</p>
                <p><el-icon><UserFilled /></el-icon> 已报名：{{ item.currentParticipants }} / {{ item.maxParticipants }}</p>
              </div>
              
              <div class="actions">
                <el-button type="primary" @click="handleDetail(item.id)">查看详情</el-button>
                <el-button type="success" @click="handleRegister(item)" :disabled="item.currentParticipants >= item.maxParticipants">
                  {{ item.currentParticipants >= item.maxParticipants ? '已满员' : '立即报名' }}
                </el-button>
              </div>
            </div>
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

.page-title {
  margin-bottom: 20px;
  color: #303133;
}

.content {
  max-width: 1200px;
  margin: 0 auto;
}

.competition-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-header .title {
  font-size: 18px;
  font-weight: bold;
}

.card-content {
  padding: 10px 0;
}

.description {
  color: #606266;
  margin-bottom: 15px;
  line-height: 1.6;
}

.info p {
  margin: 8px 0;
  color: #909399;
  display: flex;
  align-items: center;
  gap: 5px;
}

.actions {
  margin-top: 20px;
  display: flex;
  gap: 10px;
}
</style>
