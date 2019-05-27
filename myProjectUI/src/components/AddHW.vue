<template>
  <v-container grid-list-xl id="scroll-target"
               class="scroll-y">
    <v-layout  align-center justify-start column fill-height  >
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name" v-model="hwComponent.name"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Description" v-model="hwComponent.description"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Hardware rev" v-model="hwComponent.hwRev"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Board id" v-model="hwComponent.boardId"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Part number" v-model="hwComponent.partNum"
        ></v-text-field>
      </v-flex>
    </v-layout>
    <v-btn @click="addHW">Add</v-btn>
  </v-container>
</template>

<script>
  import {mapActions} from 'vuex'
    export default {
        name: "AddHW",
      data(){
          return{
            hwComponent:{
              name:'',
              description:'',
              hwRev:'',
              boardId:'',
              partNum:''
            }
          }
      },
      methods:{
          ...mapActions(["addHWAction"]),
        addHW(){
            const hw = {
              "board-id": this.hwComponent.boardId,
              "description": this.hwComponent.description,
              "hardware-rev": this.hwComponent.hwRev,
              "name": this.hwComponent.name,
              "part-number": this.hwComponent.partNum,
              idNE: this.$route.params['idNE']
            };
            console.log(this.$route.params['idNE'])
            this.addHWAction(hw);
        }

      }
    }
</script>

<style scoped>

</style>

