<template>
  <div class="eventForm">
      <form id="addEventForm">
        <label for="event_title">Event Title</label>
            <input
             v-model="newEvent.event_title"
             type="text"
             name="title"
             placeholder="Event Title"
             />
        <label for="event_date">Date</label>
            <input
             v-model="newEvent.event_date"
             type="text"
             name="date"
             placeholder="Event Date"
             /> 
         <label for="description">Description</label>
            <input
             v-model="newEvent.description"
             type="text"
             name="description"
             placeholder="Event EEFEF"
             />
         <label for="picture">Image URL</label>
            <input
             v-model="newEvent.picture"
             type="text"
             name="active"
             placeholder="Event Picture"
             />
             <br>
            <button class="btn btn-primary" v-on:click="addEvent">Add</button>
            <button class="btn btn-primary" v-on:click="updateEvent">Update</button>
            <button class="btn btn-primary" v-on:click="cancel">Cancel</button>
      </form>
  </div>
</template>

<script>
import BreweryService from "@/services/BreweryService.js"
export default {
    data() {
        return{
            newEvent:{
                breweryId: this.$route.params.id,
            },
            brewery:{},

        };
    },
     created() {
    
      BreweryService.getBreweryById(this.$route.params.id).then((response) => {
        this.brewery = response.data;
      });
    
  },
    methods: {
        addEvent(){
            BreweryService.createEvent(this.$route.params.id).then(
                (response) => {
                    this.newEvent = response.data;
                }
            )
        },
        cancel() {
      if (this.$store.state.user.authorities[0].name == 'ROLE_BREWER'){
        this.$router.push({ name: "BrewerTools" });
      }else{
      this.$router.push({ name: "BreweryBeers" });
      }
    },
     updateEvent() {
      BreweryService.updateEvent(this.newEvent);
      if (this.$store.state.user.authorities[0].name == 'ROLE_BREWER'){
        this.$router.push({ name: "BrewerTools" });
      }else{
      this.$router.push({ name: "BreweryBeers" });
      }
    }

}
};
</script>

<style>
.eventForm {
  display: flex;
  flex-direction: column;
  border: 1px, solid, black;
  background-color: rgba(245, 222, 179, 0.7);
  width: 100%;
}
#addEventForm {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
div > form > button {
  width: 80px;
  align-items: center;
}
</style>