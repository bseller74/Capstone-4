package com.techelevator.model;
//model for favorite brewery
public class Favorite {

		public Long user_id;
		public Long brewery_id;
		
		public Long getUser_id() {
			return user_id;
		}
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		public Long getBrewery_id() {
			return brewery_id;
		}
		public void setBrewery_id(Long brewery_id) {
			this.brewery_id = brewery_id;
		}
}
