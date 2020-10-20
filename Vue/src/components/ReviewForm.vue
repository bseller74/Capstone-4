<template>
  <div>
    <button id="addReviewBtn" class="btn btn-primary" v-on:click="ChangeVis">
      Add A review
    </button>
    <div class="outterForm">
      <div class="reviewForm">
        <form v-on:submit.prevent="AddReview" v-if="visibility == true">
          <label class="label" for="title">Title</label>
          <input
            class="input"
            v-model="review.title"
            type="text"
            name="title"
            placeholder="Witty Title Here"
          />
          <br />
          <label class="label" for="overall" >Overall</label>
          
          <textarea rows="3" cols="23"
            class="input"
            v-model="review.overall"
            type="text-area"
            name="overall"
            placeholder="What is your overall impression of this brew?"
          />
          
          <br />
          <label class="label" for="color">Color</label>
          <input
            class="input "
            v-model="review.color"
            type="text"
            name="color"
            placeholder="What's it look like?"
          /><br />
          <label class="label" for="taste">Taste</label>
          <input
            class="input"
            v-model="review.taste"
            type="text"
            name="taste"
            placeholder="How's it taste?"
          /><br />
          <label class="label" for="head">Head</label>
          <input
            class="input"
            v-model="review.head"
            type="text"
            name="head"
            placeholder="Foamy or no?"
          /><br />
          <label class="label" for="smell">Smell</label>
          <input
            class="input"
            v-model="review.smell"
            type="text"
            name="smell"
            placeholder="Use those olfactory sense!"
          /><br />

          <label id="selecter" class="label" for="rating">Rating</label>
          <select
            class="input"
            v-model="review.rating"
            type="text"
            name="rating"
          >
            <option value="1">One Star</option>
            <option value="2">Two Stars</option>
            <option value="3">Three Stars</option>
            <option value="4">Four Stars</option>
            <option value="5">Five Stars</option>
          </select>
          <br />
          <label class="label" for="image">Picture URL</label>
          <input
            class="input"
            v-model="review.reviewImgUrl"
            type="text"
            name="image"
            placeholder="Pics or it didn't happen!"
          />
          <br />
          <button class="btn btn-primary reviewBtn">Submit</button>
          <button class="btn btn-danger reviewBtn" v-on:click="Cancel">Cancel</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import BreweryService from "@/services/BreweryService.js";
export default {
  data() {
    return {
      review: {
        userId: this.$store.state.user.id,
        beerId: this.$route.params.id,
        breweryId: this.beer.breweryId,
        reviewDate: "",
      },
      visibility: false,
    };
  },
  props: ["beer"],
  methods: {
    AddReview() {
      this.review.reviewDate = new Date();
      BreweryService.addReview(this.review).then(() => {
        this.$router.push({
          name: "UserPage",
          params: { id: this.$store.state.user.id },
        });
      });
    },
    Cancel() {
      this.review = {
        userId: this.$store.state.user.id,
        beerId: this.$route.params.id,
        breweryId: this.beer.breweryId,
      };

      this.visibility = false;
    },
    ChangeVis() {
      if (this.$store.state.logIn) {
        this.visibility = true;
      } else {
        this.$router.push({ name: "login" });
      }
    },

    created() {
      if (this.$route.params.userId != null) {
        BreweryService.getReviewByUser(this.$route.param.userId).then(
          (response) => {
            this.newReview = response.data;
          }
        );
      }
    },
  },
};
</script>

<style>
#addReviewBtn {
  margin-top: 10px;
  width: 200px;
  margin-bottom: 15px;
}
.outterForm {
  display: flex;
  justify-content: center;
}
.reviewForm {
  display: grid;
  grid-template-columns: 1fr, 1fr;
  grid-template-areas: "label input";
}
.label {
  display: inline-block;
  width: 100px;
  grid-area: label;
  text-align: left;
}

.textarea {
  display: inline-block;
  width: 100px;
  grid-area: label;
  margin-left: 50px;
}

.input {
  display: inline-block;
  grid-area: input;
  align-self: right;
  width: 300px;
}
.reviewBtn {
  margin-top: 8px;
  margin-right: 10px;
}
#selecter {
  width: 100px;
}
</style>