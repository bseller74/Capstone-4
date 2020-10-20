<template>
  <div
    class="container col-xl-5 border border-dark rounded aligncontent"
  >
    <div class="userGrid">
      <h3>Users</h3>
      <div class="users" v-for="user in users" v-bind:key="user.id">
        <span class="flexUser"> {{ user.id }} {{ user.username }} </span>
        <button
          class="btn btn-danger deleteUser"
          v-on:click="deleteUser(user.id)"
        >
          Delete user
        </button>
      </div>
      <h3>Brewers</h3>
      <div class="brewers" v-for="brewer in brewers" v-bind:key="brewer.id">
        <span> {{ brewer.id }} {{ brewer.username }} </span>
        <button
          class="btn btn-danger deleteUser"
          v-on:click="deleteUser(brewer.id)"
        >
          Delete user
        </button>
      </div>
      <h3>Admin</h3>
      <div class="admin" v-for="admin in admins" v-bind:key="admin.id">
        <span> {{ admin.id }} {{ admin.username }} </span>
        <button
          class="btn btn-danger deleteUser"
          v-on:click="deleteUser(admin.id)"
        >
          Delete user
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import breweryServices from "../services/BreweryService.js";
export default {
  data() {
    return {
      users: [],
      brewers: [],
      admins: [],
    };
  },
  created() {
    breweryServices.getAllUsers().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        if (response.data[i].authorities[0].name == "ROLE_USER") {
          this.users.push(response.data[i]);
        } else if (response.data[i].authorities[0].name == "ROLE_BREWER") {
          this.brewers.push(response.data[i]);
        } else {
          this.admins.push(response.data[i]);
        }
      }
    });
  },
  methods: {
    deleteUser(id) {
      this.$router.push({ name: "AdminConfirmDeleteUser", params: { id: id } });
    },
  },
};
</script>

<style>
.deleteUser {
  width: 115px;
}
.userGrid {
  display: flex;
  flex-direction: column;
  width: 300px;  
}
.users {
  display: flex;
  justify-content: space-between;
  margin: 5px;
}
.brewers {
  display: flex;
  justify-content: space-between;
  margin: 5px;
}
.admin {
  display: flex;
  justify-content: space-between;
  margin: 5px;
}
.aligncontent {
  display: flex;
  align-items: center;
}
</style>