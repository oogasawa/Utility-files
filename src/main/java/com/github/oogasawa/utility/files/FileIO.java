package com.github.oogasawa.utility.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

// import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

public class FileIO {


    /**  A {@code PrintWriter} to the standard output that is more efficient than {@code System.out}. */
    public static PrintWriter stdout()  {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "UTF-8"), 512));
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return out;
    }




    public static String readFile(BufferedReader br) throws IOException {
        StringBuffer buf = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            buf.append(line + "\n");
        }

        return buf.toString();
    }

    public static String readFile(String pathStr) throws IOException {
        StringBuffer buf = new StringBuffer();
        BufferedReader br = getBufferedReader(pathStr);
        String line = null;
        while ((line = br.readLine()) != null) {
            buf.append(line + "\n");
        }
        br.close();

        return buf.toString();
    }

    public static void writeFile(String fname, String str) throws IOException {
        PrintWriter pw = getPrintWriter(fname);
        pw.write(str);
        pw.close();
    }

    public static void writeFile(File f, String str) throws IOException {
        PrintWriter pw = getPrintWriter(f);
        pw.write(str);
        pw.close();
    }

    public static String readFile(String fname, String encoding) throws IOException {
        StringBuffer buf = new StringBuffer();
        BufferedReader br = getBufferedReader(fname, encoding);
        String line = null;
        while ((line = br.readLine()) != null) {
            buf.append(line + "\n");
        }
        br.close();
        return buf.toString();
    }

    public static String readFile(File fObj, String encoding) throws IOException {
        StringBuffer buf = new StringBuffer();
        BufferedReader br = getBufferedReader(fObj, encoding);
        String line = null;
        while ((line = br.readLine()) != null) {
            buf.append(line + "\n");
        }
        br.close();
        return buf.toString();
    }

    public static String readFileWithGuessingEncoding(String fname) {

        String result = null;

        String[] encoding = {
            "ASCII", "Cp1250", "Cp1251", "Cp1253", "Cp1254", "Cp1257",
            "ISO8859_1", "ISO8859_2", "ISO8859_4", "ISO8859_5", "ISO8859_7",
            "ISO8859_9", "ISO8859_13", "ISO8859_15",
            "UTF8", "UTF-16",
            "ISO8859_3", "ISO8859_6", "ISO8859_8"
        };

        for (String enc : encoding) {
            try {
                result = readFile(enc);
                break;
            } catch (Exception e) {
                // nothing to do.
            }
        }

        return result;
    }

    public static BufferedReader getBufferedReader(InputStream st) {
        BufferedReader fr = new BufferedReader(new InputStreamReader(st));
        return fr;
    }

    public static BufferedReader getBufferedReader(String filename) throws FileNotFoundException {
        BufferedReader fr = null;
        fr = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        return fr;
    }

    public static BufferedReader getBufferedReaderGz(String gzFilename) throws FileNotFoundException, IOException {
        BufferedReader fr = null;
        fr = new BufferedReader(
                new InputStreamReader(
                        new GZIPInputStream(
                                new FileInputStream(gzFilename))));

        return fr;
    }

    // public static BufferedReader getBufferedReaderBZip2(String bz2Filename) throws FileNotFoundException, IOException {
    //     BufferedReader fr = null;
    //     fr = new BufferedReader(
    //             new InputStreamReader(
    //                     new BZip2CompressorInputStream(
    //                             new FileInputStream(bz2Filename))));

    //     return fr;
    // }


    public static BufferedReader getBufferedReader(String filename, String charset) throws UnsupportedEncodingException, FileNotFoundException {
        BufferedReader fr = null;

        Reader r = null;
        r = new InputStreamReader(new FileInputStream(filename), charset);
        fr = new BufferedReader(r);

        return fr;
    }

    public static BufferedReader getBufferedReader(File fObj, String charset) throws UnsupportedEncodingException, FileNotFoundException {
        BufferedReader fr = null;

        Reader r = null;
        r = new InputStreamReader(new FileInputStream(fObj), charset);
        fr = new BufferedReader(r);

        return fr;
    }

    public static BufferedReader getBufferedReader(File fObj) throws FileNotFoundException {
        BufferedReader fr = null;

        fr = new BufferedReader(new InputStreamReader(new FileInputStream(fObj)));

        return fr;
    }

    public static BufferedReader getBufferedReader(URL url) throws IOException {
        BufferedReader fr = null;

        fr = new BufferedReader(new InputStreamReader(url.openStream()));

        return fr;
    }

    public static PrintWriter getPrintWriter(String filename) throws IOException {
        PrintWriter fw = null;
        fw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        return fw;
    }

    public static PrintWriter getPrintWriter(File f) throws IOException {
        PrintWriter fw = null;
        fw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
        return fw;
    }

    public static PrintWriter getPrintWriter(String filename, String charset) throws UnsupportedEncodingException, FileNotFoundException {
        PrintWriter fw = null;

        fw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), charset));

        return fw;
    }

}
