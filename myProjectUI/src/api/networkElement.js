import Vue from 'vue'

const netElem = Vue.resource('http://localhost:8080/data/network-elements{/id}')

export default {
  add: elem => netElem.save({}, elem),
  update: elem => netElem.update({id: elem.id}, elem),
  remove: id => netElem.remove({id}),
  get:  netElem.get(),
  getNetElem: id => netElem.get({id: id})
}
