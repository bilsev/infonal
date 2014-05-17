package org.kodmanyagha.infonal.controller;

import junit.framework.TestCase;

import org.junit.Before;
import org.kodmanyagha.infonal.data.UsersDAO;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class UserManagementControllerTest extends TestCase {

  @Mock
  private UsersDAO usersDAO;
  @Mock
  private Model model;

  @Before
  protected void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  public void testGetAllUsers() {
    //when(model.addAttribute(null)).then

    UserManagementController controller = new UserManagementController();
    controller.setUsersDao(usersDAO);

  }
}
