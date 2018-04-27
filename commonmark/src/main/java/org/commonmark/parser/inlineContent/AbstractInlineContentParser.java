package org.commonmark.parser.inlineContent;

import org.commonmark.node.Node;
import org.commonmark.parser.InlineContinue;

public class AbstractInlineContentParser implements InlineContentParser {

    @Override
    public InlineContinue parse(String input, int pos, Node node) {
        return null;
    }

}
