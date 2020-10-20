<template>
  <div>
    <BreweryDetails />
    <div
      id="beerList"
      class="container text-center col-xl-12 mx-auto rounded"
    >
      <h2 id="beers-header">Beers</h2>
      <div class="" v-for="beer in beers" v-bind:key="beer.id">
        <router-link
          class="navlink"
          v-bind:to="{ name: 'BeerDetails', params: { id: beer.id } }"
        >
          {{ beer.name }}
        </router-link>
      </div>
    </div>
    <div id="newsContainer" class="container text-center col-xl-12 mx-auto rounded">
      <BreweryNews/>
    </div>
    <div id="eventContainer" class="container text-center col-xl-12 mx-auto rounded">
      <BreweryEvents/>
    </div>
  </div>
</template>

<script>
import BreweryDetails from "@/components/BreweryDetails.vue";
import BreweryService from "@/services/BreweryService.js";
import BreweryNews from "@/components/BreweryNews.vue";
import BreweryEvents from "@/components/BreweryEvents.vue"
export default {
  data() {
    return {
      beers: [],
    };
  },
  components: {
    BreweryDetails,
    BreweryNews,
    BreweryEvents,
  },
  // Pulls all beers from a single brewery when given a brewery id
  created() {
    BreweryService.getBeerByBrewery(this.$route.params.id).then((response) => {
      this.beers = response.data;
    });
  },
};
</script>

<style>
#beerList {
  text-align: center;
  padding: 50px;
  border-style: solid;
  border-width: 3px;
  width: 60%;
  justify-content: center;
  align-items: center;
  margin-top: -10px;
}
#newsContainer{
   background: rgba(245, 222, 179, 0.7);
  text-align: center;
  padding: 50px;
  border-style: solid;
  border-width: 3px;
  width: 60%;
  justify-content: center;
  align-items: center;
  margin-top: -10px;
}
#eventContainer{
   background: rgba(245, 222, 179, 0.7);
  text-align: center;
  padding: 50px;
  border-style: solid;
  border-width: 3px;
  width: 60%;
  justify-content: center;
  align-items: center;
  margin-top: -10px;
}
</style>