<script setup>
import { ref, onMounted } from 'vue'
import { registrationDetailApi } from '@/api/registration'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()
const route = useRoute()
const registration = ref(null)
const award = ref(null)
const loading = ref(false)

const loadDetail = async () => {
  loading.value = true
  const id = route.params.id
  const result = await registrationDetailApi(id)
  if (result.code) {
    registration.value = result.data
    if (result.data.awardId) {
      award.value = {
        awardLevel: result.data.awardLevel,
        awardName: result.data.awardName,
        score: result.data.awardScore,
        ranking: result.data.ranking,
        awardTime: result.data.awardTime
      }
    }
  }
  loading.value = false
}

const handleBack = () => {
  router.back()
}

onMounted(() => {
  loadDetail()
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
          <el-button @click="handleBack">返回</el-button>
        </div>
      </el-header>

      <el-main class="main">
        <div class="content" v-loading="loading">
          <el-card v-if="registration" class="detail-card">
            <template #header>
              <h3>报名详情</h3>
            </template>
            
            <el-descriptions :column="2" border>
              <el-descriptions-item label="竞赛名称">
                {{ registration.competitionTitle }}
              </el-descriptions-item>
              <el-descriptions-item label="学生姓名">
                {{ registration.studentName }}
              </el-descriptions-item>
              <el-descriptions-item label="学号">
                {{ registration.studentIdStr }}
              </el-descriptions-item>
              <el-descriptions-item label="团队名称">
                {{ registration.teamName || '个人报名' }}
              </el-descriptions-item>
              <el-descriptions-item label="报名时间">
                {{ registration.registrationTime }}
              </el-descriptions-item>
              <el-descriptions-item label="报名状态">
                <el-tag :type="registration.status === 'approved' ? 'success' : registration.status === 'rejected' ? 'danger' : 'warning'">
                  {{ registration.status === 'approved' ? '已通过' : registration.status === 'rejected' ? '已拒绝' : '待审核' }}
                </el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="报名费">
                ¥{{ registration.fee }}
              </el-descriptions-item>
              <el-descriptions-item label="备注" :span="2" v-if="registration.remark">
                {{ registration.remark }}
              </el-descriptions-item>
            </el-descriptions>
          </el-card>

          <el-card v-if="award" class="award-card">
            <template #header>
              <h3>获奖信息</h3>
            </template>
            
            <el-descriptions :column="2" border>
              <el-descriptions-item label="获奖等级">
                <el-tag :type="award.awardLevel === 'first' ? 'danger' : award.awardLevel === 'second' ? 'warning' : 'info'" size="large">
                  {{ award.awardLevel === 'first' ? '一等奖' : award.awardLevel === 'second' ? '二等奖' : award.awardLevel === 'third' ? '三等奖' : '优秀奖' }}
                </el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="奖项名称">
                {{ award.awardName }}
              </el-descriptions-item>
              <el-descriptions-item label="最终分数">
                <span class="score">{{ award.score || '-' }}</span>
              </el-descriptions-item>
              <el-descriptions-item label="排名">
                <span class="ranking">第 {{ award.ranking || '-' }} 名</span>
              </el-descriptions-item>
              <el-descriptions-item label="获奖时间" :span="2">
                {{ award.awardTime }}
              </el-descriptions-item>
            </el-descriptions>
          </el-card>

          <el-card v-else class="no-award-card">
            <div class="no-award">
              <el-icon size="80" color="#909399"><Trophy /></el-icon>
              <p>暂无获奖信息</p>
              <p class="tip">竞赛结束后，获奖信息将在此显示</p>
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

.main {
  padding: 20px;
}

.content {
  max-width: 1000px;
  margin: 0 auto;
}

.detail-card,
.award-card,
.no-award-card {
  margin-bottom: 20px;
}

.detail-card h3,
.award-card h3 {
  margin: 0;
  font-size: 18px;
  color: #303133;
}

.score {
  font-size: 24px;
  font-weight: bold;
  color: #67c23a;
}

.ranking {
  font-size: 24px;
  font-weight: bold;
  color: #e6a23c;
}

.no-award {
  text-align: center;
  padding: 40px 0;
}

.no-award p {
  margin: 10px 0;
  color: #909399;
}

.no-award .tip {
  font-size: 14px;
  color: #c0c4cc;
}
</style>
