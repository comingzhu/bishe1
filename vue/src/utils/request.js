import axios from 'axios';
import { ElMessage } from 'element-plus';
import router from '../router';

const request = axios.create({
  baseURL: '/api',
  timeout: 600000
})

request.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error)
  }
)

request.interceptors.response.use(
  (response) => {
    return response.data
  },
  (error) => {
    if (error.response && error.response.status === 401) {
      ElMessage.error('登录超时，请重新登录');
      localStorage.removeItem('token');
      localStorage.removeItem('loginUser');
      router.push('/login');
    } else {
      ElMessage.error(error.response?.data?.msg || '接口访问异常');
    }
    return Promise.reject(error)
  }
)

export default request
