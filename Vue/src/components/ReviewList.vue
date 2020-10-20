<template>
  <div>
    <h3>Reviews</h3>
    <div v-for="card in reviews" v-bind:key="card.id">
      <ReviewCard v-bind:chosen="card"> </ReviewCard>
    </div>
    <div>
      <ReviewForm v-bind:beer="beer"></ReviewForm>
    </div>
  </div>
</template>

<script>
import ReviewCard from "@/components/ReviewCard.vue";
import BreweryService from "@/services/BreweryService.js";
import ReviewForm from "@/components/ReviewForm.vue";
export default {
  data() {
    return {
      reviews: [],
      beer: {},
    };
  },
  components: {
    ReviewForm,
    ReviewCard,
  },
  created() {
    // use route params to populate local reviews array and beer info
    BreweryService.getReviewByBeer(this.$route.params.id).then((response) => {
      this.reviews = response.data;
    }),
      BreweryService.getBeerById(this.$route.params.id).then((response) => {
        this.beer = response.data;
      });
  },
};
</script>

<style>
</style>