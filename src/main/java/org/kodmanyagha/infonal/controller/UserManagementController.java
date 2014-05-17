package org.kodmanyagha.infonal.controller;

import javax.servlet.http.HttpSession;

import org.kodmanyagha.infonal.data.UsersDAO;
import org.kodmanyagha.infonal.data.exception.ConnectionStringSyntaxException;
import org.kodmanyagha.infonal.data.exception.DBConnectionException;
import org.kodmanyagha.infonal.data.exception.DBDriverProcessException;
import org.kodmanyagha.infonal.model.ResponseJson;
import org.kodmanyagha.infonal.model.Status;
import org.kodmanyagha.infonal.model.User;
import org.kodmanyagha.infonal.model.userinput.AddUserForm;
import org.kodmanyagha.infonal.model.userinput.EditUserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

@Controller
@RequestMapping("/ajax/userManagement")
public class UserManagementController {
  private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

  private UsersDAO usersDao;

  @Autowired
  public void setUsersDao(UsersDAO usersDao) {
    this.usersDao = usersDao;
  }

  @RequestMapping(value = "/getAllUsers.do", method = RequestMethod.GET)
  public String getAllUsers(Model model) {
    ResponseJson response = new ResponseJson();
    try {
      response.setData(usersDao.getUsers());
      response.setStatus(Status.OK);

      model.addAttribute("data", new Gson().toJson(response));

    } catch (DBConnectionException | ConnectionStringSyntaxException | DBDriverProcessException ex) {
      logger.error("--- error: " + ex);

      response.setStatus(Status.ERROR);
      response.setData(ex.getLocalizedMessage());
      model.addAttribute("data", new Gson().toJson(response));
    }
    return "json";
  }

  @RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
  public String addUser(@ModelAttribute("addUserInfo") AddUserForm userInfo, Model model,
      HttpSession session) {
    logger.debug("---Incoming message: " + userInfo);
    ResponseJson response = new ResponseJson();

    String captchaExpected =
        (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
    String captchaReceived = userInfo.getCaptcha();
    if (captchaExpected.equals(captchaReceived)) {
      try {
        logger.debug("--- captcha input is correct");
        User newUser = new User();
        newUser.setFirstname(userInfo.getFirstname());
        newUser.setLastname(userInfo.getLastname());
        newUser.setPhone(userInfo.getPhone());

        usersDao.insertUser(newUser);

        logger.debug("--- new user added: " + new Gson().toJson(newUser));

        response.setData("User add operation success");
        response.setStatus(Status.OK);
      } catch (DBDriverProcessException ex) {

        response.setData(ex.getLocalizedMessage());
        response.setStatus(Status.ERROR);
      }
    } else {
      logger.debug("--- captcha input is not correct");

      response.setData("Captcha input is not correct");
      response.setStatus(Status.ERROR);
    }

    model.addAttribute("data", new Gson().toJson(response));
    return "json";
  }

  @RequestMapping(value = "/updateUser.do", method = RequestMethod.POST)
  public String updateUser(@ModelAttribute("updateUserInfo") AddUserForm userInfo, Model model,
      HttpSession session) {
    ResponseJson response = new ResponseJson();

    try {
      User updatedUser = new User();
      updatedUser.setId(userInfo.getId());
      updatedUser.setFirstname(userInfo.getFirstname());
      updatedUser.setLastname(userInfo.getLastname());
      updatedUser.setPhone(userInfo.getPhone());

      usersDao.updateUser(updatedUser);

      logger.debug("--- user updated: " + new Gson().toJson(updatedUser));

      response.setData("User updated");
      response.setStatus(Status.OK);
    } catch (DBDriverProcessException ex) {

      response.setData(ex.getLocalizedMessage());
      response.setStatus(Status.ERROR);
    }

    model.addAttribute("data", new Gson().toJson(response));
    return "json";
  }

  @RequestMapping(value = "/removeUser.do", method = RequestMethod.POST)
  public String removeUser(@ModelAttribute("deleteUserInfo") EditUserForm userInfo, Model model) {
    logger.debug("---Incoming message: " + userInfo);
    ResponseJson response = new ResponseJson();
    try {
      User deletedeUser = new User();
      deletedeUser.setId(userInfo.getId());

      usersDao.deleteUser(deletedeUser);

      response.setData("User deleted");
      response.setStatus(Status.OK);
    } catch (DBDriverProcessException ex) {

      response.setData(ex.getLocalizedMessage());
      response.setStatus(Status.ERROR);
    }

    model.addAttribute("data", new Gson().toJson(response));
    return "json";
  }
}
