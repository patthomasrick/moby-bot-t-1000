package io.github.patthomasrick.moby;

import io.github.patthomasrick.moby.commands.*;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.*;

public class CommandListener {

    // static map of registered commands
    public static Map<String, Command> commandMap;

    // populate map with commands
    // ALL COMMANDS MUST BE ADDED HERE
    static {
        CommandListener.commandMap = new TreeMap<>();

        CommandListener.addCommand(new ChoiceCommand());
        CommandListener.addCommand(new HelloCommand());
        CommandListener.addCommand(new HelpCommand());
        CommandListener.addCommand(new YtPlayCommand());
        CommandListener.addCommand(new YtSkipCommand());
    }

    private static void addCommand(Command command) {
        for (String s : command.getAliases()) {
            CommandListener.commandMap.put(s, command);
        }
    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        // split message into args
        String[] argArray = event.getMessage().getContent().split(" ");

        // cannot have 0 args
        if (argArray.length == 0 || !argArray[0].startsWith(Moby.CMD_PREFIX)) {
            return;
        }

        // extract command arg (remove prefix)
        String commandStr = argArray[0].substring(1);

        // put other args into arraylist
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0); // remove the cmd

        // run a command
        if (CommandListener.commandMap.containsKey(commandStr)) {
            CommandListener.commandMap.get(commandStr).runCommand(event, argsList);
        }
    }
}
