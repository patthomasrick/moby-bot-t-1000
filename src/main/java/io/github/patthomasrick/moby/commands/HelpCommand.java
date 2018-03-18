package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.CommandListener;
import io.github.patthomasrick.moby.Moby;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends Command {

    public HelpCommand() {
        aliases = new String[]{"?", "help"};
        helpStr = "Sends a list of commands and their usages.";
        usageStr = "!?";
    }

//    public static void main(String args[]) {
//        String out = String.format("%s\n```", "@pat");
//        List<Command> uniqueCommands = new ArrayList<>();
//
//        for (String k : CommandListener.commandMap.keySet()) {
//            // get command
//            Command c = CommandListener.commandMap.get(k);
//
//            // test if command has already been processed (to deal with aliases)
//            if (!uniqueCommands.contains(c)) {
//                // disable repeats from now on
//                uniqueCommands.add(c);
//
//                // concat name from alias
//                String name = c.getAliases()[0];
//                for (String a : c.getAliases()) {
//                    if (!a.equals(name)) {
//                        name += ", " + a;
//                    }
//                }
//
//                // add help to output
//                out = String.format("%s\n%s\n    Usage: %s\n    Desc: %s\n", out, name, c.getUsageStr(), c.getHelpStr());
//            }
//        }
//        out += "```";
//
//        System.out.println(out);
//    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {

        String out = String.format("%s\n```", event.getAuthor().mention());
        List<Command> uniqueCommands = new ArrayList<>();

        for (String k : CommandListener.commandMap.keySet()) {
            // get command
            Command c = CommandListener.commandMap.get(k);

            // test if command has already been processed (to deal with aliases)
            if (!uniqueCommands.contains(c)) {
                // disable repeats from now on
                uniqueCommands.add(c);

                // concat name from alias
                String name = c.getAliases()[0];
                for (String a : c.getAliases()) {
                    if (!a.equals(name)) {
                        name += ", " + a;
                    }
                }

                // add help to output
                out = String.format("%s\n%s\n    Usage: %s\n    Desc: %s\n", out, name, c.getUsageStr(), c.getHelpStr());
            }
        }
        out += "```";

        Moby.sendMessage(event.getChannel(), out);
    }
}