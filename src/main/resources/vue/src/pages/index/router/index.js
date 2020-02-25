import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/MainPage'
import NotFoundComponent from '@/components/NotFoundComponent'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    { path: '/', component: MainPage},
    { path: '*', component: NotFoundComponent }
  ]
})
