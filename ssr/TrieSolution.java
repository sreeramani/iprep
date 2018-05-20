package com.ssr;

import java.util.*;

public class TrieSolution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Trie contacts = new Trie();

        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            if(op.equals("add")) {
                contacts.addString(contact);
            } else if(op.equals("find")) {
                int count = contacts.find(contact);
                System.out.println(count);
            }
        }
        contacts.printContacts();
    }

    private static class Trie {
        private class TrieNode {
            public char c;
            public HashMap <Character, TrieNode> children;
            boolean isEndpoint;

            public TrieNode(char c) {
                c = c;
                children = new HashMap <Character, TrieNode>();
                isEndpoint = false;
            }
        }

        private TrieNode overallRoot;

        public Trie() {
            //Root node does not have character value
            this.overallRoot = new TrieNode(' ');
        }

        public void addString(String str) {
            if(find(str) == 0) {
                overallRoot = addString(str, 0, overallRoot);
            }
        }

        private TrieNode addString(String str, int index, TrieNode root) {
            if(root == null || (index > (str.length() - 1))) {
                return root;
            }

            char c = str.charAt(index);
            if(root.children.get(c) == null) {
                TrieNode newNode = new TrieNode(c);
                root.children.put(c, newNode);
            }
            TrieNode nextNode = root.children.get(c);
            nextNode = addString(str, index + 1, root.children.get(c));
            if(index == (str.length() - 1)) {
                nextNode.isEndpoint = true;
            }
            root.children.remove(c);
            root.children.put(c, nextNode);
            return root;
        }

        public int find(String str) {
            return find(str, 0, overallRoot);
        }

        private int find(String str, int index, TrieNode root) {
            char c = str.charAt(index);
            if(index == (str.length() - 1)) {
                if(root.children.get(c) != null) {
                    return count(root.children.get(c), 0);
                }
            }
            if(root.children.get(c) == null) {
                return 0;
            }
            return find(str, index + 1, root.children.get(c));
        }

        private int count(TrieNode root, int count) {
            if(root.isEndpoint) {
                count++;
            }
            Set<Character> keys = root.children.keySet();
            Iterator it = keys.iterator();
            while(it.hasNext()) {
                Character c = (Character) it.next();
                count = count(root.children.get(c.charValue()), count);
            }
            return count;
        }

        public void printContacts() {
            printContacts(overallRoot, "");
        }

        private void printContacts(TrieNode root, String str) {
            if(!root.children.isEmpty()) {
                Set<Character> keys = root.children.keySet();
                Iterator it = keys.iterator();
                if(root.isEndpoint) {
                    System.out.println(str);
                }
                while(it.hasNext()) {
                    Character c = (Character) it.next();
                    printContacts(root.children.get(c.charValue()), str + c);
                }
            } else {
                System.out.println(str);
            }
        }
    }
}


