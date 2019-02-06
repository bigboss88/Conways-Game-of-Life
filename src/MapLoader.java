import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class MapLoader{
    private String filename;
    private ArrayList<Integer> cells;
    public MapLoader(String inFile){
        filename = inFile; //name of file to read from
        cells = new ArrayList<Integer>(); // List of cells to add to map
    }

    public Map read(){
        try {
            FileReader fileReader = new FileReader(this.filename);
            BufferedReader buf = new BufferedReader(fileReader); //set up our readers
            Scanner scan;
            String line;
            while((line = buf.readLine())!= null){ //while not at end of file
                //System.out.print(line+"\n");
                scan =  new Scanner(line);
                scan.useDelimiter(","); 
                int x = Integer.parseInt(scan.next());
                //System.out.println(x);
                int y = Integer.parseInt(scan.next()); //Get x and y
                //System.out.println(y);
                cells.add(x);
                cells.add(y); // Add x and y to cell
                //System.out.println("Added locations");
            }
            System.out.println("Finished file io");
            buf.close(); //close reader
            Map out = new Map(cells.get(0),cells.get(1));
            for(int i =2;i<cells.size();i+=2){ // go through each pair and add that cell to the map
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