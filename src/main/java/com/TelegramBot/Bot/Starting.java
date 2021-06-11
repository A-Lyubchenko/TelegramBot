package com.TelegramBot.Bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Starting extends RegistrationUser {

    @Override
    public String getBotUsername() {
        return TelegramConstance.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return TelegramConstance.BOT_TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
            reg(update);
            trainingUser.userChooseTraining(update);
            trainingUser.userTraining(update);


    }
}
