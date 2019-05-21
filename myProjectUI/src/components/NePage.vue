<template>
    <div>
      <network-card v-for="cards in 5" :key="cards"></network-card>
    </div>
</template>

<script>
    import NetworkCard from "./NetworkCard";
    export default {
        name: "NePage",
      components: {NetworkCard}
    }
</script>

<style scoped>

</style>
