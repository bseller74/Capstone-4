<template>
  <div class="container text-center col-xl-12 mx-auto rounded">
    <h4>Are you sure you wish to delete {{ event.event_title }}?</h4>
    <button class="btn btn-danger" v-on:click="DeleteEvent(event)">Yes</button>
    <button class="btn btn-primary" v-on:click="Cancel">Cancel</button>
  </div>
</template>

<script>
import BreweryService from "../services/BreweryService";
export default {
  data() {
    return {
      event: {},
    };
  },
  methods: {
    DeleteEvent(event) {
      //delete event from db, then send back to brewer tools
      BreweryService.deleteEvent(event.event_id).then(() => {
        window.alert(`${event.event_title} has been deleted!`);
        this.$router.push({ name: "BrewerTools" });
      });
    },
    Cancel() {
      //cancel delete, send back to brewer tools
      this.$router.push({ name: "BrewerTools" });
    },
  },
  created() {
    //collect event from route param TO DO
    BreweryService.getEventByEventId(this.$route.params.eventId).then(
      (response) => {
        this.event = response.data;
      }
    );
  },
};
</script>

<style>
</style>