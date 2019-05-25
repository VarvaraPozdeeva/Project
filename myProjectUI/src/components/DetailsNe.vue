<template>
    <div>
      <v-layout my-3>
        <v-flex xs12 sm6 offset-sm3>
          <v-card>
            <v-card-title primary-title>
              <div>
                <h3 class="headline mb-0">Network element</h3>
                <div class="text-xs-left" >
                  <div>Name: {{ne.name}}  </div>
                  <div>Type: {{ne.type}} </div>
                  <div>Vendor: {{ne.vendor}} </div>
                  <div>Ip-address: {{ne['ip-address']}}  </div>
                  <div>OS-version: {{ne['os-version']}}  </div>
                  <div>Returned to rom: {{ne['returned-to-rom']}} </div>
                  <div>System booted time: {{ne['system-booted-time']}}  </div>
                  <div>Last sync: {{ne['last-sync']}} </div>
                </div>
              </div>
            </v-card-title>
            <v-divider light></v-divider>
            <v-card-actions>
              <v-btn flat   color="indigo darken-1"  :to="'/hw-components/' + this.id ">
                Hw-component
              </v-btn>
              <v-btn flat   color="indigo darken-1"  :to="'/interfaces/' + this.id">
                Interfaces
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </div>
</template>

<script>
  import {mapActions, mapState} from 'vuex'
    export default {
        name: "DetailsNe",
      data(){
          return {
            id: this.$route.params['id']
          }
      },
      methods:{
        ...mapActions(['getNetElemAction'])
      },
      computed: mapState({
        ne: "netElement"
      }),
      created() {
        this.getNetElemAction(this.$route.params['id']);
      }
    }
</script>

<style scoped>

</style>
