import Vue from 'vue'

const netElem = Vue.resource('http://localhost:8080/data/network-elements{/id}')

export default {
  add: elem => netElem.save({}, elem),
  update: elem => netElem.update({id: elem.id}, elem),
  remove: id => netElem.remove({id}),
  get: netElem.get(),
  getI: id =>  Vue.resource('http://localhost:8080/data/interfaces/'+ id).get(),
  getHW: id => Vue.resource('http://localhost:8080/data/hardware-components/'+ id).get(),
  getLinks: id => Vue.resource('http://localhost:8080/data/links/'+ id).get(),
  getNetElem: id => Vue.resource('http://localhost:8080/data/network-elements/'+ id).get()
}
