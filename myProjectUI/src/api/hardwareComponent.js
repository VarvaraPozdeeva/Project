import Vue from 'vue'

const hwComponent = Vue.resource('http://localhost:8080/data/hardware-components{/id}');

export default {
  getHwById: id =>  hwComponent.get({id: id}),
  add: (component, id) => hwComponent.save({id:id},component),
  remove: (id) => hwComponent.remove({id:id})
}
