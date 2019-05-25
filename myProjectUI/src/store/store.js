import Vue from 'vue'
import Vuex from 'vuex'
import neApi from '../api/networkElement'

Vue.use(Vuex)

export default new Vuex.Store({
  state:{
    netElements:[],
    netElement:{},
    interfaces:[],
    hwComponent:{},
    links:[]
  },
  getters:{
    getElemByName: state => name => {
      return state.netElements.find(e => e.name === name).id;
    },
    getInterfaces:state=>{return state.interfaces},
    getNetElements:state=>{return state.netElements},
    getNetElement:state=>{return state.netElement},
    getHwComponent:state=>{return state.hwComponent},
    getLinks:state=>{return state.links},
    getInterfaceByIdIdNe: state=>(idI, idNE)=> {
      const elem = state.netElements.find(e=>e.id === idNE)
      return elem.interfaces.find(i=> i.id === idI)
    }
  },
  mutations: {
    getData(state, elements){
      state.netElements = elements
    },
    addNe(state, element){
      state.netElements = [ ...state.netElements, element]
    },
    getInterfaces(state, interfaces){
      state.interfaces = interfaces
    },
    getHW(state, component){
      state.hwComponent = component
    },
    getLinks(state, links){
      state.links = links
    },
    getNetElem(state, elem){
      state.netElement = elem
    }
  },
  actions:{
     async getDataAction({commit}){
      const result = await neApi.get
       const data =   await  result.json()
       commit('getData', data) ;
    },
    async getInterfaceAction({commit}, id){
      const result = await neApi.getI(id)
      const data =   await  result.json()
      commit('getInterfaces', data) ;
    },
    async getHWAction({commit}, id){
      const result = await neApi.getHW(id)
      const data =   await  result.json()
      commit('getHW', data) ;
    },
    async getLinksAction({commit}, id){
      const result = await neApi.getLinks(id)
      const data =   await  result.json()
      commit('getLinks', data) ;
    },
    async getNetElemAction({commit}, id){
      const result = await neApi.getNetElem(id)
      const data =   await  result.json()
      commit('getNetElem', data) ;
    },
    async addNeAction({commit}, element){
       const result = await neApi.add(element)
        console.log(result);
      commit('addNe', element);
    }
  }
})
