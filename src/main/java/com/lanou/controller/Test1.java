package com.lanou.controller;

import java.util.LinkedList;

/**
 * Created by lanou on 2017/12/11.
 */
public class Test1 {

    public static void main(String args[]){
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(3);
        list.add(1);
        list.add(2);
        String list1 = list.toString();
        String l1 = list1.substring(1,list1.length()-1);
        System.out.println(l1);
        String[] list2 = l1.split(",");
        System.out.println(list2);
        System.out.println(list2[0]);
        System.out.println(list2[1]);
        System.out.println(list2[2]);
        System.out.println(list2.length);
    }
}
