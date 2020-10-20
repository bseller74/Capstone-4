import axios from 'axios';

const http = axios.create({ baseURL: 'http://localhost:8080' })

export default {

    getBreweryList() {
        return http.get('/breweries');
    },
    getBreweryById(id) {
        return http.get(`/brewery/${id}`)
    },
    getBreweryByBrewer(id){
        return http.get(`/brewery/brewer/${id}`)
    },
    getBeerList() {
        return http.get('/beer');
    },
    getBeerById(id) {
        return http.get(`/beer/${id}`)
    },
    getBeerByBrewery(id) {
        return http.get(`/brewery/${id}/beers`)
    },
    addBrewery(brewery) {
        return http.post("/addBrewery", brewery)
    },
    updateBrewery(brewery) {
        return http.put(`/brewery/${brewery.id}`, brewery)
    },
    addBeer(beer) {
        return http.post("/addBeer", beer)
    },
    updateBeer(beer) {
        return http.put(`/beer/${beer.id}`, beer)
    },
    getReviewByBeer(id) {
        return http.get(`/beer/${id}/reviews`)
    },
    getReviewByBrewery(id) {
        return http.get(`/brewery/${id}/reviews`)
    },
    addReview(review) {
        return http.post("/new-review", review)
    },
    deleteReview(review) {
        return http.delete('/delete-review', review)
    },
    getUser(userId) {
        return http.get(`/user/${userId}`)
    },
    sendBrewerRequest(id, user) {
        return http.post(`/brewerRequest/${id}`, user)
    },
    getReviewByUser(userId) {
        return http.get(`/user/${userId}/reviews`)
    },
    getAllBrewerRequests() {
        return http.get("/brewerRequest")
    },
    SendPendingBreweryRequest(request){
        return http.post("/addBreweryRequest", request)
    },
    switchProccessedStatus(request) {
        return http.put(`/processedStatus`, request)
    },
    changeUserRole(userId, user) {
        return http.put(`/user/${user.id}`, user)
    },
    setBrewerToBrewery(user) {
        return http.put("/setBrewerToBrewery", user)
    }, 
    deleteBeer(beerId){
        return http.delete(`/beer/${beerId}`)
    },
    getAllBreweryRequests() {
        return http.get("/getAllPendingBreweryRequest")
    },
    updateBreweryRequest(request) {
        return http.put("/updateBreweryRequest", request)
    },
    getNewsByBrewery(breweryId){
        return http.get(`/news/${breweryId}`)
    },
    addNews(news){
        return http.post('/addNews', news)
    },
    updateNews(news){
        return http.put(`/updateNews`, news);
    },
    getAllNews() {
        return http.get("/allNews")
    },
    deleteNews(id){
        return http.delete(`/news/${id}`)
    },
    getNewsByNewsId(newsId){
        return http.get(`/getNewsById/${newsId}`)
    },
    getBreweryByName(name) {
        return http.get(`/getBreweryByName/${name}`)
    },
    getFavoritesByUser(id){
        return http.get(`/favorite/${id}`)
    },
    addFavorite(user_id, brewery_id){
        return http.post(`/favorite/${user_id}/${brewery_id}`)
    },
    deleteFavorite(user_id, brewery_id){
        return http.delete(`/favorite/${user_id}/${brewery_id}`)
    },
    deleteUserById(id) {
        return http.delete(`/user/${id}`)
    },
    getEventsById(breweryId){
        return http.get(`/breweryEvents/${breweryId}`)
    },
    getEventByEventId(id){
        return http.get(`/breweryEvents/event/${id}`)
    },
    deleteEvent(event_id){
        return http.delete(`/deleteEvent/${event_id}`)
    },
    updateEvent(event){
        return http.put('/updateEvent', event)
    },
    createEvent(event){
        return http.post("/newEvent", event)
    },
    getReviewReply() {
        return http.get("/replies")
    },
    getRepliesByReviewId(id){
        return http.get(`/review/${id}/replies`)
    },
    addReviewReply(replyReview) {
        return http.post("/replies", replyReview)
    },
    getAllUsers() {
        return http.get("/allUsers")
    },
    deleteBrewery(id) {
        return http.delete(`/brewery/${id}`)
    },
    deleteReplies(replyReview) {
        return http.delete("/replies", replyReview)
    },
    getAllEvents(){
        return http.get('/events')
    },
    getTopFiveBeers(){
        return http.get('/topbeers')
    },

}
