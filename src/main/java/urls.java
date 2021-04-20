import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class urls {
    private ContentReader cr = new ContentReader();
    private HeadersParser parser ;
    private Socket clientSocket ;

    private String method ;
    private  String UrlPath ;
    private  String HttpVersion ;



    public urls(String method ,String UrlPath,String HttpVersion,Socket clientSocket){

        this.clientSocket = clientSocket ;
        this.method = method ;
        this.UrlPath = UrlPath;
        this.HttpVersion = HttpVersion;


    }


    void UrlsCheck() throws IOException {


            if (UrlPath.equals("/")) {
                String html = cr.render("templates/index.html");
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(html.getBytes(StandardCharsets.UTF_8));
            }

            if (UrlPath.equals("/Led")) {
                String html = cr.render("templates/NewPage.html");
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(html.getBytes(StandardCharsets.UTF_8));
            }




            System.out.println(method  + " " + UrlPath + " " + HttpVersion + " " + cr.getAbsoluteLocationFile() );








    }







}
