<template>
  <div>
    <v-layout my-3>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title primary-title>
            <div>
              <h3 class="headline mb-0">Hardware component</h3>
              <div class="text-xs-left" >
                <div>Name: {{component.name}} </div>
                <div>Description: {{component.description}} </div>
                <div>Hardware rev: {{component['hardware-rev']}} </div>
                <div>Board id: {{component['board-id']}} </div>
                <div>Part number: {{component['part-number']}} </div>
              </div>
            </div>
          </v-card-title>
          <v-divider light></v-divider>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
  import {mapActions, mapState} from 'vuex'
    export default {
        name: "DetailsHw",
      methods:{
        ...mapActions(['getHWAction'])
      },
      computed: mapState({
        component: "hwComponent"
      }),
      created() {
        this.getHWAction(this.$route.params['idNE']);
      }
    }
</script>

<style scoped>

</style>
