package hello.config;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import hello.model.Role;
import hello.model.User;
import hello.repository.RoleRepository;
import hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class WarmupDataConfig {

    @Autowired
    private WarmupDataConfig warmupDataConfig; // self-invocation for auto-proxy

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init(){
        warmupDataConfig.createData();
    }

    @Transactional
    public void createData() {
        Role userRole = roleRepository.findByRole("USER");
        Role adminRole = roleRepository.findByRole("ADMIN");

        final User user = new User();
        user.setEmail("user@user.com.br");
        user.setName("User");
        user.setLastName("User");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setActive(1);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));

        final User admin = new User();
        admin.setEmail("admin@admin.com.br");
        admin.setName("Admin");
        admin.setLastName("Admin");
        admin.setPassword(bCryptPasswordEncoder.encode("123456"));
        admin.setActive(1);
        admin.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));

        userRepository.save(user);
        userRepository.save(admin);
    }
}
