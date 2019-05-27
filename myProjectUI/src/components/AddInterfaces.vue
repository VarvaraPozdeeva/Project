<template>
  <v-container grid-list-xl id="scroll-target"
               class="scroll-y">
    <v-layout  align-center justify-start column fill-height  >
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Name" v-model="interface.name"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Description"   v-model="interface.description"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Duplex" v-model="interface.duplex"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Ip-address" v-model="interface.ipAddress"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="MAC-address" v-model="interface.macAdress"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Mtu" v-model="interface.mtu"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Port mode" v-model="interface.portMode"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Speed" v-model="interface.speed"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Admin state" v-model="interface.adminState"
        ></v-text-field>
      </v-flex>
      <v-flex xs12 sm6 md3>
        <v-text-field
          label="Vlans" v-model="interface.vlans"
        ></v-text-field>
      </v-flex>

    </v-layout>
    <p ><strong class="red--text text--lighten-1">{{error}}</strong></p>
    <v-btn @click="addInter">Add</v-btn>
  </v-container>
</template>

<script>
  import {mapActions}from 'vuex'
    export default {
        name: "AddInterfaces",
      data(){
          return{
            interface:{
              adminState:'',
              description: '',
              duplex: '',
              ipAddress:'',
              macAdress:'',
              mtu:'',
              name:'',
              portMode:'',
              speed:'',
              vlans:[]
            },
            error:''
          }
      },
      methods:{
          ...mapActions(["addInterAction"]),
          addInter(){

            const inter = {
              "admin-status": this.interface.adminState,
              "description":this.interface.description ,
              "duplex": this.interface.duplex,
              "ip-address": this.interface.ipAddress,
              "mac-address": this.interface.macAdress,
              "mtu": this.interface.mtu,
              "name": this.interface.name,
              "port-mode": this.interface.portMode,
              "speed": this.interface.speed,
              "vlans": this.interface.vlans,
              idNE: this.$route.params['idNE']
            };
            this.addInterAction(inter).then(
              res=>{
                console.log(res)
            },
              error=>{
                console.log(error);
              })
          }
      }
    }
</script>

<style scoped>

</style>
