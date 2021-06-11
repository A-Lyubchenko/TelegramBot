package com.TelegramBot.Bot;

import lombok.Data;

@Data
public class User {
    private String chatId;
    private String name;
    private String lastname;
    private String email;
}
