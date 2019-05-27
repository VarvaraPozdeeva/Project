import Vue from 'vue'
import Vuex from 'vuex'
import neApi from '../api/networkElement'
import hwApi from '../api/hardwareComponent'
import linkApi from '../api/link'
import interApi from '../api/interfaces'

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
      console.log(state.netElements)
    },
    addNe(state, element){
      state.netElements = [ ...state.netElements, element]
    },
    addHW(state, component){
      state.hwComponent = component
    },
    deleteNE(state, element){
      const index = state.netElements.findIndex(e=>e.id === element.id);
      if(index>-1){
        state.netElements = [ ...state.netElements.slice(0,index ),
        ...state.netElements.slice(index+1)];
      }
    },
    deleteHW(state){
      state.hwComponent ={}
    },
    addInter(state, inter){
      state.interfaces = [...state.interfaces, inter]
    },
    addLink(state, link){
      state.links = [ ...state.links, link]
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
       console.log(data)
       commit('getData', data) ;
    },
    async getInterfaceAction({commit}, id){
      const result = await interApi.get(id)
      const data =   await  result.json()
      commit('getInterfaces', data) ;
    },
    async getHWAction({commit}, id){
      const result = await hwApi.getHwById(id)
      const data =   await  result.json()
      if(result.status === 200 ) {
        commit('getHW', data);
      }
      else{
        commit('deleteHW');
      }
    },
    async getLinksAction({commit}, id){
      const result = await linkApi.get(id)
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
        //console.log(result);
       if(result.status === 200) {
         commit('addNe', element);
         return result;
       }
    },
    async addLinkAction({commit}, link){
       console.log(link);
      const result = await linkApi.add(link);
      console.log(result);
      if(result.status === 200) {
        commit('addLink', link);
      }
    },
    async addHWAction({commit}, component){
       console.log(component)
      const id = component.idNE
      delete component.idNE
      console.log(component)
      const result = await hwApi.add(component, id)
      console.log(result);
      if(result.status === 200) {
        commit('addHW', component);
      }
    },
    async addInterAction({commit}, inter){
      const id = inter.idNE
      delete inter.idNE
      const result = await interApi.add(inter, id)
      console.log(result);
      if(result.status === 200) {
        commit('addInter', inter);
      }
    },
    async deleteNEAction({commit}, id){
      const result = await neApi.remove(id)
      console.log(result);
      if(result.status === 200) {
        commit('deleteNE', result.body);
      }
      else{
        return result.statusText
      }
    },
    async deleteHWAction({commit}, id){
      const result = await hwApi.remove(id)
      console.log(result);
      if(result.status === 200) {
        commit('deleteHW', result.body);
      }
      else{
        return result.statusText
      }
    }
  }
})
