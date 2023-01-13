package huffman.encode;

import huffman.FileReader;
import huffman.header.Header;
import huffman.tree.Frequency;
import huffman.tree.HuffmanTree;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class HuffmanEncode {

    public static Map<Character, String> huffmanTable;

    public static void encode(String inputFile, String outputFile) throws IOException {

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
                System.out.println("code length = " + code.length());
                System.out.println("Code before add extra bit : " + code);
                System.out.println("------------------");
                String codeAfterExtraBit = addExtraBitZero(code);
                byte[] codeByteArray = storeEach8bitInByte(codeAfterExtraBit);
                System.out.println("Byte array length "+ codeByteArray.length);
                setFileSize(inputFile);
                setExtensionFile(inputFile);
                byte[] headerByteArray = Header.createHeader();
                writeCodeAndHeaderInEncodedFile(headerByteArray, codeByteArray, "C:\\Users\\twitter\\IdeaProjects\\HuffmanAlgrothim\\output.txt");
                System.out.println("File Size Before : " + Header.originalFileSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String addExtraBitZero(String code){

        System.out.println("Code in function " + code);

        if (code.length() % 8 != 0) {
            int zeroBits = 8 - code.length() % 8;
            Header.extraBits = zeroBits;
            for (int i = 0; i < zeroBits; i++)
                code += "0";
        }

        System.out.println( "Code after add extra bits : " + code);
        return code;

    }

    public static byte[] storeEach8bitInByte(String code) {

        int numOfBytes = code.length() / 8;
        byte[] bytes = new byte[numOfBytes];
        for (int i = 0; i < numOfBytes; i++) {
            bytes[i] = (byte) Integer.parseInt(code.substring(i * 8, (i + 1) * 8), 2);
        }
        return bytes;

    }

    public static void writeCodeAndHeaderInEncodedFile(byte[] headerByteArray, byte[] codeByteArray, String encodedFilePath) throws IOException {

        String newFilePath = encodedFilePath.substring(0, encodedFilePath.lastIndexOf(".")) + ".huff";
        File file = new File(newFilePath);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            fileOutputStream.write(headerByteArray);
            fileOutputStream.write(codeByteArray);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File Size After = " + newFilePath.length());

    }

    public static void setFileSize(String path){

        File file = new File(path);
        Header.originalFileSize = file.length();

    }

    public static void setExtensionFile(String path){

        File file = new File(path);
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        String fileExtension = (index == -1) ? "" : fileName.substring(index + 1);
        Header.fileExtension = fileExtension;

    }


}
