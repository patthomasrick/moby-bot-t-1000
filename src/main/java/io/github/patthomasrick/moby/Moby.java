/**
 * Patrick Thomas
 * Moby T-1000
 */

package io.github.patthomasrick.moby;


import io.github.patthomasrick.moby.audio.AudioPlayer;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Moby T-1000
 * <p>
 * Various static methods to create a simple Discord bot.
 *
 * @author Patrick Thomas
 */
public class Moby {
    public static final String CMD_PREFIX = "!";
    public static final AudioPlayer audioPlayer = new AudioPlayer();

    /**
     * Create a Discord client given a bot token. Bot tokens are generated from
     * Discord themselves. Handles everything up to logging in, but no more. If
     * an invalid bot token is given, the error is handled and no client is
     * returned.
     *
     * @param token bot token
     * @param login should the client be logged in upon creation? no if you want to manually
     *              login the bot at a later time
     * @return client
     */
    public static IDiscordClient createClient(String token, boolean login) { // Returns a new instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder

        try {
            if (login) {
                return clientBuilder.login(); // Creates the client instance and logs the client in
            } else {
                return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String args[]) {
        // create client and dispatcher
        IDiscordClient client = Moby.createClient(Moby.getToken(), true);
        EventDispatcher dispatcher = client.getDispatcher();

        dispatcher.registerListener(new AnnotationListener());
        dispatcher.registerListener(new CommandListener());

        return;
    }

    /**
     * Load the bot token from a plain text, unencrypted file in the root of the
     * project's directory.
     *
     * @return token
     */
    private static String getToken() {
        // load token from file
        File f = new File("token.txt");

        String token = "";
        try (BufferedReader reader = Files.newBufferedReader(f.toPath())) {
            token = reader.readLine().trim();
            return token;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * A helper method to safely send a message to a channel.
     *
     * @param channel Discord channel to message
     * @param message content of message
     */
    public static void sendMessage(IChannel channel, String message) {
        // copy and pasted, apparently is safer
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException e) {
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
        });
    }
}
