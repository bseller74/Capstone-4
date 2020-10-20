<template>
  <!-- this is only used by admin tools -->
  <div class="breweryFormComponent">
    <form id="newBreweryForm">
      <label for="name">Brewery name</label>
      <input
        v-model="newBrewery.name"
        type="text"
        name="name"
        placeholder="Brewery name"
      />
      <br />
      <label for="address">Address</label>
      <input
        v-model="newBrewery.address"
        type="text"
        name="address"
        placeholder="Address"
      />
      <br />
      <label for="description">Description</label>
      <textarea rows="3" cols="23"
        v-model="newBrewery.description"
        type="text"
        name="description"
        placeholder="Description"
      />
      <br />
      <label for="image">Image url</label>
      <input
        v-model="newBrewery.image"
        type="text"
        name="image"
        placeholder="Image url"
      />
      <br />
      <label for="brewery_url">Brewery url</label>
      <input
        v-model="newBrewery.brewery_url"
        type="text"
        name="brewery_url"
        placeholder="Brewery url"
      />
      <br />
      <label for="phone">Phone number</label>
      <input
        v-model="newBrewery.phone"
        type="text"
        name="phone"
        placeholder="Phone number"
      />
      <br />
      <label for="hours">Hours</label>
      <input
        v-model="newBrewery.hours"
        type="text"
        name="hours"
        placeholder="Hours"
      />
      <br />
      <label for="active">Active</label>
      <input
        v-model="newBrewery.active"
        type="checkbox"
        name="active"
        placeholder="Active"
      />
      <br />
      <button
        class="btn btn-primary"
        v-on:click="addBrewery"
        v-if="this.$route.params.id == null"
      >
        Create
      </button>
      <button class="btn btn-primary" v-on:click="updateBrewery" v-else>
        Update
      </button>
      <button class="btn btn-primary" v-on:click="cancelForm">Cancel</button>
      <br />
    </form>
  </div>
</template>

<script>
import breweryService from "../services/BreweryService";
export default {
  data() {
    return {
      newBrewery: {},
    };
  },
  props: ["brewid"],
  watch: {
    brewid: function (newVal, oldVal) {
      // watch it
      console.log("Prop changed: ", newVal, " | was: ", oldVal);
      breweryService.getBreweryById(this.brewid).then((response) => {
        this.newBrewery = response.data;
      });
    },
  },
  methods: {
    cancelForm() {
      this.newBrewery = {};
      this.$router.push({ name: "BreweryList" });
    },
    addBrewery() {
      breweryService.addBrewery(this.newBrewery);
      this.$router.push({ name: "BreweryList" });
    },
    updateBrewery() {
      breweryService.updateBrewery(this.newBrewery);
      this.$router.push({ name: "BreweryList" });
    },
  },
  created() {},
};
</script>

<style>
#breweryFormComponent {
  display: flex;
  flex-direction: column;
  border: 1px, solid, black;
  background-color: rgba(245, 222, 179, 0.7);
  width: 70%;
}
#newBreweryForm {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
div > form > button {
  width: 80px;
  align-items: center;
}
</style>