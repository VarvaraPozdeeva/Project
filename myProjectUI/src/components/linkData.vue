<template>
  <v-card flat>
    <v-flex xs12>
      <v-card>
        <v-layout align-center justify-space-around   row fill-height>
          <div  >Name  </div>
          <div>NameA </div>
          <div>NameZ </div>
          <div>InA </div>
          <div>InZ</div>
          <div></div>
        </v-layout>
      </v-card>
      <v-card  v-for="link in links" :key="link.id" :link="link">
        <v-layout align-center justify-space-around   row fill-height>
          <v-btn flat :to="'/links/' + link.id" :link="link"> name{{}}  </v-btn>
          <div> {{link['a-interface']}} </div>
          <div> {{link['z-interface']}} </div>
          <div>{{link['a-ne']}} </div>
          <div>{{link['z-ne']}}  </div>
          <v-btn flat>delete</v-btn>
        </v-layout>
      </v-card>
    </v-flex>

  </v-card>
</template>

<script>
  import {mapActions} from 'vuex'
    export default {
        name: "linkData",
      methods:{
          ...mapActions(["getLinksAction"])
      },
      computed: {
        links() {
          console.log(this.$store.state.links);
          return this.$store.state.links
        }
      },
      created() {
        this.getLinksAction();
      }
    }
</script>

<style scoped>

</style>
