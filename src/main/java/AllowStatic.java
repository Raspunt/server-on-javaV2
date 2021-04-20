import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class AllowStatic {

    private ContentReader cr = new ContentReader();
    private HeadersParser parser ;
    private Socket clientSocket ;

    private String method ;
    private  String UrlPath ;
    private  String HttpVersion ;


    public AllowStatic(String method, String UrlPath, String HttpVersion,Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.method = method;
        this.UrlPath = UrlPath;
        this.HttpVersion = HttpVersion;
    }



    public void addStaticFolder(String staticFolder) throws IOException {


        Settings settings  = new Settings() ;


        String pathToStatic = settings.WorkDirectoryPath + staticFolder ;
        File f = new File(pathToStatic);



        for (String pathname : Objects.requireNonNull(f.list())) {
            // Print the names of files and directories
            if (UrlPath.equals("/" + pathname)) {

                String html = cr.render(staticFolder + "/" + pathname );
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(html.getBytes(StandardCharsets.UTF_8));

            }


        }




    }




}
