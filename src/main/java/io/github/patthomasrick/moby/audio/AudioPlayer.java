package io.github.patthomasrick.moby.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import io.github.patthomasrick.moby.Moby;
import sx.blah.discord.handle.audio.IAudioManager;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.MissingPermissionsException;

import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {
    private static final AudioPlayerManager playerManager;
    private static final Map<Long, MusicManager> musicManagers;

    static {
        musicManagers = new HashMap<>();
        playerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }

    // MUSIC PLAYER STUFF

    private static synchronized MusicManager getGuildAudioPlayer(IGuild guild) {
        long guildId = guild.getLongID();
        MusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new MusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setAudioProvider(musicManager.getAudioProvider());

        return musicManager;
    }

    public static void loadAndPlay(final MessageReceivedEvent event, final String trackUrl) {
        MusicManager musicManager = getGuildAudioPlayer(event.getGuild());

        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                Moby.sendMessage(
                        event.getChannel(),
                        String.format(
                                "%s Adding to queue: %s",
                                event.getAuthor().mention(),
                                track.getInfo().title));

                play(event, musicManager, track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                Moby.sendMessage(event.getChannel(),
                        String.format(
                                "Adding to queue: %s (first track of playlist %s)",
                                firstTrack.getInfo().title,
                                playlist.getName()));

                play(event, musicManager, firstTrack);
            }

            @Override
            public void noMatches() {
                Moby.sendMessage(
                        event.getChannel(),
                        String.format("%s Nothing found at %s", event.getAuthor().mention(), trackUrl));
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                Moby.sendMessage(
                        event.getChannel(),
                        String.format("%s Could not play: %s", event.getAuthor().mention(), exception.getMessage()));
            }
        });
    }

    public static void play(final MessageReceivedEvent event, MusicManager musicManager, AudioTrack track) {
        connectToVoiceChannel(event);

        musicManager.scheduler.queue(track);
    }

    public static void skipTrack(IChannel channel) {
        MusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        musicManager.scheduler.nextTrack();

        Moby.sendMessage(channel, "Skipped to next track.");
    }

    private static void connectToFirstVoiceChannel(IAudioManager audioManager) {
        for (IVoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
            if (voiceChannel.isConnected()) {
                return;
            }
        }

        for (IVoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
            try {
                voiceChannel.join();
            } catch (MissingPermissionsException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private static void connectToVoiceChannel(MessageReceivedEvent event) {
        try {
            event.getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel().join();
        } catch (MissingPermissionsException e) {
            e.printStackTrace();
            return;
        }
    }
}
