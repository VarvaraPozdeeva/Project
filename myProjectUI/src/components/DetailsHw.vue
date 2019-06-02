<template>
  <div>

    <v-card>
      <v-card-title primary-title>
        <div>
          <h3 class="headline mb-0">Hardware component</h3>
          <div class="text-xs-left" >
            <div><b>Name:</b> {{component.name}} </div>
            <div><b>Description:</b> {{component.description}} </div>
            <div><b>Hardware rev:</b> {{component['hardware-rev']}} </div>
            <div><b>Board id:</b> {{component['board-id']}} </div>
            <div><b>Part number:</b> {{component['part-number']}} </div>
          </div>
        </div>
      </v-card-title>

    </v-card>
  </div>
</template>

<script>
  import {mapActions, mapState} from 'vuex'
  export default {
    name: "DetailsHw",
    data(){
      return{
        error:'',
        flag: true
      }
    },
    methods:{
      ...mapActions(['getHWAction'])
    },
    computed: mapState({
      component: "hwComponent"
    }),
    created() {
      this.getHWAction(this.$route.params['id']).then(res=>{}, error=>{
        this.error = error.bodyText;
        this.flag = false;
      });
    }
  }
</script>

<style scoped>

</style>
