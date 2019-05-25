<template>
    <div>
      <v-layout my-3>
        <v-flex xs12 sm6 offset-sm3 class="ms-center">
          <v-card>
            <v-card-title primary-title>
              <div>
                <h3 class="headline mb-0">Link {{id}}</h3>
              </div>

            </v-card-title>

            <v-container fluid grid-list-xs >
              <v-layout row wrap center>
                <v-btn flat color="indigo darken-1" :to="'/interfaces/'+idNE+'/' ">
                  A- Interface
                </v-btn>
                <v-btn flat color="indigo darken-1" to="/interfaces/1/1">
                  Z - Interface
                </v-btn>
              </v-layout>
            </v-container>
          </v-card>
        </v-flex>
      </v-layout>
    </div>
</template>

<script>
    export default {
        name: "DetailsLink",
      data(){
          const id = this.$router.currentRoute.params['idNE'];
        return{
          idNE: this.$route.params['idNE']
        }
      },
      computed:{
          idNEZ(){
            this.$store.getters.getElemByName()
          }
      }
    }
</script>

<style scoped>

</style>
