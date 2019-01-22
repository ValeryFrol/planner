package com.dailyplanner.planner.controller;

import com.dailyplanner.planner.entity.User;
import com.dailyplanner.planner.models.ErrorJSON;
import com.dailyplanner.planner.models.LoginForm;
import com.dailyplanner.planner.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class RestApiController {
    private PasswordEncoder passwordEncoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);
    private final UserManager userManager;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.mm.yyyy");

    @Autowired
    public RestApiController(UserManager userManager) {
        this.userManager = userManager;
    }


    //MultipartHttpServletRequest - чтобы принимать любые файлы
    @RequestMapping(value = "/registration", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registration(@RequestBody MultipartHttpServletRequest request, HttpSession session) {
        LOGGER.info("Попытка зарегистрироваться");
        final String name = request.getParameter("username");
        final String surname = request.getParameter("userSurname");
        final String nickName = request.getParameter("nickname");
        final String email = request.getParameter("email");
        final String password = passwordEncoder.encode(request.getParameter("password"));
        final String repeatPassword = request.getParameter("repeat_password");
        final LocalDate dateOfBirth = LocalDate.parse(request.getParameter("date_of_birth"), formatter);

        if (name == null) {
            return new ResponseEntity<>(new ErrorJSON("name is necessary"), HttpStatus.BAD_REQUEST);
        }
        if (surname == null) {
            return new ResponseEntity<>(new ErrorJSON("surname is necessary"), HttpStatus.BAD_REQUEST);
        }
        if (nickName == null) {
            return new ResponseEntity<>(new ErrorJSON("nickname is necessary"), HttpStatus.BAD_REQUEST);
        } else if (userManager.findByNickname(nickName) != null) {
            return new ResponseEntity<>(new ErrorJSON("this nickname already exists, please try another one"), HttpStatus.CONFLICT);
        }
        if (email == null) {
            return new ResponseEntity<>(new ErrorJSON("email is necessary"), HttpStatus.BAD_REQUEST);
        } else if (userManager.findByEmail(email) != null) {
            return new ResponseEntity<>(new ErrorJSON("user already exists"), HttpStatus.CONFLICT);
        }
        if (request.getParameter("password").length() < 10 || request.getParameter("password").length() > 15) {
            return new ResponseEntity<>(new ErrorJSON("password has to contain not less then 10 symbols and not more then 15 symbols"), HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (!request.getParameter("password").matches(".*\\d+.*")) {
            return new ResponseEntity<>(new ErrorJSON("password has to contain numbers"), HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (request.getParameter("password") != repeatPassword) {
            return new ResponseEntity<>(new ErrorJSON("please check your password and repeat it correctly"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (dateOfBirth == null) {
            return new ResponseEntity<>(new ErrorJSON("date of birth is necessary"), HttpStatus.BAD_REQUEST);
        }
        //todo check format of the date of birth string: is it d.mm.yyy?
        User user = new User(name, surname, dateOfBirth, nickName, password, email);
        userManager.create(user);

        User userForSession = userManager.findByEmail(email);
        session.setAttribute("user", userForSession);

        Map<String, String> info = new HashMap<>();
        info.put("username", name);
        info.put("userSurname", surname);
        info.put("nickname", nickName);
        info.put("email", email);
        info.put("date_of_birth", dateOfBirth.toString());

        //jackson
        return new ResponseEntity<>(info, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(MultipartHttpServletRequest request, HttpSession session) {
        if (request.getParameter("email") == null || request.getParameter("password") == null) {
            return new ResponseEntity<>(new ErrorJSON("fields have to be filled in"), HttpStatus.BAD_REQUEST);
        }
        LoginForm loginForm = new LoginForm(request.getParameter("email"), request.getParameter("password"));
        //todo perform try catch thing for handling exceptions
        User userForSession = userManager.findByEmail(loginForm.getEmail());
        if (userForSession == null) {
            return new ResponseEntity<>(new ErrorJSON("user doesn't exist or email is incorrect"), HttpStatus.BAD_REQUEST);
        }
        if (!passwordEncoder.matches(request.getParameter("password"), userForSession.getPassword())) {
            return new ResponseEntity<>(new ErrorJSON("password is incorrect"), HttpStatus.UNAUTHORIZED);
        }
        session.setAttribute("user", userForSession);
        return new ResponseEntity<>("login is successfully completed", HttpStatus.OK);

    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> getClientHomepage(HttpSession session) {
        final User sessionUser = (User) session.getAttribute("user");
        return new ResponseEntity<>(sessionUser, HttpStatus.OK);
        //todo to return not only the user, but also his current day and tasks and so on
    }


    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logOut(HttpSession httpSession) {
        try {
            httpSession.invalidate();
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("you have already logged out", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("you are logged out", HttpStatus.OK);
    }

}
