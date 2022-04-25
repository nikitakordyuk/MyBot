package body.telegram;

import body.Utils;
import body.telegram.commands.operations.*;
import body.telegram.commands.service.HelpCommand;
import body.telegram.commands.service.SettingsCommand;
import body.telegram.commands.service.StartCommand;
import body.telegram.nonCommand.NonCommand;
import body.telegram.nonCommand.Settings;
import lombok.Getter;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingCommandBot {

    @Override
    public String getBotUsername() {
        return ConstantData.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return ConstantData.BOT_TOKEN;
    }

    public Bot() {
        super();

        this.nonCommand = new NonCommand();

        register(new StartCommand("start", Utils.convert("Старт")));

        register(new PlusCommand("plus", Utils.convert("Сложение")));

        register(new MinusCommand("minus", Utils.convert("Вычитание")));

        register(new PlusMinusCommand("plusminus", Utils.convert("Сложение и вычитание")));

        register(new MultiplicationCommand("multiply", Utils.convert("Умножение")));

        register(new DivisionCommand("divide", Utils.convert("Деление")));

        register(new MultiplicationDivisionCommand("multdivide", Utils.convert("Умножение и деление")));

        register(new AllCommand("all", Utils.convert("4 действия")));

        register(new HelpCommand("help", Utils.convert("Помощь")));

        register(new SettingsCommand("settings", Utils.convert("Мои настройки")));

        userSettings = new HashMap<>();
    }

    @Getter
    private static final Settings defaultSettings = new Settings(1, 15, 1);
    private final NonCommand nonCommand;

    /**
     * Настройки файла для разных пользователей. Ключ - уникальный id чата
     */
    @Getter
    private static Map<Long, Settings> userSettings;

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        String userName = Utils.getUserName(msg);

        String answer = nonCommand.nonCommandExecute(chatId, userName, msg.getText());
        setAnswer(chatId, userName, answer);
    }

    public static Settings getUserSettings(Long chatId) {
        Map<Long, Settings> userSettings = Bot.getUserSettings();
        Settings settings = userSettings.get(chatId);
        if (settings == null) {
            return defaultSettings;
        }
        return settings;
    }

    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
