import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import BreweryList from '../views/BreweryList.vue'
import BreweryBeers from '../views/BreweryBeers.vue'
import BeerDetails from "../components/BeerDetails.vue"
import BeerList from '@/views/BeerList.vue'
import userPage from "../views/UserPage.vue"
import adminTools from "../views/AdminTools.vue"
import AddBrewery from "../views/AddBrewery.vue"
import editBrewery from "../views/EditBrewery.vue"
import AddBeer from "../views/AddBeer.vue"
import editBeer from "../views/EditBeer.vue"
import BrewerTools from "@/views/BrewerTools.vue"
import DeleteBeerConfirm from "@/views/DeleteBeerConfirm.vue"
import DeleteUserConfirmation from "../views/DeleteUserConfirmation.vue"
import ReviewCard from "../components/ReviewCard.vue"
import AllUsers from "../components/UserList.vue"
import AdminConfirmDeleteUser from "../views/AdminDeleteUserConfirmation.vue"
import AdminConfirmDeleteBrewery from "../views/AdminConfirmDeleteBrewery.vue"
import DeleteEventConfirm from '@/views/DeleteEventConfirm.vue'
import NewsForm from "@/components/NewsForm.vue"
import DeleteNewsConfirm from '@/views/DeleteNewsConfirm.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/breweries',
      name: 'BreweryList',
      component: BreweryList,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/brewery/:id/beers",
      name: "BreweryBeers",
      component: BreweryBeers,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/brewery/beer/:id",
      name: "BeerDetails",
      component: BeerDetails,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/beer',
      name: 'AllBeers',
      component: BeerList
    },
    {
      path: "user/:id",
      name: "UserPage",
      component: userPage,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/adminTools",
      name: "AdminTools",
      component: adminTools,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/brewery/add",
      name: "AddBrewery",
      component: AddBrewery,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/brewery/:id/update",
      name: "UpdateBrewery",
      component: editBrewery,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/brewery/:id/addBeer",
      name: "AddBeer",
      component: AddBeer,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/beer/:id/update/:beerId",
      name: "UpdateBeer",
      component: editBeer,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/brewer",
      name: "BrewerTools",
      component: BrewerTools,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/confirmDelete/:beerId",
      name: "ConfirmDelete",
      component: DeleteBeerConfirm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/confirmDelete/:eventId",
      name: "ConfirmDeleteEvent",
      component: DeleteEventConfirm
    },
    {
      path: "/replies",
      name: "ReviewReplies",
      component: ReviewCard
    },
    {
      path: "/confirmDeleteUser/:id",
      name: "ConfirmDeleteUser",
      component: DeleteUserConfirmation,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/adminTools/allUsers",
      name: "AllUsers",
      component: AllUsers,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/adminTools/allUsers/user/:id",
      name: "AdminConfirmDeleteUser",
      component: AdminConfirmDeleteUser,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "adminTools/brewery/:id",
      name: "AdminConfirmDeleteBrewery",
      component: AdminConfirmDeleteBrewery,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "addNews/brewery/:id",
      name: "AddNews",
      component: NewsForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "updateNews/brewery/:id/update/:newsId",
      name: "UpdateNews",
      component: NewsForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "deleteNews/:newsId",
      name: "DeleteNews",
      component: DeleteNewsConfirm,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
