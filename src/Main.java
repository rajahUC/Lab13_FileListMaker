import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main
{
    static ArrayList<String> list = new ArrayList<>();
    static Scanner console = new Scanner(System.in);
    static boolean done = false;
    static boolean needsToBeSaved = false;

    static String fileName = "";

    public static void main(String[] args)
    {
        final String menu = "A - Add  D - Delete  V - View  Q - Quit  O - Open  S - Save  C - Clear";
        String cmd = "";

        do {
            displayList();
            cmd = SafeInput.getRegExString(console, menu, "[AaDdVvQqOoSsCc]");
            cmd = cmd.toUpperCase();

            switch(cmd)
            {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "V":
                    displayList();
                    break;
                case "Q":
                    quitProgram();
                    break;
                case "O":
                    openFile();
                    break;
                case "S":
                    saveFile();
                    break;
                case "C":
                    clearFile();
                    break;
            }
        } while (!done);
    }

    private static void displayList()
    {
        String text = "List";

        int textLength = text.length();
        int padding = (50 - textLength) / 2;

        StringBuilder header = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            header.append('-');
        }

        header.append(" ").append(text).append(" ");

        int remainingWidth = 50 - header.length();
        for (int i = 0; i < remainingWidth; i++) {
            header.append('-');
        }

        System.out.println(header.toString());

        if (list.size() != 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                System.out.printf("\n%d. %s", i + 1, list.get(i));
            }
        }
        else
        {
            System.out.println("\nLIST IS EMPTY");
        }
    }

    private static void addItem()
    {
        String itemAdd = SafeInput.getNonZeroLenString(console, "Enter a list item to add: ");
        list.add(itemAdd);

        needsToBeSaved = true;
    }

    private static void deleteItem()
    {
        int itemDelete = SafeInput.getRangedInt(console, "What item would you like to delete?", 1, list.size());
        itemDelete--;
        list.remove(itemDelete);

        needsToBeSaved = true;
    }

    private static void quitProgram()
    {
        boolean confirmExit = SafeInput.getYNConfirm(console, "Are you sure?");
        if (confirmExit) {
            if (needsToBeSaved)
            {
                boolean confirmSave = SafeInput.getYNConfirm(console, "Would you like to save your list?");
                if (confirmSave)
                {
                    saveFile();
                }
            }
            done = true;
        }
    }

    private static void openFile()
    {
        if (needsToBeSaved)
        {
            boolean confirmSave = SafeInput.getYNConfirm(console, "Would you like to save your list?");
            if (confirmSave)
            {
                saveFile();
            }
        }

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                fileName = selectedFile.getName();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));


                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    list.add(rec);
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");


            } else  // User closed the chooser without selecting a file
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private static void saveFile()
    {
        File workingDirectory = new File(System.getProperty("user.dir"));
        File srcDirectory = new File(workingDirectory, "src");
        Path file;

        if (!fileName.isEmpty())
        {
            file = Paths.get(srcDirectory.getPath(), fileName);
        }
        else
        {
            String newFileName = SafeInput.getNonZeroLenString(console, "Provide a basename for the file: ");
            file = Paths.get(srcDirectory.getPath(), newFileName);
        }

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(String l : list)
            {
                writer.write(l, 0, l.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        needsToBeSaved = false;
        clearFile();
    }

    private static void clearFile()
    {
        if (needsToBeSaved)
        {
            boolean confirmSave = SafeInput.getYNConfirm(console, "Would you like to save your list?");
            if (confirmSave)
            {
                saveFile();
            }
        }

        list.clear();
        fileName = "";
    }
}