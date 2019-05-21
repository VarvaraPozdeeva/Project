<template>
    <div>
      <v-layout my-3>
        <v-flex xs12 sm6 offset-sm3>
          <v-card>
            <v-card-title primary-title>
              <div>
                <h3 class="headline mb-0">Network element</h3>
                <div class="text-xs-left" >
                  <div>Name:  </div>
                  <div>Type:  </div>
                  <div>Vendor:  </div>
                  <div>Ip-address:  </div>
                  <div>OS-version:  </div>
                  <div>Returned to rom:  </div>
                  <div>System booted time:  </div>
                  <div>Last sync:  </div>
                </div>
              </div>
            </v-card-title>
            <v-divider light></v-divider>
            <v-card-actions>
              <v-btn flat   to="/hw-components/1">
                Hw-component
              </v-btn>
              <v-btn flat   to="/interfaces/1">
                Interfaces
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </div>
</template>

<script>
    export default {
        name: "DetailsNe"
    }
</script>

<style scoped>

</style>
