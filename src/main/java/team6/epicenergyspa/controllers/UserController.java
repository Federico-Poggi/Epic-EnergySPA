package team6.epicenergyspa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.model.User;
import team6.epicenergyspa.payload.customer.NewCustomerDTO;
import team6.epicenergyspa.payload.customer.NewCustomerRespDTO;
import team6.epicenergyspa.payload.user.NewUserDTO;
import team6.epicenergyspa.service.CustomerService;
import team6.epicenergyspa.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        return userService.findById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUserByIdAndUpdate(@PathVariable long userId, @RequestBody User modifiedUserPayload) {
        return userService.findByIdAndUpdate(userId, modifiedUserPayload);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
   // @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable long userId) {
        userService.findByIdAndDelete(userId);
    }

/*    @PostMapping("/postCustomer")
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewCustomerRespDTO saveUser(@RequestBody @Validated NewCustomerDTO newCustomerDTO, BindingResult bindingResult) throws BadRequestException {
        return customerService.save(newCustomerDTO, bindingResult);
    }*/

}