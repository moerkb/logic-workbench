package gui;

import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMaker;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMap;

/**
 * Implementation of RSynaxTextArea's TokenMaker for highlighting an MPA formula.
 * 
 * @author Markus Bader
 */

public class MpaTokenMaker extends AbstractTokenMaker {

	@Override
	public Token getTokenList(Segment text, int startTokenType, int startOffset) {
		resetTokenList();
		
		char[] array = text.array;
		int offset = text.offset;
		int count = text.count;
		int end = offset + count;

		int newStartOffset = startOffset - offset;
		int currentTokenStart = offset;
		int currentTokenType = startTokenType;
		
		for (int i = offset; i < end; i++) {
			char c = array[i];
			
			switch (currentTokenType) {
			case Token.NULL:
				currentTokenStart = i;
				
				switch (c) {
				case ' ':
				case '\t':
					currentTokenType = Token.WHITESPACE;
					break;
					
				default:
					currentTokenType = Token.IDENTIFIER;
					break;
				} // End of switch (c)
				break;
				
			case Token.WHITESPACE:
				switch (c) {
				case ' ':
				case '\t':
					break;
					
				default: // add whitespace, then ID
					addToken(text, currentTokenStart, i-1, Token.WHITESPACE, newStartOffset + currentTokenStart);
					currentTokenStart = i;
					currentTokenType = Token.IDENTIFIER;
				} // End of switch (c)
				break;
				
			default:
			case Token.LITERAL_NUMBER_DECIMAL_INT:
			case Token.IDENTIFIER:
				switch (c) {
				case ' ':
				case '\t':
					addToken(text, currentTokenStart, i-1, Token.IDENTIFIER, newStartOffset + currentTokenStart);
					currentTokenStart = i;
					currentTokenType = Token.WHITESPACE;
					break;
					
				default:
					if (RSyntaxUtilities.isLetterOrDigit(c) || c == '_' || c == '\\' || c == '{' || c == '}') {
						break;
					}
					// Otherwise ?
				} // End of switch (c)
			} // End of switch (currentTokenType)
		} // End of for loop
		
		switch (currentTokenType) {
		// Everything ok?
		case Token.NULL:
			addNullToken();
			break;

		// All other tokens do not continue to the next line
		default:
			addToken(text, currentTokenStart, end-1, currentTokenType, newStartOffset + currentTokenStart);
			addNullToken();
		}
		
		return firstToken;
	}

	@Override
	public void addToken(Segment segment, int start, int end, int tokenType, int startOffset) {
		// parse all keywords as identifiers
		if (tokenType == Token.IDENTIFIER) {
			int value = wordsToHighlight.get(segment, start, end);
			
			if (value != -1) {
				tokenType = value;
			}
		}
		super.addToken(segment, start, end, tokenType, startOffset);
	}

	@Override
	public TokenMap getWordsToHighlight() {
		TokenMap tokenMap = new TokenMap();
		
		// constants
		tokenMap.put("T", Token.RESERVED_WORD);
		tokenMap.put("1", Token.RESERVED_WORD);
		tokenMap.put("True", Token.RESERVED_WORD);
		tokenMap.put("F", Token.RESERVED_WORD);
		tokenMap.put("0", Token.RESERVED_WORD);
		tokenMap.put("False", Token.RESERVED_WORD);
		
		// unary operators
		tokenMap.put("!", Token.RESERVED_WORD);
		tokenMap.put("not", Token.RESERVED_WORD);
		
		// binary operators
		tokenMap.put("&", Token.RESERVED_WORD);
		tokenMap.put("and", Token.RESERVED_WORD);
		tokenMap.put("!&", Token.RESERVED_WORD);
		tokenMap.put("nand", Token.RESERVED_WORD);
		tokenMap.put("|", Token.RESERVED_WORD);
		tokenMap.put("or", Token.RESERVED_WORD);
		tokenMap.put("!|", Token.RESERVED_WORD);
		tokenMap.put("nor", Token.RESERVED_WORD);
		tokenMap.put("<-", Token.RESERVED_WORD);
		tokenMap.put("if", Token.RESERVED_WORD);
		tokenMap.put("nif", Token.RESERVED_WORD);
		tokenMap.put("->", Token.RESERVED_WORD);
		tokenMap.put("impl", Token.RESERVED_WORD);
		tokenMap.put("nimpl", Token.RESERVED_WORD);
		tokenMap.put("<->", Token.RESERVED_WORD);
		tokenMap.put("iff", Token.RESERVED_WORD);
		tokenMap.put("^", Token.RESERVED_WORD);
		tokenMap.put("xor", Token.RESERVED_WORD);
		
		return tokenMap;
	}

}
