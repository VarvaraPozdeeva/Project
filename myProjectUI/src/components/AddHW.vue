<template>
  <v-container grid-list-xl id="scroll-target"
               class="scroll-y">
    <v-layout  align-center justify-start column fill-height  >
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Description"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Hardware rev"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Board id"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Part number"
        ></v-text-field>
      </v-flex>
    </v-layout>
    <v-btn>Add</v-btn>
  </v-container>
</template>

<script>
    export default {
        name: "AddHW"
    }
</script>

<style scoped>

</style>

