package org.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;
    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        //입력 구현
        System.out.print("\n=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();
        System.out.println();

        return new Word(0,level,word,meaning);
    }

    public void addWord() {
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
        System.out.print("\n => 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print(" => 수정할 번호 선택 ");
        int number = s.nextInt();
        s.nextLine();
        System.out.print(" \n=> 뜻 입력 ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(number-1));
        word.setMeaning(meaning);
        System.out.print("단어가 수정되었습니다.");
    }
    public void deleteItem() {
        System.out.print("\n => 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print(" => 삭제할 번호 선택 ");
        int number = s.nextInt();
        s.nextLine();
        System.out.print("정말로 삭제하실래요?(y/n");
        String yn = s.next();
        if(yn.equalsIgnoreCase("y")){
            list.remove(idlist.get(number-1));
            System.out.println("단어가 삭제되었습니다.");
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


}
