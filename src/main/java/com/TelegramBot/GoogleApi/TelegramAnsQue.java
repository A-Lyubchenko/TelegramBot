package com.TelegramBot.GoogleApi;

import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;

import java.util.List;
import java.util.Objects;

public class TelegramAnsQue extends TelegramSheetsApi {


    public String getQuestion(int i) throws GeneralSecurityException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        ValueRange response = getService().spreadsheets().values()
                .get(getSpreadSheetsId(), String.format("JavaScrip!A%d:C%d",i,i))
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
            stringBuilder.append(row.get(0));
            System.out.println(stringBuilder);

        }
        return stringBuilder.toString();
    }

    public String getAnswer(int i) throws IOException, GeneralSecurityException {
        StringBuilder stringBuilder = new StringBuilder();
        ValueRange response = getService().spreadsheets().values()
                .get(getSpreadSheetsId(), String.format("JavaScrip!A%d:C%d",i,i))
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
            stringBuilder.append(row.get(1));

        }
        return stringBuilder.toString();

    }




}
