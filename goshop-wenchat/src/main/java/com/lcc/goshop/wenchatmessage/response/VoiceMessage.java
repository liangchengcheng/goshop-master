package com.lcc.goshop.wenchatmessage.response;

/**
 * Created by lcc on 2017/2/18.
 */
public class VoiceMessage extends BaseMessage {
    // 语音
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}