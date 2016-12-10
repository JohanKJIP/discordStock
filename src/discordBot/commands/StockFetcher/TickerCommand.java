package discordBot.commands.StockFetcher;

import discordBot.Command;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import java.awt.Color;
import static discordBot.commands.StockFetcher.StockFetcher.getStock;

/**
 * Created by JohanvonHacht on 06/12/16.
 */
public class TickerCommand  implements Command {
    private final String HELP = "USAGE: 'ticker symbol argument";
    private StockFetcher stock = new StockFetcher();
    public static final String newline = System.getProperty("line.separator");
    private Stock s;

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    /**
     * Method to select correct message.
     * @param args arguments from user.
     * @param event event from message.
     */
    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String ticker = args[0];
        s = getStock(ticker);
        //no other arguments
        if(args.length == 1) {
            printStockInfo(event);
        }
        //extra arguments
        else {
            switch(args[1].toLowerCase()) {
                case "eps" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | Eps: " + s.getEps()).queue();
                    break;
                case "50davg" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | 50 day moving average: " + s.getMovingav50day()).queue();
                    break;
                case "52wlow" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | 52 week low: " + s.getWeek52low()).queue();
                    break;
                case "52whigh" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | 52 week high: " + s.getWeek52high()).queue();
                    break;
                case "short" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | Short ratio: " + s.getShortRatio()).queue();
                    break;
                case "mc" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | Market cap: " + s.getMarketcap()).queue();
                    break;
                case "vol" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | Volume: " + s.getVolume()).queue();
                    break;
                case "price" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | Price: " + s.getPrice()).queue();
                    break;
                case "pe" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | Pe: " + s.getPe()).queue();
                    break;
                case "open" :
                    event.getTextChannel().sendMessage(s.getSymbol() + " | Open price: " + s.getOpen()).queue();
                    break;
                case "full" :
                    EmbedBuilder em = new EmbedBuilder();
                    em.setColor(Color.red).setTitle(s.getSymbol() + "    |    " + s.getName() + "    |    " + s.getChg() + "%");
                    User author = event.getAuthor();
                    em.setAuthor("@" + author.getName(), author.getAvatarId(),author.getEffectiveAvatarUrl());
                    em.setDescription("Price: " + s.getPrice() + " | " + "Day high: " + s.getDayhigh() + " | " + "Day low: " + s.getDaylow() + newline +newline +
                    "52wk high: " + s.getWeek52high() + " | " + "52week low: " + s.getWeek52low() + " | " + "Volume: " + s.getVolume());
                    em.setFooter("Data collected from Yahoo Finance","https://upload.wikimedia.org/wikipedia/commons/5/59/Empty.png");
                    em.setVideo("https://www.youtube.com/watch?v=VPbg7l9pL90&t=0s");
                    em.build();
                    MessageEmbed embed = em.build();
                    event.getTextChannel()
                            .sendMessage(embed).queue();
                    break;
                default:
                    event.getTextChannel().sendMessage("Please use valid arguments, see 'help for info").queue();
            }
        }
    }

    /**
     * Method to print ticker info.
     * @param event
     */
    public void printStockInfo(MessageReceivedEvent event) {
        if(!(s.getName().equals("N/A"))) {
            EmbedBuilder em = new EmbedBuilder();
            em.setColor(Color.red).setTitle(s.getSymbol() + "    |    " + s.getName() + "    |    " + s.getChg() + "%");
            User author = event.getAuthor();
            em.setAuthor("@" + author.getName(), author.getAvatarId(),author.getEffectiveAvatarUrl());
            em.setDescription("Price: " + s.getPrice() + " | " + "Day high: " + s.getDayhigh() + " | " + "Day low: " + s.getDaylow());
            em.setImage("http://stockcharts.com/c-sc/sc?s=" + s.getSymbol() + "&p=D&b=5&g=0&i=0&r=1481397950622.png");
            em.setUrl("http://finance.yahoo.com/quote/" + s.getSymbol() + "?p=" + s.getSymbol());
            em.setFooter("Data collected from Yahoo Finance","https://upload.wikimedia.org/wikipedia/commons/5/59/Empty.png");
            MessageEmbed embed = em.build();
            event.getTextChannel()
                    .sendMessage(embed).queue();
        }
        else {
            event.getTextChannel().sendMessage(s.getSymbol() + " is not a valid ticker").queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        event.getTextChannel()
                .sendMessage("http://stockcharts.com/c-sc/sc?s=" + s.getSymbol() + "&p=D&b=5&g=0&i=0&r=1481397950622.png").queue();
    }
}
