import request from '@/utils/request'

export const registrationListApi = (params) => {
    return request.get('/api/registrations', { params })
}

export const registrationDetailApi = (id) => {
    return request.get(`/api/registrations/${id}`)
}

export const registrationApi = (data) => {
    return request.post('/api/registrations', data)
}
