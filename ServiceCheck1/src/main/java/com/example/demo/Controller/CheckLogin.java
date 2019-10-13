package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.userDetailsModel.LoginClass;
import com.example.demo.userDetailsModel.User;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class CheckLogin {

	@RequestMapping(value="/" ,method= RequestMethod.GET)
	public String homelogin() {
		return "Login";
	}
	
	@RequestMapping(value="/login" ,method= RequestMethod.GET)
	//@ResponseStatus(value=HttpStatus.OK)
	public String fetchLogin(@RequestParam("userid") String uid,@RequestParam("password") String password) throws RestClientException, URISyntaxException{
		RestTemplate resttemplate=new RestTemplate();
		User user=new User();
		user.setUid(uid);
		user.setPassword(password);
		
		LoginClass st=resttemplate.postForObject(new URI("http://localhost:8080/user"), user, LoginClass.class);
		if(st.isLogin())
			return "success";
		else
			return "failure";
	}
	
}
