<template>
  <div class="container text-center col-xl-12 mx-auto rounded">
    <h4>Are you sure you wish to delete {{ brewery.name }}?</h4>
    <button class="btn btn-danger" v-on:click="deleteBrewery()">Yes</button>
    <button class="btn btn-primary" v-on:click="Cancel">Cancel</button>
  </div>
</template>

<script>
import BreweryService from "../services/BreweryService";
export default {
  data() {
    return {
      brewery: {},
    };
  },
  props: ["id"],
  methods: {
    // Method to delete a brewery
    deleteBrewery() {
      BreweryService.deleteBrewery(this.brewery.id).then(() => {
        console.log(`Brewery ${this.brewery.name} deleted`);
      });
      this.$router.push({ name: "BreweryList" });
    },
    Cancel() {
      this.$router.push({
        name: "BreweryBeers",
        params: { id: this.brewery.id },
      });
    },
  },
  // pulls a brewery from the db when given a brewery id
  created() {
    BreweryService.getBreweryById(this.$route.params.id).then((response) => {
      this.brewery = response.data;
    });
  },
};
</script>

<style>
</style>