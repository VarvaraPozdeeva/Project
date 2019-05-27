import Vue from 'vue'

const interfaces = Vue.resource('http://localhost:8080/data/interfaces{/id}')

export default {
  get: id => interfaces.get({id:id}),
  add: (inter, id) => interfaces.save({id:id}, inter)
}
