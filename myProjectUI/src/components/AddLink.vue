<template>
  <v-container grid-list-xl id="scroll-target"
               class="scroll-y">
    <v-layout  align-center justify-start column fill-height  >
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name A interface"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name A network element"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name Z interface"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name Z network element"
        ></v-text-field>
      </v-flex>

    </v-layout>

    <v-btn>Add</v-btn>
  </v-container>
</template>

<script>
    export default {
        name: "AddLink"
    }
</script>

<style scoped>

</style>
