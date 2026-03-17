import { createRouter, createWebHistory } from 'vue-router'

import IndexView from '@/views/index/index.vue'
import ClazzView from '@/views/clazz/index.vue'
import DeptView from '@/views/dept/index.vue'
import EmpView from '@/views/emp/index.vue'
import LogView from '@/views/log/index.vue'
import StuView from '@/views/stu/index.vue'
import EmpReportView from '@/views/report/emp/index.vue'
import StuReportView from '@/views/report/stu/index.vue'
import LayoutView from '@/views/layout/index.vue'
import LoginView from '@/views/login/index.vue'

import StudentHomeView from '@/views/student/home.vue'
import StudentProfileView from '@/views/student/profile.vue'
import StudentRegistrationDetailView from '@/views/student/registration-detail.vue'
import TeacherHomeView from '@/views/teacher/home.vue'
import AdminHomeView from '@/views/admin/home.vue'
import ScorerHomeView from '@/views/scorer/home.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: '',
      component: LayoutView,
      redirect: '/index',
      children: [
        { path: 'index', name: 'index', component: IndexView },
        { path: 'clazz', name: 'clazz', component: ClazzView },
        { path: 'stu', name: 'stu', component: StuView },
        { path: 'dept', name: 'dept', component: DeptView },
        { path: 'emp', name: 'emp', component: EmpView },
        { path: 'log', name: 'log', component: LogView },
        { path: 'empReport', name: 'empReport', component: EmpReportView },
        { path: 'stuReport', name: 'stuReport', component: StuReportView }
      ]
    },
    { path: '/login', name: 'login', component: LoginView },
    { path: '/student/home', name: 'studentHome', component: StudentHomeView },
    { path: '/student/profile', name: 'studentProfile', component: StudentProfileView },
    { path: '/student/registration/:id', name: 'studentRegistrationDetail', component: StudentRegistrationDetailView },
    { path: '/teacher/home', name: 'teacherHome', component: TeacherHomeView },
    { path: '/admin/home', name: 'adminHome', component: AdminHomeView },
    { path: '/scorer/home', name: 'scorerHome', component: ScorerHomeView }
  ]
})

export default router
