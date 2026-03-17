import request from '@/utils/request'

export const loginApi = (data) => {
  return request.post('/auth/login', data)
}

export const registerApi = (data) => {
  return request.post('/auth/register', data)
}

export const getInfoApi = () => {
  return request.get('/auth/info')
}

export const logoutApi = () => {
  return request.post('/auth/logout')
}
