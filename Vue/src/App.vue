<template>
  <div id="app">
    <div
      id="nav"
      class="navbar navbar-expand-lg navbar-dark py-lg-2 navbar-fixed-top"
    >
      <div>
        <router-link class="navlink" v-bind:to="{ name: 'home' }"
          ><img src="@/Resources/output-onlinepngtools.png" /></router-link
        >&nbsp;|&nbsp;
        <router-link
          class="navlink"
          v-if="this.$store.state.logIn"
          v-bind:to="{
            name: 'UserPage',
            params: { id: this.$store.state.user.id },
          }"
          >My page</router-link
        >
        <router-link class="navlink" v-else v-bind:to="{ name: 'login' }"
          >My page</router-link
        >&nbsp;|&nbsp;
        <router-link class="navlink" v-bind:to="{ name: 'BreweryList' }"
          >Breweries</router-link
        >&nbsp;|&nbsp;
        <router-link class="navlink" v-bind:to="{ name: 'AllBeers' }"
          >Beers</router-link
        >&nbsp;&nbsp;
      </div>
      <div id="right-side-nav">
        <div>
          <router-link
            class="navlink"
            v-bind:to="{ name: 'AdminTools' }"
            v-if="isAdmin()"
            >Admin Tools</router-link
          >
          <router-link
            class="navlink"
            v-bind:to="{ name: 'BrewerTools' }"
            v-else-if="isBrewer()"
            >Brewer Tools</router-link
          >
          <span class="navlink" v-else v-on:click="LoggedIn"
            >Add Your Brewery</span
          >&nbsp;|&nbsp;
        </div>
        <div>
          <router-link
            class="navlink"
            v-bind:to="{ name: 'logout' }"
            v-if="this.$store.state.logIn"
            >Logout</router-link
          >
          <router-link class="navlink" v-else v-bind:to="{ name: 'login' }"
            >Login</router-link
          >&nbsp;|&nbsp;
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  methods: {
    // Checks if the usre is logged in and is they are ad admin
    isAdmin() {
      if (this.$store.state.logIn) {
        if (this.$store.state.user.authorities[0].name == "ROLE_ADMIN") {
          return true;
        }
      } else {
        return false;
      }
    },
    // Checks if the user is logged in and is a brewer
    isBrewer() {
      if (this.$store.state.logIn) {
        if (this.$store.state.user.authorities[0].name == "ROLE_BREWER") {
          return true;
        }
      } else {
        return false;
      }
    },
    // alerts user they are not logged in and pushes them to the log in page
    LoggedIn() {
      if (!this.$store.state.logIn) {
        window.alert(
          "You must be signed in to add your brewery - create a free account to proceed!"
        );
        this.$router.push({ name: "login" });
      } else {
        this.$router.push({ name: "BrewerTools" });
      }
    },
  },
};
</script>

<style>
span:hover {
  cursor: pointer;
}
#nav {
  display: flex;
  justify-content: space-between;
  font-family: Arial, Helvetica, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background-color: goldenrod;
  color: whitesmoke;
  width: 100%;
  position: fixed;
  top: 0;
  font-size: 16pt;
  z-index: 5;
  font-family: "Bungee Inline";
}
#nav img {
  width: 75px;
  height: auto;
}
#right-side-nav {
  display: flex;
}
.navlink {
  color: #630f0f;
}
.navlink:hover {
  color: whitesmoke;
}
.navlink:visited {
  color: #630f0f;
}
.navlink:visited:hover {
  color: whitesmoke;
}
.whitelink {
  color: whitesmoke;
}
.whitelink:visited {
  color: wheat;
}
.whitelink:hover {
  color: goldenrod;
}
.whitelink:visited:hover {
  color: goldenrod;
}
body {
  padding-top: 50px;
  /* background-image: url("https://skookumbrewery.com/wp-content/uploads/2019/05/cropped-IMG_4560-6.jpg"); */
  background-image: url("https://www.doctorhomebrew.com/wp-content/uploads/2014/05/storageok.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
}
.container {
  display: flex;
  flex-direction: column;
  border: 1px, solid, black;
  background-color: rgba(245, 222, 179, 0.7);
  width: 50%;
  position: absolute;
  top: 50px;
  padding: 10px;
}
/* Overwrites some bootstrap */
.btn-primary,
.btn-primary:active,
.btn-primary:visited {
  background-color: #2c0603 !important;
  border-color: black !important;
}
/* Overwrites some bootstrap */
.btn-primary:hover {
  color: black !important;
  background-color: #fbf1cd !important;
  border: 1px solid black !important;
}
h1,
h2,
h3,
h4,
h5 {
  font-family: "Bungee Inline";
}
h6,
p {
  font-family: helvetica;
}
div > img {
  margin: 10px;
  height: 300px;
  width: auto;
}
@import url("https://fonts.googleapis.com/css2?family=Bungee&family=Bungee+Inline&family=Bungee+Outline&family=Bungee+Shade&family=Freckle+Face&family=Frijole&family=Salsa&family=Skranji&family=Walter+Turncoat&display=swap");
</style>