import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeadersParser {

    private ArrayList<String> headers = new ArrayList<>();
    private BufferedReader in ;
    private String method ;
    private String PathUrl ;
    private String HttpVersion ;


    public HeadersParser (BufferedReader in) {

        this.in = in ;

        try {
            String inputLine;
            int i = 0;
            while ((inputLine = in.readLine()) != null) {

                headers.add(inputLine);

                break;



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



        public void InitializeMethodPathHttp() {



        for (int i = 0; i < headers.size(); i++){

            String[] splitString = headers.get(i).split(":",1);

            for (int j =0;i<splitString.length;i++){
              String[] MethodAndUrl = splitString[j].split(" ");

              method = MethodAndUrl[0];
              PathUrl = MethodAndUrl[1];
              HttpVersion = MethodAndUrl[2];

            }
        }
    }



    public String getPathUrl() {
        return PathUrl ;
    }

    public String getMethod() {
        return method;
    }

    public String getHttpVersion() {
        return HttpVersion;
    }
}
