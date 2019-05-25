import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'
import Add from '../components/AddPage'
import Links from '../components/LinkPage'
import DetailsHw from '../components/DetailsHw'
import DetailsNePage from '../components/DetailsNe'
import NePage from '../components/NePage'
import DetailsLink from '../components/DetailsLink'
import DetailInterface from '../components/DetailInterface'
import Interfaces from '../components/Interfaces'
import AddNE from '../components/AddNe'
import AddHW from '../components/AddHW'
import AddLink from '../components/AddLink'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      component: Home
    },
    {
      path: '/add',
      component: Add,
      children:[
        {
          path:'/add-ne',
          component: AddNE
        },
        {
          path:'/add-link',
          component: AddLink
        },
        {
          path:'/add-hw',
          component: AddHW
        }
      ]
    },
    {
      path: '/hw-components/:idNE',
      component: DetailsHw
    },
    {
      path: '/network-elements',
      component: NePage
    },
    {
      path: '/network-elements/:id',
      component: DetailsNePage
    },
    {
      path: '/links/:idNE',
      component: Links
    },
    {
      path: '/links/:idNE/:idL',
      component: DetailsLink
    },
    {
      path: '/interfaces/:idNE',
      component: Interfaces
    },
    {
      path: '/interfaces/:idNE/:idI',
      component: DetailInterface
    }
  ],
  mode: 'history'
})
