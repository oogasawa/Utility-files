package com.github.oogasawa.utility.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Predicate;


public class Traverse {

    public static void main(String[] args) {
        if (args.length <= 1) {
            System.err.println("java -cp Util-core-1.0.0.jar net.trelliscode.util.io.Traverse your_start_dir");
        }

        // System.out.println(Arrays.toString(args));
        String startDir = args[0];
        var tr = new Traverse();
        ArrayList<String> list;

        try {
            list = tr.postOrderArray(new File(startDir));
            for (String s: list) {
                System.out.println(s);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public ArrayList<String> postOrderArray(File dir) throws IOException {
        return postOrderArray(dir, (File f)->{ return true;});
    }


    public ArrayList<String> postOrderArray(File dir, Predicate<File> filter) throws IOException
    {

        var result = new ArrayList<String>();
        if (!dir.exists() || !dir.isDirectory()) {
            return result;
        }

        File[] fs = dir.listFiles();
        for (File f: fs) {


            if (f.isDirectory()) {
                var t = this.postOrderArray(f, filter);
                t.addAll(t.size(), result);
                result = t;
            }

            if (filter.test(f)) {
                result.add(f.getCanonicalPath());
            }

        }

        return result;
    }


    public ArrayList<String> postOrderArray(File dir, Predicate<File> filter, int count, int limit)
        throws IOException
    {

        var result = new ArrayList<String>();
        if (!dir.exists() || !dir.isDirectory()) {
            return result;
        }

        File[] fs = dir.listFiles();
        for (File f : fs) {

            ++count;
            if (count > limit) {
                break;
            }


            if (f.isDirectory()) {
                var t = this.postOrderArray(f, filter, count, limit);
                count += t.size();
                t.addAll(t.size(), result);
                result = t;
            }

            if (filter.test(f)) {
                result.add(f.getCanonicalPath());
            }

        }

        return result;
    }




}
