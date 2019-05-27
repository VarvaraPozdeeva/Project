<template>
  <div >
    <v-layout align-center justify-start column fill-height>
      <v-btn v-if="flag ==false" @click="addLink">Add link</v-btn>
    <link-card v-else v-for="card in links" :key="card.id" :link="card"></link-card>
    </v-layout>
  </div>

</template>

<script>
  import LinkCard from "./LinkCard";
  import {mapActions} from 'vuex'
    export default {
        name: "LinkPage",
      components: {LinkCard},

      methods:{
        ...mapActions(['getLinksAction', "getDataAction"]),
        addLink(){
          this.$router.push({name: 'addLink'});
        }
      },
      computed: {
        links(){
          console.log( this.$store.state.links);
          return this.$store.state.links
        },
        flag(){
          return (this.$store.state.links.length !== 0)
        }
      },
      created() {
        this.getDataAction();
        this.getLinksAction(this.$route.params['idNE']);
      }
    }
</script>

<style scoped>

</style>
