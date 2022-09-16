package org.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";
    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        //입력 구현
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();
        System.out.println();

        return new Word(0,level,word,meaning);
    }

    public void addItem() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다 !!!\n");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }


    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }
    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 ");
        int number = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻 입력 ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(number-1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다!!!");
    }
    public void deleteItem() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택 ");
        int number = s.nextInt();
        s.nextLine();
        System.out.print("정말로 삭제하실래요?(y/n) ");
        String yn = s.next();
        if(yn.equalsIgnoreCase("y")){
            list.remove((int)idlist.get(number-1));
            System.out.println("단어가 삭제되었습니다!!!");
        }
        else
            System.out.println("취소되었습니다.");

    }
    public void listAll(){
        System.out.println("\n--------------------------------");
        for(int i = 0; i<list.size(); i++){
            System.out.print((i+1)+" ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------\n");
    }
    public ArrayList<Integer> listAll(String keyword){
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;
        System.out.println("\n--------------------------------");
        for(int i = 0; i<list.size(); i++){
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1)+" ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("--------------------------------\n");
        return idlist;
    }
    public void listAll(int level){
        int j = 0;
        System.out.println("\n--------------------------------");
        for(int i = 0; i<list.size(); i++){
            int ilevel = list.get(i).getLevel();
            if(level != ilevel) continue;
            System.out.print((j+1)+" ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("--------------------------------\n");
    }
    public void loadFile(){
        int count;
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            count = 0;
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===>" + count + "개 로딩 완료!!!");

    }

    public void saveFile() {
        try (PrintWriter pr = new PrintWriter(new FileWriter(fname))) {
            for(Word one : list){
                pr.write(one.toFileString()+"\n");
            }
            pr.close();
            System.out.println("===> 데이터 저장 완료!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchLevel() {
        System.out.print("=> 원하는 레벨은? (1~3) ");
        int level = s.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.print("=> 원하는 단어는? ");
        String keyword = s.next();
        listAll(keyword);
    }
}
