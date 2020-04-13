<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-card>
      <v-card-title primary-title>
          <h3 class="headline mb-0">Network element</h3>
      </v-card-title>
      <table >
        <tr><td class="prop"><div> <b>Name</b></div></td><td>{{ne.name}}</td></tr>
        <tr><td class="prop"><b>Type</b></td><td>{{ne.type}}</td></tr>
        <tr><td class="prop"><b>Vendor</b></td><td>{{ne.vendor}}</td></tr>
        <tr><td class="prop"><b>Ip-address</b></td><td>{{ne['ip-address']}}</td></tr>
        <tr><td class="prop"><b>OS-version</b></td><td>{{ne['os-version']}}</td></tr>
        <tr><td class="prop"><b>Returned to rom</b></td><td> {{ne['returned-to-rom']}}</td></tr>
        <tr><td class="prop"><b>System booted time</b></td><td> {{ne['system-booted-time']}}</td></tr>
        <tr><td class="prop"><b>Last sync</b></td><td>{{ne['last-sync']}}</td></tr>
      </table>

    </v-card>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'
  export default {
    name: "NetworkElement",
    data(){
      return{
        ne:{
          "ip-address":'',
          "last-sync": '',
          "name": '',
          "os-version": '',
          "returned-to-rom": '',
          "system-booted-time": '',
          "type": '',
          "vendor": ''
        }
      }
    },
    created() {
      this.init()
    },
    methods:{
      ...mapActions(["getNetElemAction"]),
      init(){
        const id  = this.$route.params['id'];
        //this.getNetElemAction(id);

        this.$http.get(this.$store.state.path + 'network-elements/' + id)
          .then(result => result.json()
            .then(data=>{
              this.ne = data;
              this.$store.state.netElement =data;
            } ) )

        //setTimeout(() => {this.ne = this.$store.state.netElement}, 100);
      },
    }
  }
</script>

<style scoped>
  /*tr:nth-child(even) {background-color: white }*/
  /*tr{*/
  /* // background-color: #f2f2f2;*/

  /*}*/
  table{
    border-collapse: collapse;
    width: 100%;
  }
  .prop{
    width: 30%;
  }
  td, th{
    padding: 8px 8px 8px 60px;
    border-bottom: 1px solid #ddd;
  }
</style>
