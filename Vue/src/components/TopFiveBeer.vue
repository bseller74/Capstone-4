<template>
    <div>
        <H4 id="topFiveHeader">Top 5 Beers</H4>
            <div id="topFiveContainer" v-for="beer in beers" v-bind:Key="beer.id">
             <router-link id="beerlink" v-bind:to="{ name: 'BeerDetails', params: { id: beer.id } }">
                 {{beer.name}}
                </router-link>
            </div>

    </div>
  
</template>

<script>
import BreweryService from "@/services/BreweryService.js"

export default {
    data(){
        return{
          beers: [],
        }
    },

    created(){
        BreweryService.getTopFiveBeers().then(
            (response) => {
                    this.beers = response.data;
              
            }
        )
    }


}
</script>

<style>
#topFiveHeader{
    text-decoration: underline;
}
 #beerLink{
  color: black;
}
#beerlink:hover {
  color: whitesmoke;
}
#beerlink:visited {
  color: #630f0f;
}
#beerlink:visited:hover {
  color: whitesmoke;
}


</style>