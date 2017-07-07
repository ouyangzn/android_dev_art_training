package com.ouyangzn.android_dev_art_training.module.chapter2.aidl;

import com.ouyangzn.android_dev_art_training.module.chapter2.aidl.Book;

interface IBookManager {
  List<Book> getBookList();

  void addBook(in Book book);
}