package org.kodmanyagha.infonal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.kodmanyagha.infonal.data.InfonalDataAccess;
import org.kodmanyagha.infonal.model.ResponseJson;
import org.kodmanyagha.infonal.model.Status;
import org.kodmanyagha.infonal.model.User;
import org.kodmanyagha.infonal.model.userinput.AddUserForm;
import org.kodmanyagha.infonal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

@Controller
@RequestMapping("/ajax/userManagement")
public class UserManagementController {
  private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

  private InfonalDataAccess infonalDataAccess;

  @Autowired
  public void setInfonalDataAccess(InfonalDataAccess infonalDataAccess) {
    this.infonalDataAccess = infonalDataAccess;
  }

  @RequestMapping(value = "/getAllUsers.do", method = RequestMethod.GET)
  public String getAllUsers(Model model) {
    logger.debug("Returning all users");

    List<User> users = new ArrayList<>();
    User user = null;
    for (int i = 0; i < 5; i++) {
      user = new User();
      user.setFirstname("Name" + i);
      user.setLastname("lastname" + i);
      user.setId(i);
      user.setPhone("537 123 " + StringUtil.intToString(i, 2) + " "
          + StringUtil.intToString(i + 3, 2));
      users.add(user);
    }

    ResponseJson response = new ResponseJson();
    response.setData(users);
    response.setStatus(Status.OK);

    model.addAttribute("data", new Gson().toJson(response));

    return "json";
  }

  @RequestMapping(value = "/testMethod.do", method = RequestMethod.GET)
  public String testMethod(Model model) {
    List<String> exampleList = new ArrayList<>();
    if (this.infonalDataAccess == null)
      exampleList.add("infonalDataAccess is null");
    else
      exampleList.add(this.infonalDataAccess.getExampleString());

    model.addAttribute("data", new Gson().toJson(exampleList));

    return "json";
  }

  @RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
  public String addUser(@ModelAttribute("addUserInfo") AddUserForm userInfo, Model model,
      HttpSession session) {
    logger.debug("---Incoming message: " + userInfo);

    String captchaExpected =
        (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
    String captchaReceived = userInfo.getCaptcha();
    if (captchaExpected.equals(captchaReceived)) {
      logger.debug("--- captcha input is correct");

    } else {
      logger.debug("--- captcha input is not correct");

    }

    model.addAttribute("data", new Gson().toJson("Hello World"));
    return "json";
  }

  @RequestMapping(value = "/greetings/{msg}.do", method = RequestMethod.GET)
  public String greetingsMsg(@PathVariable String msg, ModelMap model) {
    model.addAttribute("data", new Gson().toJson(msg));
    return "json";
  }
}
