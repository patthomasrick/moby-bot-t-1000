package io.github.patthomasrick.moby.commands;

import io.github.patthomasrick.moby.CommandListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AliasCommand extends Command {

    public AliasCommand() {
        aliases = new String[]{"alias"};
        helpStr = "Makes a shortcut to another command with arguments.";
        usageStr = "!alias command arg0 arg1 ... argn";

        this.loadAliases("aliases.txt");
    }

    /**
     * Load saved aliases from a file.'
     * <p>
     * Aliases are saved in the following format:
     * aliasname command arg0 arg1 ... argn
     *
     * @param fName Name of the text file to load the aliases from.
     * @return Map of command names and commands
     */
    public void loadAliases(String fName) {
        // initialize scanner
        Scanner reader = null;
        try {
            reader = new Scanner(new File(fName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // add
        while (reader.hasNextLine()) {
            String line = reader.nextLine().trim();

            if (!line.isEmpty()) {
                String[] argArray = line.split(" ");
                List<String> args = new ArrayList<>();
                Collections.addAll(args, argArray);

                this.addCommand(args);
            }
        }
    }

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        int status = this.addCommand(args);
        switch (status) {
            case (0):
                event.getMessage().reply("Alias added successfully.");
                break;

            case (1):
                event.getMessage().reply("Not enough arguments, at the very least there must " +
                        "be the name of the alias you want to make and a command to reference " +
                        "it to.");
                break;

            case (2):
                event.getMessage().reply("This alias would overwrite an existing command or alias.");
                break;

            default:
                event.getMessage().reply("An unknown error occurred.");
                break;
        }
    }

    private int addCommand(List<String> args) {
        // check if not already used
        if (args.size() >= 1 && CommandListener.commandMap.get(args.get(0)) == null) {
            // valid call
            // extract names
            String aliasName = args.get(0);
            String commandName = args.get(1);
            Command command = CommandListener.commandMap.get(commandName);

            // extract args
            List<String> safeArgs = new ArrayList<>(args);
            safeArgs.remove(0); // remove the name
            safeArgs.remove(0); // remove the command

            // make command
            AliasedCommand aCmd = new AliasedCommand(aliasName, command, safeArgs);
            CommandListener.addCommand(aCmd);

            return 0;

        } else if (args.size() < 1) {
//            event.getMessage().reply("Not enough arguments, at the very least there must " +
//                    "be the name of the alias you want to make and a command to reference " +
//                    "it to.");
            return 1;
        } else if (CommandListener.commandMap.get(args.get(0)) != null) {
//            event.getMessage().reply("This alias would overwrite an existing command or alias.");
            return 2;
        } else {
            return -1;
        }
    }
}
