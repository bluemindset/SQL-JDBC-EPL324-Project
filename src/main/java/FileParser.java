import java.io.*;


class FileParser {

     static String getFileContentAsString(String path) {
        StringBuilder fileString = new StringBuilder("");
        // The name of the file to open.
        String fileName = path;
        // This will reference one line at a time
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                fileString.append(line +"\n");
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.err.println("Unable to open file '" + fileName + "'");
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return fileString.toString();
    }

    static Reader getReaderForFile(String fileName) throws FileNotFoundException {
         return new BufferedReader(new FileReader(fileName));
    }
}