<template>
  <div
    id="brewerInsiderBox"
    class="container text-center col-xl-12 mx-auto border border-dark rounded"
  >
    <div id="BrewBeerList">
      <router-link
        id="route"
        v-bind:to="{ name: 'BreweryBeers', params: { id: brewery.id } }"
        >{{ brewery.name }}</router-link
      >
      <h4>Beers</h4>
      <div v-for="beer in beers" v-bind:key="beer.id" id="beerSideBar">
        <p>{{ beer.name }} &nbsp;</p>

        <p>
          <button class="btn btn-primary" v-on:click="getCurrentBeer(beer)">
            Edit
          </button>
          <button class="btn btn-danger" v-on:click="deleteBeer(beer)">
            Delete
          </button>
        </p>
      </div>
      <h4>Events</h4>
      <div v-for="event in events" v-bind:key="event.id" id="beerSideBar">
        <p>{{ event.event_title }} &nbsp;</p>

        <p>
          <button
            class="btn btn-primary"
            v-on:click.prevent="getCurrentEvent(event)"
          >
            Edit
          </button>
          <button
            class="btn btn-danger"
            v-on:click.prevent="deleteEvent(event)"
          >
            Delete
          </button>
        </p>
      </div>
      <h4>NEWS</h4>
      <div v-for="item in news" v-bind:key="item.newsId" id="beerSideBar">
        <p>{{ item.newstitle }} &nbsp;</p>

        <p>
          <button
            class="btn btn-primary"
            v-on:click.prevent="getCurrentNews(item)"
          >
            Edit
          </button>
          <button
            class="btn btn-danger"
            v-on:click.prevent="deleteNews(item)"
          >
            Delete
          </button>
        </p>
      </div>

      <div id="BTbuttons">
        <button class="btn btn-primary" v-on:click="addBeer()">Add Beer</button>
        <button class="btn btn-primary" v-on:click="toggleVisEvent()">
          Add Event
        </button>
        <button class="btn btn-primary" v-on:click="addNews()">Add News</button>
        <button
          class="btn btn-primary"
          id="updateBreweryInformationButton"
          v-on:click="toggleVisUpdate()"
        >
          Update Brewery Information
        </button>
      </div>
    </div>

    <form class="BIforms" v-if="VisUpdate">
       <h4>Update Your Brewery</h4>
      <label for="name">Brewery name</label>
      <input
        v-model="brewery.name"
        type="text"
        name="name"
        placeholder="Brewery name"
      />
      <br />
      <label for="address">Address</label>
      <input
        v-model="brewery.address"
        type="text"
        name="address"
        placeholder="Address"
      />
      <br />
      <label for="description">Description</label>
      <input
        v-model="brewery.description"
        type="text"
        name="description"
        placeholder="Description"
      />
      <br />
      <label for="image">Image url</label>
      <input
        v-model="brewery.image"
        type="text"
        name="image"
        placeholder="Image url"
      />
      <br />
      <label for="brewery_url">Brewery url</label>
      <input
        v-model="brewery.brewery_url"
        type="text"
        name="brewery_url"
        placeholder="Brewery url"
      />
      <br />
      <label for="phone">Phone number</label>
      <input
        v-model="brewery.phone"
        type="text"
        name="phone"
        placeholder="Phone number"
      />
      <br />
      <label for="hours">Hours</label>
      <input
        v-model="brewery.hours"
        type="text"
        name="hours"
        placeholder="Hours"
      />
      <br />
      <p>
        Active:
        <span>
          <input
            v-model="brewery.active"
            type="checkbox"
            name="active"
            placeholder="Active"
        /></span>
      </p>
      <br />
      <button class="btn btn-primary" v-on:click.prevent="updateBrewery">
        Update
      </button>
      <button class="btn btn-danger" v-on:click.prevent="clearBreweryForm">
        Cancel
      </button>

      <br />
    </form>

    <form class="BIforms" v-if="VisEvent">
       <h2>Add an event for your brewery!</h2>
      <label for="event_title">Event Title</label>
      <input
        v-model="currentEvent.event_title"
        type="text"
        name="title"
        placeholder="Event Title"
      />
      <label for="event_date">Date</label>
      <input
        v-model="currentEvent.event_date"
        type="date"
        name="date"
        placeholder="Event Date"
      />
      <label for="description">Description</label>
          <textarea rows="3" cols="23"
        v-model="currentEvent.description"
        type="text"
        name="description"
        placeholder="Event Description"
      />
      <label for="picture">Image URL</label>
      <input
        v-model="currentEvent.picture"
        type="text"
        name="active"
        placeholder="Event Picture"
      />
      <br />
      <button
        class="btn btn-primary"
        v-on:click.prevent="addEvent"
        v-if="currentEvent.event_id == null"
      >
        Add
      </button>
      <button
        class="btn btn-primary"
        v-on:click.prevent="updateEvent(currentEvent)"
        v-else
      >
        Update
      </button>
      <button class="btn btn-danger" v-on:click.prevent="clearEventForm">
        Cancel
      </button>
    </form>
    <img v-if="picVis" v-bind:src="this.brewery.image" />
  </div>
</template>

<script>
import BreweryService from "@/services/BreweryService.js";
export default {
  data() {
    return {
      currentBeer: {},
      beers: [],
      brewery: {},
      VisUpdate: false,
      VisEvent: false,
      VisNews: false,
      events: [],
      currentEvent: {},
      news: [],
      currentNews: {},
    };
  },
  computed: {
    settingBreweryId() {
      let id = this.brewery.id;
      return id;
    },
    picVis() {
      if (this.VisEvent == false && this.VisUpdate == false) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    getCurrentBeer(beer) {
      this.currentBeer = beer;
      this.$router.push({
        name: "UpdateBeer",
        params: { id: this.brewery.id, beerId: this.currentBeer.id },
      });
    },
    addBeer() {
      this.currentBeer = {};
      this.$router.push({ name: "AddBeer", params: { id: this.brewery.id } });
    },
    deleteBeer(beer) {
      this.currentBeer = beer;
      this.$router.push({
        name: "ConfirmDelete",
        params: { beerId: this.currentBeer.id },
      });
    },
    clearBreweryForm() {
      this.VisUpdate = false;
    },
    clearEventForm() {
      this.currentEvent = {};
      this.VisEvent = false;
    },
    updateBrewery() {
      BreweryService.updateBrewery(this.brewery);
      this.$router.push({
        name: "BreweryBeers",
        params: { id: this.brewery.id },
      });
    },
    getCurrentEvent(event) {
      this.currentEvent = event;
      this.VisUpdate = false;
      this.VisEvent = true;
      //to acquire current event and display edit form
    },
    deleteEvent(event) {
      this.currentEvent = event;
      this.$router.push({
        name: "ConfirmDeleteEvent",
        params: { eventId: this.currentEvent.event_id },
      });
      //to delete event from DB
    },
    addEvent() {
      this.currentEvent.brewery_id = this.brewery.id;
      BreweryService.createEvent(this.currentEvent).then(() => {
        this.VisEvent = false;
        this.$emit("finished");
        this.$router.push({
          name: "BreweryBeers",
          params: { id: this.brewery.id },
        });
      });
    },
    updateEvent(event) {
      BreweryService.updateEvent(event).then(() => {
        this.VisEvent = false;
        this.$emit("finished");
      });
    },

    //NEWS METHODS
    getCurrentNews(item) {
      console.log(item);
      this.currentNews = item;
      this.$router.push({
        name: "UpdateNews",
        params: { id: this.brewery.id, newsId: this.currentNews.newsId },
      });
    },
    addNews() {
      this.currentNews = {};
      this.$router.push({ name: "AddNews", params: { id: this.brewery.id } });
    },
    deleteNews(news){
      this.$router.push({
        name: "DeleteNews",
        params: { newsId: news.newsId },
      });
    },
    toggleVisUpdate() {
      if (this.VisUpdate == true) {
        this.VisUpdate = false;
      } else {
        this.VisEvent = false;
        this.VisUpdate = true;
      }
    },
    toggleVisEvent() {
      if (this.VisEvent == true) {
        this.VisEvent = false;
      } else {
        this.VisUpdate = false;
        this.currentEvent = {};
        this.VisEvent = true;
      }
    },
  },
  created() {
    BreweryService.getBreweryByBrewer(this.$store.state.user.id).then(
      (response) => {
        this.brewery = response.data;
        BreweryService.getBeerByBrewery(this.brewery.id).then((response) => {
          this.beers = response.data;
          this.currentBeer = {};
          BreweryService.getEventsById(this.brewery.id).then((response) => {
            this.events = response.data;
            BreweryService.getNewsByBrewery(this.brewery.id).then(
              (response) => {
                this.news = response.data;
                this.currentBeer = {};
              }
            );
          });
        });
      }
    );
  },
};
</script>

<style>
#brewerInsiderBox {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: space-between;
  height: 100%;
  min-height: 550px;
  
}
#BrewBeerList {
  display: flex;
  flex-direction: column;
  align-items: space-between;
  width: 30%;
  height: 100%;
  border-right: black 5px solid;
  margin-right: 5px;
}
#beerSideBar {
  font-weight: bold;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-right: 10px;
}
.BIforms {
  display: flex;
  flex-direction: column;
  width: 70%;
}
.BIforms label {
  text-align: left;
  margin-left: 50px;
}
.BIforms p {
  text-align: left;
  margin-left: 50px;
}
.BIforms input {
  margin-left: 50px;
  width: 50%;
}
.BIforms textarea {
    margin-left: 50px;
  width: 50%;
}

#brewerInsiderBox button {
  margin: 5px;
}
#brewerInsiderBox img {
  position: absolute;
  left: 40%;
  right: 50%;
  height: 500px;
  width: auto;
  
}
#updateBreweryInformationButton {
  width: auto;
}
#route {
  font-family: "Bungee Inline";
  font-size: 30px;
  color:#630f0f;
}

#route:hover {
  color: whitesmoke;
}
#route:visited {
  color: #630f0f;
}
#route:visited:hover {
  color: whitesmoke;
}


</style>