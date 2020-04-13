<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform" xmlns:v-scroll="">

  <v-layout row justify-center>
    <v-dialog  v-model="dialog" persistent max-width="600px" >
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Add Network element</v-btn>
      </template>

      <v-stepper v-model="e1">
        <v-stepper-header>
          <v-stepper-step :complete="e1 > 1" step="1">
            Network element</v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step :complete="e1 > 2" step="2">
            Hardware component</v-stepper-step>
        </v-stepper-header>


        <v-stepper-items>
          <v-stepper-content  step="1">
            <v-card flat>
              <v-card-text>Network element</v-card-text>
              <v-container
                id="scroll-target"
                style="max-height: 400px"
                class="scroll-y"
              >
                <v-layout
                  v-scroll:#scroll-target
                  column
                >

                  <v-flex xs12 sm6 md4>
                    <v-text-field label="Name" v-model="element.nameNe" >

                    </v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
<!--                    <v-text-field  label="Type" v-model="element.type" >-->

<!--                    </v-text-field>-->

                    <v-select
                      :items="type"
                      v-model="element.type"
                      label="Type"
                      required
                    ></v-select>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="Vendor" v-model="element.vendor"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="IP-address" v-model="element.ipAddress"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="OS-version" v-model="element.osVersion"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="Returned to rom" v-model="element.RetToRom"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="System booted time" v-model.number="element.SysBootTime"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="Last sync" v-model.number="element.LSync"
                    ></v-text-field>
                  </v-flex>
                </v-layout>
              </v-container>

            </v-card>
            <v-layout align-center justify-end row fill-height>
              <v-btn color="primary"  @click="e1 = 2">Continue</v-btn>
              <v-btn color="primary"  @click="dialog=false; e1=1; clear()">
                Cancel
              </v-btn>
            </v-layout>
          </v-stepper-content>

          <v-stepper-content step="2">
            <v-card flat>

              <v-card-text>Hardware component</v-card-text>
              <v-container>
                <v-layout  column >

                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="Name" v-model="hwComponent.name"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="Description" v-model="hwComponent.description"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="Hardware rev" v-model="hwComponent.hwRev"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs12>
                    <v-text-field
                      label="Board id" v-model="hwComponent.boardId"
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field
                      label="Part number" v-model="hwComponent.partNum"
                    ></v-text-field>
                  </v-flex>
                </v-layout>
              </v-container>

            </v-card>
            <v-layout align-center justify-end row fill-height>
              <v-btn color="primary"  @click="e1 = 1"> Previous</v-btn>
              <v-btn  color="primary" @click="save">Save</v-btn>
              <v-btn color="primary" @click="dialog=false; e1=1 ; clear()">
                Cancel
              </v-btn>
            </v-layout>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>

    </v-dialog>
  </v-layout>


</template>

<script>
  import {mapActions} from 'vuex';
  export default {
    data() {
      return {
        dialog: false,
        e1: 0,
        idNE:'',
        type:['router', 'switch'],
        element:{
          nameNe:'',
          type:'',
          vendor:'cisco',
          ipAddress:'',
          osVersion:'',
          RetToRom:'',
          SysBootTime:'',
          LSync:''
        },
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
      clear(){
        this.element.ipAddress=''
        this.element.LSync=''
        this.element.nameNe=''
        this.element.osVersion=''
        this.element.RetToRom=''
        this.element.SysBootTime=''
        this.element.type=''
        this.element.vendor=''
        this.hwComponent.boardId=''
        this.hwComponent.description=''
        this.hwComponent.hwRev=''
        this.hwComponent.name=''
        this.hwComponent.partNum=''
      },
      save(){
        this.dialog = false;
        this.e1 = 1;
        const id =  this.addNe();
        console.log(id);
        id.then(res=>console.log(res));
        this.$emit('add');
        this.clear()
      },
      ...mapActions(["addNeAction", "addHWAction"]),
      async addNe(){
        console.log('add network')
        const ne = {
          netElem: {
            "ip-address": this.element.ipAddress,
            "last-sync": this.element.LSync,
            "name": this.element.nameNe,
            "os-version": this.element.osVersion,
            "returned-to-rom": this.element.RetToRom,
            "system-booted-time": this.element.SysBootTime,
            "type": this.element.type,
            "vendor": this.element.vendor
          },
          hw:{
            "board-id": this.hwComponent.boardId,
            "description": this.hwComponent.description,
            "hardware-rev": this.hwComponent.hwRev,
            "name": this.hwComponent.name,
            "part-number": this.hwComponent.partNum
          }
        }
        this.addNeAction(ne);
      }
    }
  }
</script>

<style scoped>

</style>
