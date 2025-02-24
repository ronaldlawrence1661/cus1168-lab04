package academy.javapro;

class ExpressionParser {
    private final String input;
    private int position;

    public ExpressionParser(String input) {
        this.input = input;
        this.position = 0;
    }

   
    public double parseExpression() {
        double result = parseTerm();
        while (position < input.length()) {
            char currentChar = input.charAt(position);
            if (currentChar == '+') {
                position++; 
                result += parseTerm(); 
            } else {
                break; 
            }
        }
        return result;
    }
  
    
        private double parseTerm() {
            double result = parseFactor(); 
    
          
            while (position < input.length()) {
                char currentChar = input.charAt(position);
                if (currentChar == '*') {
                    position++;
                    result *= parseFactor(); 
                } else {
                    break; 
                }
            }
            return result;
        }

    // factor → ( expr )
  
        private double parseFactor() {
            if (position < input.length() && input.charAt(position) == '(') {
                position++; // Skip the '('
                double result = parseExpression(); // Parse the expression inside parentheses
                if (position < input.length() && input.charAt(position) == ')') {
                    position++; // Skip the ')'
                } else {
                    throw new IllegalArgumentException("Expected closing parenthesis");
                }
                return result;
            } else {
                return parseNumber(); // Otherwise, it's a number
            }
        }

   
   
        private double parseNumber() {
            StringBuilder number = new StringBuilder();
    
          
            while (position < input.length() && Character.isDigit(input.charAt(position))) {
                number.append(input.charAt(position));
                position++;
            }
    
            if (position < input.length() && input.charAt(position) == '.') {
                number.append('.');
                position++;
                while (position < input.length() && Character.isDigit(input.charAt(position))) {
                    number.append(input.charAt(position));
                    position++;
                }
            }
    
           
            return Double.parseDouble(number.toString());
        }

    public static void main(String[] args) {
       
        String[] testCases = {
                "2 + 3 * (4 + 5)",    
                "2 + 3 * 4",          
                "(2 + 3) * 4",        
                "2 * (3 + 4) * (5 + 6)", 
                "1.5 + 2.5 * 3"      
        };

      
        for (String expression : testCases) {
            System.out.println("\nTest Case: " + expression);
            try {
                ExpressionParser parser = new ExpressionParser(expression.replaceAll("\\s+", "")); // Remove spaces
                double result = parser.parseExpression();
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}