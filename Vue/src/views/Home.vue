<template>
  <div class="maingrid">
    <div class="home border border-dark rounded">
      <h1>Denver Brew Finder</h1>
      <img src="@/Resources/output-onlinepngtools.png" id="main-page-logo" />
    </div>
    <div class="beerCard">
      <TopFiveBeer />
    </div>
    <div class="news">
      <h3>Brewery News</h3>
      <div
        class="border border-dark rounded"
        v-for="item in news"
        v-bind:Key="item.id"
      >
        <h3>Brewery: {{ item.breweryName }}</h3>
        <h4 id="newsTitle">Title: {{ item.newstitle }}</h4>
        <p id="newsbody">{{ item.body }}</p>
        <img id="newsImage" v-bind:src="item.newsImageUrl" />
      </div>
    </div>
    <div class="events">
      <h3>Upcoming Events</h3>
      <div
        class="border border-dark rounded"
        v-for="event in events"
        v-bind:Key="event.event_id"
      >
        <h3>Brewery: {{ event.brewery_name }}</h3>
        <h4 id="eventTitle">Event: {{ event.event_title }}</h4>
        <p id="eventDate">Date: {{ event.event_date }}</p>
        <p id="eventDescription">Description: {{ event.description }}</p>
        <img id="eventImage" v-bind:src="event.picture" />
      </div>
    </div>
  </div>
</template>

<script>
import breweryServices from "../services/BreweryService.js";
import TopFiveBeer from "@/components/TopFiveBeer.vue";
export default {
  name: "home",
  data() {
    return {
      news: [],
      counter: 1,
      events: [],
    };
  },
  components: {
    TopFiveBeer,
  },
  // Pulls news and events from the db to desplay on the landing page
  created() {
    breweryServices.getAllNews().then((response) => {
      this.news = response.data.reverse();
    });
    breweryServices.getAllEvents().then((response) => {
      for (let i = response.data.length - 1; i >= 0 && i > response.data.length - 5; i--) {
        this.events.push(response.data[i]);
      }
    });
  },
  methods: {
    // Method that gets brewery name whan given a brewery id
    getBreweryName(id) {
      return breweryServices.getBreweryById(id).then((response) => {
        return response.data.name;
      });
    },
  },
};
</script>
<style>
.maingrid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 10px;
  margin-top: 50px;
  margin-left: 50px;
  margin-right: 50px;
  grid-template-areas:
    "main main"
    "news beer"
    "news event";
}
.home {
  grid-area: main;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px, solid, black;
  background-color: rgba(245, 222, 179, 0.7);
}
#main-page-logo {
  width: 400px;
  height: auto;
}
.home > h1 {
  font-family: "Bungee Shade";
  font-size: 70px;
}
.news {
  text-align: center;
  grid-area: news;
  align-items: center;
  justify-content: center;
  border: 3px, solid, black;
  background-color: rgba(245, 222, 179, 0.7);
}
.events {
  text-align: center;
  height: 100%;
  width: 100%;
  align-items: center;
  justify-content: center;
  grid-area: event;
  border: 3px, solid, black;
  background-color: rgba(245, 222, 179, 0.7);
}
.beerCard {
  grid-area: beer;
  text-align: center;
  align-items: center;
  justify-content: center;
  border: 3px, solid, black;
  background-color: rgba(240, 192, 104, 0.9);
}
#eventTitle,
#newsTitle {
  font-family: helvetica;
}
</style>