import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'
import DetailsHw from '../components/DetailsHw'
import DetailsNePage from '../components/DetailsNe'
import InventoryPage from '../components/InventoryPage'
import DetailInterface from '../components/DetailInterface'
import DetailsLink from '../components/DetailsLink'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      component: Home
    },
    {
      path: '/inventory',
      component: InventoryPage
    },

    {
      path: '/hw-components/:idNE',
      component: DetailsHw
    },

    {
      name: 'netElem',
      path: '/network-elements/:id',
      component: DetailsNePage
    },
    {
      path: '/links/:id',
      component: DetailsLink
    },
    {
      path: '/interfaces/:idNE/:idI',
      component: DetailInterface
    }
  ],
  mode: 'history'
})
