<template>
    <div>
      <network-card v-for="element in netElements "
                    :key="element.id"
                    :element="element"/>
    </div>
</template>

<script>
    import NetworkCard from "./NetworkCard";
    import {mapActions, mapState} from 'vuex'

    export default {
      name: "NePage",
      components: {NetworkCard},
      methods:{
        ...mapActions(['getDataAction'])
      },
      computed: mapState({
        netElements: "netElements"
      }),
      created() {
        this.getDataAction();
      }
    }
</script>

<style scoped>

</style>
