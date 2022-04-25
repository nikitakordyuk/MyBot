package body.telegram.commands.operations;

import body.Utils;
import body.calculation.OperationEnum;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class MultiplicationDivisionCommand extends OperationCommand {
    public MultiplicationDivisionCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        sendAnswer(absSender, chat.getId(), OperationEnum.getMultiplicationDivision(), this.getDescription(),
                this.getCommandIdentifier(), userName);
    }
}
