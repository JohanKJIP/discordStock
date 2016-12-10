package discordBot.commands;

import discordBot.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

/**
 * Created by JohanvonHacht on 06/12/16.
 */
public class HelpCommand  implements Command {
    private final String HELP = "USAGE: 'help";
    public static final String newline = System.getProperty("line.separator");

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        EmbedBuilder em = new EmbedBuilder();
        em.setColor(Color.red).setTitle("Commands: ");
        User author = event.getAuthor();
        em.setAuthor("@" + author.getName(), author.getAvatarId(),author.getEffectiveAvatarUrl());
        em.setDescription("'ticker symbol args  -  To display stock information. Only symbol needed but can return single value with args e.g. pe, eps, mc, short etc." +
                newline + newline + "'name  -  example 'erik, 'linus, 'pontus" +
                newline + newline + "'math  -  return a simple math problem");
        em.build();
        MessageEmbed embed = em.build();
        event.getTextChannel()
                .sendMessage(embed).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        return;
    }
}
