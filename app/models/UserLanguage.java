package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.*;

@Entity
public class UserLanguage extends Model {
  public User user;
  public Language language;

	public UserLanguage(User user, Language lang){
		this.user  = user;
		this.language = lang;
	}
}