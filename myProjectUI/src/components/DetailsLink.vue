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

                  <v-layout ma-3 v-for="comp in 2" :key="comp" >
                    <v-flex >
                      <v-card color="indigo lighten-4">
                        <v-card-title primary-title>
                          <div>
                            <h3 class="headline mb-0">{{comp == 1 ? 'A' : 'Z'}}-component</h3>
                          </div>
                        </v-card-title>
                        <v-card-actions>
                          <v-btn flat color="indigo darken-1" to="/interfaces/1/1">
                              Interface
                          </v-btn>
                          <v-btn flat color="indigo darken-1" to="/network-elements/1">
                              Network element
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-flex>
                  </v-layout>
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
        return{
          id: this.$route.params['id']
        }
      }
    }
</script>

<style scoped>

</style>
