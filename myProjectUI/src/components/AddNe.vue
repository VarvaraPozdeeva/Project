<template>
    <v-container grid-list-xl id="scroll-target"
                 class="scroll-y">
      <v-layout  align-center justify-start column fill-height  >
        <v-flex xs20 sm6 md3>
          <v-text-field
            label="Name" v-model="element.nameNe"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="Type" v-model="element.type"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="Vendor" v-model="element.vendor"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="IP-address" v-model="element.ipAddress"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="OS-version" v-model="element.osVersion"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="Returned to rom" v-model="element.RetToRom"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="System booted time" v-model.number="element.SysBootTime"
          ></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
          <v-text-field
            label="Last sync" v-model.number="element.LSync"
          ></v-text-field>
        </v-flex>
      </v-layout>

      <v-btn @click="addNe">Add</v-btn>
    </v-container>
</template>

<script>
  import {mapActions} from 'vuex'
  export default {
    data(){
      return{
        element:{
          nameNe:'',
          type:'',
          vendor:'cisco',
          ipAddress:'',
          osVersion:'',
          RetToRom:'',
          SysBootTime:'45554215',
          LSync:'145454556'
        }
      }
    },
    methods:{
      ...mapActions(["addNeAction"]),
      async addNe(){
        const ne ={
          "ip-address": this.element.ipAddress,
          "last-sync": this.element.LSync,
          "name": this.element.nameNe,
          "os-version": this.element.osVersion,
          "returned-to-rom": this.element.RetToRom,
          "system-booted-time": this.element.SysBootTime,
          "type": this.element.type,
          "vendor": this.element.vendor
        }
        const res =  await this.addNeAction(ne)
          console.log(res);
        const data = res.body;
        console.log(data.id);
        this.$router.push({name: 'addHW', params:{idNE: data.id}});

      }
    }
  }
</script>

<style scoped>

</style>
