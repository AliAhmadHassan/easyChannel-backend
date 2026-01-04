package com.byteanalysis.easychannel.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.byteanalysis.easychannel.api.entity.User;
import com.byteanalysis.easychannel.api.security.enums.ProfileEnum;
import com.byteanalysis.easychannel.api.security.jwt.JwtUserFactory;
import com.byteanalysis.easychannel.api.service.UserService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

    	if(userName == "loop")
    	{
    		User user = new User();
    		user.setProfile(ProfileEnum.ROLE_LOOP);
    		user.setName("loop");
    		return JwtUserFactory.create(user);
    	}    	
    	
    		User user = userService.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userName));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}