<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>

    <v-toolbar flat color="white">
      <v-toolbar-items >
        <v-btn flat :class="{active :flagSub === false}" @click="flagSub = false">Details</v-btn>
        <v-btn flat :class="{active :flagSub}" @click="flagSub = true">Sub-interfaces</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <dialog-sub v-if="flagSub === true" @addSub="addSub"></dialog-sub>
    </v-toolbar>

    <v-card v-if="flagSub === false">
      <v-card-title primary-title>
        <h3 class="headline mb-0">Interface</h3>
      </v-card-title>
      <table>
        <tr><td class="prop"><b>Name</b></td><td>{{interface['name']}} </td></tr>
        <tr><td class="prop"><b>Description</b></td><td>{{interface['description']}} </td></tr>
        <tr><td class="prop"><b>MAC-address</b></td><td>{{interface['mac-address']}}</td></tr>
        <tr><td class="prop"><b>Ip-address</b></td><td>{{interface['ip-address']}} </td></tr>
        <tr><td class="prop"><b>MTU</b></td><td>{{interface['mtu']}} </td></tr>
        <tr><td class="prop"><b>Speed</b></td><td>{{interface['speed']}} </td></tr>
        <tr><td class="prop"><b>Duplex</b></td><td> {{interface['duplex']}} </td></tr>
        <tr><td class="prop"><b>Admin state</b></td><td>{{interface['admin-status']}} </td></tr>
        <tr><td class="prop"><b>Port mode</b></td><td>{{interface['port-mode']}} </td></tr>
        <tr><td class="prop"><b>Vlans</b></td><td>{{interface['vlans']}} </td></tr>
      </table>
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
        this.$http.get(this.$store.state.path + 'interfaces/' + this.idNE)
          .then(result=> result.json()
            .then(data=>{
              console.log(data);
              this.interface = data.find(i=>i.id === this.idI);
            }))
        // console.log(this.$store.getters.getInterfaceByIdIdNe(this.idI, this.idNE));
        // this.interface  =  this.$store.getters.getInterfaceByIdIdNe(this.idI, this.idNE);
        this.initSub();

      },
      initSub(){
        const id = this.idI
        this.getSubByIdInt(id);
        setTimeout(() => { this.subInters = this.$store.state.subInters;}, 500);
      },
      addSub(){
        setTimeout(() => {this.initSub (); console.log('saveSubInter')}, 500);
      }
    },
    created() {
      this.getDataAction();
      setTimeout(() => {this.init();}, 300);
    }
  }
</script>

<style scoped>
  table{
    border-collapse: collapse;
    width: 100%;
  }
  .prop{
    width: 30%;
  }
  td, th{
    padding: 8px 8px 8px 60px;
    border-bottom: 1px solid #ddd;
  }
  .active{
    background-color: #dddddd;
  }
</style>
