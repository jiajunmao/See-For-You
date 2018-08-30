package com.app.CClient.Utlis;


import android.media.MediaCodec;
import android.media.MediaFormat;
import android.view.Surface;

import com.esong.lib.EsongLib;

import java.nio.ByteBuffer;

public class StreamManager {
    public static int FRAME_WIDTH = 1280;
    public static int FRAME_HEIGHT = 720;
    public static final int FRAME_RATE = 10;
    private MediaCodec mMediaDecoder;
    private Surface mSurface;
    private int mFrameIndex = 0;
    public StreamManager(Surface mSurface){
        this.mSurface=mSurface;
    }
    public static void closeChanal(final int sock) {
        ConnectManager.cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                int result = EsongLib.GetInstance().ESongCloseChannel(sock);
            }
        });
    }

    private boolean setupDecoder(String mime, int width, int height) {
        MediaFormat mediaFormat = MediaFormat.createVideoFormat(mime, width, width);
        mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, 1);
        mediaFormat.setInteger(MediaFormat.KEY_SAMPLE_RATE, 1);
        mediaFormat.setInteger(MediaFormat.KEY_CHANNEL_COUNT, 1);
        try {
            mMediaDecoder = MediaCodec.createDecoderByType(mime);
            if (mSurface != null && mSurface.isValid()) {
                mMediaDecoder.configure(mediaFormat, mSurface, null, 0);
                mMediaDecoder.start();
            }
        } catch (Exception e) {
            ESLog.e("MediaCodec == " + e.getMessage());
            return false;
        }
        return true;
    }

    private void offerDecoder(byte[] input, int length) {
        try {
            ByteBuffer[] inputBuffers = mMediaDecoder.getInputBuffers();
            int inputBufferIndex = mMediaDecoder.dequeueInputBuffer(-1);
            if (inputBufferIndex >= 0) {
                ByteBuffer inputBuffer = inputBuffers[inputBufferIndex];
                long timestamp = mFrameIndex++ * 1000000 / FRAME_RATE;
                // Log.e(TAG,"offerDecoder timestamp: "
                // +timestamp+" inputSize: "+length + " bytes");
                inputBuffer.clear();
                inputBuffer.put(input, 0, length);
                mMediaDecoder.queueInputBuffer(inputBufferIndex, 0, length,
                        timestamp, 0);
            }
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int outputBufferIndex = mMediaDecoder.dequeueOutputBuffer(
                    bufferInfo, 0);

            while (outputBufferIndex >= 0) {
                mMediaDecoder.releaseOutputBuffer(outputBufferIndex, true);
                outputBufferIndex = mMediaDecoder.dequeueOutputBuffer(
                        bufferInfo, 0);
            }
        } catch (Throwable t) {
            t.printStackTrace();
            if (mMediaDecoder != null) {
                mMediaDecoder.release();
                mMediaDecoder = null;
            }
        }
    }

    public void startPlayH264File(byte[] stream, int size) {
        assert (mSurface != null);
        if (mMediaDecoder == null) {
            if (!setupDecoder( "video/avc", FRAME_WIDTH, FRAME_HEIGHT)) {
                return;
            }
        }
        readH264FromFile(stream, size);
    }

    private void readH264FromFile(byte[] stream, int size) {
        byte[] buf = stream;
        offerDecoder(buf, size);
    }
}
