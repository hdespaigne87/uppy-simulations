package com.uppy.simulations.utils;

import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public final class PusherUtil {
    private static final String appId = "1049326";
    private static final String apiKey = "581b35f9cd749ea30e34";
    private static final String apiSecret = "76466cafb77f53376f80";
    private static final String cluster = "us2";

    @Getter
    @AllArgsConstructor
    public static class EventChannelConfig {
        private String channelName;
        private List<String> eventsToReceive;
    }

    public static void sendMessage(String channel, String eventName, Object data) {
        Pusher pusher = new Pusher(PusherUtil.appId, PusherUtil.apiKey, PusherUtil.apiSecret);
        pusher.setCluster(PusherUtil.cluster);
        pusher.trigger(channel, eventName, data);
    }

    public static void startClient(List<EventChannelConfig> channelsToSuscribe) {
        PusherOptions options = new PusherOptions().setCluster(PusherUtil.cluster);
        com.pusher.client.Pusher pusher = new com.pusher.client.Pusher(PusherUtil.apiKey, options);
        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                System.out.println("State changed to " + change.getCurrentState() +
                        " from " + change.getPreviousState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                System.out.println("There was a problem connecting!");
            }
        });
        for(EventChannelConfig channelConfig : channelsToSuscribe) {
            Channel channel = pusher.subscribe(channelConfig.getChannelName());
            for(String event : channelConfig.getEventsToReceive()) {
                channel.bind(event, new ChannelEventListener() {

                    @Override
                    public void onEvent(PusherEvent pusherEvent) {
                        System.out.println("---------------------");
                        System.out.println("---------------------");
                        System.out.println(String.format("Event <%s> Received with Data:\n\n%s", pusherEvent.getEventName(), pusherEvent.getData()));
                        System.out.println("---------------------");
                        System.out.println("---------------------");
                    }

                    @Override
                    public void onSubscriptionSucceeded(String channelName) {
                        System.out.println("Subscribed to channel: " + channelName);
                    }

                    // Other ChannelEventListener methods
                });
            }
        }
    }
}
