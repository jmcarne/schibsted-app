package com.scmspain.codetest.view;

import org.rendersnake.HtmlCanvas;

import java.io.IOException;

/**
 * Created by josep.carne on 25/02/2017.
 */
public class UnAuthenticated {
    public String doAuthenticated() throws IOException {
        final HtmlCanvas html = new HtmlCanvas();
        return html
                .html()
                .body()
                .h1().content("401 ERROR: YOU ARE NOT AUTHENTICATED")
                ._body()
                ._html()
                .toHtml();
    }
}
