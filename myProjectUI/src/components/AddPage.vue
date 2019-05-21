<template>
    <div >
      <h1 >Add Page</h1>
      <v-btn flat to="/add-ne" >Add network element </v-btn>
      <v-btn flat to="/add-link" >Add links </v-btn>
      <v-btn flat  to="/add-hw">Add hardware component</v-btn>
      <router-view></router-view>

    </div>
</template>

<script>
    import AddNe from "./AddNe";
    export default {
        name: "HomePage",
      components: {AddNe}
    }
</script>

<style scoped>

</style>
