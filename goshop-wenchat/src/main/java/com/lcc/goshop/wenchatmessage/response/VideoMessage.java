package com.lcc.goshop.wenchatmessage.response;

/**
 * Created by lcc on 2017/2/18.
 */
public class VideoMessage extends BaseMessage {

    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}