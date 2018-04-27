package org.commonmark.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Node;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.Text;
import org.commonmark.parser.InlineContinue;
import org.commonmark.parser.inlineContent.AbstractInlineContentParser;

public class NewLineBreakParser extends AbstractInlineContentParser {

    private static final Pattern FINAL_SPACE = Pattern.compile(" *$");
    
    private char peek(String input, int index) {
        if (index < input.length()) {
            return input.charAt(index);
        } else {
            return '\0';
        }
    }

    
    @Override
    public InlineContinue parse(String input, int index, Node node) {
        index++; // assume we're at a \n

        Node lastChild = node.getLastChild();
        // Check previous text for trailing spaces.
        // The "endsWith" is an optimization to avoid an RE match in the common case.
        if (lastChild != null && lastChild instanceof Text && ((Text) lastChild).getLiteral().endsWith(" ")) {
            Text text = (Text) lastChild;
            String literal = text.getLiteral();
            Matcher matcher = FINAL_SPACE.matcher(literal);
            int spaces = matcher.find() ? matcher.end() - matcher.start() : 0;
            if (spaces > 0) {
                text.setLiteral(literal.substring(0, literal.length() - spaces));
            }
            node.appendChild((spaces >= 2 ? new HardLineBreak() : new SoftLineBreak()) );
        } else {
            node.appendChild((new SoftLineBreak()) );
        }

        // gobble leading spaces in next line (Look ahead)
        while (peek(input, index) == ' ') {
            index++;
        }
        return new InlineContinue(index);
    }

   

}
