import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class MapLoader{
    private String filename;
    private ArrayList<Integer> cells;
    public MapLoader(String inFile){
        filename = inFile;
        cells = new ArrayList<Integer>();
    }

    public Map read(){
        try {
            FileReader fileReader = new FileReader(this.filename);
            BufferedReader buf = new BufferedReader(fileReader);
            Scanner scan;
            String line;
            while((line = buf.readLine())!= null){
                //System.out.print(line+"\n");
                scan =  new Scanner(line);
                scan.useDelimiter(",");
                int x = Integer.parseInt(scan.next());
                //System.out.println(x);
                int y = Integer.parseInt(scan.next());
                //System.out.println(y);
                cells.add(x);
                cells.add(y);
                //System.out.println("Added locations");
            }
            System.out.println("Finished file io");
            buf.close();
            Map out = new Map(cells.get(0),cells.get(1));
            for(int i =2;i<cells.size();i+=2){
                System.out.println(i+ " "+ cells.size());
                out.getCell(cells.get(i), cells.get(i+1)).resurect();
            }
            System.out.println(out.toString());
            return out;
        } 

        catch(Exception e){
            System.out.println("Error when loading in File: " +e);
            return null;
        }
    }
}