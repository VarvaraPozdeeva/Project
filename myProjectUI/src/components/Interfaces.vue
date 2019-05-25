<template>
  <div>
    <interface-card v-for="card in interfaces"
                    :key="card.id"
                    :interface="card"/>
  </div>
</template>

<script>
    import InterfaceCard from "./InterfaceCard";
    import {mapActions, mapState} from 'vuex'
    export default {
        name: "Interfaces",
      methods:{
        ...mapActions(['getInterfaceAction'])
      },
      computed: mapState({
        interfaces: "interfaces"
      }),
      created() {
        this.getInterfaceAction(this.$route.params['idNE']);
      },
      components: {InterfaceCard}
    }
</script>

<style scoped>

</style>
