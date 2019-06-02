<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>

    <v-toolbar flat color="white">
      <v-toolbar-items >
        <v-btn flat @click="flagSub = false">Details</v-btn>
        <v-btn flat @click="flagSub = true">Sub-interfaces</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <dialog-sub v-if="flagSub === true" @addSub="addSub"></dialog-sub>
    </v-toolbar>

    <v-card v-if="flagSub === false">
      <v-card-title primary-title>
        <div>
          <h3 class="headline mb-0">Interface</h3>
          <div class="text-xs-left" >
            <div><b>Name:</b> {{interface['name']}} </div>
            <div><b>Description:</b>{{interface['description']}}  </div>
            <div><b>MAC-address:</b> {{interface['mac-address']}} </div>
            <div><b>Ip-address:</b> {{interface['ip-address']}} </div>
            <div><b>MTU:</b> {{interface['mtu']}} </div>
            <div><b>Speed:</b> {{interface['speed']}} </div>
            <div><b>Duplex:</b> {{interface['duplex']}} </div>
            <div><b>Admin state:</b> {{interface['admin-status']}} </div>
            <div><b>Port mode</b> {{interface['port-mode']}} </div>
            <div><b>Vlans:</b> {{interface['vlans']}} </div>
            <div><b>Sub-interfaces:</b> {{interface[subInterface]}} </div>
          </div>
        </div>
      </v-card-title>
    </v-card>

    <v-data-table v-if="flagSub ===true"
                  hide-actions :headers="headers"
                  :items="subInters"  class="elevation-1" >

      <template v-slot:items="elem">

        <td class="text-xs-left" > {{ elem.item.name }}</td>
        <td class="text-xs-left">{{ elem.item['ip-address']}}</td>
        <td class="text-xs-left">{{ elem.item['vlan'] }}</td>
        <td class="text-xs-left">{{ elem.item['subnet-mask'] }}</td>
        <td class="text-xs-left">{{ elem.item['encapsulation'] }}</td>
        <td class="text-xs-left">{{ elem.item['description'] }}</td>

      </template>

      <template v-slot:no-data>
        <v-btn color="primary" @click="initSub">
          show data
        </v-btn>
      </template>

    </v-data-table>
  </div>
</template>

<script>
  import { mapActions} from 'vuex'
  import DialogSub from "./DialogSub";
  export default {
    components: {DialogSub},
    name: "DetailInterface",
    data(){
      return {
        subInterface:{},
        idI: this.$route.params['idI'],
        idNE: this.$route.params['idNE'],
        flagSub: false,
        subInters:[],
        headers: [
          {text: 'Name', align: 'left', sortable: false, value: 'name'},
          {text: 'IP-address',align: 'left' ,sortable: false, value: 'ip-address'},
          {text: 'Vlan',align: 'left' ,sortable: false, value: 'vlan'},
          {text: 'subnet-mask',align: 'left' ,sortable: false, value: 'subnet-mask'},
          {text: 'encapsulation',align: 'left' ,sortable: false, value: 'encapsulation'},
          {text: 'description',align: 'left' ,sortable: false, value: 'description'}
        ],
        interface:{}
      }
    },
    methods:{
      ...mapActions(["getDataAction", "getSubByIdInt"]),
      init(){
        console.log(this.$store.getters.getInterfaceByIdIdNe(this.idI, this.idNE));
        this.interface  =  this.$store.getters.getInterfaceByIdIdNe(this.idI, this.idNE);
        this.initSub();

      },
      initSub(){
        const id = this.idI
        this.getSubByIdInt(id);
        setTimeout(() => { this.subInters = this.$store.state.subInters;}, 300);
      },
      addSub(){
        setTimeout(() => {this.initSub (); console.log('saveSubInter')}, 500);
      }
    },
    created() {
      this.getDataAction();
      setTimeout(() => {this.init();}, 500);
    }
  }
</script>

<style scoped>

</style>
