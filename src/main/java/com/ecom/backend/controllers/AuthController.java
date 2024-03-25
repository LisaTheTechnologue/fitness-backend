package com.ecom.backend.controllers;


import com.ecom.backend.dto.AuthenticationRequest;
import com.ecom.backend.dto.SignupRequest;
import com.ecom.backend.dto.UserDto;
import com.ecom.backend.entity.User;
import com.ecom.backend.repository.UserRepository;
import com.ecom.backend.services.auth.AuthService;
import com.ecom.backend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
//@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private static final String  TOKEN_PREFIX= "Bearer ";
  private static final String HEADER_STRING = "Authorization";
  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;
  private final AuthService authService;
//  @Autowired
//  RoleRepository roleRepository;


  private final JwtUtil jwtUtil;
  private final UserDetailsService userDetailsService;

  @PostMapping("/authenticate")
  public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                        HttpServletResponse response) throws IOException, JSONException {
    try {
      authenticationManager
              .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                      authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw  new BadCredentialsException("Incorrect username or password");
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

    final String jwt = jwtUtil.generateToken(userDetails.getUsername());

    if(optionalUser.isPresent()) {
      response.getWriter().write(new JSONObject()
                      .put("userId", optionalUser.get().getId())
                      .put("role", optionalUser.get().getRole())
                      .toString()
              );
    }

    response.addHeader("Access-Control-Expose-Headers", "Authorization");
    response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, " +
            "X-Requested-With, Content-Type, Accept, X-Custom-header");
    response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
  }

  @PostMapping("/sign-up")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//    }
//
    if (authService.hasUserWithEmail(signUpRequest.getEmail())) {
      return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
    }
//
    // Create new user's account
    UserDto userDto = authService.createUser(signUpRequest);
    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }
//
//    Set<String> strRoles = signUpRequest.getRole();
//    Set<Role> roles = new HashSet<>();
//
//    if (strRoles == null) {
//      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//        case "admin":
//          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(adminRole);
//
//          break;
//        case "mod":
//          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(modRole);
//
//          break;
//        default:
//          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(userRole);
//        }
//      });
//    }
//
//    user.setRoles(roles);
//    userRepository.save(user);
//
//    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//  }
//
//  @PostMapping("/signout")
//  public ResponseEntity<?> logoutUser() {
//    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//            .body(new MessageResponse("You've been signed out!"));
//  }
}
