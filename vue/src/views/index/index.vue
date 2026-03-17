<script setup>
import { ref, onMounted } from 'vue';
import { Trophy, User, Document, DataAnalysis } from '@element-plus/icons-vue';

// 统计数据
const statistics = ref({
  totalCompetitions: 0,
  totalStudents: 0,
  totalRegistrations: 0,
  totalAwards: 0
});

// 模拟数据（实际应该从API获取）
onMounted(() => {
  // 这里可以调用API获取真实数据
  statistics.value = {
    totalCompetitions: 156,
    totalStudents: 2847,
    totalRegistrations: 5234,
    totalAwards: 892
  };
});
</script>

<template>
  <div class="dashboard">
    <div class="welcome-section">
      <h1>欢迎使用大学生竞赛活动管理平台</h1>
      <p class="subtitle">Comprehensive Competition Management System for College Students</p>
    </div>

    <div class="statistics-section">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon competition">
                <el-icon :size="40"><Trophy /></el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-value">{{ statistics.totalCompetitions }}</div>
                <div class="stat-label">竞赛总数</div>
              </div>
            </div>
          </el-card>
        </el-col>
        
        <el-col :span="6">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon student">
                <el-icon :size="40"><User /></el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-value">{{ statistics.totalStudents }}</div>
                <div class="stat-label">学生总数</div>
              </div>
            </div>
          </el-card>
        </el-col>
        
        <el-col :span="6">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon registration">
                <el-icon :size="40"><Document /></el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-value">{{ statistics.totalRegistrations }}</div>
                <div class="stat-label">报名总数</div>
              </div>
            </div>
          </el-card>
        </el-col>
        
        <el-col :span="6">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon award">
                <el-icon :size="40"><Trophy /></el-icon>
              </div>
              <div class="stat-info">
                <div class="stat-value">{{ statistics.totalAwards }}</div>
                <div class="stat-label">获奖总数</div>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <div class="features-section">
      <el-card>
        <template #header>
          <div class="card-header">
            <span>平台功能</span>
          </div>
        </template>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="feature-item">
              <h3>竞赛管理</h3>
              <p>支持竞赛信息的发布、编辑、查询和管理，包括竞赛类型、时间、地点等详细信息</p>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="feature-item">
              <h3>报名管理</h3>
              <p>学生可以在线报名参加竞赛，管理员可以审核报名信息，管理报名状态</p>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="feature-item">
              <h3>数据统计</h3>
              <p>提供全面的数据统计分析，包括竞赛统计、报名统计、获奖统计等功能</p>
            </div>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>

<style scoped>
.dashboard {
  padding: 20px;
}

.welcome-section {
  text-align: center;
  margin-bottom: 40px;
  padding: 30px 0;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 10px;
  color: white;
}

.welcome-section h1 {
  font-size: 36px;
  margin-bottom: 10px;
  font-weight: bold;
}

.subtitle {
  font-size: 16px;
  opacity: 0.9;
}

.statistics-section {
  margin-bottom: 30px;
}

.stat-card {
  height: 120px;
}

.stat-content {
  display: flex;
  align-items: center;
  height: 100%;
}

.stat-icon {
  width: 70px;
  height: 70px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20px;
  color: white;
}

.stat-icon.competition {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.stat-icon.student {
  background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
}

.stat-icon.registration {
  background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
}

.stat-icon.award {
  background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);
}

.stat-info {
  flex: 1;
}

.stat-value {
  font-size: 32px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 5px;
}

.stat-label {
  font-size: 14px;
  color: #909399;
}

.features-section {
  margin-top: 30px;
}

.card-header {
  font-size: 18px;
  font-weight: bold;
}

.feature-item {
  padding: 20px;
  text-align: center;
}

.feature-item h3 {
  color: #409eff;
  margin-bottom: 15px;
  font-size: 20px;
}

.feature-item p {
  color: #606266;
  line-height: 1.8;
  font-size: 14px;
}
</style>