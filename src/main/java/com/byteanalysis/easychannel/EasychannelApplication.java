package com.byteanalysis.easychannel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.byteanalysis.easychannel.api.entity.User;
import com.byteanalysis.easychannel.api.repository.UserRepository;
import com.byteanalysis.easychannel.api.security.enums.ProfileEnum;

@SpringBootApplication
public class EasychannelApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasychannelApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(UserRepository consumerRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            initUsers(consumerRepository, passwordEncoder);
        };

    }
    
	private void initUsers(UserRepository consumerRepository, PasswordEncoder passwordEncoder) {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setProfile(ProfileEnum.ROLE_ADMIN);

        User find = consumerRepository.findByUsername("admin");
        if (find == null) {
            consumerRepository.save(admin);
        }
    }
}
