<template>
        <v-card  class="black--text">
          <v-card-title primary-title>
            <div>
              <h3 class="headline mb-0">Link "{{ln.name}}"</h3>
              <div class="text-xs-left" > <b>Network element A: </b>  {{ln['a-ne']}} </div>
              <div class="text-xs-left" > <b>Interface A:</b>   {{ln['a-interface']}}  </div>
              <div class="text-xs-left" > <b>Network element Z: </b>  {{ln['z-ne']}}  </div>
              <div class="text-xs-left" > <b>Interface Z:</b>   {{ln['z-interface']}}  </div>
            </div>
          </v-card-title>

        </v-card>
</template>

<script>
  import {mapActions} from 'vuex'
    export default {

      data(){
        return{
          ln:{}
        }
      },
      computed:{
        ln1(){
          return this.$store.getters.getLinksByID(this.$route.params['id'])
        }
      },
      methods:{
        ...mapActions(["getLinksAction"]),
        init(){
          console.log('init link card')
          this.ln = this.$store.getters.getLinksByID(this.$route.params['id'])
        }
      },
      created() {
        this.getLinksAction();
        this.init();
        console.log(this.ln)
      }
    }
</script>

<style scoped>

</style>
