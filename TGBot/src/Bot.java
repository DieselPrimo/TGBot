import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;



public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message!=null && message.hasText()){
            if(message.getText().equals("/start")){
                sendMsg(message, "Здавствуйте я официант Александр");
            }
            else
                sendMsg(message, "Здавствуйте я Александр");
        }

    }


    private void sendMsg(Message message, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);
        try {
            sendMessage(sendMessage);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }

    }



    @Override
    public String getBotUsername() {
        return "ОфициантАлександр";
    }

    @Override
    public String getBotToken() {
        return "486867498:AAE9Q6m00AsAOmcuFgsagRZzBRdguuKczI0";
    }

    @Override
    public void onClosing() {

    }
}
