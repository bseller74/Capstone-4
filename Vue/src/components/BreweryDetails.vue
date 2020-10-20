<template>
  <div id="breweryInfo" class="container text-center col-xl-12 mx-auto rounded">
    <div class="flexLeft">
      <img v-bind:src="brewery.image" width="180px" height="auto" />
    </div>
    <div class="flexRight">
      <h2 id="name">
        {{ brewery.name }}
      </h2>
      <p id="address">
        {{ brewery.address }}
      </p>
      <p id="phone">
        {{ brewery.phone }}
      </p>
      <p id="hours">
        {{ brewery.hours }}
      </p>
      <p id="description">
        {{ brewery.description }}
      </p>
      <a class="navlink" target="blank" v-bind:href="brewery.brewery_url">Web site</a> <br />
      <br />
      <a
        target="blank"
        class="navlink"
        v-bind:href="directions + brewery.address"
        >Directions</a
      >
      <router-link
        class="navlink"
        v-bind:to="{ name: 'UpdateBrewery', params: { id: brewery.id } }"
        v-if="isAdmin()"
      >
        Update Brewery
      </router-link>
      <br />
      <router-link
        class="navlink"
        v-bind:to="{ name: 'AddBeer', params: { id: brewery.id } }"
        v-if="isAdmin()"
      >
        Add Beer
      </router-link>
      <br />
      <span
        class="navlink"
        v-if="brewery.brewerId == ''"
        v-on:click="sendBrewerRequest"
      >
        Claim this brewery
      </span>
      <button
        id="RemoveFavoriteBtn"
        class="btn btn-primary"
        v-if="isFavorite && this.$store.state.logIn"
        v-on:click.prevent="toggleFavorite"
      >
        Unfavorite
      </button>
      <button
        id="MakeFavoriteBtn"
        class="btn btn-primary"
        v-else-if="this.$store.state.logIn"
        v-on:click.prevent="toggleFavorite"
      >
        Favorite
      </button>
      <button
        id="deleteBrewery"
        class="btn btn-danger"
        v-on:click="deleteBrewery(brewery.id)"
        v-if="isAdmin()"
      >
        Delete brewery
      </button>
    </div>
  </div>
</template>

<script>
import BreweryService from "@/services/BreweryService.js";

export default {
  data() {
    return {
      brewery: {},
      directions: "https://www.google.com/maps/place/",
      isFavorite: false,
      brewerRequest: {},
    };
  },
  created() {
    BreweryService.getBreweryById(this.$route.params.id).then((response) => {
      this.brewery = response.data;
      BreweryService.getFavoritesByUser(this.$store.state.user.id).then(
        (response) => {
          let results = response.data;
          for (let i = 0; i < results.length; i++) {
            if (results[i].brewery_id == this.brewery.id) {
              this.isFavorite = true;
            }
          }
        }
      );
    });
  },
  methods: {
    sendBrewerRequest() {
      this.brewerRequest.userId = this.$store.state.user.id;
      this.brewerRequest.username = this.$store.state.user.username;
      BreweryService.sendBrewerRequest(
        this.$route.params.id,
        this.brewerRequest
      ).then((response) => {
        if (response.status === 200) {
          alert("Request Sent");
        }
      });
    },
    isAdmin() {
      if (this.$store.state.logIn) {
        if (this.$store.state.user.authorities[0].name == "ROLE_ADMIN") {
          return true;
        }
      } else {
        return false;
      }
    },
    toggleFavorite() {
      if (this.isFavorite == true) {
        BreweryService.deleteFavorite(
          this.$store.state.user.id,
          this.brewery.id
        ).then(() => {
          this.isFavorite = false;
        });
      } else {
        BreweryService.addFavorite(
          this.$store.state.user.id,
          this.brewery.id
        ).then(() => {
          this.isFavorite = true;
        });
      }
    },
    deleteBrewery(id) {
      this.$router.push({
        name: "AdminConfirmDeleteBrewery",
        params: { id: id },
      });
    },
  },
  components: {},
};
</script>

<style>
.flexLeft {
  display: flex;
  grid-area: img;
}
.felxRight {
  display: flex;
  grid-area: text;
}
#breweryInfo {
  display: grid;
  grid-template-columns: 1fr, 1fr;
  grid-template-areas: "img text";
  text-align: center;
  padding: 50px;
  margin: 10px;
  border-style: solid;
  border-width: 3px;
  width: 60%;
  justify-content: center;
  align-items: center;
}

#name {
  text-decoration: underline;
}
#deleteBrewery {
  width: 150px;
}
</style>