<template>
  <div>
    <v-layout align-center justify-start column fill-height>
      <v-btn v-if="flag ==false" @click="addInterfaces">Add interfaces</v-btn>
    <interface-card v-else v-for="card in interfaces"
                    :key="card.id"
                    :interface="card"/>
    </v-layout>
  </div>
</template>

<script>
    import InterfaceCard from "./InterfaceCard";
    import {mapActions, mapState} from 'vuex'
    export default {
        name: "Interfaces",
      methods:{
        ...mapActions(['getInterfaceAction']),
        addInterfaces(){
          const id = this.$route.params['idNE']
          this.$router.push({name:'addInter', params:{idNE:id}})
        }
      },
      computed:{
          ...mapState({
            interfaces: "interfaces"
          }),
        flag(){
            return (this.$store.state.interfaces.length !==0)
        }
      },
      created() {
        this.getInterfaceAction(this.$route.params['idNE']);
      },
      components: {InterfaceCard}
    }
</script>

<style scoped>

</style>
