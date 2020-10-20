<template>
  <div class="container text-center col-xl-12 mx-auto rounded">
    <h4>Are you sure you wish to delete {{ news.newstitle }}?</h4>
    <button class="btn btn-danger" v-on:click="DeleteNews(news)">Yes</button>
    <button class="btn btn-primary" v-on:click="Cancel">Cancel</button>
  </div>
</template>

<script>
import BreweryService from "../services/BreweryService";
export default {
  data() {
    return {
      news: {},
    };
  },
  methods: {
    DeleteNews(news) {
      //delete news from db, then send back to brewer tools
      BreweryService.deleteNews(news.newsId).then(() => {
        window.alert(`${news.newstitle} has been deleted!`);
        this.$router.push({ name: "BrewerTools" });
      });
    },
    Cancel() {
      //cancel delete, send back to brewer tools
      this.$router.push({ name: "BrewerTools" });
    },
  },
  created() {
    //collect news from route param // need get news by newsId
    BreweryService.getNewsByNewsId(this.$route.params.newsId).then(
      (response) => {
        this.news = response.data;
      }
    );
  },
};
</script>

<style>
</style>