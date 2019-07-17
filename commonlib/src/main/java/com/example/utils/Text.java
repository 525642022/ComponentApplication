package com.example.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Text {


    public static void main(String[] args) {
        Integer[] text = {5, 7, 9, 8, 3, 1, 2};
//        insert(text);
        quick1(text,0,text.length-1);
//        List list = java.util.Arrays.asList(gb(text));
        List list = Arrays.asList(text);
        ArrayList list1 = new ArrayList(list);
        System.out.println(list1.toString());
//        System.out.println( getLen("abcaklss"));
    }
    public  static void quickTest(Integer []  text,int start,int end){
        if(start>=end){
            return;
        }
        int temp = quick(text,start,end);
        quickTest(text,0,temp);
        quickTest(text,temp+1,end);
    }

    private static int quick(Integer[] text, int start, int end) {
        int current = text[start];
        while(start<end){
            while(text[end]>=current&&start<end){
                end--;
            }
            text[start] = text[end];
            while(text[start]<=current&&start<end){
                start++;
            }
            text[end] = text[start];
        }
        text [end] = current;
        return end;
    }
    private static int getTemp(Integer[] text, int start, int end) {
        int current = text[start];
        while (start < end) {
            while (text[end] >= current && start < end) {
                end--;
            }
            text[start] = text[end];
            while (text[start] <= current && start < end) {
                start++;
            }
            text[end] = text[start];
        }
        text[end] = current;
        return end;
    }
    public static void  maopao (Integer []  text){
        int len   = text.length;
        if(len == 0) return;
        for(int  i = 0 ;i<len;i++){
            for(int j = 0; j<len - i - 1;j++){
                if(text[j+1]<text[j]){
                    swap(text,j,j+1);
                }
            }
        }
    }
    public static void insert(Integer[] text){
        int len = text.length;
        if(len == 0) return;
        for(int  i =0; i<len-1;i++){
            int pre = i;
            int current = text[pre+1];
            while(pre >=0&&text[pre]>current){
                text[pre+1] = text[pre];
                pre --;
            }
            text [pre +1] =current;
        }
    }
    public static Integer [] gb(Integer[] text){
        int len = text.length;
        if(len<2){
            return text;
        }
        int mid = len/2;
        Integer []  left = Arrays.copyOfRange(text,0,mid);
        Integer []  right = Arrays.copyOfRange(text,mid,len);
        return  mergeGb(gb(left),gb(right));
    }

    private static Integer[] mergeGb(Integer[] left, Integer[] right) {
        Integer []  result = new Integer[left.length+right.length];
        int i = 0,j=0;
        for(int index = 0;index<result.length;index++){
            if(i>=left.length){
                 result[index] = right[j++];
            }else if(j>=right.length){
                result [index] = left[i++];
            }else if(right[j]>left[i]){
                result [index] = left[i++];
            }else{
                result[index] = right[j++];
            }
        }
        return result;
    }

    public static void maoPao(Integer[] test) {
        int len = test.length;
        if (len == 0) return;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (test[j + 1] < test[j]) {
                    swap(test, j, j + 1);
                }
            }
        }
    }

    private static void swap(Integer[] test, int i, int j) {
        int temp = test[i];
        test[i] = test[j];
        test[j] = temp;
    }

    public static void insertSort(Integer[] text) {
        int len = text.length;
        if (len == 0) return;
        for (int i = 0; i < text.length - 1; i++) {
            int pre = i;
            int current = text[pre + 1];
            while (pre >= 0 && text[pre] > current) {
                text[pre + 1] = text[pre];
                pre--;
            }
            text[pre + 1] = current;
        }
    }

    public static Integer[] mergeSort(Integer[] text) {
        int len = text.length;
        if (len < 2) return text;
        int mid = len / 2;
        Integer[] left = Arrays.copyOfRange(text, 0, mid);
        Integer[] right = Arrays.copyOfRange(text, mid, len);
        return merge(mergeSort(left), mergeSort(right));
    }

    private static Integer[] merge(Integer[] right, Integer[] left) {
        Integer[] result = new Integer[right.length + left.length];
        int i = 0, j = 0;
        for (int index = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    public static void quickSort(Integer[] text, int start, int end) {
        if (start >= end) {
            return;
        }
        int temp = getTemp(text, start, end);
        quickSort(text,start,temp);
        quickSort(text,temp+1,end);
    }

    public static int partition(Integer []array,int lo,int hi) {
        //固定的切分方式
        int key = array[lo];
        while (lo < hi) {
            while (array[hi] >= key && hi > lo) {//从后半部分向前扫描
                hi--;
            }
            array[lo] = array[hi];
            while (array[lo] <= key && hi > lo) {
                lo++;
            }
            array[hi] = array[lo];
        }
        array[hi] = key;
        return hi;
    }
    public static void quick1(Integer[] text, int start, int end){
        if(start>= end){
               return;
        }
        int temp = getTemp1(text,start,end);
        quick1(text,start,temp);
        quick1(text,temp+1,end);
    }

    private static int getTemp1(Integer[] text, int start, int end) {
        int current = text[start];
        while(start<end){
            while (text[end]>=current&&start<end){
                end -- ;
            }
            text[start] = text[end];
            while(text[start]<=current&&start<end){
                start++;
            }
            text[end] = text[start];
        }
        text [end] = current;
        return  end;
    }

}
