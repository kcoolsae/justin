package common;

import com.typesafe.config.Config;
import com.vladsch.flexmark.html.*;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MarkdownServiceImpl implements MarkdownService {

    // TODO HTML-tags not allowed

    private final Parser parser;

    private final HtmlRenderer renderer;

    @Inject
    public MarkdownServiceImpl(Config config) {
        MutableDataSet formatOptions = new MutableDataSet();
        formatOptions.set(HtmlRenderer.INDENT_SIZE, 2);
        formatOptions.set(HtmlRenderer.MAX_TRAILING_BLANK_LINES, 0);
        formatOptions.set(HtmlRenderer.ESCAPE_HTML, true);
        parser = Parser.builder(formatOptions).build();
        renderer = HtmlRenderer.builder(formatOptions).build();
    }

    public String toHtml(String markdown) {
        if (markdown == null || markdown.isBlank()) {
            return "";
        } else {
            String html = renderer.render(parser.parse(markdown));
            return html.substring(0, html.length() - 1); // remove the newline at the end
        }
    }

}
