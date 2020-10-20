<template>
  <div class= 'container text-center col-xl-12 mx-auto rounded'>
      <h4>Are you sure you wish to delete {{beer.name}}?</h4>
      <button class="btn btn-danger" v-on:click='DeleteBeer(beer)'>Yes</button>
        <button class="btn btn-primary" v-on:click='Cancel'>Cancel</button>
  </div>
</template>

<script>
import BreweryService from '../services/BreweryService'
export default {
    data(){
        return{
            beer : {}
        }
    },
    methods: {
        // Delets beer given a beer object
        DeleteBeer(beer){
            BreweryService.deleteBeer(beer.id).then(
                () => {
            window.alert(`${beer.name} has been deleted!`)
            if(this.$store.state.user.authorities[0].name == "ROLE_BREWER"){
            this.$router.push({name : 'BrewerTools'})
            } else{
                this.$router.push({name : 'AdminTools'})
            }
            }
            )},
        Cancel(){
            this.$router.push({name : 'BrewerTools'})
        }
    },
    // Pulls a beer by id from the db
    created(){
        BreweryService.getBeerById(this.$route.params.beerId).then(
            (response) => {
                this.beer = response.data;
            }
        )
    }
}
</script>

<style>

</style>