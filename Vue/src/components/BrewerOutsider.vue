<template>
  <div
    class="container text-center col-xl-5 mx-auto border border-dark rounded"
  >
    <h3>Are you a Brewer?</h3>
    <p>
      Looking to join our community of Denver Breweries? Submit this form and
      our Admin will be in touch!
    </p>

    <div class="breweryFormComponent">
      <form class = "addBrewery" id="newBreweryForm" v-if="vis">
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
         <p>
        Is your brewery currently active?&nbsp;&nbsp;&nbsp;
        <span>
        <input
          v-model="newBrewery.active"
          type="checkbox"
          name="active"
          placeholder="Active"
        /> </span>
      </p>
        <br />
        <span>
          <button class="btn btn-primary" v-on:click.prevent="SendRequest">
            Request Brewery
          </button>
          <button class="btn btn-primary" v-on:click.prevent="CancelForm">
            Cancel
          </button>
        </span>
        <br />
      </form>
    </div>
    <!-- displays message after form is submitted -->
    <div v-if="!vis">
      <h3>Thanks for submitting your request!</h3>
      <p>Our team will review your request and will be reaching out shortly.</p>
    </div>
  </div>
</template>

<script>
import BreweryService from "../services/BreweryService";
export default {
  data() {
    return {
      newBrewery: {},
      vis: true,
    };
  },
  methods: {
    CancelForm() {
      this.newBrewery = {};
      this.$router.push("/");
    },
    SendRequest() {
      //creates request in DB for admin, shows message
      this.newBrewery.userId = this.$store.state.user.id;
      this.newBrewery.processed = false;
      BreweryService.SendPendingBreweryRequest(this.newBrewery).then(() => {
        this.vis = false;
        this.newBrewery = {};
      });
    },
  },
  created() {
    //sets form visible and empty
    this.vis = true;
    this.newBrewery = {};
  },
};
</script>

<style>
#breweryFormComponent {
  width: 800px;
  align-self: center;
  justify-self: center;
}
#newBreweryForm {
  align-self: center;
  justify-self: center;
}

.addBrewery p {
    text-align: center;
}


</style>