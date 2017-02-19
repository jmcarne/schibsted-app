package com.scmspain.codetest.view;

import org.rendersnake.HtmlCanvas;

import java.io.IOException;

import static org.rendersnake.HtmlAttributesFactory.for_;
import static org.rendersnake.HtmlAttributesFactory.id;
import static org.rendersnake.HtmlAttributesFactory.type;

/**
 * Created by josep.carne on 13/02/2017.
 */
public class LoginFormImpl {
    private static final String ID_USERNAME = "username";
    private static final String ID_PASSWORD = "password";
    private static final String VAR_USERNAME = "username";
    private static final String VAR_PASSWORD = "password";

    public String doNoRequiredLogin() throws IOException {
        final HtmlCanvas html = new HtmlCanvas();
        return html
                .html()
                .body()
                .h1().content("NO REQUIRED LOGIN")
                ._body()
                ._html()
                .toHtml();
    }

    public String doRequiredLogin(String requestedURI) throws IOException {
        final HtmlCanvas html = new HtmlCanvas();
        return html
                .html()
                .form(id("sample").action(requestedURI).method("post"))
                .label(for_(ID_USERNAME)).write("Username:")._label()
                .input(
                        id(ID_USERNAME)
                                .name(VAR_USERNAME))
                .br()
                .label(for_(ID_PASSWORD)).write("Password:")._label()
                .input(
                        type("password")
                                .id(ID_PASSWORD)
                                .name(VAR_PASSWORD))
                .br()
                .input(type("submit").value("Log me in"))
                ._form()
                .toHtml();
    }
}
