<template>
  <v-container fluid grid-list-lg >
    <v-layout row wrap>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title primary-title>
            <div>
              <h3 class="headline mb-0">Network element </h3>
              <div class="text-xs-left"> name:    </div>
            </div>
          </v-card-title>
          <v-divider light></v-divider>
          <v-card-actions>
            <v-btn flat color="indigo darken-1" to="/network-elements/1" left>
              Details
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
    export default {
        name: "NetworkCard"
    }
</script>

<style scoped>

</style>
