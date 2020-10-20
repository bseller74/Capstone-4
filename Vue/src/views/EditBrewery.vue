<template>
  <div
    id="addBrewery"
    class="container col-xl-5 mx-auto border border-dark rounded"
  >
    <h2 class="text-center">Edit a Brewery</h2>
    <breweryForm v-bind:brewid="brewery.id"/>
  </div>
</template>

<script>
import breweryForm from "../components/BreweryForm";
import breweryService from "../services/BreweryService";
export default {
  data() {
    return {
      brewery: {},
    };
  },
  components: {
    breweryForm,
  },
  methods: {
    updateBrewery() {
      breweryService.updateBrewery(this.brewery);
      this.$router.push({ name: "BreweryList" });
    },
  },
  created() {
    breweryService.getBreweryById(this.$route.params.id).then((response) => {
      this.brewery = response.data;
    });
  },
};
</script>

<style>
#addBrewery {
  display: flex;
  flex-direction: column;
  border: 1px, solid, black;
  background-color: wheat;
  width: 70%;
}
#newBreweryForm {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
div > button {
  width: 100px;
  align-items: center;
}
</style>