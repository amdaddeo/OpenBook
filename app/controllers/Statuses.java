package controllers;

import java.util.*;

import play.*;
import play.mvc.*;
import controllers.Secure;
import models.*;
import play.data.validation.*;

@With(Secure.class)
public class Statuses extends Controller {
    
  public static void show(Long id){
    Status status = Status.findById(id);
    User author = User.findById(id);
    render(status, author);
  }
  
  public static void postStatus(
    @Required(message="A message is required") String content) 
  {
    
    Status status = new Status(Users.user(), content).save();
    Application.news(Users.user().id);
  }
  
  public List<Status> returnAll(){
    return Status.findAll();
  }
}
