package org.amber.dental.controller;

import org.amber.dental.model.DoctorProfile;
import org.amber.dental.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.amber.dental.model.User;
import org.amber.dental.service.UserService;
import java.util.List;
/**
 * Created by chenlinquan on 4/25/18.
 */
@Controller
public class DoctorController {
    @Autowired
    private UserService userService;

    @Autowired
    private DentistService dentistService;

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

    @RequestMapping(value = {"/doctors"}, method = RequestMethod.GET)
    public ModelAndView findAllDoctors() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<DoctorProfile> dentistList = dentistService.findAllDentist();
        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Welcome To Dental Appointment");
        modelAndView.addObject("dentistList", dentistList);
        modelAndView.addObject("Message", "Find Your Dentist");
        modelAndView.setViewName("doctor/list");
        return modelAndView;
    }

    @RequestMapping(value = {"/doctor/{id}"}, method = RequestMethod.GET)
    public ModelAndView findDoctorDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        DoctorProfile dentist = dentistService.findDentistById(id);
        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Welcome To Dental Appointment");
        modelAndView.addObject("Message", "Find Your Dentist");
        modelAndView.addObject("dentistName", dentist.getUser().getFirstName() + " " + dentist.getUser().getLastName());
        modelAndView.addObject("dentistAddressLine1", dentist.getAddress1() + ", " + dentist.getAddress2());
        modelAndView.addObject("dentistAddressLine2", dentist.getCity() + ", " + dentist.getState() + ", " + dentist.getCountry() + ", " + dentist.getZipCode());
        modelAndView.addObject("dentist", dentist);
        modelAndView.setViewName("doctor/detail");
        return modelAndView;
    }
}
