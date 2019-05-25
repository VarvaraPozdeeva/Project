<template>
  <div>
    <v-layout my-3>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title primary-title>
            <div>
              <h3 class="headline mb-0">Interface</h3>
              <div class="text-xs-left" >
                <div>Name: {{interface['name']}} </div>
                <div>Description:{{interface['description']}}  </div>
                <div>MAC-address: {{interface['mac-address']}} </div>
                <div>Ip-address: {{interface['ip-address']}} </div>
                <div>MTU: {{interface['mtu']}} </div>
                <div>Speed: {{interface['speed']}} </div>
                <div>Duplex: {{interface['duplex']}} </div>
                <div>Admin state: {{interface['admin-status']}} </div>
                <div>Port mode {{interface['port-mode']}} </div>
                <div>Vlans: {{interface['vlans']}} </div>
                <div>Sub-interfaces: {{interface['sub-interface']}} </div>
              </div>
            </div>
          </v-card-title>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
  import { mapGetters, mapActions} from 'vuex'
    export default {
      name: "DetailInterface",
      methods:{
          ...mapActions(["getNetElemAction"])
      },
      created() {
        this.getNetElemAction(this.$route.params['idNE'])
      },
      computed: {
        ...mapGetters(["getInterfaceByIdIdNe"]),
        interface() {
          return this.getInterfaceByIdIdNe(this.$route.params['idI'], this.$route.params['idNE'])
        }
      }
    }
</script>

<style scoped>

</style>
