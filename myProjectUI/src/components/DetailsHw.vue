<template>
  <div>
    <v-layout align-center justify-start column fill-height>
    <h2 ><strong class="red--text text--lighten-1">{{error}}</strong></h2>
    <v-layout my-3 v-if="flag == true">
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title primary-title>
            <div>
              <h3 class="headline mb-0">Hardware component</h3>
              <div class="text-xs-left" >
                <div>Name: {{component.name}} </div>
                <div>Description: {{component.description}} </div>
                <div>Hardware rev: {{component['hardware-rev']}} </div>
                <div>Board id: {{component['board-id']}} </div>
                <div>Part number: {{component['part-number']}} </div>
              </div>
            </div>
          </v-card-title>
          <v-btn flat  color="indigo darken-1" @click="deleteHW">
            Delete </v-btn>
          <v-divider light></v-divider>
        </v-card>
      </v-flex>
    </v-layout>
    <v-btn v-else @click="addHW">Add hardware component</v-btn>
    </v-layout>
  </div>
</template>

<script>
  import {mapActions, mapState} from 'vuex'
    export default {
        name: "DetailsHw",
      data(){
          return{
            error:'',
            flag: true
          }
      },
      methods:{
        ...mapActions(['getHWAction', "deleteHWAction"]),
        deleteHW() {
          const id = this.$route.params['idNE']
          this.deleteHWAction(id);
          console.log('deleted hw');
          this.$router.push({name: 'netElem', params: {id: id}})
        },
        addHW(){
          const id  = this.$route.params['idNE']
          this.$router.push({name: 'addHW', params:{idNE:id}});
        }
      },
      computed: mapState({
        component: "hwComponent"
      }),
      created() {
        this.getHWAction(this.$route.params['idNE']).then(res=>{}, error=>{
          this.error = error.bodyText;
          this.flag = false;
        });
      }
    }
</script>

<style scoped>

</style>
