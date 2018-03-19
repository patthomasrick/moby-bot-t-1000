package io.github.patthomasrick.moby;

import io.github.patthomasrick.moby.commands.*;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.*;

public class CommandListener {

    // some precanned responses
    public static final String REPLY_NOT_ADMIN = "Use must be an admin to use this command.";

    // static map of registered commands
    public static Map<String, Command> commandMap;

    // populate map with commands
    // ALL COMMANDS MUST BE ADDED HERE
    static {
        CommandListener.commandMap = new TreeMap<>();

        CommandListener.addCommand(new ChoiceCommand());
        CommandListener.addCommand(new HelloCommand());
        CommandListener.addCommand(new HelpCommand());
        CommandListener.addCommand(new SourceCommand());
        CommandListener.addCommand(new YtPlayCommand());
        CommandListener.addCommand(new YtSkipCommand());
        CommandListener.addCommand(new VoiceJoinCommand());
        CommandListener.addCommand(new VoiceLeaveCommand());
        CommandListener.addCommand(new RestartCommand());
        CommandListener.addCommand(new SayCommand());

        CommandListener.addCommand(new AliasCommand()); // HAS TO BE LAST
    }

    /**
     * A helper method to more concisely add a command mapping.
     *
     * @param command Command object
     */
    public static void addCommand(Command command) {
        for (String s : command.getAliases()) {
            CommandListener.commandMap.put(s, command);
        }
    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {

        // if the message starts with alias, behave differently
        String[] cmdArray = {event.getMessage().getContent()};
        if (!cmdArray[0].startsWith("!alias")) {
            // split into subcommands
            cmdArray = event.getMessage().getContent().split("&&");
        }

        for (String cmd : cmdArray) {
            cmd = cmd.trim();

            // split message into args
            String[] argArray = cmd.split(" ");

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
            } else {
                event.getMessage().reply(String.format("`%s`: Not a command.", cmd));
            }
        }
    }
}
