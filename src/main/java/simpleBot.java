import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class simpleBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        if (command.equals("/usd")) {
            XMLService xmlService = new XMLService();
            String[] usdparsing = xmlService.parseUSD();
            for (int i = 0; i < 10; i++) {
                String message = usdparsing[i];
                SendMessage response = new SendMessage();
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                try {
                    execute(response);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }
            }
        }

        if (command.equals("/eur")) {
            XMLService xmlService = new XMLService();
            String[] eurparsing = xmlService.parseEUR();
            for (int i = 0; i < 10; i++) {
                String message = eurparsing[i];
                SendMessage response = new SendMessage();
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                try {
                    execute(response);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }
            }
        }

        if (command.equals("/rub")) {
            XMLService xmlService = new XMLService();
            String[] rubparsing = xmlService.parseRUB();
            for (int i = 0; i < 10; i++) {
                String message = rubparsing[i];
                SendMessage response = new SendMessage();
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                try {
                    execute(response);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }
            }
        }


    }

    @Override
    public String getBotUsername() {
        // TODO
        return "freedomfct_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5458158172:AAE_TW_OYPoUOoF60myTNvhOBCl5ZT7cExc";
    }
}
