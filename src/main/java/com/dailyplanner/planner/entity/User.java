package com.dailyplanner.planner.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public final class User {
    private int id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String nickname;
    private String password;
    private String email;
    private UserAccount userAccount;

    public User(int id,String name, String surname, Date dateOfBirth, String nickname, String password, String email) {
        this.id =id; //todo when adding new user id has to be automatically calculated according to id from DB, change it when DB is ready
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

   public String getName() {
        return name;
    }

    public int getId() {return id;}

    public String getSurname() {
        return surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.name.equals(user.name) &&
                this.surname.equals(user.surname) &&
                this.dateOfBirth.equals(user.dateOfBirth) &&
                this.nickname.equals(user.nickname) &&
                this.password.equals(user.password) &&
                this.email.equals(user.email) &&
                this.userAccount.equals(user.userAccount);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.dateOfBirth == null ? 0 : this.dateOfBirth.hashCode());
        hash = 31 * hash + (this.name == null ? 0 : this.name.hashCode());
        return hash;
    }
}
