import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/pages/HomeView'
import LoginView from '@/pages/login/LoginView'


const routes = [
  { path: '/', name: 'HomeView', component: HomeView },
  { path: '/login', name: 'LoginView', component: LoginView},
  // ,
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router