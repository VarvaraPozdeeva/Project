<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <v-toolbar flat color="white">

      <v-toolbar-items >
        <v-btn flat :class="{active :flagNe}" @click="ne" >Network elements</v-btn>
        <v-btn flat  :class="{active :flagLink}" @click="link">Links</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <dialog-ne v-if="flagNe===true" @add="addNe"></dialog-ne>
      <dialog-link v-if="flagLink ===true" @addLink="addLink"></dialog-link>
    </v-toolbar>

    <v-data-table  v-if="flagNe === true"
                   hide-actions :headers="headersNE"
                   :items="netElems" class="elevation-1"  >

      <template v-slot:items="netElem">
        <td>
          <v-btn flat :to="'/network-elements/' + netElem.item.id" >
            {{ netElem.item.name }}
          </v-btn>
        </td>
        <td class="text-xs-right">{{ netElem.item.type }}</td>
        <td class="text-xs-right">{{ netElem.item.vendor }}</td>
        <td class="text-xs-right">{{ netElem.item['os-version'] }}</td>
        <td class="text-xs-right">{{ netElem.item['returned-to-rom'] }}</td>
        <td class="justify-center layout px-0">
          <v-icon    @click="deleteNE(netElem.item)">
            delete
          </v-icon>
        </td>
      </template>

      <template v-slot:no-data>
        <v-btn color="primary" @click="initializeNE">
          show data
        </v-btn>
      </template>

    </v-data-table>

    <v-data-table v-if="flagLink === true"
                  hide-actions  :headers="headersLink"
                  :items="links" :flag="flagg" class="elevation-1" >

      <template v-slot:items="link">
        <td> <v-btn flat :to="'/links/' + link.item.id" >
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
  import DialogNe from "./DialogNe";
  import DialogLink from "./DialogLink";
  import {mapActions} from 'vuex'
  export default {
    components: {DialogLink, DialogNe },
    data(){
      return {
        flagNe: true,
        flagg:false,
        flagLink: false,
        headersNE: [
          {text: 'Name',align: 'left',sortable: false,value: 'name'},
          { text: 'Type',align: 'right', sortable: false,value: 'type' },
          { text: 'Vendor',align: 'right',sortable: false, value: 'vendor' },
          { text: 'OS-version',align: 'right',sortable: false, value: 'os-version' },
          { text: 'Returned to rom',align: 'right',sortable: false, value: 'returned-to-rom' },
          { text: 'Actions',align: 'center', value: 'action', sortable: false }
        ],
        netElems: [],
        headersLink: [
          {text: 'Name', align: 'left', sortable: false, value: 'name'},
          {text: 'A-network element', sortable: false, value: 'a-ne'},
          {text: 'A-subInterface',sortable: false, value: 'a-interface'},
          {text: 'Z-network element',sortable: false, value: 'z-ne'},
          {text: 'Z-subInterface',sortable: false, value: 'z-interface'},
          {text: 'Actions', value: 'action', sortable: false}
        ],
        links: []
      }
    },
    created () {
      this.initializeNE()
      this.initializeLink ()
    },
    methods: {
      ...mapActions(["getDataAction", 'deleteNEAction',
        "getLinksAction", "deleteLinkAction"]),
      initializeNE () {
        console.log('init')
        this.getDataAction();
        this.netElems = this.$store.state.netElements
      },
      deleteNE (item) {
        this.deleteNEAction(item.id)
        const index =  this.netElems.findIndex(e=>e.id === item.id)
        this.netElems = [
          ...this.netElems.slice(0,index),
          ...this.netElems.slice(index+1)
        ];
        this.$store.state.netElements = this.netElems;
        setTimeout(() => {this.initializeLink()}, 300);
      },
      ne(){
        this.flagNe = true
        this.flagLink = false
      },
      addNe(){
        setTimeout(() => {this.initializeNE(); this.flagg = !this.flagg}, 300);
      },
      addLink(){
        setTimeout(() => {this.initializeLink (); console.log('saveLink')}, 600);
      },
      link(){
        this.initializeLink();
        this.flagNe = false
        this.flagLink = true
      },
      initializeLink () {
        this.getLinksAction();
        this.links = this.$store.state.links;
        console.log(this.links)
      },
      deleteLink (item) {
        console.log(item);
        this.deleteLinkAction(item);
        const index =  this.links.findIndex(e=>e.id === item.id)
        this.links = [...this.links.slice(0,index),
          ...this.links.slice(index+1)];
      }
    }
  }
</script>

<style scoped>
  .active{
    background-color: #dddddd;
  }
</style>
