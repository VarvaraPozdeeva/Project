<template>
  <div>
    <v-layout my-3>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title primary-title>
            <div>
              <h3 class="headline mb-0">Hardware component</h3>
              <div class="text-xs-left" >
                <div>Name:  </div>
                <div>Description:  </div>
                <div>Hardware rev:  </div>
                <div>Board id:  </div>
                <div>Part number:  </div>
              </div>
            </div>
          </v-card-title>
          <v-divider light></v-divider>

        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
    export default {
        name: "DetailsHw"
    }
</script>

<style scoped>

</style>
