package ru.freedomfinance.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="chat_ids")
public class Chat_id extends AbstractBaseEntity{
    private String chat_id;
    public Chat_id() {

    }

    public Chat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }
}
