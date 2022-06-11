package ru.freedomfinance.core;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.freedomfinance.model.Chat_id;
import ru.freedomfinance.model.Eur;
import ru.freedomfinance.model.Rub;
import ru.freedomfinance.model.Usd;
import ru.freedomfinance.repos.ChatIdRepository;
import ru.freedomfinance.repos.EurRepository;
import ru.freedomfinance.repos.RubRepository;
import ru.freedomfinance.repos.UsdRepository;
import ru.freedomfinance.service.XMLService;

import java.util.List;

@Component
@EnableScheduling
public class simpleBot extends TelegramLongPollingBot {

    private final UsdRepository usdRepository;
    private final EurRepository eurRepository;
    private final RubRepository rubRepository;
    private final XMLService xmlService;
    private final ChatIdRepository chatIdRepository;

    public simpleBot(UsdRepository usdRepository, EurRepository eurRepository, RubRepository rubRepository, XMLService xmlService, ChatIdRepository chatIdRepository) {
        this.usdRepository = usdRepository;
        this.eurRepository = eurRepository;
        this.rubRepository = rubRepository;
        this.xmlService = xmlService;
        this.chatIdRepository = chatIdRepository;
    }

    @Scheduled(cron = "01 00 02 * * *", zone = "Asia/Hong_Kong")
    public void messageEveryNight() {

        xmlService.parseUSD();
        xmlService.parseEUR();
        xmlService.parseRUB();
        List<Usd> lastTwoUsd = usdRepository.getLastTwoUsdCurrencyChanges();
        List<Eur> lastTwoEur = eurRepository.getLastTwoEurCurrencyChanges();
        List<Rub> lastTwoRub = rubRepository.getLastTwoRubCurrencyChanges();

        Usd usd1 = lastTwoUsd.get(0);
        Usd usd2 = lastTwoUsd.get(1);

        Eur eur1 = lastTwoEur.get(0);
        Eur eur2 = lastTwoEur.get(1);

        Rub rub1 = lastTwoRub.get(0);
        Rub rub2 = lastTwoRub.get(1);

        Double price1 = Double.valueOf(usd1.getPrice());
        Double price2 = Double.valueOf(usd2.getPrice());

        Double change1 = (price2*100.0/price1)-100.0;

        price1 = Double.valueOf(eur1.getPrice());
        price2 = Double.valueOf(eur2.getPrice());

        Double change2 = (price2*100.0/price1)-100.0;

        price1 = Double.valueOf(rub1.getPrice());
        price2 = Double.valueOf(rub2.getPrice());

        Double change3 = (price2*100.0/price1)-100.0;

        if (change1 >= 10.0) {
            List<Chat_id> allIds = chatIdRepository.findAll();
            for (Chat_id c : allIds) {
                SendMessage response = new SendMessage();
                response.setChatId(c.getChat_id());
                response.setText("Сегодня курс доллара подорожал на " + change1 + "процентов");
                try {
                    execute(response);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }
            }
        }
        else if (change1 <= -10.0) {
            List<Chat_id> allIds = chatIdRepository.findAll();
            for (Chat_id c : allIds) {
                SendMessage response = new SendMessage();
                response.setChatId(c.getChat_id());
                response.setText("Сегодня курс доллара упал на " + change1 + "процентов");
                try {
                    execute(response);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }
            }
        }

        if (change2 >= 10.0) {
            List<Chat_id> allIds = chatIdRepository.findAll();
            for (Chat_id c : allIds) {
                SendMessage response = new SendMessage();
                response.setChatId(c.getChat_id());
                response.setText("Сегодня курс евро подорожал на " + change1 + "процентов");
                try {
                    execute(response);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }
            }
        }
        else if (change2 <= -10.0) {
            List<Chat_id> allIds = chatIdRepository.findAll();
            for (Chat_id c : allIds) {
                SendMessage response = new SendMessage();
                response.setChatId(c.getChat_id());
                response.setText("Сегодня курс евро упал на " + change1 + "процентов");
                try {
                    execute(response);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }
            }
        }

        if (change3 >= 10.0) {
            List<Chat_id> allIds = chatIdRepository.findAll();
            for (Chat_id c : allIds) {
                SendMessage response = new SendMessage();
                response.setChatId(c.getChat_id());
                response.setText("Сегодня курс рубля подорожал на " + change3 + "процентов");
                try {
                    execute(response);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }
            }
        }
        else if (change3 <= -10.0) {
            List<Chat_id> allIds = chatIdRepository.findAll();
            for (Chat_id c : allIds) {
                SendMessage response = new SendMessage();
                response.setChatId(c.getChat_id());
                response.setText("Сегодня курс рубля упал на " + change3 + "процентов");
                try {
                    execute(response);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }
            }
        }

    }


    @Override
    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        if (command.equals("/usd")) {
            List<Usd> l = usdRepository.getLastUsdCurrencyChanges();
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());

            int cnt_chatid = chatIdRepository.countByIds(update.getMessage().getChatId().toString());
            if (cnt_chatid == 0) {
                chatIdRepository.save(new Chat_id(update.getMessage().getChatId().toString()));
            }

            String ans = "";
            for (Usd u : l) {
                ans = ans + u.getDate() + " " + u.getPrice() + " " + u.getDifference();
                ans = ans + "\n";
            }
            response.setText(ans);
            try {
                execute(response);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }
        }

        if (command.equals("/eur")) {
            List<Eur> l = eurRepository.getLastEurCurrencyChanges();
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            String ans = "";
            for (Eur eur : l) {
                ans = ans + eur.getDate() + " " + eur.getPrice() + " " + eur.getDifference();
                ans = ans + "\n";
            }
            response.setText(ans);
            try {
                execute(response);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }
        }

        if (command.equals("/rub")) {
            List<Rub> l = rubRepository.getLastRubCurrencyChanges();
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            String ans = "";
            for (Rub r : l) {
                ans = ans + r.getDate() + " " + r.getPrice() + " " + r.getDifference();
                ans = ans + "\n";
            }
            response.setText(ans);
            try {
                execute(response);
            } catch (TelegramApiException E) {
                E.printStackTrace();
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
