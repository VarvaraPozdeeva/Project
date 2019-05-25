<template>
  <div >
    <link-card v-for="card in links" :key="card.id" :link="card"></link-card>
  </div>
</template>

<script>
  import LinkCard from "./LinkCard";
  import {mapActions} from 'vuex'
    export default {
        name: "LinkPage",
      components: {LinkCard},
      methods:{
        ...mapActions(['getLinksAction'])
      },
      computed: {
        links(){
          console.log( this.$store.state.links);
          return this.$store.state.links
        }
      },
      created() {
        this.getLinksAction(this.$router.currentRoute.params['idNE']);
      }
    }
</script>

<style scoped>

</style>
