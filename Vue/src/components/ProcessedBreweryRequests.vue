<template>
  <div>
    <h2>Processed Brewery Requests</h2>
    <div v-for="brewery in processedBreweries" v-bind:key="brewery.id">
      <div id="breweryInfoBottum" class="text-center col-xl-12 mx-auto rounded">
        <div class="flexLeft">
          <img v-bind:src="brewery.image" width="180px" height="auto" />
        </div>
        <div class="flexRight">
          <h2 id="name">
            {{ brewery.name }}
          </h2>
          <p id="address">
            {{ brewery.address }}
          </p>
          <p id="phone">
            {{ brewery.phone }}
          </p>
          <p id="hours">
            {{ brewery.hours }}
          </p>
          <p id="description">
            {{ brewery.description }}
          </p>
          <a class="navlink" v-bind:href="brewery.brewery_url">Web site</a>
          <br />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import breweryService from "../services/BreweryService.js";
export default {
  data() {
    return {
      pendingBreweries: [],
      processedBreweries: [],
    };
  },
  created() {
    breweryService.getAllBreweryRequests().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        if (response.data[i].processed) {
          this.processedBreweries.push(response.data[i]);
        } else {
          this.pendingBreweries.push(response.data[i]);
        }
      }
    });
  },
};
</script>

<style>
</style>