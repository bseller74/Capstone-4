<template>
  <div class="container text-center col-xl-12 mx-auto rounded">
    <h4>Are you sure you wish to delete {{ user.username }}?</h4>
    <button class="btn btn-danger" v-on:click="deleteUser()">Yes</button>
    <button class="btn btn-primary" v-on:click="Cancel">Cancel</button>
  </div>
</template>

<script>
import BreweryService from "../services/BreweryService";
export default {
  data() {
    return {
      user: {},
    };
  },
  methods: {
    // Deletes users after they click the delete from the "my page"
    deleteUser() {
      BreweryService.deleteUserById(this.$route.params.id).then(() => {
        console.log(`User ${this.user.username} deleted`);
      });
      this.$router.push({name: "logout"})
    },
    Cancel() {
      this.$router.push({ name: "UserPage" });
    },
  },
  // Gets user info by user id
  created() {
    BreweryService.getUser(this.$route.params.id).then((response) => {
      this.user = response.data;
    });
  },
};
</script>

<style>
</style>