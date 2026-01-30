import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator
{
    public static void main(String[] args)
    {
        ArrayList<String> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean done = false;

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        String personRec = "";
        String ID = "";
        String FirstName = "";
        String LastName = "";
        String Title = "";
        int YOB = 0;

        do{

            ID = SafeInput.getNonZeroLenString(in, "Enter ID [6 digits] ");
            FirstName = SafeInput.getNonZeroLenString(in, "Enter First Name: ");
            LastName = SafeInput.getNonZeroLenString(in, "Enter Last Name: ");
            Title = SafeInput.getNonZeroLenString(in, "Enter Title: ");
            YOB = SafeInput.getRangedInt(in, "Enter Year of Birth [1900-2023]: ", 1000, 9999);

            personRec = ID + ", " + FirstName + ", " + LastName + ", " + Title + ", " + YOB;
            folks.add(personRec);

            done = SafeInput.getYNConfirm(in, "Are you done? ");



        }while(!done);

        for(String p: folks)
            System.out.println(p);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : folks)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
