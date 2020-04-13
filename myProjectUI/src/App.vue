<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title>Network inventory</v-toolbar-title>
      <v-divider class="mx-2" inset vertical ></v-divider>
      <v-toolbar-items >
        <v-btn flat to="/"  :class="{active :flagHome}"  @click="flagHome=true" >Home</v-btn>
        <v-btn flat to="/inventory" :class="{active :flagHome === false}" @click="flagHome=false">Inventory</v-btn>
      </v-toolbar-items>
    </v-toolbar>

    <v-content>
      <router-view/>
    </v-content>

  </v-app>
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      flagHome:true
    }
  }
}
</script>

<style scoped>
  .active{
    background-color: #dddddd;
  }
</style>
