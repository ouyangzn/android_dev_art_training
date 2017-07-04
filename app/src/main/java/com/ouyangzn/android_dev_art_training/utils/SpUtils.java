/*
 * Copyright (c) 2016.  ouyangzn   <email : ouyangzn@163.com>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ouyangzn.android_dev_art_training.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ouyangzn on 2017/3/17.<br/>
 * Description：SharedPreferences工具类
 */
public class SpUtils {

  private static final String SP_DEFAULT_NAME = "config";
  private static final String SP_DEFAULT_MULTI_NAME = "config_multi";

  public static void put(Context context, String key, String value) {
    getEditor(context).putString(key, value).apply();
  }

  public static String getString(Context context, String key) {
    return getString(context, key, "");
  }

  public static String getString(Context context, String key, String def) {
    return getSp(context).getString(key, def);
  }

  public static void put(Context context, String key, int value) {
    getEditor(context).putInt(key, value).apply();
  }

  public static int getInt(Context context, String key) {
    return getInt(context, key, -1);
  }

  public static int getInt(Context context, String key, int def) {
    return getSp(context).getInt(key, def);
  }

  public static void put(Context context, String key, boolean value) {
    getEditor(context).putBoolean(key, value).apply();
  }

  public static boolean getBoolean(Context context, String key) {
    return getBoolean(context, key, false);
  }

  public static boolean getBoolean(Context context, String key, boolean def) {
    return getSp(context).getBoolean(key, def);
  }

  public static SharedPreferences getSp(Context context) {
    return getSp(context, SP_DEFAULT_NAME);
  }

  public static SharedPreferences getSpMultiProcess(Context context) {
    return getSpMultiProcess(context, SP_DEFAULT_MULTI_NAME);
  }

  public static SharedPreferences getSp(Context context, String name) {
    return context.getSharedPreferences(name, Context.MODE_MULTI_PROCESS);
  }

  public static SharedPreferences getSpMultiProcess(Context context, String name) {
    return context.getSharedPreferences(name, Context.MODE_MULTI_PROCESS);
  }

  public static SharedPreferences.Editor getEditor(Context context) {
    return getSp(context).edit();
  }

  public static SharedPreferences.Editor getEditor(Context context, String name) {
    return getSp(context, name).edit();
  }

  public static SharedPreferences.Editor getEditorMultiProcess(Context context) {
    return getSpMultiProcess(context).edit();
  }

  public static SharedPreferences.Editor getEditorMultiProcess(Context context, String name) {
    return getSpMultiProcess(context, name).edit();
  }
}
