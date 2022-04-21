package uz.pdp.appjwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appjwttoken.payload.LoginDto;
import uz.pdp.appjwttoken.secirity.JwtProvider;
import uz.pdp.appjwttoken.service.MyAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    MyAuthService myAuthService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto dto){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    dto.getUsername(),
                    dto.getPassword()
            ));
            String token = jwtProvider.generateToken(dto.getUsername());
            return ResponseEntity.ok(token);
        }catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Login Yoki Parol Xato");
        }
    }
}
