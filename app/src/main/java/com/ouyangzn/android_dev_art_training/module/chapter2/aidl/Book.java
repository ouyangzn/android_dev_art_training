package com.ouyangzn.android_dev_art_training.module.chapter2.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ouyangzn on 2017/7/4.<br/>
 * Description：
 */
public class Book implements Parcelable {
  public static final Creator<Book> CREATOR = new Creator<Book>() {
    @Override public Book createFromParcel(Parcel source) {
      return new Book(source);
    }

    @Override public Book[] newArray(int size) {
      return new Book[size];
    }
  };
  private int bookId;
  private String bookName;

  public Book() {
  }

  protected Book(Parcel in) {
    this.bookId = in.readInt();
    this.bookName = in.readString();
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.bookId);
    dest.writeString(this.bookName);
  }
}
