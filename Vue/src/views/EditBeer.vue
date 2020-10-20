<template>
  <div
    id="updateBeer"
    class="contianer col-xl-5 mx-auto border border-dark rounded"
  >
    <h2 class="text-center">Edit Beer</h2>
    <beerForm v-bind:BeerIdProp='$route.params.beerId' />
    <br />
  </div>
</template>

<script>
import beerForm from "../components/BeerForm";
export default {
  data() {
    return {};
  },
  components: {
    beerForm,
  },
};
</script>

<style>
#updateBeer {
  display: flex;
  flex-direction: column;
  border: 1px, solid, black;
  background-color: wheat;
  width: 70%;
  margin-top: 50px;
}
#newBreweryForm {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
div > button {
  width: 100px;
  align-items: center;
}
</style>