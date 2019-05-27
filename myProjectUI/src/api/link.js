import Vue from "vue";
const link = Vue.resource('http://localhost:8080/data/links{/id}');

export default {
  get: id=> link.get({id:id}),
  add: elem => link.save(elem)
}
