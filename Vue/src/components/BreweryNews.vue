<template>
  <div id="breweryNews">
      <h2>Brewery News</h2><br>
          <div class="newsContainer" v-for='news in News' v-bind:Key="news.id">
              <h5 id="newsTitle">{{news.newstitle}}</h5>
              <p id="newsbody">{{news.body}}</p>
                      <img v-bind:src="news.newsImageUrl">
          </div>
  </div>
</template>

<script>
import BreweryService from "@/services/BreweryService.js";

export default {
    data(){
        return{
            News: [],


        }
    },

    created(){
        BreweryService.getNewsByBrewery(this.$route.params.id).then(
            (response) => {
                this.News = response.data.reverse();
            }
        )
    } 

};

</script>

<style>

</style>