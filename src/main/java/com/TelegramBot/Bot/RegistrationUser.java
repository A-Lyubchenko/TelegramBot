package com.TelegramBot.Bot;

import com.TelegramBot.GoogleApi.TelegramAnsQue;
import com.TelegramBot.GoogleApi.TelegramTraining;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RegistrationUser extends TelegramLongPollingBot {
    private static final Map<Long, User> userMap = new HashMap<>();
    TrainingUser trainingUser = new TrainingUser();
    TelegramAnsQue telegramAnsQue = new TelegramAnsQue();
    TelegramTraining telegramTraining = new TelegramTraining();
    private static int forLesson = 4;
    private static int forQue = 2;
    private static int forAns = 2;

    protected void reg(Update update) throws InterruptedException {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        switch (text) {
            case "/start":
                if (!userMap.containsKey(chatId)) {
                    msg(chatId, "Привет!\n" +
                            "\n" +
                            "Я Telegram бот, и я помогу подготовиться к собеседованию по разным языкам програмирования.");
                    Thread.sleep(500);
                    msgForReg(chatId, "Вижу, ты новенький. Для начала нажми на кнопку Зарегистрироваться, чтобы я мог сохранять твой прогресс.\n" +
                            "\n" +
                            "Если не видишь кнопки - нажми на (::), чтобы открыть клавиатуру.");
                } else {
                    msg(chatId, "Вы уже зарегистрированы под логином " + userMap.get(chatId).getEmail());
                }
                break;
            case "⚠️ Зарегистрироваться":
                msg(chatId, "Пожалуйста введите ваш емаил.");
                break;
        }
        if (getValidEmail(update)) {
            getUser(update);
            Thread.sleep(700);
            msg(chatId, "Благодарим за регистрацию.");
            Thread.sleep(800);
            msgChooseTraining(chatId, "Выберите, какой язык для обучения вас интересует.");
        }
    }


    private void msg(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void msgForReg(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        getRegKeyBoard(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void msgChooseTraining(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        trainingUser.keyBoardsForChoosingTraining(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void msgChooseFormatTraining(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        trainingUser.keyBoardFormatTraining(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void msg1(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        trainingUser.keyBoard1(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void msg2(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        trainingUser.keyBoardFormatTraining1(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void msg3(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        trainingUser.keyBoardFormatTraining2(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void msg4(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        trainingUser.keyBoardFormatTraining3(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void msg5(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(message);
        trainingUser.keyBoardFormatTraining4(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private boolean getValidEmail(Update update) {
        String text = update.getMessage().getText();
        return text.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}$");
    }

    private void getUser(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        User user = new User();
        user.setEmail(text);
        userMap.put(chatId, user);
    }

    private void getRegKeyBoard(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow keyboardButtons = new KeyboardRow();
        keyboardButtons.add("⚠️ Зарегистрироваться");

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardButtons);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }


    class TrainingUser {


        // Выбор, что пользователь будет изучать, какой язык
        protected void userChooseTraining(Update update) throws GeneralSecurityException, IOException, InterruptedException {
            Long chatId = update.getMessage().getChatId();
            String returnMessage = update.getMessage().getText();
            switch (returnMessage) {
                case "✅ HTML/CSS":
                    break;
                case "✅ JavaScript":
                    Thread.sleep(2000);
                    msgChooseFormatTraining(chatId, "С чего вы хотите начать обучение?");
                    break;
                case "✅ React":
                    //добавить код
                    break;
                case "\uD83D\uDD27 Настройки":
                    //добавить код
                    break;
                case "\uD83D\uDD27 Настроить напоминание":
                    //добавить код
                    break;
                case "\uD83C\uDF24 Просмотреть погоду, всегда есть о чем поговорить:)":

                    break;
                case "Напишите интересующий вас город":
            }
        }

        protected void keyBoardsForChoosingTraining(SendMessage sendMessage) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setOneTimeKeyboard(true);

            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(new KeyboardButton("✅ HTML/CSS"));
            keyboardFirstRow.add("✅ JavaScript");
            keyboardFirstRow.add("✅ React");
            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add(new KeyboardButton("⚙️ Настройки"));
            keyboardSecondRow.add("⚙️ Настроить напоминание");

            KeyboardRow keyboardThreeRow = new KeyboardRow();
            keyboardThreeRow.add(new KeyboardButton("\uD83C\uDF24 Просмотреть погоду, всегда есть о чем поговорить:)"));

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            keyboard.add(keyboardThreeRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
        }

        //Выбор для пользователя, когда выбрал изучение JavaScript
        protected void userTraining(Update update) throws GeneralSecurityException, IOException, InterruptedException {
            Long chatId = update.getMessage().getChatId();
            String returnMessage = update.getMessage().getText();
            switch (returnMessage) {
                case "\uD83C\uDFE1 Перейти в главное меню":
                    Thread.sleep(1000);
                    msgChooseTraining(chatId, "Вы выбрали пунк: \"Главное меню\"");
                    break;
                case "1️⃣  Начать обучение с первого вопроса":
                    forQue+=2;
                    Thread.sleep(500);
                    msg4(chatId, telegramAnsQue.getQuestion(forQue));
                    break;
                case "Получить ответ":
                    msg(chatId, telegramAnsQue.getAnswer(forQue));
                    //Добавить логику
                case "Начать с первого вопроса. Показать сразу вопрос/ответ/ссылка - на видео":
                    msg2(chatId, telegramTraining.getOneLesson(forLesson));
                    break;
                case "Перейти к следующему  \uD83D\uDD1C":
                    forLesson += 2;
                    Thread.sleep(500);
                    msg2(chatId, telegramTraining.getOneLesson(forLesson));
                    break;
                case "\uD83D\uDD19  Перейти к предыдущему":
                    forLesson -= 2;
                    Thread.sleep(500);
                    msg2(chatId, telegramTraining.getOneLesson(forLesson));
                    break;
                case "\uD83D\uDCDC  Просмотреть весь список вопросов  \uD83D\uDCDC":
                    Thread.sleep(500);
                    msg3(chatId, telegramTraining.getAllQuestions());
                    break;
                case "Посмотреть ответ":
                    forAns+=2;
                    Thread.sleep(500);
                    msg5(chatId, telegramAnsQue.getAnswer(forLesson));
                    break;
                case "Перейти к следующему вопросу  \uD83D\uDD1C":
                    forQue+=2;
                    Thread.sleep(500);
                    msg5(chatId, telegramAnsQue.getQuestion(forQue));
                    break;
            }
        }

        private void keyBoardFormatTraining(SendMessage sendMessage) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add("\uD83D\uDCDC  Просмотреть весь список вопросов  \uD83D\uDCDC");
            keyboardFirstRow.add(new KeyboardButton("1️⃣  Начать обучение с первого вопроса"));

            KeyboardRow keyboardThirdRow = new KeyboardRow();
            keyboardThirdRow.add(new KeyboardButton("Начать с первого вопроса. Показать сразу вопрос/ответ/ссылка - на видео"));


            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add(new KeyboardButton("\uD83C\uDFE1 Перейти в главное меню"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardThirdRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
        }

        private void keyBoard1(SendMessage sendMessage) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(new KeyboardButton("Просмотреть видео на этот вопрос"));
            keyboardFirstRow.add("Получить ответ");

            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add(new KeyboardButton("\uD83C\uDFE1 Перейти в главное меню"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
        }

        private void keyBoardFormatTraining1(SendMessage sendMessage) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(new KeyboardButton("\uD83D\uDD19  Перейти к предыдущему"));
            keyboardFirstRow.add(new KeyboardButton("Перейти к следующему  \uD83D\uDD1C"));

            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add(new KeyboardButton("\uD83C\uDFE1 Перейти в главное меню"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
        }

        private void keyBoardFormatTraining2(SendMessage sendMessage) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(new KeyboardButton("1️⃣  Начать обучение с первого вопроса"));
            keyboardFirstRow.add(new KeyboardButton("\uD83D\uDD16  Выбрать ответ на вопрос"));

            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add(new KeyboardButton("\uD83C\uDFE1 Перейти в главное меню"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
        }

        private void keyBoardFormatTraining3(SendMessage sendMessage) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(new KeyboardButton("Посмотреть ответ"));
            keyboardFirstRow.add(new KeyboardButton("Просмотреть видео по данному вопросу"));

            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add(new KeyboardButton("\uD83C\uDFE1 Перейти в главное меню"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
        }
        private void keyBoardFormatTraining4(SendMessage sendMessage) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(new KeyboardButton("\uD83D\uDD19  Перейти к предыдущему вопросу"));
            keyboardFirstRow.add(new KeyboardButton("Перейти к следующему вопросу  \uD83D\uDD1C"));
            keyboardFirstRow.add(new KeyboardButton("Просмотреть видео по данному вопросу"));

            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add(new KeyboardButton("\uD83C\uDFE1 Перейти в главное меню"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
        }
    }
}

