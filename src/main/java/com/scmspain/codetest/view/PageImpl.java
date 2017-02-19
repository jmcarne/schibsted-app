package com.scmspain.codetest.view;

import org.rendersnake.HtmlCanvas;

import java.io.IOException;

import static org.rendersnake.HtmlAttributesFactory.href;

/**
 * Created by josep.carne on 13/02/2017.
 */
public class PageImpl {
    public String doPage(int number, String userName) throws IOException {
        final HtmlCanvas html = new HtmlCanvas();
        return html
                .html()
                .body()
                .h1().content("PAGE: " + number)
                .h1().content("Hello " + userName)
                .h1().a(href("/app/login/logout.html")).write("Logout")._a()._h1()
                ._body()
                ._html()
                .toHtml();
    }
}
