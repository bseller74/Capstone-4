<template>
  <div>
    <h2>Processed Brewers Requests</h2>
    <div v-for="user in processedBrewers" v-bind:key="user.id">
      {{ user.id }} || {{ user.userId }} || {{ user.username }} ||
      {{ user.breweryId }}
    </div>
  </div>
</template>


<script>
import breweryService from "../services/BreweryService";
export default {
  data() {
    return {
      pendingBrewers: [],
      processedBrewers: [],
    };
  },
  created() {
    breweryService.getAllBrewerRequests().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        if (response.data[i].processed) {
          this.processedBrewers.push(response.data[i]);
        } else {
          this.pendingBrewers.push(response.data[i]);
        }
      }
    });
  },
};
</script>

<style>

</style>