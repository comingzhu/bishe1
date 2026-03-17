import request from '@/utils/request'

export const competitionListApi = (params) => {
    return request.get('/api/competitions', { params })
}

export const competitionDetailApi = (id) => {
    return request.get(`/api/competitions/${id}`)
}
