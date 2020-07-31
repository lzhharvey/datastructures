package com.example.datastructures.stack;

import java.util.LinkedList;
import java.util.Stack;

public class LinkedListStack<E> extends Stack<E> {
  private LinkedList<E> list;
  public LinkedListStack(){
    list=new LinkedList<>();
  }
  public int getSize() {
    return list.size();
  }
  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }
  @Override
  public E push(E e) {
    list.addFirst(e);
    return null;
  }
  @Override
  public E pop() {
    return list.removeFirst();
  }
  @Override
  public E peek() {
    return list.getFirst();
  }
  @Override
  public String toString() {
    StringBuilder stringBuilder=new StringBuilder();
    stringBuilder.append("Stack: top ");
    stringBuilder.append(list);
    return stringBuilder+"";
  }
  public static void main(String[] args) {
    //栈
    LinkedListStack<Integer> stack=new LinkedListStack<>();

    for (int i=0;i<5;i++){
      //入栈
      stack.push(i);
      System.out.println(stack);
    }
    //出栈
    Integer pop = stack.pop();
    System.out.println(stack);
  }
}
