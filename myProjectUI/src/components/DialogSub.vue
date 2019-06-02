<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform" xmlns:v-scroll="">

  <v-layout row justify-center>
    <v-dialog  v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Add Sub Interface</v-btn>
      </template>

      <v-card>
        <v-card-title> <div class="headline">Sub-interface</div></v-card-title>
        <v-card-text><strong class="red--text text--lighten-1">{{error}}</strong></v-card-text>
        <v-layout  column >
          <v-card flat>
            <v-container id="scroll-target" style="max-height: 400px"  class="scroll-y" >
              <v-layout v-scroll:#scroll-target column >

                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Name" v-model="subInterface.name"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Description"   v-model="subInterface.description"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Encapsulation" v-model="subInterface.encapsulation"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Ip-address" v-model="subInterface.ipAddress"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="subnet-mask" v-model="subInterface.subMask"
                  ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field
                    label="Vlan" v-model="subInterface.vlan"
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
        subInterface:{
          description: '',
          ipAddress:'',
          name:'',
          vlan:'',
          encapsulation:'',
          subMask:''
        },
        dialog: false
      }
    },
    methods:{
      ...mapActions(["addSubInter"]),
      save(){
        const inter = {
          "description":this.subInterface.description ,
          "ip-address": this.subInterface.ipAddress,
          "name": this.subInterface.name,
          "vlan": this.subInterface.vlan,
          "encapsulation":this.subInterface.encapsulation,
          "subnet-mask": this.subInterface.subMask,
          idIn: this.$route.params['idI']
        };
        this.addSubInter(inter).then(
          res=>{
            this.dialog = false;
            this.e1 = 1;
            this.clear();
            this.error =''
            this.$emit('addSub')
          },
          error=>{
            this.error = error.bodyText
            console.log(error);
          })
      },
      clear(){
        this.subInterface.description =''
        this.subInterface.ipAddress=''
        this.subInterface.name=''
        this.subInterface.vlan =''
        this.subInterface.encapsulation=''
        this.subInterface.subMask=''

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


