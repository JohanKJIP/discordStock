package discordBot;

import discordBot.commands.*;
//import discordBot.commands.MathProblem.AnswerProblem;
import discordBot.commands.StockFetcher.TickerCommand;
import discordBot.utils.CommandParser;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import java.util.HashMap;

/**
 * Created by JohanvonHacht on 06/12/16.
 */
public class Main {

    private static JDA jda;
    public static final CommandParser parser = new CommandParser();

    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    /**
     * Try create jdabuilder, add commands.
     * @param args
     */
    public static void main(String[] args) {
        try {
            jda = new JDABuilder(AccountType.BOT).addListener(new BotListener()).setToken("MjU1NzQ1MzUyNTU1MTY3NzQ1.CyiE_Q.hpnR8UXFKsgY8Te1AfrZpFZJHuA").buildBlocking();
            jda.setAutoReconnect(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        commands.put("ticker", new TickerCommand());
        commands.put("help", new HelpCommand());
    }

    /**
     * Handles commands if safe.
     * @param cmd command entered
     */
    public static void handleCommand(CommandParser.CommandContainer cmd) {
        if(commands.containsKey(cmd.invoke)) {
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if(safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
    }
}