package com.duanyi.array;

import java.util.HashSet;
import java.util.Set;

public class MyArray {

    private int size;  //数组大小
    private int data[];
    private int index;  //当前数组下标

    public MyArray(int size) {
        this.size = size;
        data = new int[size];
        index = 0;
    }

    public void print(){
        for (int i = 0; i < index; i++) {
            System.out.println(data[i]);
        }
    }

    public void  insert(int n, int loc) {
        if(loc++ < size){
            for(int i = size -1 ; loc < i; i--){
                data[i] = data[i-1];
            }
            data[loc] = n;
        }
    }

    public void delete(int loc){
        for(int i =loc; i < size; i++){
            if(loc != size-1){
                data[i] = data[i+1];
            }else{
                data[i]=0;
            }
        }
        index--;
    }
}
