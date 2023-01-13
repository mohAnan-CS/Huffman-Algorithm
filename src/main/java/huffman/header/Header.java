package huffman.header;

import huffman.tree.HuffmanTree;
import huffman.tree.Node;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Header {

    public static int extraBits;
    public static String fileExtension;
    public static long originalFileSize;

    public static byte[] createHeader() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        storeTree(HuffmanTree.root, byteArrayOutputStream);

        byte[] treeData = byteArrayOutputStream.toByteArray();
        byte[] extraBitsData = ByteBuffer.allocate(4).putInt(extraBits).array();
        byte[] originalFileSizeData = ByteBuffer.allocate(8).putLong(originalFileSize).array();
        byte[] fileExtensionData = fileExtension.getBytes(StandardCharsets.UTF_8);

        int headerSize = 4 + treeData.length + originalFileSizeData.length + fileExtensionData.length;
        ByteBuffer buffer = ByteBuffer.allocate(headerSize);
        buffer.put(extraBitsData);
        buffer.put(treeData);
        buffer.put(originalFileSizeData);
        buffer.put(fileExtensionData);

        return buffer.array();
    }

    private static void storeTree(Node node, ByteArrayOutputStream byteArrayOutputStream) {
        if (node.isLeaf()) {
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(node.getFrequency());
        } else {
            byteArrayOutputStream.write(1);
            storeTree(node.getLeft(), byteArrayOutputStream);
            storeTree(node.getRight(), byteArrayOutputStream);
        }
    }
}
