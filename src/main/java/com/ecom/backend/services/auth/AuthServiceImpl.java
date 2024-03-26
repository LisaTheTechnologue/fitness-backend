package com.ecom.backend.services.auth;

import com.ecom.backend.dto.SignupRequest;
import com.ecom.backend.dto.UserDto;
import com.ecom.backend.entity.Order;
import com.ecom.backend.entity.User;
import com.ecom.backend.enums.OrderStatus;
import com.ecom.backend.enums.UserRole;
import com.ecom.backend.repository.OrderRepository;
import com.ecom.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${app.admin.email}")
    private String adminEmail = "";
    @Value("${app.admin.password}")
    private String adminPassword = "";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OrderRepository orderRepository;

    @Override
     public UserDto createUser(SignupRequest signupRequest) {

         User user =new User();
         user.setEmail(signupRequest.getEmail());
         user.setName(signupRequest.getName());
         user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
         user.setRole(UserRole.CUSTOMER);
         User createdUser = userRepository.save(user);

         Order order = new Order();
         order.setAmount(0L);
         order.setTotalAmount(0L);
         order.setDiscount(0L);
         order.setUser(createdUser);
         order.setOrderStatus(OrderStatus.Pending);
         orderRepository.save(order);

         UserDto userDto = new UserDto();
         userDto.setId(createdUser.getId());
         return  userDto;
     }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount  = userRepository.findByRole(UserRole.ADMIN);
        if(null == adminAccount){
            User user = new User();
            user.setEmail(adminEmail);
            user.setName(adminEmail);
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode(adminPassword));
            userRepository.save(user);
        }
    }
}
