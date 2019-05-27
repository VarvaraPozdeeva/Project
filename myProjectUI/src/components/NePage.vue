<template>
    <div>
      <network-card v-for="element in netElements "
                    :key="element.id"
                    :element="element"/>
    </div>
</template>

<script>
    import NetworkCard from "./NetworkCard";

    export default {
      name: "NePage",
      components: {NetworkCard},
      data() {
        return {
          netElements: [],
        }
      },
      created() {
        this.$http.get('http://localhost:8080/data/network-elements')
          .then(result => {
            result.json().then(data => {
              console.log(data);
              this.netElements = data;
            })
          })
      }
    }
</script>

<style scoped>

</style>
