package com.html5parser.algorithms;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.html5parser.classes.ParserContext;
import com.html5parser.classes.Token;

public class InsertForeignElement {

	public static Element run(ParserContext context, Token token,
			String namespace) {
		Node adjustedInsertionLocation = AppropiatePlaceForInsertingANode
				.run(context);
		Element element = CreateAnElementForAToken.run(
				adjustedInsertionLocation, namespace, token);
		try {
			adjustedInsertionLocation.appendChild(element);
		} catch (DOMException e) {
			// TODO drop the new element on the floor
		}

		context.getOpenElements().push(element);

		return element;
	}
}
