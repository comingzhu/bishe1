import request from '@/utils/request'

export const awardListApi = (params) => {
    return request.get('/api/awards', { params })
}

export const myAwardsApi = () => {
    return request.get('/api/awards/my')
}
