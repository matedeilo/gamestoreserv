package com.store.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery_user")
public class DeliveryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int user_id;

    private int game_id;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
