package com.duanyi.array;

public class longestCommonPrefix {
    public static  String solution(String[] strs){
        if(strs.length == 0) {
            return "";
        }
        String headStr = strs[0];
        String[] strArr = headStr.split("");
        String s = strArr[0];
        for (int m = 0; m < strArr.length; m++) {
            for (int i = 0; i < strs.length-1; i++) {
                if(!strs[i].startsWith(s)) {
                    return s.substring(0,s.length()-1);
                }
            }
            s+= strArr[m];
        }
        return s;
    }

    public static void main(String[] args) {
        String[] strs = new String[2];
        strs[0] = "a";
       // strs[1] = "b";
        System.out.println(solution(strs));
     }
}
