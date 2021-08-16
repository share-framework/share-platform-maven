package org.andot.share.core.test;

import java.util.*;

public class TreeTest {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node20 = new Node(20);
        Node node21 = new Node(21);
        node1.setLeft(node20);
        node1.setRight(node21);
        Node node30 = new Node(30);
        Node node31 = new Node(31);
        Node node32 = new Node(32);
        node20.setLeft(node30);
        node21.setLeft(node31);
        node21.setRight(node32);
        backSequenceByStack(node1);
        Collections.sort(new ArrayList<>());
    }


    /**
     * 层序遍历
     * @param node
     */
    public static void sequence(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        if(node == null) {
            return;
        }
        if(node != null) {
            queue.add(node);
        }

        while (!queue.isEmpty()) {
            Node node1 = queue.poll();
            if(node1.getLeft() != null) {
                queue.add(node1.getLeft());
            }
            if(node1.getRight() != null) {
                queue.add(node1.getRight());
            }
            System.err.println(node1.getValue());
        }
    }

    public static void frontSequence(Node node) {
        if(node != null) {
            System.err.println(node.getValue());
            if(node.getLeft() != null) {
                frontSequence(node.getLeft());
            }
            if(node.getRight() != null) {
                frontSequence(node.getRight());
            }
        }
    }

    public static void frontSequenceByStack(Node node) {
        Stack<Node> stack = new Stack<>();
        if(node == null) {
            return;
        }
        stack.push(node);
        while (!stack.isEmpty()) {
            Node node1 = stack.pop();
            if(node1.getRight() != null) {
                stack.push(node1.getRight());
            }
            if(node1.getLeft() != null) {
                stack.push(node1.getLeft());
            }
            System.err.println(node1.getValue());
        }
    }

    public static void centerSequence(Node node) {
        if(node != null) {
            if(node.getLeft() != null) {
                frontSequence(node.getLeft());
            }
            System.err.println(node.getValue());
            if(node.getRight() != null) {
                frontSequence(node.getRight());
            }
        }
    }

    public static void backSequence(Node node) {
        if(node != null) {
            if(node.getLeft() != null) {
                frontSequence(node.getLeft());
            }
            if(node.getRight() != null) {
                frontSequence(node.getRight());
            }
            System.err.println(node.getValue());
        }
    }

    public static void backSequenceByStack(Node node) {
        Stack<Node> stack = new Stack<>();
        if(node == null) {
            return;
        }
        stack.push(node);
        while (!stack.isEmpty()) {
            Node node1 = stack.pop();
            if(node1.getLeft() != null) {
                stack.push(node1.getLeft());
            }
            if(node1.getRight() != null) {
                stack.push(node1.getRight());
            }
            System.err.println(node1.getValue());
        }
    }


}
