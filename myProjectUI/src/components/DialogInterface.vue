<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform" xmlns:v-scroll="">

  <v-layout row justify-center>
    <v-dialog  v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Add Interface</v-btn>
      </template>

      <v-card>
        <v-card-title> <div class="headline">Interface</div></v-card-title>
        <v-card-text>
          <strong class="red--text text--lighten-1">{{error}}
          </strong></v-card-text>
        <v-layout  column >
          <v-card flat>
            <v-container id="scroll-target" style="max-height: 400px"  class="scroll-y" >
              <v-layout v-scroll:#scroll-target column >

                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Name" v-model="interface.name"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Description"   v-model="interface.description"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Duplex" v-model="interface.duplex"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Ip-address" v-model="interface.ipAddress"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="MAC-address" v-model="interface.macAdress"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Mtu" v-model="interface.mtu"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Port mode" v-model="interface.portMode"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Speed" v-model="interface.speed"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Admin state" v-model="interface.adminState"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Vlans" v-model="interface.vlans"
                  ></v-text-field>
                </v-flex>

              </v-layout>
            </v-container>
          </v-card>
        </v-layout>

        <v-layout align-center justify-end row fill-height>
          <v-btn  color="primary" @click="save">Save</v-btn>
          <v-btn color="primary" @click="close">Cancel</v-btn>
        </v-layout>
      </v-card>
    </v-dialog>
  </v-layout>


</template>

<script>
  import {mapActions} from 'vuex'
  export default {
    data() {
      return {
        error:'',
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
          vlans:''
        },
        dialog: false,
        e1: 0
      }
    },
    methods:{
      ...mapActions(["addInterAction"]),
      save(){

        const inter = {
          "admin-status": this.subInterface.adminState,
          "description":this.subInterface.description ,
          "duplex": this.subInterface.duplex,
          "ip-address": this.subInterface.ipAddress,
          "mac-address": this.subInterface.macAdress,
          "mtu": this.subInterface.mtu,
          "name": this.subInterface.name,
          "port-mode": this.subInterface.portMode,
          "speed": this.subInterface.speed,
          idNE: this.$route.params['id']
        };
        inter.vlans = this.subInterface.vlans.split(/[\s,]+/)
        this.addInterAction(inter).then(
          res=>{
            console.log(res)
            this.dialog = false;
            this.e1 = 1;
            this.clear();
            this.error =''
            this.$emit('addInter')
          },
          error=>{
            this.error = error.bodyText
            console.log(error);
          })
      },
      clear(){
        this.subInterface.adminState=''
        this.subInterface.description =''
        this.subInterface.duplex=''
        this.subInterface.ipAddress=''
        this.subInterface.macAdress=''
        this.subInterface.mtu=''
        this.subInterface.name=''
        this.subInterface.portMode=''
        this.subInterface.speed=''
        this.subInterface.vlans =''

      },
      close(){
        this.dialog = false;
        this.e1 = 1;
        this.clear();
        this.error =''
      }
    }
  }
</script>

<style scoped>

</style>
