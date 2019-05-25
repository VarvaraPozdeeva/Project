<template>
  <v-container fluid grid-list-lg>
    <v-layout row wrap>
      <v-flex xs12 sm6 offset-sm3 >
        <v-card  class="black--text">
          <v-card-title primary-title>
            <div>
              <h3 class="headline mb-0">Link {{link.id}}</h3>
              <div class="text-xs-left" > Interface A:   {{link['a-interface']}} </div>
              <div class="text-xs-left" > Interface Z:   {{link['z-interface']}}  </div>
            </div>
          </v-card-title>
          <v-divider light></v-divider>
          <v-card-actions>
            <v-btn flat color="indigo darken-1" :to="'/interfaces/'+idNE+'/'+link.interfaceA.id" left>
             A-Interface
            </v-btn>
            <v-btn flat color="indigo darken-1" :to="'/interfaces/'+
            (this.$store.getters.getElemByName(link['z-ne']))+'/'+link.interfaceZ.id" left>
              Z-Interface
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
    export default {
      props:['link'],
      data(){
        return{
          idNE: this.$router.currentRoute.params['idNE']
        }
      }
    }
</script>

<style scoped>

</style>
