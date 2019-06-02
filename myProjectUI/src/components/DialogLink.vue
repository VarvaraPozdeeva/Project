<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform" xmlns:v-scroll="">

  <v-layout row justify-center>
    <v-dialog  v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Add Link</v-btn>
      </template>
      <v-card>
        <v-card-text>Link</v-card-text>
        <v-card-text><strong class="red--text text--lighten-1">
          {{error}}
        </strong></v-card-text>
        <v-container>
          <v-layout  column >

            <v-flex xs12 sm6>
              <v-select
                :items="aNE"
                v-model="aNeSelect"
                label="A-network element"
                required
              ></v-select>
            </v-flex>

            <v-flex xs12 sm6>
              <v-select
                :items="aInter"
                v-model="aIntSelect"
                label="A-interface"
                required
              ></v-select>
            </v-flex>

            <v-flex xs12 sm6>
              <v-select
                :items="zNE"
                v-model="zNeSelect"
                label="Z-network element"
                required
              ></v-select>
            </v-flex>

            <v-flex xs12 sm6>
              <v-select
                :items="zInter"
                v-model="zIntSelect"
                label="Z-interface"
                required
              ></v-select>
            </v-flex>

            <v-flex xs12 sm6>
            </v-flex>
          </v-layout>
        </v-container>

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
    props:['flag'],
    data() {
      return {
        error:'',
        netElems: this.$store.state.netElements,
        aNeSelect:'',
        aIntSelect:'',
        zNeSelect:'',
        zIntSelect:'',
        dialog: false,
      }
    },
    computed:{
      aInter(){
        if(this.aNeSelect === '')
          return [];
        this.getAInterfaceByNameNEAction(this.aNeSelect);
        let inters = []
        this.$store.state.Ainterfaces.forEach(i=>inters.push(i.name))
        return inters

      },
      zInter(){
        if(this.zNeSelect === '')
          return [];
        this.getZInterfaceByNameNEAction(this.zNeSelect);
        let inters = []
        this.$store.state.Zinterfaces.forEach(i=>inters.push(i.name))
        return inters
      },

      zNE(){
        let z=[];
        this.netElems.forEach(e=>{
          z.push(e.name);
        })
        const index = z.findIndex(e => e === this.aNeSelect)
        z = [...z.slice(0, index), ...z.slice(index + 1)];
        return z;
      },
      aNE(){
        let z=[];
        this.netElems.forEach(e=>{
          z.push(e.name);
        })
        return z;
      }
    },
    created(){
      this.init();
      console.log(this.$store.state.hwComponent)
    },
    methods:{
      ...mapActions(["getDataAction", "getAInterfaceByNameNEAction",
        'getZInterfaceByNameNEAction', 'addLinkAction']),
      save(){

        const link = {
          "a-interface": this.aIntSelect,
          "a-ne": this.aNeSelect,
          "z-interface": this.zIntSelect,
          "z-ne": this.zNeSelect
        };
        this.addLinkAction(link).then(
          res=> {
            this.error =''
            this.clear();
            this.$emit('addLink');
            this.dialog = false;
          },
          error=>{
            this.error = error.bodyText
            this.clear();
            console.log(error.bodyText)
          })

      },
      clear(){
        this.aNeSelect=''
        this.aIntSelect=''
        this.zNeSelect=''
        this.zIntSelect=''
      },
      close(){
        this.dialog = false;
        this.aNeSelect=''
        this.aIntSelect=''
        this.zNeSelect=''
        this.zIntSelect=''
      },
      init() {
        this.getDataAction();
        setTimeout(() => { this.netElems = this.$store.state.netElements }, 300);
      }
    }
  }
</script>

<style scoped>

</style>
