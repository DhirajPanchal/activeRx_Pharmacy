package io.active.pharmacy.gateway.service;


import io.active.pharmacy.base.dto.CustomErrorResponse;
import io.active.pharmacy.base.dto.LoginRequest;
import io.active.pharmacy.base.dto.LoginResponse;
import io.active.pharmacy.base.dto.UserDto;
import io.active.pharmacy.base.entity.Address;
import io.active.pharmacy.base.entity.User;
import io.active.pharmacy.base.exception.CustomErrorException;
import io.active.pharmacy.base.util.EntityDtoUtil;
import io.active.pharmacy.gateway.repository.AddressRepository;
import io.active.pharmacy.gateway.repository.UserRepository;

import io.active.pharmacy.gateway.security.classic.TokenProvider;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.UUID;

import static io.active.pharmacy.base.util.EntityDtoUtil.toUser;


@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;


    public Mono<User> register(UserDto userDto) {
        log.info("_register srv");

        System.out.println("START");

        return repository.findByEmail(userDto.getEmail())
                .flatMap(__ -> {
                            CustomErrorResponse customErrorResponse = CustomErrorResponse
                                    .builder()
                                    .traceId(UUID.randomUUID().toString())
                                    .timestamp(OffsetDateTime.now().now())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .statusCode(HttpStatus.BAD_REQUEST.value())
                                    .build();
                            throw new CustomErrorException("User already exists with email [ " + userDto.getEmail() + " ]", customErrorResponse);
                        }
                )
                .switchIfEmpty(Mono.defer(() -> repository.save(toUser(userDto)))).cast(User.class);


    }


    public Mono<LoginResponse> login(LoginRequest loginRequest) {
        return userDetailsService.findByUsername(loginRequest.getUsername())
                .filter(u -> passwordEncoder.matches(loginRequest.getPassword(), u.getPassword()))
                .map(tokenProvider::generateToken)
                .map(LoginResponse::new)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));

    }

    public Mono<UserDto> profile(Authentication authentication) {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        if (user != null && user.getUsername() != null) {

            Mono<User> userMono = repository.findByEmail(user.getUsername())
                    //.doOnNext(v -> System.out.println("USER : " + v))
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));

            Mono<UserDto> combinedMono = userMono.zipWhen(u -> {
                        //System.out.println("ADD : " + u.getAddressId());
                        if (u.getAddressId() != null) {
                            return addressRepository.findById(u.getAddressId())
                                    .switchIfEmpty(Mono.just(new Address()));
                        } else {
                            return Mono.just(new Address());
                        }
                    },
                    (v1, v2) -> EntityDtoUtil.toUserDto(v1, v2));

            return combinedMono;
        } else {
            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        }

//            Mono<User> userMono = repository.findByEmail(user.getUsername())
//                    .doOnNext(v -> System.out.println(" U ID : " + v.getId() + ", ADD : " + v.getAddressId()))
//                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
//
//            Mono<Address> addressMono = addressRepository.findById(userMono.map(u -> u.getAddressId()))
//                    .doOnNext(v -> System.out.println(" ADD ::" + v))
//                    .switchIfEmpty(Mono.just(new Address()));
//
//
//            return Mono.zip(userMono, addressMono)
//                    .doOnNext(c -> {
//                        System.out.println("--");
//                        System.out.println(c.getT1());
//                        System.out.println(c.getT2());
//                    })
//                    .map(c -> MappingUtil.userAndAddress2Dto(c.getT1(), c.getT2()));


//        V-3
//        if(user != null && user.getUsername() != null) {
//            return repository.findByEmail(user.getUsername())
//                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
//        } else {
//            return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));
//        }
//         V-1
//        Mono.just(
//                new ProfileResponse(
//                        user.getUsername(),
//                        user.getAuthorities().stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .map(name -> name.substring("ROLE_".length()))
//                                .collect(Collectors.toSet())));

//        V-2
//        Mono<User> userProfile = repository.findByEmail(user.getUsername());
//
//        Mono<String> userName = Mono.just(user.getUsername());
//
//        Mono<Set<String>> roles = Mono.just(user.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .map(name -> name.substring("ROLE_".length()))
//                .collect(Collectors.toSet()));
//
//
//        return userProfile.zipWith(roles)
//                .map(p -> new ProfileResponse(p.getT1(), p.getT2()));


    }
}
