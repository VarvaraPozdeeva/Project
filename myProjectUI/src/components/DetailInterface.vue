<template>
  <div>
    <v-layout my-3>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title primary-title>
            <div>
              <h3 class="headline mb-0">Interface</h3>
              <div class="text-xs-left" >
                <div>Name:  </div>
                <div>Description:  </div>
                <div>MAC-address:  </div>
                <div>Ip-address:  </div>
                <div>MTU:  </div>
                <div>Speed:  </div>
                <div>Duplex:  </div>
                <div>Admin state:  </div>
                <div>Port mode  </div>
                <div>Vlans:  </div>
                <div>Sub-interfaces:  </div>
              </div>
            </div>
          </v-card-title>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
    export default {
        name: "DetailInterface"
    }
</script>

<style scoped>

</style>
