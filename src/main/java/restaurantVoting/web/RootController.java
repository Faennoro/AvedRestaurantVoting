package restaurantVoting.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:restaurants";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String getUsers() {
        return "users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/votes")
    public String getVotes() {
        return "votes";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/restaurants")
    public String getRestaurants() {
        return "restaurants";
    }
}
