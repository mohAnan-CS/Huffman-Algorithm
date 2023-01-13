package huffman.encode;

import huffman.FileReader;
import huffman.tree.Frequency;
import huffman.tree.HuffmanTree;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class HuffmanEncode {

    public static String extensionFile ;
    public static long originalFileSize ;
    public static int extraBits ;
    private static Map<Character, String> huffmanTable;


    public static void encode(String inputFile, String outputFile) throws IOException {

        String text = FileReader.readFile(inputFile);
        System.out.println(text);
        Map<Character, Integer> characterHashMap = Frequency.calculateFrequency(text);
        huffmanTable = HuffmanTree.createHuffmanTree(characterHashMap);

        for (Map.Entry<Character, String> entry : huffmanTable.entrySet()) {
            System.out.println(entry);
        }

//        System.out.println();
//        System.out.println("???????????????????????");
//        System.out.println("File Size : " + getFileSize("C:\\Users\\twitter\\IdeaProjects\\HuffmanAlgrothim\\test.txt"));
//        System.out.println("Extinsion File " + setExtensionFile("C:\\Users\\twitter\\IdeaProjects\\HuffmanAlgrothim\\test.txt"));

        // Open the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Open the input file and encode the characters
            try (BufferedReader reader = new BufferedReader(new java.io.FileReader(inputFile))) {
                int ch;
                String encodedData = "";
                while ((ch = reader.read()) != -1) {
                    char c = (char) ch;
                    encodedData += huffmanTable.get(c);
                    System.out.println(c);
                    System.out.println("--------------------");
                }

                //writer.write(code);
                //writeEncodedFile2("output.txt",encodedData);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void encode2(String inputFile, String outputFile) throws IOException {

        String text = FileReader.readFile(inputFile);
        Map<Character, Integer> characterHashMap = Frequency.calculateFrequency(text);
        huffmanTable = HuffmanTree.createHuffmanTree(characterHashMap);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileChannel channel = inputStream.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            String code = "";

            while (channel.read(buffer) != -1) {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++) {
                    char currentChar = (char) buffer.get(i);
                    if(huffmanTable.containsKey(currentChar)){
                        System.out.println(currentChar);
                        System.out.println(huffmanTable.get(currentChar));
                        code+=huffmanTable.get(currentChar);
                        System.out.println(" i = " + i);
                    }else{
                        // handle characters not present in the Huffman table
                        System.out.println("else");
                    }
                    System.out.println("---------------------------");
                }
                buffer.clear();
                System.out.println("385 % 8 = " + 385%8 );
                System.out.println("code length = " + code.length());
                System.out.println(code);
                System.out.println("------------------");
                addExtraBitZero(code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void addExtraBitZero(String code){

        System.out.println("Code in function " + code);

        if (code.length() % 8 != 0) {
            int zeroBits = 8 - code.length() % 8;
            extraBits = zeroBits;
            for (int i = 0; i < zeroBits; i++) {
                code += "0";
            }
        }
        System.out.println( "Code after add extra bits : " + code);

    }







    public static void writeEncodedFile(String encodedFileName, String encodedData) throws IOException {
        FileWriter writer = new FileWriter("example.txt");
        // Write Huffman encoding table
        writer.write(huffmanTable.size()+"\n");
        //dos.writeInt(huffmanTable.size());
        for (Map.Entry<Character, String> entry : huffmanTable.entrySet()) {
            //dos.writeChar(entry.getKey());
            //dos.writeUTF(entry.getValue());
            writer.write(entry.getKey());
            writer.write(entry.getValue());
        }
        writer.write("\n");
        // Write the original file name
        //dos.writeUTF(extensionFile);
        writer.write(extensionFile);
        // Write the original file size
        //dos.writeLong(originalFileSize);
        writer.write("\n");
        writer.write(String.valueOf(originalFileSize));
        // Write the number of extra bits
        //dos.writeInt(extraBits);
        writer.write("\n");
        writer.write(String.valueOf(extraBits));
        // Write the encoded data
        //dos.writeUTF(encodedData);
        writer.write("\n");
        writer.write(encodedData);


        writer.close();
        System.out.println("File size after compress = " + getFileSize("example.txt"));
    }

//    public static void writeEncodedFile2(String encodedFileName, String encodedData) throws IOException {
//
//        System.out.println(encodedData);
//
//        // Create a new output stream for the encoded file
//        try (FileOutputStream out = new FileOutputStream(encodedFileName)) {
//            // Write the Huffman encoding table to the output stream
//            out.write(huffmanTable.size());
//            for (Map.Entry<Character, String> entry : huffmanTable.entrySet()) {
//                out.write(entry.getKey());
//                out.write(entry.getValue().getBytes());
//            }
//
//            // Write the encoded data to the output stream in binary format
//            StringBuilder sb = new StringBuilder();
//            for (char c : encodedData.toCharArray()) {
//                sb.append(c);
//                if (sb.length() == 8) {
//                    byte b = (byte) Integer.parseInt(sb.toString(), 2);
//                    out.write(b);
//                    sb = new StringBuilder();
//                }
//            }
//
//            // If there are remaining bits in sb
//            if (sb.length() > 0) {
//                byte[] lastByte = new byte[(8 - sb.length())/8];
//                int i = 0;
//                for (; i < lastByte.length; i++) {
//                    lastByte[i] = 0;
//                }
//                for (char c : sb.toString().toCharArray()) {
//                    lastByte[i / 8] |= (c - '0') << (7 - i % 8);
//                    i++;
//                }
//                out.write(lastByte);
//            }
//        }
//        System.out.println("File size after compress = " + getFileSize(encodedFileName));
//    }


    public static byte[] store8BitInByte(Map<Character, String> huffmanTable) {

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, String> entry : huffmanTable.entrySet()) {
            sb.append(entry.getValue());
        }
        String allCodes = sb.toString();
        int numPaddingBits = 8 - allCodes.length() % 8;
        if (numPaddingBits < 8) {
            for (int i = 0; i < numPaddingBits; i++) {
                allCodes += "0";
            }
        }

        byte[] encodedTable = new byte[allCodes.length() / 8];
        for (int i = 0; i < encodedTable.length; i++) {
            int start = i * 8;
            int end = start + 8;
            encodedTable[i] = (byte) Integer.parseInt(allCodes.substring(start, end), 2);
        }
        return encodedTable;
    }

    public static long getFileSize(String path){

        File file = new File(path);
        originalFileSize = file.length();
        return file.length();

    }

    public static String setExtensionFile(String path){

        String[] split = path.split("\\\\");
        extensionFile = split[split.length - 1 ];
        return split[split.length - 1 ];
    }


}
