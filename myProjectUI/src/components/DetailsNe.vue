<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>

    <v-toolbar flat color="white">

      <v-toolbar-items >
        <v-btn flat @click="ne">Details</v-btn>
        <v-btn flat @click="hwComponentFlag">Hardware Component</v-btn>
        <v-btn flat @click="interfacesFlag">Interfaces</v-btn>
        <v-btn flat @click="linksFlag">Links</v-btn>
      </v-toolbar-items>

      <v-spacer></v-spacer>
      <dialog-interface v-if="flagInt===true" @addInter="addInterface" ></dialog-interface>
    </v-toolbar>

    <network-element v-if="flagNe===true" ></network-element>
    <details-hw v-if="flagHw===true"></details-hw>

    <v-data-table v-if="flagInt === true"  hide-actions
                  :headers="headersInterface" :items="interfaces"  class="elevation-1" >

      <template v-slot:items="inter">
        <td class="text-xs-left" >
          <v-btn flat :to="'/interfaces/'+ idNE + '/'+ inter.item.id" >
            {{ inter.item.name }}
          </v-btn></td>
        <td class="text-xs-left">{{ inter.item['mac-address'] }}</td>
        <td class="text-xs-left">{{ inter.item['ip-address']}}</td>
        <td class="text-xs-left">{{ inter.item['ip-address'] }}</td>
        <td class="text-xs-left">{{ inter.item['admin-status'] }}</td>
        <td class="justify-left layout px-0">
          <v-icon    @click="deleteInter(inter.item)">
            delete
          </v-icon>
        </td>
      </template>

      <template v-slot:no-data>
        <v-btn color="primary" @click="initInterface">
          show data
        </v-btn>
      </template>

    </v-data-table>

    <v-data-table v-if="flagLink === true"
                  hide-actions  :headers="headersLink"
                  :items="links" class="elevation-1" >

      <template v-slot:items="link">
        <td>
          <v-btn flat :to="'/links/' + link.item.id" >
            {{ link.item.name }}
          </v-btn></td>
        <td class="text-xs-right">{{ link.item['a-ne'] }}</td>
        <td class="text-xs-right">{{ link.item['a-interface']}}</td>
        <td class="text-xs-right">{{ link.item['z-ne'] }}</td>
        <td class="text-xs-right">{{ link.item['z-interface'] }}</td>
        <td class="justify-center layout px-0">
          <v-icon    @click="deleteLink(link.item)">
            delete
          </v-icon>
        </td>
      </template>

      <template v-slot:no-data>
        <v-btn color="primary" @click="initializeLink">
          show data
        </v-btn>
      </template>

    </v-data-table>




  </div>
</template>

<script>
  import {mapActions, mapState} from 'vuex'
  import DialogInterface from "./DialogInterface";
  import NetworkElement from "./NetworkElement";
  import DetailsHw from "./DetailsHw";
  export default {
    name: "DetailsNe",
    components: {DetailsHw, NetworkElement, DialogInterface},
    data(){
      return {
        interfaces:[],
        links:[],
        flagInt:false,
        flagNe: true,
        flagLink: false,
        flagHw: false,
        headersLink: [
          {text: 'Name', align: 'left', sortable: false, value: 'name'},
          {text: 'A-network element', sortable: false, value: 'a-ne'},
          {text: 'A-subInterface',sortable: false, value: 'a-interface'},
          {text: 'Z-network element',sortable: false, value: 'z-ne'},
          {text: 'Z-subInterface',sortable: false, value: 'z-interface'},
          {text: 'Actions', value: 'action', sortable: false}
        ],
        headersInterface: [
          {text: 'Name', align: 'left', sortable: false, value: 'name'},
          {text: 'MAC-address', align: 'left' , sortable: false, value: 'mac-address'},
          {text: 'IP-address',align: 'left' ,sortable: false, value: 'ip-address'},
          {text: 'Admin status',align: 'left' ,sortable: false, value: 'admin-status'},
          {text: 'Actions' , align: 'center', value: 'action', sortable: false}
        ],
        idNE: this.$route.params['id']
      }
    },
    methods:{
      ne(){
        this.flagNe = true
        this.flagLink = false
        this.flagHw = false
        this.flagInt = false

      },
      hwComponentFlag(){
        this.flagNe = false
        this.flagLink = false
        this.flagHw = true
        this.flagInt = false

      },
      interfacesFlag(){
        this.flagNe = false
        this.flagLink = false
        this.flagHw = false
        this.flagInt = true

      },
      linksFlag(){
        this.flagNe = false
        this.flagLink = true
        this.flagHw = false
        this.flagInt = false
      },
      ...mapActions(['getLinksByIdAction', "deleteNEAction",
         "getInterfaceAction", 'deleteInterfaceAction', 'deleteLinkAction']),
      deleteNE(){
        this.deleteNEAction(this.$route.params['id'])
          .then(
            res=>{
              this.$router.push({path: '/network-elements'})
            },
            error=>{
              console.log(error)
            })
      },
      deleteInter(item){
        this.deleteInterfaceAction(item.id);
        const index = this.interfaces.findIndex( i => i === item);
        this.interfaces = [
          ...this.interfaces.slice(0, index),
          this.interfaces.slice(index+1)
        ];
        setTimeout(() => {this.initInterface(); this.initializeLink()}, 500);
      },
      addInterface(){
        setTimeout(() => {this.initInterface (); console.log('saveInter')}, 500);
      },
      initInterface(){
        const id = this.idNE
        this.getInterfaceAction(id);
        setTimeout(() => { this.interfaces = this.$store.state.interfaces;}, 300);
      },
      initializeLink () {
        const id = this.idNE;
        this.getLinksByIdAction(id);
        setTimeout(() => { this.links = this.$store.state.linksNE;}, 300);
        console.log(this.links)
      },
      deleteLink (item) {
        this.deleteLinkAction(item);
        const index =  this.links.findIndex(e=>e.id === item.id)
        this.links = [...this.links.slice(0,index),
          ...this.links.slice(index+1)];
      }
    },
    created() {
      this.initInterface();
      this.initializeLink();
    }
  }
</script>

<style scoped>

</style>
