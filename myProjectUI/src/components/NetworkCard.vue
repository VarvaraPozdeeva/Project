<template>
  <v-container fluid grid-list-lg >
    <v-layout row wrap>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title primary-title>
            <div>
              <h3 class="headline mb-0">Network element </h3>
              <div class="text-xs-left"> name: {{element.name}}</div>
            </div>
          </v-card-title>
          <v-divider light></v-divider>
          <v-card-actions>
            <v-btn flat color="indigo darken-1"
                   :to="'/network-elements/' + element.id"  left>
              Details
            </v-btn>
            <v-btn flat  color="indigo darken-1"
                   :to="'/links/' + element.id">Links </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
    export default {
      props:['element'],
        name: "NetworkCard"
    }
</script>

<style scoped>

</style>
