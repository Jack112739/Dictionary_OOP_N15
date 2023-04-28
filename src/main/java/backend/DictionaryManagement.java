package main.java.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class DictionaryManagement {
    static private Dictionary dictor;

    public DictionaryManagement() {
    }

    public DictionaryManagement(Dictionary used_dictionary) {
        dictor = used_dictionary;
    }

    public Dictionary getDictionary() {
        return dictor;
    }
    public void insertFromCommandline() {
        Scanner cin = new Scanner(System.in, String.valueOf(StandardCharsets.UTF_8));
        int n = cin.nextInt();
        cin.nextLine();
        for (int i = 0; i < n; i++) {
            String target, explain;
            target = cin.nextLine();
            explain = cin.nextLine();
            Word needToAdd = new Word(target, explain);
            dictor.add(needToAdd);
        }
        cin.close();
    }

    public void insertFromFile() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("data/Dictionaries.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
                StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] words;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                words = line.split("\t");
                if (words.length >= 2) {
                    Word newWordFromDictionaries = new Word(words[0], words[1]);
                    dictor.add(newWordFromDictionaries);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String searchWord(String str) {
        return dictor.explain(str);
    }

    public boolean checkWord(String str) {
        return dictor.inside(str);
    }

    public void dictionaryLookup() {
        Scanner cin = new Scanner(System.in, String.valueOf(StandardCharsets.UTF_8));
        while (true) {
            String need_to_translate = cin.nextLine();
            need_to_translate = need_to_translate.trim().toLowerCase();
            if (need_to_translate.charAt(0) < 'a' || need_to_translate.charAt(0) > 'z') break;
            System.out.println(dictor.explain(need_to_translate));
        }
        cin.close();
    }

    public void dictionaryExportToFile(String FileName) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(FileName);
        PrintWriter cout = new PrintWriter(outputStream);
        List<Word> export = dictor.get_word_with_prefix(null);
        for (int i = 0; i < export.size(); i++) {
            cout.println(export.get(i).getWord_target() + "\t" + export.get(i).getWord_explain());
        }
        cout.close();
    }

    public static void RenewDictionary(File direction) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(direction);
        PrintWriter cout = new PrintWriter(outputStream);
        List<Word> export = dictor.get_word_with_prefix(null);
        for(int i = 0; i < export.size(); i++) {
            cout.println("@" + export.get(i).getWord_target() + "\n" + export.get(i).getWord_explain() + "\n");
        }
        cout.close();
    }
    public void manage() {
        Scanner cin = new Scanner(System.in, String.valueOf(StandardCharsets.UTF_8));
        while (true) {
            String input_query = cin.nextLine();
            String[] query_type = input_query.split(":");
            if (query_type[0].equals("add")) {
                String[] query = query_type[1].split("\t");
                Word newWord = new Word(query[0], query[1]);
                dictor.add(newWord);
            } else if (query_type[0].equals("fix")) {
                String[] query = query_type[1].split("\t");
                Word need_to_fix = new Word(query[0], query[1]);
                dictor.fix(need_to_fix, query[1]);
            } else if (query_type[0].equals("erase")) {
                String query = query_type[1];
                dictor.erase(query);
            } else break;
        }
        cin.close();
    }

    public List<String> dictionarySearcher(String search_target) {
        List<Word> prefix = dictor.get_word_with_prefix(search_target);
        List<String> word_with_prefix = new ArrayList<>();
        for (int i = 0; i < prefix.size(); i++)
            word_with_prefix.add(prefix.get(i).getWord_target());
        return word_with_prefix;
    }

    public void InsertOfflineData() throws IOException {
        dictor = new Dictionary();
        FileInputStream fileInputStream = new FileInputStream("data/OfflineDictionary.txt");
        Scanner cin = new Scanner(fileInputStream, String.valueOf(StandardCharsets.UTF_8));
        String vietnamese = "", english = "";
        while (cin.hasNextLine()) {
            String input = cin.nextLine();
            if (input.length() > 0 && input.charAt(0) == '@') {
                if (!vietnamese.equals("")) {
                    Word newWord = new Word(english, vietnamese);
                    dictor.add(newWord);
                }
                String[] seperate = input.split("/");
                if (seperate.length > 1) vietnamese = "phát âm: " + seperate[1];
                else vietnamese = "";
                seperate = seperate[0].split("@");
                if (seperate.length > 0) english = seperate[1].trim().toLowerCase();

            } else if(!english.equals("")) {
                vietnamese = vietnamese + "\n" + input;
            }
        }
    }

    public static int Dictionary_size() {
        return dictor.size();
    }

    public static void main(String[] args) throws IOException {
        DictionaryManagement test = new DictionaryManagement();
        test.insertFromFile();
        test.dictionaryLookup();

    }
}
