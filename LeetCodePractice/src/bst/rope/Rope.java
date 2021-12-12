package bst.rope;
/*
https://github.com/tdeitch/jrope/blob/master/Rope.java

question: given a start idx, a length and a root node, return the represented string
tag: google

 */
public class Rope {
    String data;
    int leftLen;
    Rope left;
    Rope right;

    public Rope(String data){
        this.data = data;
        this.leftLen = data.length();
    }

    public Rope(Rope a, Rope b){
        this.left = a;
        this.right = b;
        this.leftLen = getLength(a);
    }

    public int getLength(){
        return getLength(this);
    }

    public int getLength(Rope node){
        int res = 0;
        while(node != null){
            res += node.leftLen;
            node = node.right;
        }
        return res;
    }

    public Rope concat(Rope node) {
        return concat(this, node);
    }

    public Rope concat(Rope a, Rope b) {
        if(a == null){
            return b;
        } else if(b == null){
            return a;
        }
        return new Rope(a, b);
    }

    public char charAt(int i) {
        return charAt(this, i);
    }

    public char charAt(Rope node, int i){
        if(node.left == null){
            assert i >= 0 && i < node.leftLen;
            return node.data.charAt(i);
        }
        if(i < node.leftLen){
            return charAt(node.left, i);
        } else {
            return charAt(node.right, i - node.leftLen);
        }
    }

    public String subString(int start, int len){
        int end = start + len - 1;
        return search(start, end,this);
    }

    private String search(int start, int end, Rope node) {
        if(node.left == null) {
            return node.data.substring(start, end + 1);
        }
        if(end < node.leftLen){
            return search(start, end, node.left);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(search(start, node.leftLen - 1, node.left));
        sb.append(search(0, end - node.leftLen, node.right));
        return sb.toString();
    }

    public String toString(){
        if(left == null){
            return data;
        }
        return left.toString() + right.toString();
    }
}
