package com.TelegramBot.GoogleApi;

import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class TelegramTraining extends TelegramSheetsApi{
    
    public String getOneLesson(int i) throws IOException, GeneralSecurityException {
        StringBuilder stringBuilder = new StringBuilder();
        ValueRange response = getService().spreadsheets().values()
                .get(getSpreadSheetsId(), String.format("JavaScrip!A%d:C%d", i, i))
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data find!");
            return null;
        }
        for (List row : values) {
            if (row.isEmpty()) {
                continue;
            }
            stringBuilder.append(String.format("%s\n %s\n %s\n", row.get(0), row.get(1), row.get(2)));
        }
        return stringBuilder.toString();
    }
    public String getAllQuestions() throws IOException, GeneralSecurityException {
        StringBuilder stringBuilder = new StringBuilder();
        ValueRange response = getService().spreadsheets().values()
                .get(getSpreadSheetsId(), "JavaScrip!A4:C")
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data find!");
            return null;
        }
        for (List row : values) {
            if (row.isEmpty()) {
                continue;
            }
            stringBuilder.append(String.format("%s\n",row.get(0)));
        }
        return stringBuilder.toString();
    }





}

