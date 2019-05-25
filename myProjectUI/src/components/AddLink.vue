<template>
  <v-container grid-list-xl id="scroll-target"
               class="scroll-y">
    <v-layout  align-center justify-start column fill-height  >
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name A interface" v-model="aInter"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name A network element"  v-model="zInter"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name Z interface" v-model="aNE"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name Z network element" v-model="zNE"
        ></v-text-field>
      </v-flex>

    </v-layout>

    <v-btn @click="addLink">Add</v-btn>
  </v-container>
</template>

<script>
    export default {
        name: "AddLink",
      data(){
          return{
            aInter:'',
            zInter:'',
            aNE:'',
            zNE:''
        }
      },
       methods:{
          addLink(){
            const link ={
              "a-interface": this.aInter,
              "a-ne": this.zInter,
              "z-interface": this.aNE,
              "z-ne": this.zNE
            };
            this.$http.post('http://localhost:8080/data/links', link)
              .then(response=>{
                console.log('')
                },
                error=>{
                console.log(error.bodyText)
              });
          }
       }
    }
</script>

<style scoped>

</style>
