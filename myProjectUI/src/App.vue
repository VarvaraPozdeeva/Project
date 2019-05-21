<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title>Network system</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items >
        <v-btn flat to="/" >Home</v-btn>
        <v-btn flat to="/add" >Add</v-btn>
        <v-btn flat to="/links">Links </v-btn>
        <v-btn flat to="/network-elements">Network Elements</v-btn>
      </v-toolbar-items>
    </v-toolbar>

    <v-content>
      <router-view/>
    </v-content>

  </v-app>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
</style>
