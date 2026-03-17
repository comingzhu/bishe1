<script setup>
import { ref } from 'vue'
import { loginApi, registerApi } from '@/api/auth'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()
const activeTab = ref('login')
const loginForm = ref({
  username: '',
  password: '',
  role: 'student'
})

const registerForm = ref({
  username: '',
  password: '',
  realName: '',
  role: 'student',
  studentId: '',
  workId: '',
  email: '',
  phone: ''
})

const login = async () => {
  if (!loginForm.value.username) {
    ElMessage.warning('请输入用户名')
    return
  }
  if (!loginForm.value.password) {
    ElMessage.warning('请输入密码')
    return
  }

  const result = await loginApi(loginForm.value)
  if (result.code) {
    ElMessage.success('登录成功')
    localStorage.setItem('token', result.data.token)
    localStorage.setItem('loginUser', JSON.stringify(result.data.user))
    
    const role = result.data.user.role
    if (role === 'student') {
      router.push('/student/home')
    } else if (role === 'teacher') {
      router.push('/teacher/home')
    } else if (role === 'admin') {
      router.push('/admin/home')
    } else if (role === 'scorer') {
      router.push('/scorer/home')
    }
  } else {
    ElMessage.error(result.msg)
  }
}

const register = async () => {
  if (!registerForm.value.username) {
    ElMessage.warning('请输入用户名')
    return
  }
  if (!registerForm.value.password) {
    ElMessage.warning('请输入密码')
    return
  }
  if (!registerForm.value.realName) {
    ElMessage.warning('请输入真实姓名')
    return
  }

  if (registerForm.value.role === 'student' && !registerForm.value.studentId) {
    ElMessage.warning('请输入学号')
    return
  }

  if (registerForm.value.role === 'teacher' && !registerForm.value.workId) {
    ElMessage.warning('请输入工号')
    return
  }

  const result = await registerApi(registerForm.value)
  
  if (result.code) {
    ElMessage.success('注册成功，请登录')
    activeTab.value = 'login'
  } else {
    ElMessage.error(result.msg)
  }
}

const clearLogin = () => {
  loginForm.value = {
    username: '',
    password: '',
    role: 'student'
  }
}

const clearRegister = () => {
  registerForm.value = {
    username: '',
    password: '',
    realName: '',
    role: 'student',
    studentId: '',
    workId: '',
    email: '',
    phone: ''
  }
}
</script>

<template>
  <div id="container">
    <div class="login-form">
      <el-form label-width="100px">
        <p class="title">大学生竞赛活动管理平台</p>
        
        <el-tabs v-model="activeTab" class="login-tabs">
          <el-tab-pane label="登录" name="login">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>

            <el-form-item label="角色" prop="role">
              <el-select v-model="loginForm.role" placeholder="请选择角色" style="width: 100%">
                <el-option label="学生" value="student"></el-option>
                <el-option label="老师" value="teacher"></el-option>
                <el-option label="管理员" value="admin"></el-option>
                <el-option label="评分员" value="scorer"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button class="button" type="primary" @click="login">登 录</el-button>
              <el-button class="button" type="info" @click="clearLogin">重 置</el-button>
            </el-form-item>
          </el-tab-pane>

          <el-tab-pane label="注册" name="register">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>

            <el-form-item label="真实姓名" prop="realName">
              <el-input v-model="registerForm.realName" placeholder="请输入真实姓名"></el-input>
            </el-form-item>

            <el-form-item label="角色" prop="role">
              <el-select v-model="registerForm.role" placeholder="请选择角色" style="width: 100%">
                <el-option label="学生" value="student"></el-option>
                <el-option label="老师" value="teacher"></el-option>
                <el-option label="管理员" value="admin"></el-option>
                <el-option label="评分员" value="scorer"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="学号" prop="studentId" v-if="registerForm.role === 'student'">
              <el-input v-model="registerForm.studentId" placeholder="请输入学号"></el-input>
            </el-form-item>

            <el-form-item label="工号" prop="workId" v-if="registerForm.role === 'teacher'">
              <el-input v-model="registerForm.workId" placeholder="请输入工号"></el-input>
            </el-form-item>

            <el-form-item label="邮箱" prop="email">
              <el-input v-model="registerForm.email" placeholder="请输入邮箱（可选）"></el-input>
            </el-form-item>

            <el-form-item label="手机号" prop="phone">
              <el-input v-model="registerForm.phone" placeholder="请输入手机号（可选）"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button class="button" type="primary" @click="register">注 册</el-button>
              <el-button class="button" type="info" @click="clearRegister">重 置</el-button>
            </el-form-item>
          </el-tab-pane>
        </el-tabs>
      </el-form>
    </div>
  </div>
</template>

<style scoped>
#container {
  padding: 10%;
  min-height: 100vh;
  background-image: url('../../assets/bg1.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}

.login-form {
  max-width: 550px;
  padding: 30px;
  margin: 0 auto;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  background-color: white;
}

.title {
  font-size: 30px;
  font-family: '楷体';
  text-align: center;
  margin-bottom: 30px;
  font-weight: bold;
}

.login-tabs {
  margin-bottom: 20px;
}

.button {
  margin-top: 20px;
  width: 120px;
}
</style>
