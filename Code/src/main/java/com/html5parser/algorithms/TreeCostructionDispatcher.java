package com.html5parser.algorithms;

import org.w3c.dom.Element;

import com.html5parser.classes.InsertionMode;
import com.html5parser.classes.ParserContext;
import com.html5parser.classes.Token;
import com.html5parser.classes.Token.TokenType;
import com.html5parser.constants.Namespace;
import com.html5parser.factories.InsertionModeFactory;
import com.html5parser.factories.TokenizerStateFactory;
import com.html5parser.interfaces.IInsertionMode;

public class TreeCostructionDispatcher {
	public static Boolean run(ParserContext context) {
		/*
		 * there is no adjusted current node If the adjusted current node is an
		 * element in the HTML namespace 
		 * 
		 * If the adjusted current node is a MathML text integration point and 
		 * the token is a start tag whose tag name is neither "mglyph" nor "malignmark" 
		 * 
		 * If the adjusted current node is a MathML text integration 
		 * point and the token is a character token 
		 * 
		 * If the adjusted current node is an annotation-xml element in
		 * the MathML namespace and the token is a start tag whose tag name is
		 * "svg" 
		 * 
		 * If the adjusted current node is an HTML integration point and
		 * the token is a start tag 
		 * If the adjusted current node is an HTML integration point and 
		 * the token is a character token 
		 * If the token is an end-of-file token
		 */
		Element adjustedCurrentNode = context.getAdjustedCurrentNode();
		Token currentToken = context.getTokenizerContext().getCurrentToken();
		if (!(adjustedCurrentNode == null
				|| adjustedCurrentNode.getNamespaceURI().equals(Namespace.HTML)
				|| (IntegrationPoint
						.isMathMLTextIntegrationPoint(adjustedCurrentNode) && !(currentToken
						.getValue().equals("mglyph") || currentToken.getValue()
						.equals("malignmark")))
				|| (IntegrationPoint
						.isMathMLTextIntegrationPoint(adjustedCurrentNode) && currentToken.getType().equals(TokenType.character))
				|| (adjustedCurrentNode.getNamespaceURI().equals(Namespace.MathML) 
						&& adjustedCurrentNode.getNodeName().equals("annotation-xml")
						&& currentToken.getValue().equals("svg")
						&& currentToken.getType().equals(TokenType.start_tag))
				|| (IntegrationPoint.isHtmlIntegrationPoint(adjustedCurrentNode)
						&& currentToken.getType().equals(TokenType.start_tag))
				|| (IntegrationPoint.isHtmlIntegrationPoint(adjustedCurrentNode)
						&& currentToken.getType().equals(TokenType.character))
				|| currentToken.getType().equals(TokenType.end_of_file)
				))
		{
			return false;

		}
		return true;

	}

}
