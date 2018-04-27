package org.commonmark.parser.inlineContent;

import org.commonmark.node.Node;
import org.commonmark.parser.InlineContinue;


public interface InlineContentParser {

    InlineContinue parse(String input, int pos, Node node);
}
