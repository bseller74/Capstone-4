<template>
  <div class="beerForm">
    <form id="addBeerForm">
      <label for="name">Beer Name</label>
      <input
        v-model="newBeer.name"
        type="text"
        name="name"
        placeholder="Beer name"
      />
      <br />
      <label for="type">Type</label>
      <input
        v-model="newBeer.type"
        type="text"
        name="type"
        placeholder="Type"
      /> 
      <br />
      <label for="description">Description</label>
     <textarea rows="3" cols="23"
        v-model="newBeer.description"
        type="text"
        name="description"
        placeholder="Beer description"
      />
      <br />
      <label for="abv">abv</label>
      <input v-model="newBeer.abv" type="text" name="abv" placeholder="abv" />

      <br />

      <label for="ibu">ibu</label>
      <input v-model="newBeer.ibu" type="text" name="ibu" placeholder="ibu" />
      <br />
      <label for="image">Image URL</label>
      <input
        v-model="newBeer.imgUrl"
        type="text"
        name="active"
        placeholder="Image URL"
      />
      <br />
      <label for="Current">Is this beer currently on tap?</label>
      <input
        v-model="newBeer.current"
        type="checkbox"
        name="description"
        placeholder="Beer description"
      />
      <br />

      <button
        class="btn btn-primary"
        v-on:click="saveBeer"
        v-if="this.$route.params.beerId == null"
      >
        Add
      </button>
      <button class="btn btn-primary" v-on:click="updateBeer" v-else>
        Update
      </button>
      <button class="btn btn-danger" v-on:click="cancel">Cancel</button>
    </form>
  </div>
</template>

<script>
import breweryService from "../services/BreweryService.js";

export default {
  data() {
    return {
      newBeer: {
        breweryId: this.$route.params.id,
      },
    };
  },
  methods: {
    saveBeer() {
      //checks if user is brewer or admin, creates beer in DB and sends user to their logical page
      breweryService.addBeer(this.newBeer);
      if (this.$store.state.user.authorities[0].name == "ROLE_BREWER") {
        window.alert(`${this.newBeer.name} has been created!`);
        this.$router.push({ name: "BrewerTools" });
      } else {
        this.$router.push({ name: "BreweryBeers" });
      }
    },
    updateBeer() {
      //same process but with updating a current beer
      breweryService.updateBeer(this.newBeer);
      if (this.$store.state.user.authorities[0].name == "ROLE_BREWER") {
        this.$router.push({ name: "BrewerTools" });
      } else {
        this.$router.push({ name: "BreweryBeers" });
      }
    },
    cancel() {
      //sends user back to where they came from
      if (this.$store.state.user.authorities[0].name == "ROLE_BREWER") {
        this.$router.push({ name: "BrewerTools" });
      } else {
        this.$router.push({ name: "BreweryBeers" });
      }
    },
  },
  created() {
    //populates local beer data from route param
    if (this.$route.params.beerId != null) {
      breweryService.getBeerById(this.$route.params.beerId).then((response) => {
        this.newBeer = response.data;
      });
    }
  },
};
</script>

<style>
.beerForm {
  display: flex;
  flex-direction: column;
  border: 1px, solid, black;
  background-color: rgba(245, 222, 179, 0.7);
  width: 100%;
}
#addBeerForm {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
div > form > button {
  width: 80px;
  align-items: center;
}
</style>