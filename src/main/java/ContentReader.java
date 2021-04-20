import java.io.*;
import java.util.Objects;

public class ContentReader {

    private String AbsoluteLocationFile;


    public String render(String PathToFile){

        StringBuilder rez = new StringBuilder();
        StringBuilder rezContent = new StringBuilder();

        rez.append("HTTP/1.1 200 OK\r\n");

        try {

            String AbsoluteLocationFile = "src/main/java/" + PathToFile ;
            setAbsoluteLocationFile(AbsoluteLocationFile);



            InputStream fr = new FileInputStream(AbsoluteLocationFile);




            String[] endWish =  AbsoluteLocationFile.split("\\.");

            String endWishFile = endWish[1];


            if (endWishFile.equals("html")){
                rez.append("Content-Type: text/html\r\n");
            }
            if (endWishFile.equals("jpg") || endWishFile.equals("jpeg")){
                rez.append("Content-Type: images/jpeg\r\n");
            }

            if (endWishFile.equals("png")){
                rez.append("Content-Type: images/png\r\n");
            }

            if (endWishFile.equals("js")){
                rez.append("Content-Type: text/javascript\r\n");
            }
            if (endWishFile.equals("css")){
                rez.append("Content-Type: text/css\r\n");

            }




            BufferedReader br = new BufferedReader(new InputStreamReader(fr));

            String line ;
            while ((line = br.readLine()) != null){
                rezContent.append(line).append('\n');
                if (".".equals(line)){
                    break;
                }
            }

            rez.append("Content-Length:" + rezContent.length() + "\r\n");
            rez.append("\r\n");
            rez.append(rezContent.toString());





        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return String.valueOf(rez);
    }

    public void setAbsoluteLocationFile(String absoluteLocationFile) {
        AbsoluteLocationFile = absoluteLocationFile;
    }

    public String getAbsoluteLocationFile() {
        return AbsoluteLocationFile;
    }
}
