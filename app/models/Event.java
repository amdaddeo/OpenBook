package models;

import java.util.*;
import javax.persistence.*;
import play.data.validation.*;

import controllers.Events;
import controllers.Application;
import models.Post;

import play.db.jpa.*;

@Entity
public class Event extends Model {

  @ManyToOne
  public User author;

  /*
   * @OneToMany public EventInvite invitedUsers;
   */

  public String name;
  public String script;
  public String location;
  public Date startDate;
  public Date endDate;
  public boolean givenEndDate = false;

  public String privilege;
  public boolean open = false;
  public boolean friends = false;
  public boolean inviteOnly = false;
  // public Location eventVenue;

  @ManyToMany
  public List<User> members;

  public Event(User author, String name, String script,
      String location) {
    this.author = author;
    this.name = name;
    this.script = script;
    this.location = location;
    this.members = new ArrayList<User>();
    this.members.add(author);
  }

  public EventInvite newEventInvite(User curGuest) {
    EventInvite myEventInvite = new EventInvite(this, curGuest).save();
    this.save();
    return myEventInvite;
  }

  public List<Post> getPosts() {
    return Post
        .find(
            "SELECT p FROM Post p WHERE p.postType = ? and p.title = ? order by p.updatedAt desc",
            Post.type.EVENT, this.id.toString()).fetch();
  }

  public void addMember(User u) {
    if (!members.contains(u))
      members.add(u);
  }

  public void removeMember(User u) {
    if (members.contains(u))
      members.remove(u);
  }

  public int getMemberCount() {
    return members.size();
  }
}