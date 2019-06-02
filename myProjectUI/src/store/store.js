import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state:{
    netElements:[],
    netElement:{},
    newNE:{},
    newHW:{},
    interfaces:[],
    subInters:[],
    Ainterfaces:[],
    Zinterfaces:[],
    hwComponent:{},
    links:[],
    linksNE:[],
    path:'http://localhost:8080/data/'
  },
  getters:{
    getElemByName: state => name => {
      return state.netElements.find(e => e.name === name).id;
    },
    //getInterfaces:state=>{return state.interfaces},
    getNetElements:state=>{return state.netElements},
    getNetElement:state=>{return state.netElement},
    getHwComponent:state=>{return state.hwComponent},
    getLinks:state=>{return state.links},
    getLinksByID:state=>id=>{console.log(id);console.log(state. links); return state.links.find(l=> l.id === id)},


    getInterfaceByIdIdNe: state=>(idI, idNE)=> {
      const elem = state.netElements.find(e=>e.id === idNE)
      console.log(elem)
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
    deleteLink(state, link){
      const index = state.links.findIndex( e=> e.id === link.id);
      state.links = [
        ...state.links.slice(0, index),
        ...state.links.slice(index+1)
      ];
    },
    deleteInter(state, inter){
      const index = state.interfaces.findIndex( e=> e.id === inter.id);
      state.interfaces = [
        ...state.interfaces.slice(0, index),
        ...state.interfaces.slice(index+1)
      ];
    },
    getSubInter(state, sub){
      state.subInters = sub
    },
    addInter(state, inter){
      state.interfaces = [...state.interfaces, inter]
    },
    addLink(state, l){
      l.name = l['a-ne'] + '_'+ l['a-interface'] +'__'+ l['z-ne'] + '_'+ l['z-interface']
      state.links = [ ...state.links, l]
    },
    addLinkNE(state, l){
      l.name = l['a-ne'] + '_'+ l['a-interface'] +'__'+ l['z-ne'] + '_'+ l['z-interface']
      state.linksNE = [ ...state.linksNE, l]
    },
    getInterfaces(state, interfaces){
      state.interfaces = interfaces
    },
    getAInterfaces(state, interfaces){
      state.Ainterfaces = interfaces
    },
    getZInterfaces(state, interfaces){
      state.Zinterfaces = interfaces
    },
    getHW(state, component){
      state.hwComponent = component
    },
    getLinks(state, links){
      links.forEach(l=>{
        l.name = l['a-ne'] + '_'+ l['a-interface'] +'__'+ l['z-ne'] + '_'+ l['z-interface']
      })
      state.links = links
    },
    getLinksNE(state, links){
      links.forEach(l=>{
        l.name = l['a-ne'] + '_'+ l['a-interface'] +'__'+ l['z-ne'] + '_'+ l['z-interface']
      })
      state.linksNE = links
    },
    getNetElem(state, elem){
      state.netElement = elem
    },
    addNewNE(state, ne){
      state.newNE = ne;
    },
    addNewHW(state, hw){
      state.newHW = hw;
    },
    addSubInter(state, sub){
      state.subInters = [...state.subInters,  sub];
    }
  },
  actions:{
     async getDataAction({commit}){
       const result = await Vue.http.get(this.state.path + 'network-elements')
       if( result.status === 200 ) {
         const data = await result.json()
         commit('getData', data);
       }
    },
    async getInterfaceAction({commit}, id){
      const result = await Vue.http.get(this.state.path + 'interfaces/' + id);
      const data =   await  result.json()
      commit('getInterfaces', data) ;
    },
    async getAInterfaceByNameNEAction({commit}, name){
      const id = this.state.netElements.find(e=>e.name === name).id
      const result = await Vue.http.get(this.state.path + 'interfaces/' + id);
      const data =   await  result.json()
      commit('getAInterfaces', data) ;
    },
    async getZInterfaceByNameNEAction({commit}, name){
      const id = this.state.netElements.find(e=>e.name === name).id
      const result = await Vue.http.get(this.state.path + 'interfaces/' + id);
      const data =   await  result.json()
      commit('getZInterfaces', data) ;
    },
    async getHWAction({commit}, id){
      const result = await Vue.http.get(this.state.path + 'hardware-components/' + id);
      const data =   await  result.json()
      if(result.status === 200 ) {
        commit('getHW', data);

      }
      else{
        commit('deleteHW');
      }
    },
    async getLinksAction({commit}){
      const result = await Vue.http.get(this.state.path + 'links');
      const data =   await  result.json()
      console.log("get link" + data)
      commit('getLinks', data) ;
    },
    async getLinksByIdAction({commit}, id){
      const result = await Vue.http.get(this.state.path + 'links/' + id);
     const data =   await  result.json()
      console.log(data);
      commit('getLinksNE', data) ;
    },
    async getNetElemAction({commit}, id){
      const result = await Vue.http.get(this.state.path + 'network-elements/' + id)
      const data =   await  result.json()
      commit('getNetElem', data) ;
    },

    async getSubByIdInt({commit},id){
      const result = await Vue.http.get(this.state.path + 'sub-interfaces/' + id);
      const data = await  result.json();
      commit('getSubInter', data);
    },

    async addSubInter({commit},  sub){
       const id = sub.idIn;
       delete sub.idIn
      const result = await Vue.http.post(this.state.path + 'sub-interfaces/' + id, sub);
      if(result.status === 200) {
        console.log(result.body);
        commit('addSubInter', result.body);
      }
    },

    async addNeAction({commit}, element){
      const resultNe = await Vue.http.post(this.state.path + 'network-elements', element.netElem)
       if(resultNe.status === 200) {
         commit('addNe', resultNe.body);
         const result = await Vue.http.post(this.state.path + 'hardware-components/' + resultNe.body.id, element.hw)
         if(result.status === 200) {
           commit('addHW', result.body);
         }
       }
    },
    async addLinkAction({commit}, link){
      const result = await Vue.http.post(this.state.path + 'links', link);
      if(result.status === 200) {
        console.log(result.body);
        commit('addLink', result.body);
      }
    },
    async addInterAction({commit}, inter){
      const id = inter.idNE
      delete inter.idNE
      const result = await Vue.http.post(this.state.path + 'interfaces/' + id, inter);
      console.log(result);
      if(result.status === 200) {
        commit('addInter', inter);
      }
    },
    async deleteNEAction({commit}, id){
      const result = await Vue.http.delete(this.state.path + 'network-elements/' + id);
      console.log(result);
      if(result.status === 200) {
        commit('deleteNE', result.body);
      }
      else{
        return result.statusText
      }
    },

    async deleteHWAction({commit}, id){
      const result = await Vue.http.delete(this.state.path + 'hardware-components/' + id);
      console.log(result);
      if(result.status === 200) {
        commit('deleteHW', result.body);
      }
      else{
        return result.statusText
      }
    },
    async deleteLinkAction({commit}, link){
       const id = link.id
      const result = await Vue.http.delete(this.state.path + 'links/' + id);

      if(result.status === 200) {
        commit('deleteLink', result.body);
      }
      else{
        return result.statusText
      }
    },
    async deleteInterfaceAction({commit}, id){
      const result = await Vue.http.delete(this.state.path + 'interfaces/' + id);

      console.log(result);
      if(result.status === 200) {
        commit('deleteInter', result.body);
      }
      else{
        return result.statusText
      }
    },
    addNe({commit}, ne){
       commit('addNewNE', ne)
    },
    addHw({commit}, hw){
       commit('addNewHW', hw)
    }
  }
})
