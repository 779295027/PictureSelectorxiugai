package com.luck.picture.lib.photoview;

import android.app.Activity;

import com.luck.picture.lib.entity.LocalMedia;

/**
 * Created by sunshaoshuai on 2020-01-01
 */
public interface OnImageViewLongClickListener {
    void onLongClick(Activity activity, LocalMedia media);
}