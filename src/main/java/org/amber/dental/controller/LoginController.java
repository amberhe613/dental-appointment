package org.amber.dental.controller;

import javax.validation.Valid;


import org.amber.dental.model.DoctorProfile;
import org.amber.dental.model.InsuranceCompany;
import org.amber.dental.service.DentistService;
import org.amber.dental.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.amber.dental.model.User;
import org.amber.dental.service.UserService;


/**
 * Created by chenlinquan on 4/15/18.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private DentistService dentistService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    @Qualifier("userService")
    public void setClientService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    @Qualifier("dentistService")
    public void setClientService(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @Autowired
    @Qualifier("insuranceService")
    public void setClientService(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Welcome To Dental Appointment");
        modelAndView.setViewName("user/home");
        return modelAndView;
    }

    @RequestMapping(value={"/doctors/new"}, method = RequestMethod.GET)
    public ModelAndView doctorRegister(){
        ModelAndView modelAndView = new ModelAndView();
        DoctorProfile dentist = new DoctorProfile();
        modelAndView.addObject("dentist", dentist);
        modelAndView.setViewName("doctor/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/doctors/new", method = RequestMethod.POST)
    public ModelAndView createDentist(@Valid DoctorProfile dentist, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("doctor/registration");
        } else {
            dentistService.saveDentist(dentist, user);
            modelAndView.addObject("successMessage", "Dentist has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("dentist", new DoctorProfile());
            modelAndView.setViewName("doctor/registration");

        }
        return modelAndView;
    }

    @RequestMapping(value={"/insurance/new"}, method = RequestMethod.GET)
    public ModelAndView insuranceRegister(){
        ModelAndView modelAndView = new ModelAndView();
        InsuranceCompany insuranceCompany = new InsuranceCompany();
        modelAndView.addObject("insuranceCompany", insuranceCompany);
        modelAndView.setViewName("insurance/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/insurance/new", method = RequestMethod.POST)
    public ModelAndView createInsuranceCompany(@Valid InsuranceCompany insuranceCompany, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName().toString());
        User user = userService.findUserByEmail(auth.getName());
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("insurance/registration");
        } else {
            insuranceService.saveInsuranceCompany(insuranceCompany, user);
            modelAndView.addObject("successMessage", "Insurance Company has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("insuranceCompany", new InsuranceCompany());
            modelAndView.setViewName("insurance/registration");

        }
        return modelAndView;
    }
}
