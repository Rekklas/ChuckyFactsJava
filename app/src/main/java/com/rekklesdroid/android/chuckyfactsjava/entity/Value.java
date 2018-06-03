package com.rekklesdroid.android.chuckyfactsjava.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Value implements Parcelable {

    private Integer id;
    private String joke;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.joke);
    }

    public Value() {
    }

    protected Value(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.joke = in.readString();
    }

    public static final Parcelable.Creator<Value> CREATOR = new Parcelable.Creator<Value>() {
        @Override
        public Value createFromParcel(Parcel source) {
            return new Value(source);
        }

        @Override
        public Value[] newArray(int size) {
            return new Value[size];
        }
    };
}