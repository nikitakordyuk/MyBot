package body.telegram.commands.operations;

import body.Utils;
import body.calculation.OperationEnum;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Collections;

public class MinusCommand extends OperationCommand {
    public MinusCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        sendAnswer(absSender, chat.getId(), Collections.singletonList(OperationEnum.SUBTRACTION), this.getDescription(),
                this.getCommandIdentifier(), userName);

    }
}
