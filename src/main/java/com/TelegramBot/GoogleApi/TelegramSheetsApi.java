package com.TelegramBot.GoogleApi;

import com.google.api.client.googleapis.apache.v2.GoogleApacheHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Lists;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class TelegramSheetsApi {
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String APPLICATION_PROPERTIES_FILE_PATH = "/application";
    private static Properties properties;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static HttpRequestInitializer getCredentials() throws IOException {
        InputStream inputStream = TelegramSheetsApi.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (inputStream == null) {
            throw new FileNotFoundException("Файл не найден" + CREDENTIALS_FILE_PATH);
        }
        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream)
                .createScoped(Lists.newArrayList(Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)));
        return new HttpCredentialsAdapter(credentials);
    }

    private static Properties getProperties() throws IOException {
        if (properties != null) {
            return properties;
        }
        InputStream inputStream = TelegramSheetsApi.class.getResourceAsStream(APPLICATION_PROPERTIES_FILE_PATH);
        if (inputStream == null) {
            throw new FileNotFoundException("Файл не найден" + APPLICATION_PROPERTIES_FILE_PATH);
        }
        properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    private static HttpTransport getTransport() throws IOException, GeneralSecurityException {
        return GoogleApacheHttpTransport.newTrustedTransport();
    }

    protected static String getSpreadSheetsId() throws IOException {
        return getProperties().getProperty("spreadsheets_id");
    }

    protected static Sheets getService() throws GeneralSecurityException, IOException {
        Sheets service = new Sheets.Builder(getTransport(), JSON_FACTORY, getCredentials())
                .setApplicationName("SomeName").build();
        Spreadsheet execute = service.spreadsheets().get(getSpreadSheetsId()).execute();
        List<Sheet> sheetsList = execute.getSheets();
        sheetsList.forEach(sheet -> System.out.println(((SheetProperties) sheet.get("properties")).get("title")));
        return service;

    }


}
