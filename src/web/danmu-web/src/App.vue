<template>
  <div id="app">
    <DanMu />
  </div>
</template>

<script>
import DanMu from './components/DanMu.vue';
import Vue from 'vue'
import Router from 'vue-router'

export default {
  name: 'App',
  components: {
    DanMu
  },
  data() {
    return {
      formData: {
        username: '',
        email: '',
        message: ''
      }
    };
  },

};
Vue.use(Router)

const routes = [
  { path: '/', component: Home },
  { path: '/page-two', component: PageTwo }
]

export default new Router({
  routes
})

// 定义组件
const Home = () => import('@/views/Home.vue')
const PageTwo = () => import('@/views/PageTwo.vue')
</script>