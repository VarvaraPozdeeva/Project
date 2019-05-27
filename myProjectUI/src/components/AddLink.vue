<template>
  <v-container grid-list-xl id="scroll-target"
               class="scroll-y">
    <v-layout  align-center justify-start column fill-height  >
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name A interface" :rules="aInterRule" v-model="link.aInter"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name A network element" :rules="aNERule"   v-model="link.zInter"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name Z interface" v-model="link.aNE"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name Z network element" v-model="link.zNE"
        ></v-text-field>
      </v-flex>

    </v-layout>
    <p ><strong class="red--text text--lighten-1">{{error}}</strong></p>
    <v-btn @click="addLink">Add</v-btn>
  </v-container>
</template>

<script>
  import {mapActions} from 'vuex'

    export default {
        name: "AddLink",
      data(){
          return{
            link: {
              aInter:'',
              zInter:'',
              aNE:'',
              zNE:''
            },
            aInterRule:[
              v => !!v || 'Enter a-interface please'
            ],
            aNERule:[
              v => !!v || 'Enter a-ne please'
            ],
            error:''
        }
      },
       methods:{
         ...mapActions(["addLinkAction"]),
        async addLink(){
            const link ={
              "a-interface": this.link.aInter,
              "a-ne": this.link.zInter,
              "z-interface": this.link.aNE,
              "z-ne": this.link.zNE
            };
            this.addLinkAction(link).then(
              res=> {
                console.log('')
                this.error =''
              },
              error=>{
                console.log(error.bodyText)
                this.error = error.bodyText
              })
          }
       }
    }
</script>

<style scoped>

</style>
