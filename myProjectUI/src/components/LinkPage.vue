<template>
  <div >
    <link-card v-for="card in 5" :key="card"></link-card>
  </div>
</template>

<script>
  import LinkCard from "./LinkCard";
    export default {
        name: "LinkPage",
      components: {LinkCard}
    }
</script>

<style scoped>

</style>
