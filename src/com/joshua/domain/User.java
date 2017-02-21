package com.joshua.domain;

import java.io.Serializable;
import java.sql.Timestamp;



/**
 * CREATE TABLE `users` ( `id` int(11) NOT NULL AUTO_INCREMENT, `username`
 * varchar(40) DEFAULT NULL, `password` varchar(100) DEFAULT NULL, `nickname`
 * varchar(40) DEFAULT NULL, `email` varchar(100) DEFAULT NULL, `role`
 * varchar(20) DEFAULT NULL, `state` int(11) DEFAULT NULL, `activecode`
 * varchar(100) DEFAULT NULL, `updatetime` timestamp NOT NULL DEFAULT
 * CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (`id`) )
 * ENGINE=InnoDB DEFAULT CHARSET=latin1;
 * 
 * @author joshua.xue
 *
 */
public class User implements Serializable {
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String role;
	private int state;
	private String activecode;
	private Timestamp updatetime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getActivecode() {
		return activecode;
	}

	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

}
