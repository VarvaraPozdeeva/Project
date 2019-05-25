import Vue from 'vue'
import Vuetify from 'vuetify'
import App from './App'
import router from './router'
import './api/resource'
import '@babel/polyfill'
import store from './store/store'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify);

new Vue({
  el: '#app',
  router,
  store,
  render: h=> h(App)
});
