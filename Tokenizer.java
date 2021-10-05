import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    public static HashMap<String,String> getKeyword() {
        HashMap<String,String> keyword = new HashMap<>();
        keyword.put("if","If");
        keyword.put("else","Else");
        keyword.put("while","While");
        keyword.put("break","Break");
        keyword.put("continue","Continue");
        keyword.put("return","Return");
        return keyword;
    }

    public static HashMap<String,String> getSeparator() {
        HashMap<String,String> separator = new HashMap<>();
        separator.put("=","Assign");
        separator.put(";","Semicolon");
        separator.put("(","LPar");
        separator.put(")","RPar");
        separator.put("{","LBrace");
        separator.put("}","RBrace");
        separator.put("+","Plus");
        separator.put("*","Mult");
        separator.put("/","Div");
        separator.put("<","Lt");
        separator.put(">","Gt");
        return separator;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pattern ident = Pattern.compile("[a-zA-Z_][a-zA-Z_0-9]*");
        Pattern number = Pattern.compile("[0-9]+");
        Pattern eq = Pattern.compile("==");
        Pattern other = Pattern.compile("=|;|\\(|\\)|(\\{)|}|\\+|\\*|/|<|>");

        HashMap<String,String> keyword = getKeyword();
        HashMap<String,String> separator = getSeparator();

        while (sc.hasNext()) {
            String in = sc.next();
            while(in.length()>0) {
                Matcher identMatcher = ident.matcher(in);
                Matcher numberMatcher = number.matcher(in);
                Matcher eqMatcher = eq.matcher(in);
                Matcher otherMatcher = other.matcher(in);
                if(identMatcher.lookingAt()){
                    if(keyword.containsKey(identMatcher.group()))
                        System.out.println(keyword.get(identMatcher.group()));
                    else
                        System.out.println("Ident("+identMatcher.group()+')');
                    in = in.substring(identMatcher.end());
                } else if (numberMatcher.lookingAt()){
                    System.out.println("Number("+numberMatcher.group()+')');
                    in = in.substring(numberMatcher.end());
                } else if (eqMatcher.lookingAt()){
                    System.out.println("Eq");
                    in = in.substring(eqMatcher.end());
                } else if (otherMatcher.lookingAt()){
                    System.out.println(separator.get(otherMatcher.group()));
                    in = in.substring(otherMatcher.end());
                } else {
                    System.out.println("Err");
                    System.exit(0);
                }
            }
        }
    }
}
