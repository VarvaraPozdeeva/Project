<template>
        <v-card  class="black--text">
          <v-card-title primary-title>
              <h3 class="headline mb-0">Link "{{ln.name}}"</h3>
          </v-card-title>
          <table >
          <tr><td class="prop"><b>Network element A </b> </td><td>{{ln['a-ne']}} </td></tr>
          <tr><td class="prop" ><b>Interface A</b>  </td><td>{{ln['a-interface']}}  </td></tr>
          <tr><td class="prop"><b>Network element Z </b></td><td>{{ln['z-ne']}}  </td></tr>
          <tr><td class="prop"><b>Interface Z</b> </td><td>{{ln['z-interface']}}  </td></tr>
        </table>

        </v-card>
</template>

<script>
  import {mapActions} from 'vuex'
    export default {

      data(){
        return{
          ln:{}
        }
      },
      computed:{
        ln1(){
          return this.$store.getters.getLinksByID(this.$route.params['id'])
        }
      },
      methods:{
        ...mapActions(["getLinksAction"]),
        init(){
          console.log('init link card')
          this.ln = this.$store.getters.getLinksByID(this.$route.params['id'])
        }
      },
      created() {
        this.getLinksAction();
        this.init();
        console.log(this.ln)
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
