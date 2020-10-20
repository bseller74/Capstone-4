package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.LoginDTO;
import com.techelevator.model.RegisterUserDTO;
import com.techelevator.model.User;
import com.techelevator.model.UserAlreadyExistsException;
import com.techelevator.model.UserRoleChange;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

@RestController
@CrossOrigin
public class AuthenticationController {

	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private UserDAO userDAO;

	public AuthenticationController(TokenProvider tokenProvider,
			AuthenticationManagerBuilder authenticationManagerBuilder, UserDAO userDAO) {
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.userDAO = userDAO;
	}
//  controller for creating token for security purposes 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDto) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginDto.getUsername(), loginDto.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication, false);

		User user = userDAO.findByUsername(loginDto.getUsername());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		return new ResponseEntity<>(new LoginResponse(jwt, user), httpHeaders, HttpStatus.OK);
	}
// controller to create new user
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(@Valid @RequestBody RegisterUserDTO newUser) {
		try {
			User user = userDAO.findByUsername(newUser.getUsername());
			throw new UserAlreadyExistsException();
		} catch (UsernameNotFoundException e) {
			userDAO.create(newUser.getUsername(), newUser.getPassword(), newUser.getRole());
		}
	}
// controller for getting user role
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public User getUserById(@PathVariable("userId") long userId) {
		return userDAO.getUserById(userId);
	}
// controller for changing user role
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
	public void updateUserRole(@PathVariable("userId") long userId, @Valid @RequestBody UserRoleChange user) {
		userDAO.updateUserRole(user);
	}
// controller for deleting user role
	@RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
	public void deleteUserById(@PathVariable long id) {
		userDAO.deleteUserById(id);
	}
// controller for deleting bad users 
	@RequestMapping(path = "/allUsers", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	/**
	 * Object to return as body in JWT Authentication.
	 */
	static class LoginResponse {

		private String token;
		private User user;

		LoginResponse(String token, User user) {
			this.token = token;
			this.user = user;
		}

		@JsonProperty("token")
		String getToken() {
			return token;
		}

		void setToken(String token) {
			this.token = token;
		}

		@JsonProperty("user")
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	}
}
