package body.telegram.commands.service;

import body.Utils;
import body.telegram.Bot;
import body.telegram.nonCommand.Settings;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class SettingsCommand extends ServiceCommand{
    public SettingsCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        Long chatId = chat.getId();
        Settings settings = Bot.getUserSettings(chatId);
        sendAnswer(absSender, chatId, this.getCommandIdentifier(), userName,
                String.format(Utils.convert("*Текущие настройки*\n" +
                                "- минимальное число, использующееся в заданиях - *%s*\n" +
                                "- максимальное число, использующееся в заданиях - *%s*\n" +
                                "- число страниц итогового файла - *%s*\n\n" +
                                "Если Вы хотите изменить эти параметры, введите через пробел или запятую 3 числа - " +
                                "минимальное число, максимальное число и количество страниц в файле (не более 10)\n\n") +
                                "\uD83D\uDC49" + Utils.convert("Например, 3,15,6 или 4 17 3"),
                        settings.getMin(), settings.getMax(), settings.getListCount()));

    }
}
