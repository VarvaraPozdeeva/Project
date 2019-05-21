<template>
    <v-container grid-list-xl id="scroll-target"
                 class="scroll-y">
      <v-layout  align-center justify-start column fill-height  >
        <v-flex xs20 sm6 md3>
          <v-text-field
            label="Name"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="Type"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="Vendor"
            placeholder="cisco"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="IP-address"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="OS-version"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="Returned to rom"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="System booted time"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="Last sync"
          ></v-text-field>
        </v-flex>
      </v-layout>

      <v-btn>Add</v-btn>
    </v-container>
</template>

<script>
  export default {
  }
</script>

<style scoped>

</style>
