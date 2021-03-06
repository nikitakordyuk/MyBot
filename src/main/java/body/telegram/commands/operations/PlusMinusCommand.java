package body.telegram.commands.operations;

import body.Utils;
import body.calculation.OperationEnum;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class PlusMinusCommand extends OperationCommand {
    public PlusMinusCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        sendAnswer(absSender, chat.getId(), OperationEnum.getPlusMinus(), this.getDescription(),
                this.getCommandIdentifier(), userName);

    }
}
