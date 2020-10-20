<template>
  <div>
    <h2>Pending Brewery Requests</h2>
    <p>Request Id || User Id</p>
    <div v-for="brewery in pendingBreweries" v-bind:key="brewery.id">
      <div id="breweryInfoTop" class="text-center col-xl-12 mx-auto rounded">
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
          <a class="navlink" v-bind:href="brewery.brewery_url">Web site</a>
          <br />
          <br />
        </div>
      </div>
      <p>
        {{ brewery.id }} || {{ brewery.userId }} ||
        <span class="navlink" v-on:click="approveBrewery(brewery)">Approve</span
        >&nbsp;||&nbsp;
        <span class="navlink" v-on:click="declineBrewery(brewery)"
          >Decline</span
        >
      </p>
    </div>
  </div>
</template>

<script>
import breweryService from "../services/BreweryService";
export default {
  data() {
    return {
      pendingBreweries: [],
      processedBreweries: [],
      userToUpdate: {},
    };
  },
  created() {
    breweryService.getAllBreweryRequests().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        if (response.data[i].processed) {
          this.processedBreweries.push(response.data[i]);
        } else {
          this.pendingBreweries.push(response.data[i]);
        }
      }
    });
  },
  methods: {
    approveBrewery(brewery) {
      brewery.brewerId = brewery.userId;
      brewery.active = true;
      brewery.processed = true;
      breweryService.getUser(brewery.userId).then(
        (response) => {
          this.userToUpdate = response.data;
          this.userToUpdate.userId = this.userToUpdate.id
          this.userToUpdate.newRole = "ROLE_BREWER";
          breweryService
          .changeUserRole(this.userToUpdate.id, this.userToUpdate)
          .then(
              (response) => {
              if (response.status == 200) {
                console.log("role changed");
              }
            }
          );
        });
      breweryService.updateBreweryRequest(brewery).then((response) => {
        if (response.status == 200) {
          console.log("proccessed");
        }
      });
      breweryService.addBrewery(brewery).then((response) => {
        if (response.status == 201) {
          console.log("Brewery created");
        }
        breweryService.getBreweryByName(brewery.name).then((response) => {
          brewery.breweryId = response.data.id
          breweryService
            .setBrewerToBrewery(brewery)
            .then((response) => {
              if (response.status == 200) {
                console.log("User added to brewery");
              }
            });
               this.$emit('finishedBrewery');
        })
      });
    },
    declineBrewery(brewery) {
      brewery.processed = true;
      breweryService.updateBreweryRequest(brewery).then((response) => {
        if (response.status == 200) {
          console.log("proccessed");
        }
         this.$emit('finishedBrewery');
      });
    },
  },
};
</script>

<style>
</style>