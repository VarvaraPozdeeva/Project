<template>
  <div>

    <v-card>
      <v-card-title primary-title>
          <h3 class="headline mb-0">Hardware component</h3>
      </v-card-title>
      <table>
        <tr><td class="prop"><b>Name</b></td><td>{{component.name}} </td></tr>
        <tr><td class="prop"><b>Description</b> </td><td>{{component.description}} </td></tr>
        <tr><td class="prop"><b>Hardware rev</b></td><td>{{component['hardware-rev']}} </td></tr>
        <tr><td class="prop"><b>Board id</b></td><td>{{component['board-id']}} </td></tr>
        <tr><td class="prop"><b>Part number</b></td><td>{{component['part-number']}} </td></tr>
      </table>

    </v-card>
  </div>
</template>

<script>
  import {mapActions, mapState} from 'vuex'
  export default {
    name: "DetailsHw",
    data(){
      return{
        error:'',
        flag: true
      }
    },
    methods:{
      ...mapActions(['getHWAction'])
    },
    computed: mapState({
      component: "hwComponent"
    }),
    created() {
      this.getHWAction(this.$route.params['id']).then(res=>{}, error=>{
        this.error = error.bodyText;
        this.flag = false;
      });
    }
  }
</script>

<style scoped>
  table{
    border-collapse: collapse;
    width: 100%;
  }
  .prop{
    width: 30%;
  }
  td, th{
    padding: 8px;
    padding: 8px 8px 8px 60px;
    border-bottom: 1px solid #ddd;
  }
</style>
