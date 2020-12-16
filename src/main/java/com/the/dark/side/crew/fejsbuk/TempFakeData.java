package com.the.dark.side.crew.fejsbuk;

import com.the.dark.side.crew.fejsbuk.model.User;
import com.the.dark.side.crew.fejsbuk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// It's a temp sql data for testing only, will be delete...

@SpringBootApplication
public class TempFakeData implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TempFakeData.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.save(new User("Amelia", "Wright", "ameliawright", "awright@example.com"));
        this.userRepository.save(new User("Amelia", "Wright", "ameliawright", "sdfsd"));
        this.userRepository.save(new User("Roxane", "Pouros", "roxanepouros", "koelpingarret@example.com"));
    }

}
