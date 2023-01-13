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
                System.out.println("code length = " + code.length());
                System.out.println("Code before add extra bit : " + code);
                System.out.println("------------------");
                String codeAfterExtraBit = addExtraBitZero(code);
                byte[] byteArray = storeEach8bitInByte(codeAfterExtraBit);
                System.out.println("Byte array length "+ byteArray.length);
                writeCodeInEncodedFile(byteArray, "C:\\Users\\twitter\\IdeaProjects\\HuffmanAlgrothim\\output.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String addExtraBitZero(String code){

        System.out.println("Code in function " + code);

        if (code.length() % 8 != 0) {
            int zeroBits = 8 - code.length() % 8;
            extraBits = zeroBits;
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

    public static void writeCodeInEncodedFile(byte[] byteCodeArray, String encodedFilePath) throws IOException {

        String newFilePath = encodedFilePath.substring(0, encodedFilePath.lastIndexOf(".")) + ".huff";
        File file = new File(newFilePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(byteCodeArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
