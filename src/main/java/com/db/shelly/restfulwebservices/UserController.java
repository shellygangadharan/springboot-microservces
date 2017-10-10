package com.db.shelly.restfulwebservices;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.db.shelly.Exception.UserNotFoundException;
import com.db.shelly.domain.User;
import com.db.shelly.services.UserDaoService;

@RestController
public class UserController {

   @Autowired
   private UserDaoService service;
	
	@GetMapping(value="/users")
	public List<User> findUsers(){
		return service.getUsers() ;
	}


	// HATEOAS having issues
	/* @GetMapping(value="/users/{id}")
	public  Resource<User> findUser( @PathVariable Integer id){
		User user= service.findUser(id);
		if (user== null){
			throw new UserNotFoundException("Id "+ id+" is missing") ;
		}else {
			Resource<User> resource = new Resource<User>(user) ;
			ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).findUsers()) ;
			resource.add(linkTo.withRel("users"));
			return resource;
		}
	} */
	
	 @GetMapping(value="/users/{id}")
		public  User findUser( @PathVariable Integer id){
			User user= service.findUser(id);
			if (user== null){
				throw new UserNotFoundException("Id "+ id+" is missing") ;
			}else {
					return user;
			}
		}
	
	@DeleteMapping(value="/users/{id}")
	public  User deleteUser( @PathVariable Integer id){
		User user= service.deleteUser(id);
		if (user== null){
			throw new UserNotFoundException("Id "+ id+" is missing") ;
		}else {
		return user;
		}
	}
	
	
	
	@PostMapping(value="/users")
	public ResponseEntity<Void> addUser(@Valid @RequestBody User user){
		User savedUser= service.addNewUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build() ;
	}

}
