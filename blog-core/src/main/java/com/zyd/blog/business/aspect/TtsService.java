package com.zyd.blog.business.aspect;

import com.alibaba.druid.util.StringUtils;
import com.zyd.blog.business.util.tts.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.InputStream;

@Slf4j
@Component
public class TtsService {

    @Resource
    private Authentication authentication;

    /**
     * 合成音频
     */
    public byte[] genAudioBytes(String textToSynthesize, String locale, String gender, String voiceName) {

        String accessToken = authentication.genAccessToken();
        if (StringUtils.isEmpty(accessToken)) {
            return new byte[0];
        }
        try {
            HttpsURLConnection webRequest = HttpsConnection.getHttpsConnection(TtsConst.TTS_SERVICE_URI);
            webRequest.setDoInput(true);
            webRequest.setDoOutput(true);
            webRequest.setConnectTimeout(5000);
            webRequest.setReadTimeout(300000);
            webRequest.setRequestMethod("POST");

            webRequest.setRequestProperty("Content-Type", "application/ssml+xml");
            webRequest.setRequestProperty("X-Microsoft-OutputFormat", TtsConst.AUDIO_24KHZ_48KBITRATE_MONO_MP3);
            webRequest.setRequestProperty("Authorization", "Bearer " + accessToken);
            webRequest.setRequestProperty("X-Search-AppId", "07D3234E49CE426DAA29772419F436CC");
            webRequest.setRequestProperty("X-Search-ClientID", "1ECFAE91408841A480F00935DC390962");
            webRequest.setRequestProperty("User-Agent", "TTSAndroid");
            webRequest.setRequestProperty("Accept", "*/*");

            String body = XmlDom.createDom(locale, gender, voiceName, textToSynthesize);
            if (StringUtils.isEmpty(body)) {
                return new byte[0];
            }
            byte[] bytes = body.getBytes();
            webRequest.setRequestProperty("content-length", String.valueOf(bytes.length));
            webRequest.connect();
            DataOutputStream dop = new DataOutputStream(webRequest.getOutputStream());
            dop.write(bytes);
            dop.flush();
            dop.close();

            InputStream inSt = webRequest.getInputStream();
            ByteArray ba = new ByteArray();

            int rn2 = 0;
            int bufferLength = 4096;
            byte[] buf2 = new byte[bufferLength];
            while ((rn2 = inSt.read(buf2, 0, bufferLength)) > 0) {
                ba.cat(buf2, 0, rn2);
            }

            inSt.close();
            webRequest.disconnect();

            return ba.getArray();
        } catch (Exception e) {
            log.error("Synthesis tts speech failed {}", e.getMessage());
        }
        return null;
    }



    /**
     * 生成中文音频信息
     */
    public byte[] getZHAudioBuffer(String gender, String chapterContent, String locale, String voiceName) {
        byte[] audioBuffer;
        if (chapterContent.length() <= 2600) {
            audioBuffer = genAudioBytes(chapterContent, locale, gender, voiceName);
        } else {
            byte[] audioBuffer1 = genAudioBytes(chapterContent.substring(0, chapterContent.length() / 2), locale, gender, voiceName);
            byte[] audioBuffer2 = genAudioBytes(chapterContent.substring(chapterContent.length() / 2), locale, gender, voiceName);
            ByteArray byteArray = new ByteArray(audioBuffer1);
            byteArray.cat(audioBuffer2);
            audioBuffer = byteArray.getArray();
        }
        return audioBuffer;
    }

    /**
     * 生成英文音频信息
     */
    public byte[] getUSAudioBuffer(String gender, String chapterContent, String locale, String voiceName) {
        String[] words = chapterContent.split(" ");
        byte[] audioBuffer;
        int maxLength = 1500;
        if (words.length <= maxLength) {
            audioBuffer = genAudioBytes(chapterContent, locale, gender, voiceName);
        } else {
            String[] part1 = new String[maxLength];
            String[] part2 = new String[words.length - maxLength];
            for (int i = 0; i < words.length; i++) {
                if (i < maxLength) {
                    part1[i] = words[i];
                } else {
                    part2[i - maxLength] = words[i];
                }
            }
            byte[] audioBuffer1 = genAudioBytes(String.join(" ", part1), locale, gender, voiceName);
            byte[] audioBuffer2 = genAudioBytes(String.join(" ", part2), locale, gender, voiceName);
            ByteArray byteArray = new ByteArray(audioBuffer1);
            byteArray.cat(audioBuffer2);
            audioBuffer = byteArray.getArray();
        }
        return audioBuffer;
    }
}