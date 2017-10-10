package com.db.shelly.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.db.shelly.Exception.UserNotFoundException;
import com.db.shelly.domain.User;

@Service
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();
	private static int userCount =3;
	static{
		users.add(new User(1,"Sam",new Date()));
		users.add(new User(2,"Amit",new Date()));
		users.add(new User(3,"Hari",new Date()));
	}
	
	
	public User addNewUser(User user){
		if(user.getId() == null) {
			user.setId(userCount++);
			users.add(user);
		}
		return user;
	}

	
	public User findUser(Integer id){
		for (User user: users){
			if (id.equals(user.getId())) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public User deleteUser(Integer id){
		Iterator<User> it = users.iterator();
		while (it.hasNext()){
			User user = it.next();
			
			if (user.getId().equals(id) ){
				it.remove();
				return user;
			}
			
		}
		return null;
	}
}
