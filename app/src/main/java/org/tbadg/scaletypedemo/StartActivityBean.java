package org.tbadg.scaletypedemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2020/3/2 11:52
 * *
 */
public class StartActivityBean implements Parcelable {

    private String mTitle;

    private Class mActivityClass;


    public StartActivityBean(String title, Class activityClass) {
        mTitle = title;
        mActivityClass = activityClass;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Class getActivityClass() {
        return mActivityClass;
    }

    public void setActivityClass(Class activityClass) {
        mActivityClass = activityClass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeSerializable(this.mActivityClass);
    }

    protected StartActivityBean(Parcel in) {
        this.mTitle = in.readString();
        this.mActivityClass = (Class) in.readSerializable();
    }

    public static final Creator<StartActivityBean> CREATOR = new Creator<StartActivityBean>() {
        @Override
        public StartActivityBean createFromParcel(Parcel source) {
            return new StartActivityBean(source);
        }

        @Override
        public StartActivityBean[] newArray(int size) {
            return new StartActivityBean[size];
        }
    };
}
