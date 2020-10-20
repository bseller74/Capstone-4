<template>
<div> 
    <H3>Brewery Events</H3><br>
    <div id="eventCard" v-for="event in events" v-bind:key="event.event_id" >
        <h4>Brewery: {{event.name}}</h4>
        <h4 id="eventTitle">{{event.event_title}}</h4>
        <h5 id="eventDate"> {{event.event_date}}</h5>
        <p id="description">{{event.description}}</p>
        <img id="event-picture" v-bind:src="event.picture"/>
    </div>
</div>
  
</template>

<script>
import BreweryService from "@/services/BreweryService.js"

export default {
    data(){
        return{
            events: [],
            brewery: {},
        }
    },
    created () {
        BreweryService.getEventsById(this.$route.params.id).then(
            (response) => {
                this.events = response.data.reverse();
            }
        )

        BreweryService.getBreweryById(this.$route.params.id).then(
            (response) => {
                this.brewery = response.data;
                this.brewery.breweryId = this.$route.params.id;
            }
        )

    }
}
</script>

<style>
.navlink {
  color: black;
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
</style>