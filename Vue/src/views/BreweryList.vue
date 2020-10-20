<template>
  <div class="container text-center col-xl-8 mx-auto border border-dark rounded">
      <h1 id='BreweryListHeader'>Breweries</h1>
    <div
      id="breweryList"
    >
      <!--text-center col-xl-5 mx-auto border border-dark rounded-->
      <div v-for='brewery in faves' v-bind:key='brewery.id' id='favoritedBreweryCard' class='brewery'>
        <h3>
          <router-link
            v-bind:to="{ name: 'BreweryBeers', params: { id: brewery.id } }"
            class="brewery-name"
          >
        <img v-bind:src="brewery.image" width="180px" height="auto" />
          </router-link>
        </h3>
      </div>
      <div v-for="brewery in nonFaves" v-bind:key="brewery.id" class="brewery">
        <h3>
          <router-link
            v-bind:to="{ name: 'BreweryBeers', params: { id: brewery.id } }"
            class="brewery-name"
          >
        <img v-bind:src="brewery.image" width="180px" height="auto" />
          </router-link>
        </h3>
      </div>
    </div>
  </div>
</template>

<script>
import BreweryService from "@/services/BreweryService.js";
export default {
  name: "BreweryList",
  data() {
    return {
      breweries: [],
      faves : []
    };
  },
  computed: {
    // Sorts breweries by favoret
    nonFaves(){
      let nonFaves = [];
      this.breweries.forEach((brewery) => {
        if (!this.faves.includes(brewery)){
          nonFaves.push(brewery);
        }
      })
      return nonFaves;
    }
  },
  // pulls a list of brewery from the db
  created() {
    BreweryService.getBreweryList().then((response) => {
      this.breweries = response.data;
       BreweryService.getFavoritesByUser(this.$store.state.user.id).then(
        (response) => {
         let favesIDs = response.data;
           favesIDs.forEach((fav) => {
            this.breweries.forEach((brewery) => {
              if (fav.brewery_id == brewery.id) {
                this.faves.push(brewery);
              }
            });
          });
        }
      );
    });
  },
};
</script>

<style>
#breweryList{
  display:flex;
  flex-direction: row;
  flex-wrap: wrap;
  width: 100%
}
#breweryList img{
  height:250px;
  width: 250px;
  border-radius: 50%;
}
#favoritedBreweryCard img{
  border: gold solid 5px;
}
.brewery {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 15px;
}
h2 {
  text-align: center;
  font-weight: bold;
}
.brewery-name {
  color: black;
}
body {
  padding-top: 50px;
}
img {
  margin-right: 20px;
}
#addBreweryBtn {
  width: auto;
}
#BreweryListHeader{
  margin-top: 50px;
}
</style>