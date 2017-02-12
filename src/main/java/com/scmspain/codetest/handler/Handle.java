package com.scmspain.codetest.handler;

import com.sun.net.httpserver.HttpExchange;

/**
 * Created by josep.carne on 12/02/2017.
 */
public interface Handle {
    public void handle(HttpExchange httpExchange);
}
