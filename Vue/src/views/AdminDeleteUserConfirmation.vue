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
  props: ["id"],
  methods: {
    deleteUser() {
        BreweryService.deleteUserById(this.user.id).then(() => {
          console.log(`User ${this.user.username} deleted`);
        });
      this.$router.push({ name: "AllUsers" });
    },
    Cancel() {
      this.$router.push({ name: "AllUsers" });
    },
  },
  created() {
    BreweryService.getUser(this.$route.params.id).then((response) => {
      this.user = response.data;
    });
  },
};
</script>

<style>
</style>