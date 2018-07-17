

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;



import static com.googlecode.objectify.ObjectifyService.ofy;



@WebServlet(
    name = "RegisterServlet",
    urlPatterns = {"/api/register"}
)


public class RegisterServlet extends HttpServlet {
	
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
      
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    String username = request.getParameter("username");
   
    checkNotNull(username, "username can't not be null");
    checkArgument(username.length()>3, "username must more than 3");
    
    String password = request.getParameter("password");
    
    checkNotNull(password, "password can't not be null"); 
    checkArgument(password.length()>5, "password must more than 5");
    
    User existUser = ofy().load().type(User.class).id(username).now();
    System.out.println(existUser);
    checkArgument( existUser==null , "User "+username+" exists ");
    
    User user  = new User();
    user.setUsername(username);
    user.setPassword(password);
    
    
    ofy().save().entity(user);
    
    
    
    
    
    response.getWriter().printf("%s",username);
    

  }
}