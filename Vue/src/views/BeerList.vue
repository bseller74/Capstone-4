<template>
  <div class="beer-container">
    <div id='BeersListHeader' class='beer-type'>
    <h3>All Beers</h3>
    </div>
    <div id="Sours-Container" class="beer-type">
      <h3>Sours</h3>
      <ul>
        <li v-for="beer in getSours" v-bind:key="beer.id">
          <router-link
            v-bind:to="{ name: 'BeerDetails', params: { id: beer.id } }"
            class="navlink"
          >
            {{ beer.name }} ({{ beer.type }})</router-link
          >
        </li>
      </ul>
    </div>
    <div id="IPA-Container" class="beer-type">
      <h3>IPAs</h3>
      <ul>
        <li v-for="beer in getIPAs" v-bind:key="beer.id">
          <router-link
            v-bind:to="{ name: 'BeerDetails', params: { id: beer.id } }"
            class="navlink"
          >
            {{ beer.name }} ({{ beer.type }})</router-link
          >
        </li>
      </ul>
    </div>
    <div id="Ale-Container" class="beer-type">
      <h3>Ales</h3>
      <ul>
        <li v-for="beer in getAles" v-bind:key="beer.id">
          <router-link
            v-bind:to="{ name: 'BeerDetails', params: { id: beer.id } }"
            class="navlink"
          >
            {{ beer.name }} ({{ beer.type }})</router-link
          >
        </li>
      </ul>
    </div>
    <div id="Lager-Container" class="beer-type">
      <h3>Lagers</h3>
      <ul>
        <li v-for="beer in getLagers" v-bind:key="beer.id">
          <router-link
            v-bind:to="{ name: 'BeerDetails', params: { id: beer.id } }"
            class="navlink"
          >
            {{ beer.name }} ({{ beer.type }})</router-link
          >
        </li>
      </ul>
    </div>
    <div id="Stout-Porter-Container" class="beer-type">
      <h3>Stouts and Porters</h3>
      <ul>
        <li v-for="beer in getStoutPorter" v-bind:key="beer.id">
          <router-link
            v-bind:to="{ name: 'BeerDetails', params: { id: beer.id } }"
            class="whitelink"
          >
            {{ beer.name }} ({{ beer.type }})</router-link
          >
        </li>
      </ul>
    </div>
    <div id="Others-Container" class="beer-type">
      <h3>Other</h3>
      <ul>
        <li v-for="beer in getOthers" v-bind:key="beer.id">
          <router-link
            v-bind:to="{ name: 'BeerDetails', params: { id: beer.id } }"
            class="whitelink"
          >
            {{ beer.name }} ({{ beer.type }})</router-link
          >
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import BreweryService from "@/services/BreweryService.js";
export default {
  data() {
    return {
      beers: [],
    };
  },
  // Seperates beers out into catagories such as Stouts Ails IPAs etc...
  computed: {
    getSours() {
      let Sours = [];
      for (let i = 0; i < this.beers.length; i++) {
        if (this.beers[i].type.includes("Sour")) {
          Sours.push(this.beers[i]);
        }
      }
      return Sours;
    },
    getIPAs() {
      let IPAs = [];
      for (let i = 0; i < this.beers.length; i++) {
        if (this.beers[i].type.includes("IPA")) {
          IPAs.push(this.beers[i]);
        }
      }
      return IPAs;
    },
    getAles() {
      let Ales = [];
      for (let i = 0; i < this.beers.length; i++) {
        if (this.beers[i].type.includes("Ale")) {
          Ales.push(this.beers[i]);
        }
      }
      return Ales;
    },
    getLagers() {
      let Lagers = [];
      for (let i = 0; i < this.beers.length; i++) {
        if (
          this.beers[i].type.includes("Lager") ||
          this.beers[i].type.includes("Pils")
        ) {
          Lagers.push(this.beers[i]);
        }
      }
      return Lagers;
    },
    getStoutPorter() {
      let SPs = [];
      for (let i = 0; i < this.beers.length; i++) {
        if (
          this.beers[i].type.includes("Stout") ||
          this.beers[i].type.includes("Porter")
        ) {
          SPs.push(this.beers[i]);
        }
      }
      return SPs;
    },
    getOthers() {
      let Others = [];
      for (let i = 0; i < this.beers.length; i++) {
        if (
          !this.beers[i].type.includes("IPA") &&
          !this.beers[i].type.includes("Sour") &&
          !this.beers[i].type.includes("Stout") &&
          !this.beers[i].type.includes("Porter") &&
          !this.beers[i].type.includes("Lager") &&
          !this.beers[i].type.includes("Ale") &&
          !this.beers[i].type.includes("IPA") &&
          !this.beers[i].type.includes("Pils")
        ) {
          Others.push(this.beers[i]);
        }
      }
      return Others;
    },
  },
  // Gets all beers from db
  created() {
    BreweryService.getBeerList().then((response) => {
      this.beers = response.data;
    });
  },
};
</script>

<style>
.beer-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}
h3 {
  text-align: center;
  font-weight: 300;
}
.beer-type {
  display: flex;
  flex-direction: column;
  border: solid 1px black;
  border-radius: 20px;
  padding: 10px;
  width: 70%;
  color: black;
}
.beer-type > ul {
  display: flex;
  flex-wrap: wrap;
}
.beer-type > ul > li {
  margin-right: 30px;
}
#Sours-Container {
  background-color: rgba(253, 238, 111, 0.85);
}
#IPA-Container {
  background-color: rgba(251, 200, 0, 0.85);
}
#Ale-Container {
  background-color: rgba(234, 165, 0, 0.85);
}
#Lager-Container {
  background-color: rgba(221, 135, 0, 0.85);
}
#Stout-Porter-Container {
  background-color: rgba(99, 15, 15, 0.85);
  color: whitesmoke;
}
#Others-Container {
  background-color: rgba(44, 6, 3, 0.85);
  color: whitesmoke;
}
#BeersListHeader{
  background-color: rgba(251, 241, 205, 0.8);
}

</style>