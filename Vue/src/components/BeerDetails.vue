<template>
  <div id="beerInfo" class="container text-center col-xl-12 mx-auto rounded">
    <h2 id="beer-name-brewery" >
      <div id='#adminBeerButtons'>
          <button
      class="btn btn-primary"
      v-if="isAdmin()"
      v-on:click.prevent="editBeer"
      >
      Edit
    </button><span> &nbsp;
         <button
      class="btn btn-danger"
      v-if="isAdmin()"
      v-on:click.prevent="deleteBeer"
    >
      Delete
    </button>
    </span>
    </div>
      {{ beer.name }} <br />
      <span v-bind="getBrewery()">
        <router-link
          class="navlink"
          v-bind:to="{ name: 'BreweryBeers', params: { id: brewery.id } }"
        >
          {{ brewery.name }}
        </router-link>
      </span>
    </h2>
    <h1 id="avgRatingDisplay">{{ beer.rating }}</h1>

    <img id="beerImg" v-bind:src="beer.imgUrl" width="180px" height="auto" />
    <ul id="beer-stats">
      <li>
        <b>{{ beer.type }}</b>
      </li>
      <li><b>ABV : </b>{{ beer.abv }}</li>
      <li><b>IBU : </b>{{ beer.ibu }}</li>
      <li>
        <b>{{ this.setStatus }}</b>
      </li>
    </ul>
    <p id="beerDesc">{{ beer.description }}</p>
    <div id="reviewListContainer">
      <ReviewList />
      <!-- link to component holding all reviews for this beer -->
    </div>
  </div>
</template>

<script>
import ReviewList from "@/components/ReviewList.vue";
import breweryServices from "../services/BreweryService.js";
export default {
  data() {
    return {
      beer: {},
      brewery: {},
    };
  },
  computed: {
    setStatus() {
      // checks if beer is set to active, determines which string to display
      if (this.beer.current) {
return "This beer is currently on tap! ";
 
      } else {
        return "This beer is currently not on tap.";
      }
    },
  },
  methods: {
    getBrewery(){
    breweryServices.getBreweryById(this.beer.breweryId).then((response) => {
        this.brewery = response.data;
      });
    },
    editBeer() {
      this.$router.push({name: 'UpdateBeer', params: {id: this.brewery.id, beerId: this.beer.id}})
      
    },
      deleteBeer() {
      this.$router.push({name: 'ConfirmDelete', params: {beerId: this.beer.id }})
      
    },

    isAdmin() {
      // checks if current user is admin
      if (this.$store.state.logIn) {
        if (this.$store.state.user.authorities[0].name == "ROLE_ADMIN") {
          return true;
        }
      } else {
        return false;
      }
    },
  },
  created() {
    // populates local beer info based on route
    breweryServices.getBeerById(this.$route.params.id).then((response) => {
      this.beer = response.data;
    });
     breweryServices.getBreweryById(this.beer.breweryId).then((response) => {
        this.brewery = response.data;
      });
  },
  components: {
    ReviewList,
  },
};
</script>

<style>
#beerInfo {
  background: wheat;
  text-align: center;
  padding: 50px;
  margin: 10px;
  border-style: solid;
  border-width: 3px;
  width: 60%;
  justify-content: center;
  align-items: center;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "img name rating"
    "img desc type"
    "review review review";
  gap: 3px;
}
li {
  list-style-type: none;
  text-align: left;
}

#avgRatingDisplay {
  font-weight: bold;
  color: goldenrod;
  background-color: black;
  margin: 50px;
  padding: 5px;
  border: solid 3px goldenrod;
  grid-area: rating;
  width: 150px;
  align-self: center;
  justify-self: right;
}
#beerImg {
  grid-area: img;
  max-height: 100%;
  max-width: 90%;
}
#beer-name-brewery {
  margin-top: 40px;
  grid-area: name;
  align-self: start;
   justify-content: flex-start;
}
#beer-stats {
  grid-area: type;
}
#beerInfo > h2 > span {
  font-size: 25px;
  margin-top: 20px;
}
#beerDesc {
  grid-area: desc;
}
#adminBeerButtons {
  font-size: 10px;
  margin-right: 50%;
   margin-left: 40%;
  margin-bottom: 20px;
  z-index: 10;
}
#reviewListContainer {
  grid-area: review;
}


</style>