<template>
  <div>
    <h2>Pending Brewers Requests</h2>
    <div v-for="user in pendingBrewers" v-bind:key="user.id" class="pendingBrewer">
      {{ user.id }} || {{ user.userId }} || {{ user.username }} ||
      {{ user.breweryId }}
      <span class="navlink" v-on:click="approve(user)">Approve</span>
      <span class="navlink" v-on:click="decline(user)">Decline</span>
      <br>
    </div>
  </div>
</template>

<script>
import breweryService from "../services/BreweryService";
export default {
  data() {
    return {
      pendingBrewers: [],
      processedBrewers: [],
    };
  },
  created() {
    breweryService.getAllBrewerRequests().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        if (response.data[i].processed) {
          this.processedBrewers.push(response.data[i]);
        } else {
          this.pendingBrewers.push(response.data[i]);
        }
      }
    });
  },
  methods: {
    addBrewery() {
      this.$router.push("/brewery/add");
    },
    approve(user) {
      user.processed = true;
      user.newRole = "ROLE_BREWER";
      breweryService.switchProccessedStatus(user).then((response) => {
        if (response.status == 200) {
          console.log("proccessed");
        }
      });
      breweryService.changeUserRole(user.userId, user).then((response) => {
        if (response.status == 200) {
          console.log("role changed");
        }
      });
      breweryService.setBrewerToBrewery(user).then((response) => {
        if (response.status == 200) {
          console.log("Brewery updated");
        }
        this.$emit('finished');
      });
    },
    decline(user) {
      user.processed = true;
      breweryService.switchProccessedStatus(user).then((response) => {
        if (response.status == 200) {
          console.log("proccessed");
        }
        this.$emit('finished');
      });
    },
  },
};
</script>

<style>
.pendingBrewer {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
}
</style>