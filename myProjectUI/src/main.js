import Vue from 'vue'
import Vuetify from 'vuetify'
import App from './App'
import router from './router'
import Resource from 'vue-resource'
import '@babel/polyfill'
import store from './store/store'
import 'vuetify/dist/vuetify.min.css'
Vue.use(Vuetify);
Vue.use(Resource);

new Vue({
  el: '#app',
  router,
  store,
  render: h=> h(App)
});
