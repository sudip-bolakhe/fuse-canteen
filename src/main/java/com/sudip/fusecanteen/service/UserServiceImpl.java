package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.UserDTO;
import com.sudip.fusecanteen.model.Role;
import com.sudip.fusecanteen.model.User;
import com.sudip.fusecanteen.repository.UserRepository;
import com.sudip.fusecanteen.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final RoleService roleService;

    @Value("${auth.url}")
    private String authUrl;

    @Value("${security.clientId}")
    String clientId;

    @Value("${security.clientSecret}")
    String clientSecret;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder
            , final UserMapper userMapper, final RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @Override
    public User add(UserDTO userDTO) {
        List<Role> roles = roleService.getByNames(userDTO.getRoles());
        User user = userMapper.convertToUser(userDTO, roles);
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userRepository.save(user);
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public List<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found "));
    }

    @Override
    public ResponseEntity<?> login(UserDTO userDTO) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(authUrl).path("/oauth/token")
                .queryParam("username", userDTO.getUsername())
                .queryParam("password", userDTO.getPassword())
                .queryParam("grant_type", "password").build();
        return restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, new HttpEntity<>(null, getHeaders()), String.class);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Basic " + getBasics());
        return httpHeaders;
    }

    private String getBasics() {
        return Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
    }
}
