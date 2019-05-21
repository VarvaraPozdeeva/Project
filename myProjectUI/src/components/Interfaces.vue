<template>
  <div>
    <interface-card v-for="card in 5" :key="card"></interface-card>
  </div>
</template>

<script>
    import InterfaceCard from "./InterfaceCard";
    export default {
        name: "Interfaces",
      components: {InterfaceCard}
    }
</script>

<style scoped>

</style>
