package com.gunna.bigburger.androidapp.data.remote.network;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;


import java.net.InetSocketAddress;
import java.net.Socket;

import static com.gunna.bigburger.androidapp.app.Constants.NETWORK_TIMEOUT_MS;

public class NetworkStatusImpl implements NetworkStatus {

    @Override
    public Flowable<Boolean> isOnline() {
        return Flowable.create(emitter -> {
            {
                Thread checkConnectionThread = new Thread(() -> {
                    try {
                        Socket sock = new Socket();
                        InetSocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);
                        sock.connect(sockaddr, NETWORK_TIMEOUT_MS);
                        sock.close();
                        emitter.onNext(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        emitter.onNext(false);
                    }
                    emitter.onComplete();
                });
                checkConnectionThread.start();
            }
        }, BackpressureStrategy.BUFFER);
    }
}
