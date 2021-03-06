package jpsam3hklam9.des;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;


public class DESInterface {
    //oque eu devo implementar é:


    private static String cifra3DES(long[] blocks, long key, long key2, long key3, int a) {

        long key1 = getKey("12345678");

        String acm_str = new String("");
        DES des = new DES();
        long[] cipherTexts = new long[blocks.length], plainTexts = new long[blocks.length];

        for (int i = 0; i < blocks.length; i++) {
            cipherTexts[i] = des.encrypt(blocks[i], key1);
            acm_str+=cipherTexts[i]+",";
        }

        return acm_str;
    }
    private static String decifra3DES(long[] original, long key, long key2, long key3, int a) {
        ArrayList<String> mensagem = new ArrayList<String>();
        long key1 = getKey("12345678");

        String acm_str = new String("");
        DES des = new DES();
        byte[] bytes;
        long[] plainTexts = new long[original.length];


        System.out.println("\nDecrypted plaintext: ");
        for (int i = 0; i < original.length; i++) {
            plainTexts[i] = des.decrypt(original[i], key1);

        }

        for (long block : plainTexts) {
            bytes = ByteBuffer.allocate(8).putLong(block).array();
            String pedaco = new String(bytes);
            acm_str +=pedaco;
        }
        //acm_str=acm_str.replaceAll("\\W+|\\S+","");
        acm_str=acm_str.replaceAll(" ","XW");


        acm_str=acm_str.replaceAll("[^a-zA-Z0-9]","");

        acm_str=acm_str.replaceAll("XW+"," ");

        acm_str=acm_str.replaceAll("[J]+","");

        return acm_str;

    }


    public static String cifrarDES(String s) {

        byte[] text = s.getBytes();
        long key = getKey("12345678");
        long[] blocks = splitInputIntoBlocks(text);


        return cifraDES(blocks, key, 0);// RETORNA a string cifrada;
    }
    public static String decifrarDES(String s) {

        byte[] text = s.getBytes();
        long[] blocks = splitInputIntoBlocks(text);
        long key = getKey("12345678");

        String[] arrayDeStrings = s.split(",");

        long longConvertido;
        long[] arrayDeLongs = new long[blocks.length];

        for(int i = 0; i<arrayDeStrings.length; i++){
            longConvertido = Long.parseLong(arrayDeStrings[i]);

            arrayDeLongs[i]=longConvertido;
        }

        return decifraDES(arrayDeLongs, key,0);

    }

    public static String cifrar3DES(String s,String chave) {
        String[] arrayDeStrings = chave.split(",");


        byte[] text = s.getBytes();
        long key1 = getKey(arrayDeStrings [0]);
        long key2 = getKey(arrayDeStrings [1]);
        long key3 = getKey(arrayDeStrings [2]);

        long[] blocks = splitInputIntoBlocks(text);

        return cifra3DES(blocks, key1,key2,key3,1);// RETORNA a string cifrada;
    }


    public static String decifrar3DES(String s,String chave) {
        //chave padrão
        String[] arrayDeStrings1 = "jf92j2ei,ad892dus,sidhd823".split(",");

        arrayDeStrings1 = chave.split(",");


        byte[] text = s.getBytes();
        long key1 = getKey(arrayDeStrings1 [0]);
        long key2 = getKey(arrayDeStrings1 [1]);
        long key3 = getKey(arrayDeStrings1 [2]);


        long[] blocks = splitInputIntoBlocks(text);

        String[] arrayDeStrings = s.split(",");

        long longConvertido;
        long[] arrayDeLongs = new long[blocks.length];

        for(int i = 0; i<arrayDeStrings.length; i++){
            longConvertido = Long.parseLong(arrayDeStrings[i]);

            arrayDeLongs[i]=longConvertido;
        }

        return decifra3DES(arrayDeLongs, key1,key2, key3,1);// RETORNA a string decifrada;



    }

    private static String cifraDES(long[] blocks, long key, int mode) {
        String acm_str = new String("");
        DES des = new DES();
        long[] cipherTexts = new long[blocks.length], plainTexts = new long[blocks.length];

            for (int i = 0; i < blocks.length; i++) {
                cipherTexts[i] = des.encrypt(blocks[i], key);
                acm_str+=cipherTexts[i]+",";
            }

        return acm_str;
    }

    private static String decifraDES(long[] cipherTexts, long key,int mode ) {
        ArrayList<String> mensagem = new ArrayList<String>();

        String acm_str = new String("");
        DES des = new DES();
        byte[] bytes;
        long[] plainTexts = new long[cipherTexts.length];


        System.out.println("\nDecrypted plaintext: ");
        for (int i = 0; i < cipherTexts.length; i++) {
            plainTexts[i] = des.decrypt(cipherTexts[i], key);

        }

        for (long block : plainTexts) {
            bytes = ByteBuffer.allocate(8).putLong(block).array();
            String pedaco = new String(bytes);
            acm_str +=pedaco;
        }

        return acm_str;
    }


    private static String cifra3DES(long[] blocks, long key1, long key2, long key3,Integer a) {
        ArrayList<String> mensagem = new ArrayList<String>();
        String acm_str = new String("");

        DES des = new DES();
        long[] cipherTexts = new long[blocks.length], plainTexts = new long[blocks.length],cipherTexts2= new long[blocks.length];

        //cifra
        for (int i = 0; i < blocks.length; i++) {
            cipherTexts[i] = des.encrypt(blocks[i], key1);
        }

        //decifra
        for (int i = 0; i < cipherTexts.length; i++)
        {
            plainTexts[i] = des.decrypt(cipherTexts[i], key2);
        }

        //cifra
        for (int i = 0; i < blocks.length; i++) {
            acm_str+=cipherTexts[i]+",";
        }

        return acm_str;
    }



        private static String decifra3DES(long[] original, long key1, long key2, long key3, Integer a) {
        ArrayList<String> mensagem = new ArrayList<String>();

        String acm_str = new String("");
        DES des = new DES();
        byte[] bytes;
        long[] cipherTexts = new long[original.length];
        long[] plainTexts = new long[original.length];
        long[] blocks = new long[original.length];

        //decifrar

        for (int i = 0; i < original.length; i++) {
            blocks[i] = des.decrypt(original[i], key1);

        }

        //cifra


        for (int i = 0; i < blocks.length; i++)
        {
            cipherTexts[i] = des.encrypt(blocks[i], key2);
        }

        //decifrar

        for (int i = 0; i < cipherTexts.length; i++) {
            plainTexts[i] = des.decrypt(cipherTexts[i], key3);

        }

        for (long block : plainTexts) {
            bytes = ByteBuffer.allocate(8).putLong(block).array();
            String pedaco = new String(bytes);
            acm_str +=pedaco;
        }


        return acm_str;


    }



    private static long[] splitInputIntoBlocks(byte[] input)
    {
        long blocks[] = new long[input.length / 8 + 1];

        for (int i = 0, j = -1; i < input.length; i++)
        {
            if (i % 8 == 0)
                j++;
            blocks[j] <<= 8;
            blocks[j] |= input[i];
        }

        return blocks;
    }

    private static long getKey(String stri)
    {
        String keyStr = stri;
        byte[] keyBytes;
        long key64 = 0;

        if (keyStr.length() > 8)
        {
            System.out.println("Input is greater than 64 bits.");
            System.exit(0);
        }

        keyBytes = keyStr.getBytes();

        for (byte keyByte : keyBytes)
        {
            key64 <<= 8;
            key64 |= keyByte;
        }

        return key64;
    }
    private static long getKey(BufferedReader reader)
    {
        String keyStr = "";
        byte[] keyBytes;
        long key64 = 0;

        try
        {
            keyStr = reader.readLine();
        } catch (IOException e)
        {
         //   printErrorAndDie("");
        }

        if (keyStr.length() > 8)
        {
            System.out.println("Input is greater than 64 bits.");
            System.exit(0);
        }

        keyBytes = keyStr.getBytes();

        for (byte keyByte : keyBytes)
        {
            key64 <<= 8;
            key64 |= keyByte;
        }

        return key64;
    }




}


//        acm_str=acm_str.replaceAll("[^a-zA-Z0-9]+","");
//      acm_str=acm_str.replaceAll("[J]+","");
