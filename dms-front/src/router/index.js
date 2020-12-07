import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'home',
        component: () => import(/* webpackChunkName: "about" */ '../components/page/Home.vue'),
        // component: Home,
        redirect: '/home/dashboard',
        children: [
            {
                path: '/home/dashboard',
                component: () => import(/* webpackChunkName: "about" */ '../components/page/Dashboard.vue'),
                meta: { title: "系统首页" }
            },
            {
                path: '/home/form',
                component: () => import(/* webpackChunkName: "about" */ '../components/page/Form.vue'),
                meta: { title: "表单数据" }
            },
            {
                path: '/home/table',
                component: () => import(/* webpackChunkName: "about" */ '../components/page/Table.vue'),
                meta: { title: "表格数据" }
            },
            {
                path: '/home/tabs',
                component: () => import(/* webpackChunkName: "about" */ '../components/page/Tabs.vue'),
                meta: { title: "选项卡" }
            },
            {
                path: '/home/tabs2',
                component: () => import(/* webpackChunkName: "about" */ '../components/page/Tabs2.vue'),
                meta: { title: "三级菜单" }
            }
        ]
  },
  {
      path: '/login',
      name: 'login',
      component: () => import(/* webpackChunkName: "about" */ '../components/page/Login.vue')
  },
  {
      path: '/register',
      name: 'register',
      component: () => import(/* webpackChunkName: "about" */ '../components/page/Register.vue')
  },
  {
      path: '/about',
      name: 'about',
      component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
