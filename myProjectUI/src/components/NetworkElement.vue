<template>
  <div>
    <v-card>
      <v-card-title primary-title>
        <div>
          <h3 class="headline mb-0">Network element</h3>
          <div class="text-xs-left" >
            <div> <b>Name</b>: {{ne.name}}</div>
            <div> <b>Type:</b> {{ne.type}}</div>
            <div><b>Vendor:</b> {{ne.vendor}}</div>
            <div><b>Ip-address:</b> {{ne['ip-address']}}</div>
            <div><b>OS-version:</b> {{ne['os-version']}}</div>
            <div><b>Returned to rom:</b> {{ne['returned-to-rom']}}</div>
            <div><b>System booted time:</b> {{ne['system-booted-time']}}</div>
            <div> <b>Last sync:</b> {{ne['last-sync']}}</div>
          </div>
        </div>
      </v-card-title>
      <v-divider light></v-divider>

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
        this.getNetElemAction(id);
        setTimeout(() => {this.ne = this.$store.state.netElement}, 100);
      },
    }
  }
</script>

<style scoped>

</style>
