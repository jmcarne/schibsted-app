package com.scmspain.codetest.view;

import org.rendersnake.HtmlCanvas;

import java.io.IOException;

/**
 * Created by Josep Maria on 27/02/2017.
 */
public class UnAuthorizedImpl {
    public String doNotAuthorized() throws IOException {
        final HtmlCanvas html = new HtmlCanvas();
        return html
                .html()
                .body()
                .h1().content("403 ERROR: YOU ARE NOT AUTHORIZED")
                ._body()
                ._html()
                .toHtml();
    }
}
